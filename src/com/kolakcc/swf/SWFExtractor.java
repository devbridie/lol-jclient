package com.kolakcc.swf;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.kolakcc.loljclient.util.FileSystem;

public class SWFExtractor {
	private static String[][] files = { { "ImagePack_items.swf", "items", "png" }, 
						 				{ "ImagePack_buddyIcons.swf", "buddyIcons", "jpg" },
						 				{ "ImagePack_spells.swf", "spells", "png" },
						 				{ "ImagePack_masteryIcons.swf", "masteries", "png" } };
	// file, destination folder, file type
	
	public SWFExtractor() {
		//check swfdump
		File swfdump = new File("C:\\Program Files (x86)\\SWFTools\\swfdump.exe");
		if (!swfdump.exists()) swfdump = new File("C:\\Program Files\\SWFTools\\swfdump.exe");
        if (!swfdump.exists()) swfdump = new File("/usr/local/bin/swfdump");
		if (!swfdump.exists()) swfdump = new File("swfdump");
		if (!swfdump.exists()) {
			JFileChooser swfFileChooser = new JFileChooser();
			swfFileChooser.setDialogTitle("Select swfdump");
			swfFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			swfFileChooser.showOpenDialog(new JFrame());
			
			swfdump = swfFileChooser.getSelectedFile();
		}
		if (!swfdump.exists()) throw new RuntimeException("SWFdump not found.");
		String swfdumpLocation = swfdump.getParent() + File.separatorChar;
		Process swfdumpProcess = null;
		Scanner outputScanner = null;
		Scanner outputScannerWithDelimiter = null;
		try {
			for (String[] file : files) {
				swfdumpProcess = new ProcessBuilder(swfdumpLocation + "swfdump", FileSystem.getRADSFile("assets/imagePacks/"+file[0]).getAbsolutePath()).start();
				outputScanner = new Scanner(swfdumpProcess.getInputStream());
				outputScannerWithDelimiter = outputScanner.useDelimiter("\n");
				String output;
				while (true) {
					if (!outputScannerWithDelimiter.hasNext()) break;
					output = outputScannerWithDelimiter.next();
					Pattern pat = Pattern.compile(".*exports (.+?) as.*__e_(.*)\"");
					Matcher m = pat.matcher(output);
					if (m.find()) {
						ProcessBuilder pb = new ProcessBuilder(swfdumpLocation + "swfextract", FileSystem.getRADSFile("assets/imagePacks/"+file[0]).getAbsolutePath(), "-"+file[2].charAt(0),m.group(1), "--output", file[1]+File.separatorChar+m.group(2)+"."+file[2]);
						System.out.println(pb.command());
						pb.directory(FileSystem.getFile("app://img/"));
						pb.start();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputScanner != null) outputScanner.close();
			if (outputScannerWithDelimiter != null) outputScannerWithDelimiter.close();
		}
	}
}
