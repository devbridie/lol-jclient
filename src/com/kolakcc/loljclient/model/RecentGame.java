package com.kolakcc.loljclient.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.util.FileSystem;

public class RecentGame extends Game implements Comparable<RecentGame>, Serializable {
	private static final long serialVersionUID = -3817902081361651336L;
	ArrayList<FellowPlayer> fellowPlayers;
	boolean eligibleFWOTD, leaver, afk, premadeTeam, invalid, ranked;
	Date createDate;
	double KCoefficient, predictedWinPercent;
	Double ID;
	HashMap<String,Integer> statistics;
	int experienceEarned, 
		spell1,
		spell2,
		teamID,
		userServerPing,
		adjustedRating,
		premadeSize,
		boostXPEarned,
		boostIPEarned,
		gameID,
		timeInQueue,
		dataVersion,
		eloChange,
		IPearned,
		teamRating,
		rating,
		championID,
		skinIndex,
		level,
		summonerID,
		userID;
	Object difficulty, futureData, rawStatsJSON;
	String gameType, gameMode, difficultyString, subType, queueType, gameTypeEnum, skinName;

	@SuppressWarnings("unused") //needed for serialization
	private RecentGame() { }
	public RecentGame(TypedObject to) {
		super(to);

		gameType = getString("gameType");
		experienceEarned = getInt("experienceEarned");
		rawStatsJSON = getObject("rawStatsJson");
		eligibleFWOTD = getBool("eligibleFirstWinOfDay");
		difficulty = getString("difficulty");
		gameMapID = getInt("gameMapId");
		leaver = getBool("leaver");
		spell1 = getInt("spell1");
		gameTypeEnum = getString("gameTypeEnum");
		teamID = getInt("teamId");
		summonerID = getInt("summonerId");
		afk = getBool("afk");
		boostXPEarned = getInt("boostXpEarned");
		level = getInt("level");
		invalid = getBool("invalid");
		dataVersion = getInt("dataVersion");
		userID = getInt("userId");
		createDate = getDate("createDate");
		userServerPing = getInt("userServerPing");
		adjustedRating = getInt("adjustedRating");
		premadeSize = getInt("premadeSize");
		boostIPEarned = getInt("boostIpEarned");
		gameID = getInt("gameId");
		timeInQueue = getInt("timeInQueue");
		IPearned = getInt("ipEarned");
		eloChange = getInt("eloChange");
		futureData = getString("futureData");
		gameMode = getString("gameMode");
		difficultyString = getString("difficultyString");
		KCoefficient = getDouble("KCoefficient");
		teamRating = getInt("teamRating");
		subType = getString("subType");
		queueType = getString("queueType");
		premadeTeam = getBool("premadeTeam");
		predictedWinPercent = getDouble("predictedWinPct");
		rating = getInt("rating");
		championID = getInt("championId");
		spell2 = getInt("spell2");
		skinIndex = getInt("skinIndex");
		ranked = getBool("ranked");
		skinName = getString("skinName");
		ID = getDouble("id");
		
		statistics = new HashMap<String, Integer>();
		for (TypedObject stat : getArray("statistics")) {
			String key = stat.getString("statType");
			int value = stat.getInt("value");
			statistics.put(key,value);
		}
		
		fellowPlayers = new ArrayList<FellowPlayer>();
		for (TypedObject player : getArray("fellowPlayers")) {
			fellowPlayers.add(new FellowPlayer(player));
		}
		checkFields();
	}

	@Override
	public int compareTo(RecentGame otherGame) {
		return createDate.compareTo(otherGame.createDate);
	}

	public int getStatistic(String key) {
		if (!statistics.containsKey(key)) return 0;
		return statistics.get(key);
	}
	
