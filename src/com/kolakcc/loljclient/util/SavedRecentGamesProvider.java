package com.kolakcc.loljclient.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import com.kolakcc.loljclient.model.RecentGame;
import com.kolakcc.loljclient.model.ServerInfo;
import com.kolakcc.loljclient.model.Summoner;

public class SavedRecentGamesProvider {
	static String gamesDirectoryFormat = "app://data/games/%s/%.0f/"; //%s -> region, %.0f -> summonerID
	static String gameFileFormat = gamesDirectoryFormat+"%d";

	public static ArrayList<RecentGame> getSavedGames(Summoner summoner) throws Exception {
		ArrayList<RecentGame> ret = new ArrayList<RecentGame>();
		String gamesDirectory = String.format(gamesDirectoryFormat, ServerInfo.currentServerInfo.region, summoner.getSummonerID());
		for (File game : FileSystem.getFile(gamesDirectory).listFiles()) {
			if (!game.getName().equals("README")) {
				FileInputStream fis = new FileInputStream(game.getAbsolutePath());
		        ObjectInputStream ois = new ObjectInputStream(fis);
		        ret.add((RecentGame) ois.readObject());
		        ois.close();
		        fis.close();
			}
		}
		return ret;
	}
	public static void save(RecentGame game, Summoner summoner) throws IOException {
		ObjectOutputStream objectOutputStream = null;
		RandomAccessFile raf = null;
		try {
			//TODO: write to XML or something instead of Java's silly format
			String gameFileString = String.format(gameFileFormat, ServerInfo.currentServerInfo.region, summoner.getSummonerID(), game.getGameID());
			raf = new RandomAccessFile(FileSystem.getFile(gameFileString), "rw");
			FileOutputStream fos = new FileOutputStream(raf.getFD());
			objectOutputStream = new ObjectOutputStream(fos);
			objectOutputStream.writeObject(game);
		} finally {
			if (objectOutputStream != null) {
				objectOutputStream.close();
			}
			if (raf != null) {
				raf.close();
			}
		}
	}
}
