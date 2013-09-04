package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class CustomGamePlayer extends ChampionSelectPlayer {
	int accountID;
	String botDifficulty;
	String summonerInternalName;
	int profileIconID;
	int summonerID;
	int badges; // has value 8, bitwise comparison?
	int dataVersion;
	int pickTurn;
	boolean inSync;
	String summonerName;
	int pickMode;
	String originalPlatformID;

	// No clue what these are
	Object timeAddedToQueue; // is this the queue dodge penalty?
	int index;
	int queueRating; // seems to be 0 for custom games?
	int originalAccountNumber; // Is this from before a server change or
										// something?
	Object minor; // always false?
	Object locale; // seems to always be null...
	int lastSelectedSkinIndex; // always 0?
	boolean teamOwner; // always false?
	Object futureData; // always null?
	Object teamParticipantID; // always null?
	
	String partnerID;

	public CustomGamePlayer(TypedObject data) {
		super(data);
		this.timeAddedToQueue = getObject("timeAddedToQueue");
		this.index = getInt("index");
		this.queueRating = getInt("queueRating");
		this.accountID = getInt("accountId");
		this.botDifficulty = getString("botDifficulty");
		this.originalAccountNumber = getInt("originalAccountNumber");
		this.summonerInternalName = getString("summonerInternalName");
		this.minor = getBool("minor");
		this.locale = getObject("locale");
		this.lastSelectedSkinIndex = getInt("lastSelectedSkinIndex");
		this.profileIconID = getInt("profileIconId");
		this.teamOwner = getBool("teamOwner");
		this.futureData = getProbablyNull("futureData");
		this.summonerID = getInt("summonerId");
		this.badges = getInt("badges");
		this.dataVersion = getInt("dataVersion");
		this.pickTurn = getInt("pickTurn");
		this.inSync = getBool("clientInSynch");
		this.summonerName = getString("summonerName");
		this.pickMode = getInt("pickMode");
		this.originalPlatformID = getString("originalPlatformId");
		this.teamParticipantID = getObject("teamParticipantId");
		this.partnerID = getString("partnerId");
		checkFields();
	}

	public boolean isBot() {
		return (this.botDifficulty == "NONE");
	}
	public String toString() {
		if ((championSelection != null) && (championSelection.championID != 0)) {
			System.out.println(championSelection);
			return this.summonerName + " (" + Champion.getChampionFromID(this.championSelection.championID).getDisplayName() + ")";
		}
		return this.summonerName;
	}

	public int getAccountID() {
		return accountID;
	}

	public String getBotDifficulty() {
		return botDifficulty;
	}

	public String getSummonerInternalName() {
		return summonerInternalName;
	}

	public int getProfileIconID() {
		return profileIconID;
	}

	public int getSummonerID() {
		return summonerID;
	}

	public int getBadges() {
		return badges;
	}

	public int getDataVersion() {
		return dataVersion;
	}

	public int getPickTurn() {
		return pickTurn;
	}

	public boolean isInSync() {
		return inSync;
	}

	public String getSummonerName() {
		return summonerName;
	}

	public int getPickMode() {
		return pickMode;
	}

	public String getOriginalPlatformID() {
		return originalPlatformID;
	}

	public Object getTimeAddedToQueue() {
		return timeAddedToQueue;
	}

	public int getIndex() {
		return index;
	}

	public int getQueueRating() {
		return queueRating;
	}

	public int getOriginalAccountNumber() {
		return originalAccountNumber;
	}

	public Object getMinor() {
		return minor;
	}

	public Object getLocale() {
		return locale;
	}

	public int getLastSelectedSkinIndex() {
		return lastSelectedSkinIndex;
	}

	public boolean isTeamOwner() {
		return teamOwner;
	}

	public Object getFutureData() {
		return futureData;
	}

	public Object getTeamParticipantID() {
		return teamParticipantID;
	}
}
