package com.kolakcc.loljclient.util;

import java.io.File;
import java.io.IOException;

public class FileSystem {
	//TODO: move all filesystem calls to here
	public static File getFile(String path) {
		//should have a form like lol://RADS/... or app://
		String[] split = path.split("://");
		String prefix = null;
		if (split[0].equals("lol")) {
			prefix = Configuration.getLeagueDirectory();
		} else if (split[0].equals("app")) {
			prefix = "";
		}
		if (prefix == null) {
			throw new RuntimeException(String.format("Prefix not recognized: %s for input %s",split[0],path));
		}
		
		if (prefix.endsWith(File.separator)) {
			prefix = prefix + File.separator;
		}
		
		//replace ? with first directory (version names in solutions/projects
		StringBuilder pathAfterWildcard = new StringBuilder(prefix);
		String[] wildcardSplit = split[1].split("\\?");
		if (wildcardSplit.length > 1) {
			for (int i = 0; i < wildcardSplit.length; i += 2) {
				pathAfterWildcard.append(wildcardSplit[i]);
				File temporary = new File(pathAfterWildcard.toString());
				String[] files = temporary.list();
				if (files == null) {
					throw new RuntimeException(String.format("Wildcard ? did not have a result in %s",pathAfterWildcard.toString()));
				}
				pathAfterWildcard.append(files[0]);				
				pathAfterWildcard.append(wildcardSplit[i+1]);
			}
		} else {
			pathAfterWildcard.append(split[1]);
		}
		File ret = new File(pathAfterWildcard.toString().replace('/', File.separatorChar));
		if (!ret.exists()) {
			if (split[0].equals("app")) {
				ret.getParentFile().mkdirs();
				if (path.endsWith("/")) {
					ret.mkdir();
					System.out.printf("Directory %s created %n", ret.getPath());
				} else {
					try {
						if (ret.createNewFile()) {
							System.out.printf("File %s created %n", ret.getPath());
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				throw new RuntimeException(String.format("Path for %s not found, tried %s" ,path, ret.getAbsolutePath()));
			}
		}	
		return ret;
	}
	
	public static File getRADSFile(String path) { //convience
		return getFile("lol://RADS/projects/lol_air_client/releases/?/deploy/"+path);
	}
}
