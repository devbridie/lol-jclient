package com.kolakcc.loljclient.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.kolakcc.loljclient.util.FileSystem;
import com.kolakcc.loljclient.view.AboutView;

public class AboutController extends KolaController {
	protected AboutView view;
	public AboutController() {
		String displayText = "Loading...";

		File file = FileSystem.getFile("app://ABOUT");
		StringBuilder fileContents = new StringBuilder((int)file.length());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			String lineSeparator = System.getProperty("line.separator");
			while(scanner.hasNextLine()) {        
				fileContents.append(scanner.nextLine() + lineSeparator);
			}
			displayText = fileContents.toString();
		} catch (FileNotFoundException e) {
			HandleException(e);
		} finally {
			if (scanner != null) scanner.close();
		}
		
		view = new AboutView(displayText);
	}
}