	public void save(String summonerName) throws IOException {
		ObjectOutputStream objectOutputStream = null;
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(FileSystem.getFile("app://data/games/"+summonerName+"/"+getGameID()), "rw");
			FileOutputStream fos = new FileOutputStream(raf.getFD());
			objectOutputStream = new ObjectOutputStream(fos);
			objectOutputStream.writeObject(this);
		} finally {
			if (objectOutputStream != null) {
				objectOutputStream.close();
			}
			if (raf != null) {
				raf.close();
			}
		}
	}
	
	public static ArrayList<RecentGame> getSavedGames(Summoner summoner) throws Exception {
		ArrayList<RecentGame> ret = new ArrayList<RecentGame>();
		for (File game : FileSystem.getFile(String.format("app://data/games/%.0f/", summoner.getSummonerID())).listFiles()) {
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

	@Override
	public String toString() {
		return "RecentGame [gameType=" + gameType + ", experienceEarned="
				+ experienceEarned + ", eligibleFWOTD=" + eligibleFWOTD
				+ ", difficulty=" + difficulty + ", leaver=" + leaver
				+ ", spell1=" + spell1 + ", teamID=" + teamID + ", statistics="
				+ statistics + ", spell2=" + spell2 + ", afk=" + afk
				+ ", dataVersion=" + dataVersion + ", createDate=" + createDate
				+ ", userServerPing=" + userServerPing + ", adjustedRating="
				+ adjustedRating + ", premadeSize=" + premadeSize
				+ ", boostXPEarned=" + boostXPEarned + ", boostIPEarned="
				+ boostIPEarned + ", gameID=" + gameID + ", timeInQueue="
				+ timeInQueue + ", IPearned=" + IPearned + ", eloChange="
				+ eloChange + ", futureData=" + futureData + ", gameMode="
				+ gameMode + ", difficultyString=" + difficultyString
				+ ", teamRating=" + teamRating + ", subType=" + subType
				+ ", queueType=" + queueType + ", premadeTeam=" + premadeTeam
				+ ", rating=" + rating + ", championID=" + championID
				+ ", invalid=" + invalid + ", level=" + level
				+ ", rawStatsJSON=" + rawStatsJSON + ", summonerID="
				+ summonerID + ", gameTypeEnum=" + gameTypeEnum + ", ID=" + ID
				+ ", KCoefficient=" + KCoefficient + ", predictedWinPct="
				+ predictedWinPercent + ", userID=" + userID + "]";
	}

	public HashMap<String, Integer> getStatistics() {
		return statistics;
	}

	public String getGameType() {
		return gameType;
	}

	public int getExperienceEarned() {
		return experienceEarned;
	}

	public boolean isEligibleFWOTD() {
		return eligibleFWOTD;
	}

	public Object getDifficulty() {
		return difficulty;
	}

	public boolean isLeaver() {
		return leaver;
	}

	public int getSpell1() {
		return spell1;
	}

	public int getSpell2() {
		return spell2;
	}

	public int getTeamID() {
		return teamID;
	}

	public boolean isAfk() {
		return afk;
	}

	public int getDataVersion() {
		return dataVersion;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public int getUserServerPing() {
		return userServerPing;
	}

	public int getAdjustedRating() {
		return adjustedRating;
	}

	public int getPremadeSize() {
		return premadeSize;
	}

	public int getBoostXPEarned() {
		return boostXPEarned;
	}

	public int getBoostIPEarned() {
		return boostIPEarned;
	}

	public int getGameID() {
		return gameID;
	}

	public int getTimeInQueue() {
		return timeInQueue;
	}

	public int getIPearned() {
		return IPearned;
	}

	public int getEloChange() {
		return eloChange;
	}

	public Object getFutureData() {
		return futureData;
	}

	public String getGameMode() {
		return gameMode;
	}

	public String getDifficultyString() {
		return difficultyString;
	}

	public int getTeamRating() {
		return teamRating;
	}

	public String getSubType() {
		return subType;
	}

	public String getQueueType() {
		return queueType;
	}

	public boolean isPremadeTeam() {
		return premadeTeam;
	}

	public int getRating() {
		return rating;
	}

	public int getChampionID() {
		return championID;
	}

	public boolean isInvalid() {
		return invalid;
	}

	public int getLevel() {
		return level;
	}

	public Object getRawStatsJSON() {
		return rawStatsJSON;
	}

	public int getSummonerID() {
		return summonerID;
	}

	public String getGameTypeEnum() {
		return gameTypeEnum;
	}

	public Object getID() {
		return ID;
	}

	public double getKCoefficient() {
		return KCoefficient;
	}

	public double getPredictedWinPercent() {
		return predictedWinPercent;
	}

	public int getUserID() {
		return userID;
	}
	
	public boolean equals(Object o) {
		if (o instanceof RecentGame) {
			RecentGame game = (RecentGame) o;
			if (game.getGameID() == this.getGameID()) return true;
			return false;
		}
		return o.equals(this);
	}
	public int hashCode() {
		return this.getGameID();
	}
}
