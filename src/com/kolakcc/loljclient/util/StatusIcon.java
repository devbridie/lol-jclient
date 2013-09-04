package com.kolakcc.loljclient.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;

public class StatusIcon {
	private static ImageIcon available;
	private static ImageIcon away;
	private static ImageIcon inGame;
	private static ImageIcon offline;
	
	private static ImageIcon loadIcon(String fn) {
		try {
			return new ImageIcon(ImageIO.read(FileSystem.getFile("app://img/"+fn+".png"))); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ImageIcon getAvailable() {
		if (available == null) {
			available = loadIcon("available");
		}
		return available;
	}
	public static ImageIcon getAway() {
		if (away == null) {
			away = loadIcon("away");
		}
		return away;
	}
	public static ImageIcon getInGame() {
		if (inGame == null) {
			inGame = loadIcon("extended-away");
		}
		return inGame;
	}
	public static ImageIcon getOffline() {
		if (offline == null) {
			offline = loadIcon("invisible");
		}
		return offline;
	}
	public static ImageIcon fromPresence(Presence p) {
	    if (p.getStatus() != null) {
	    	Matcher gameStatusMatcher = Pattern.compile("<gameStatus>(.+?)</gameStatus>").matcher(p.getStatus());
		    while (gameStatusMatcher.find()) {
		    	switch (gameStatusMatcher.group(1)) {
		    		case "inGame": return StatusIcon.getInGame();
		    		case "outOfGame": 
		    			if (p.getMode().equals(Mode.chat)) return StatusIcon.getAvailable();
		    			else if (p.getMode().equals(Mode.away)) return StatusIcon.getAway();
		    			break;
		    		case "inQueue": return StatusIcon.getInGame();
		    	}
		    }
	    }
	    return StatusIcon.getOffline();
	}
}
