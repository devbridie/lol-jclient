package com.kolakcc.loljclient.model;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import com.kolakcc.loljclient.util.FileSystem;

public class RADSAirClientWrapper {
	public static BufferedInputStream getBIS(String relativeFilePath)
			throws Exception {
		FileInputStream fis = new FileInputStream(getFile(relativeFilePath));
		return new BufferedInputStream(fis);
	}


	public static File getFile(String relativeFilePath) throws Exception {
		relativeFilePath = relativeFilePath.replace('/', File.separatorChar);
		// some files are not in the same captials as in riots database		
		File f = FileSystem.getRADSFile(relativeFilePath);
		if (!f.isFile()) {
			System.out.println("Could not find file " + relativeFilePath);
			for (File compare : f.getParentFile().listFiles()) {
				if (compare.getName().equalsIgnoreCase(f.getName())) {
					System.out.println("Found actual file: " + f.getName());
					f = compare;
					break;
				}
			}
		}

		if (!f.canRead()) {
			throw new Exception(String.format("Unreadable: %s", f.getPath()));
		}
		if ((f.isFile()) && (f.canRead())) {
			return f;
		}

		throw new Exception(String.format("Unknown error opening %s", f.getPath()));
	}

	public static BufferedImage getImage(String relativeFilePath)
			throws Exception {
		File f = getFile(relativeFilePath);
		return ImageIO.read(f);
	}
}
