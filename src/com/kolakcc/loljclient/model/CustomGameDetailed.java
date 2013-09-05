package com.kolakcc.loljclient.model;

import java.util.ArrayList;

import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.util.TOUtils;

public class CustomGameDetailed extends ModelFromTO { //TODO: group this and customgamelistitem under game
	String name;
	String gameMode;
	int ID;
	String allowSpectators;
	int spectatorCount;
	int gameMapID;
	int maxPlayers;

	ArrayList<CustomGamePlayer> team1;
	ArrayList<CustomGamePlayer> team2;
	boolean passwordSet;
	String gameType;
	int gameTypeConfigID;
	String gameState;
	Object statusOfParticipants;
	int dataVersion;
	String roomName;
	int spectatorDelay;
	String terminatedCondition;
	String queueTypeName;
	Object passbackURL;
	double optimisticLock;
	String roomPassword;
	int queuePosition;
	Object futureData;
	double expiryTime;
	int mapID;
	int pickTurn;
	ArrayList<PlayerChampionSelection> playerChampionSelections;
	CustomGamePlayer owner;
	Object passbackDataPacket;
	
	String gameModeString;
	Object glmGameID;
	Object glmHost;
	Object glmPort;
	Object glmSecurePort;
	int joinTimerDuration;
	TypedObject[] observers, bannedChampions, banOrder;
	
	public CustomGameDetailed(TypedObject to) {
		super(to);
		
		team1 = new ArrayList<CustomGamePlayer>();
		for (TypedObject teamPlayer : TOUtils.ArrayToTOArray(getArray("teamOne"))) {
			this.team1.add(new CustomGamePlayer(teamPlayer));
		}
		
		team2 = new ArrayList<CustomGamePlayer>();
		for (TypedObject teamPlayer : TOUtils.ArrayToTOArray(getArray("teamTwo"))) {
			team2.add(new CustomGamePlayer(teamPlayer));
		}
		glmGameID = getObject("glmGameId");
		glmHost = getObject("glmHost");
		glmPort = getObject("glmPort");
		allowSpectators = getString("spectatorsAllowed");
		maxPlayers = getInt("maxNumPlayers");
		glmSecurePort = getInt("glmSecurePort");
		gameMode = getString("gameMode");
		ID = getInt("id");
		name = getString("name");
		
		passwordSet = getBool("passwordSet");
		allowSpectators = getString("spectatorsAllowed");
		gameType = getString("gameType");
		gameTypeConfigID = getInt("gameTypeConfigId");
		gameState = getString("gameState");
		statusOfParticipants = getProbablyNull("statusOfParticipants");
		dataVersion = getInt("dataVersion");
		roomName = getString("roomName");
		spectatorDelay = getInt("spectatorDelay");
		terminatedCondition = getString("terminatedCondition");
		queueTypeName = getString("queueTypeName");
		passbackURL = getProbablyNull("passbackUrl");
		optimisticLock = getDouble("optimisticLock");
		roomPassword = getString("roomPassword");
		queuePosition = getInt("queuePosition");
		futureData = getProbablyNull("futureData");
		expiryTime = getDouble("expiryTime");
		//TODO: fix seconds
		System.out.printf("Time left: %d minutes and %f seconds %n", Math.round(expiryTime/60/1000), (expiryTime % (60*1000))/1000);
		
		mapID = getInt("mapId");
		pickTurn = getInt("pickTurn");
		gameState = getString("gameStateString");
		passbackDataPacket = getProbablyNull("passbackDataPacket");
		
		playerChampionSelections = new ArrayList<PlayerChampionSelection>();
		for (TypedObject pcs : getArray("playerChampionSelections")) {
			playerChampionSelections.add(new PlayerChampionSelection(pcs));
		}
		owner = new CustomGamePlayer(getTO("ownerSummary"));
		
		observers = getArray("observers");
		bannedChampions = getArray("bannedChampions");
		banOrder = getArray("banOrder");
		joinTimerDuration = getInt("joinTimerDuration");
		checkFields();
	}

	public ArrayList<CustomGamePlayer> getTeam1() {
		return team1;
	}

	public ArrayList<CustomGamePlayer> getTeam2() {
		return team2;
	}

	public String getName() {
		return name;
	}

	public String getGameMode() {
		return gameMode;
	}

	public int getID() {
		return ID;
	}

	public String getAllowSpectators() {
		return allowSpectators;
	}

	public int getSpectatorCount() {
		return spectatorCount;
	}

	public int getGameMapID() {
		return gameMapID;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public boolean isPasswordSet() {
		return passwordSet;
	}

	public String getGameType() {
		return gameType;
	}

	public int getGameTypeConfigID() {
		return gameTypeConfigID;
	}

	public String getGameState() {
		return gameState;
	}

	public Object getStatusOfParticipants() {
		return statusOfParticipants;
	}

	public int getDataVersion() {
		return dataVersion;
	}

	public String getRoomName() {
		return roomName;
	}

	public int getSpectatorDelay() {
		return spectatorDelay;
	}

	public String getTerminatedCondition() {
		return terminatedCondition;
	}

	public String getQueueTypeName() {
		return queueTypeName;
	}

	public Object getPassbackURL() {
		return passbackURL;
	}

	public double getOptimisticLock() {
		return optimisticLock;
	}

	public String getRoomPassword() {
		return roomPassword;
	}

	public int getQueuePosition() {
		return queuePosition;
	}

	public Object getFutureData() {
		return futureData;
	}

	public double getExpiryTime() {
		return expiryTime;
	}

	public int getMapID() {
		return mapID;
	}

	public int getPickTurn() {
		return pickTurn;
	}

	public ArrayList<PlayerChampionSelection> getPlayerChampionSelections() {
		return playerChampionSelections;
	}

	public CustomGamePlayer getOwner() {
		return owner;
	}

	public Object getPassbackDataPacket() {
		return passbackDataPacket;
	}

	public String getGameModeString() {
		return gameModeString;
	}

	public Object getGlmGameID() {
		return glmGameID;
	}

	public Object getGlmHost() {
		return glmHost;
	}

	public Object getGlmPort() {
		return glmPort;
	}

	public Object getGlmSecurePort() {
		return glmSecurePort;
	}
}
