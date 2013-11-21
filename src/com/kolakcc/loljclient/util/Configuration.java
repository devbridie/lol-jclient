package com.kolakcc.loljclient.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	public static String PVPVersion = "3.14.XX";
	private static Properties config = null;
	private static File configFile = null;
	
	private static void initializeProperties() {
		config = new Properties();
		configFile = FileSystem.getFile("app://data/configuration.properties");
		try {
			if (!configFile.exists()) configFile.createNewFile();
			config.load(new FileInputStream(configFile));
			String password = config.getProperty("password");
			if (password != null) {
				password = AES.decrypt(password);
				config.setProperty("password", password);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getDefaultLAF() {
		if (config == null) initializeProperties();
		return config.getProperty("laf", "");
	}
	public static String getDefaultRegion() {
		if (config == null) initializeProperties();
		return config.getProperty("region", "");
	}
	
	public static String getDefaultUsername() {
		if (config == null) initializeProperties();
		return config.getProperty("username", "");
	}
	
	public static String getDefaultPassword() {
		if (config == null) initializeProperties();
		return config.getProperty("password", "");
	}
	
	public static String getLeagueDirectory() {
		//TODO: remove useless code FileSystem checks for already
		if (config == null) initializeProperties();
		String uncheckedDirectory;
        if (System.getProperty("os.name").contains("OS X"))
            uncheckedDirectory = config.getProperty("leaguedir", "/Applications/League of Legends.app/Contents/LOL/");
        else
            uncheckedDirectory = config.getProperty("leaguedir", "C:\\Riot Games\\League of Legends\\");
		if (!uncheckedDirectory.endsWith(File.separator)) uncheckedDirectory = uncheckedDirectory + File.separator;
		config.put("leaguedir",uncheckedDirectory);
		Configuration.flushConfig();
		return uncheckedDirectory;
	}
	
	public static String getXMPPStatus() {
		if (config == null) initializeProperties();
		return config.getProperty("status", "");
	}
	
	public static boolean checkLeagueDirectory() {
		File f = new File(Configuration.getLeagueDirectory());
		return (f.isDirectory() && f.canRead());
	}
	
	public static void setStatus(String status) {
		set("status", status);
	}
	
	public static void setLeagueDirectory(File dir) {
		set("leaguedir", dir.getAbsolutePath());
	}
	
	public static void setDefaultLAF(String LAF) {
		set("laf",LAF);
	}
	
	public static void flushConfig() {
		if (config == null) initializeProperties();
		try {
			config.store(new FileOutputStream(configFile), "");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void set(String key, String value) {
		if (config == null) initializeProperties();
		if (key == "password") {
			value = AES.encrypt(value);
		}
		config.put(key, value);
		Configuration.flushConfig();
	}
}
