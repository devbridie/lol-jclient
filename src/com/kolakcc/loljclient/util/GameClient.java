package com.kolakcc.loljclient.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.kolakcc.loljclient.StartupClass;
import com.kolakcc.loljclient.model.PlayerCredentials;
public class GameClient {
	private static File getEXE() {
		return FileSystem.getFile("lol://RADS/solutions/lol_game_client_sln/releases/?/deploy/League of Legends.exe");
	}
	public static void start() {
		ArrayList<String> arguments = new ArrayList<String>();
		arguments.add(getEXE().getAbsolutePath());
		Scanner lastCmdScanner = null;
		Scanner lastCmdScannerWithDelimiter = null;
		try {
			lastCmdScanner = new Scanner(FileSystem.getFile("lol://RADS/solutions/lol_game_client_sln/releases/?/deploy/lastCmd.log"));
			lastCmdScannerWithDelimiter = lastCmdScanner.useDelimiter("\\A");
			String text = lastCmdScannerWithDelimiter.next();
			String[] args = text.split("\" \"");
			for (String arg : args) {
				arguments.add(arg.replace("\"", ""));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (lastCmdScanner != null) lastCmdScanner.close();
			if (lastCmdScannerWithDelimiter != null) lastCmdScannerWithDelimiter.close();
		}
		start(arguments);
		
	}
	
	public static void start(List<String> args) {
		try {
			ProcessBuilder pb = new ProcessBuilder();
			if ((getEXE() != null) && (getEXE().exists())) {
				pb.command(args);
				pb.directory(FileSystem.getFile("lol://RADS/solutions/lol_game_client_sln/releases/?/deploy/"));
			}
			System.out.println(pb.command());
			System.out.println(FileSystem.getFile("lol://RADS/solutions/lol_game_client_sln/releases/?/deploy/"));
			pb.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void start(PlayerCredentials pc) {
		try {
			StartupClass.Client.invoke("gameService", "setClientReceivedGameMessage", new Object[] { pc.getGameID(), "GAME_START_CLIENT" });
		} catch (IOException e) {
			e.printStackTrace();
		}		
		start(Arrays.asList(new String[] { getEXE().getAbsolutePath(), 
				"8394", //??? on my computers the same, always
				"LoLLauncher.exe", //not sure what this is supposed to do
				"C:/Riot Games/League of Legends/RADS/projects/lol_air_client/releases/0.0.1.0/deploy/LolClient.exe", //not sure what this is supposed to do
				String.format("%s %d %s %.0f", pc.getServerIP(), pc.getServerPort(), pc.getEncryptionKey(), pc.getSummonerID()) }));
	}
}
