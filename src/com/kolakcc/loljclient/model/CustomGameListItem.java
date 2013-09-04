package com.kolakcc.loljclient.model;

import java.util.ArrayList;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class CustomGameListItem extends ModelFromTO {
	String name;
	String gameMode;
	int ID;
	String allowSpectators;
	int spectatorCount;
	int gameMapID;
	int maxPlayers;
	CustomGamePlayer owner;
	boolean privateGame;
	ArrayList<CustomGamePlayer> team1;
	int team1Count;
	ArrayList<CustomGamePlayer> team2;
	int team2Count;

	// No clue what all of this is
	String gameModeString; // seems to be the same as gameMode
	Object glmGameID;
	Object glmHost;
	Object glmPort;
	Object glmSecurePort;

	public CustomGameListItem(TypedObject data) {
		super(data);
		this.spectatorCount = getInt("spectatorCount");
		this.glmGameID = getObject("glmGameId");
		this.glmHost = getObject("glmHost");
		this.glmPort = getObject("glmPort");
		this.gameModeString = getString("gameModeString");
		this.allowSpectators = getString("allowSpectators");
		this.gameMapID = getInt("gameMapId");
		this.maxPlayers = getInt("maxNumPlayers");
		this.glmSecurePort = getInt("glmSecurePort");
		this.gameMode = getString("gameMode");
		this.ID = getInt("id");
		this.privateGame = getBool("privateGame");
		this.team1Count = getInt("team1Count");
		this.team2Count = getInt("team2Count");
		this.owner = new CustomGamePlayer(getTO("owner"));
		this.name = getString("name");
		checkFields();
	}

	public boolean allowsSpectators() {
		return (this.allowSpectators.equals("ALL"));
	}

	@Override
	public String toString() {
		return "CustomGame [name=" + this.name + ", gameMode=" + this.gameMode
				+ ", ID=" + this.ID + ", allowSpectators="
				+ this.allowSpectators + ", spectatorCount="
				+ this.spectatorCount + ", gameMapID=" + this.gameMapID
				+ ", maxPlayers=" + this.maxPlayers + ", Owner=" + this.owner
				+ ", privateGame=" + this.privateGame + ", team1Count="
				+ this.team1Count + ", team2Count=" + this.team2Count
				+ ", gameModeString=" + this.gameModeString + ", glmGameID="
				+ this.glmGameID + ", glmHost=" + this.glmHost + ", glmPort="
				+ this.glmPort + ", glmSecurePort=" + this.glmSecurePort + "]";
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

	public CustomGamePlayer getOwner() {
		return owner;
	}

	public boolean isPrivateGame() {
		return privateGame;
	}

	public ArrayList<CustomGamePlayer> getTeam1() {
		return team1;
	}

	public int getTeam1Count() {
		return team1Count;
	}

	public ArrayList<CustomGamePlayer> getTeam2() {
		return team2;
	}

	public int getTeam2Count() {
		return team2Count;
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
