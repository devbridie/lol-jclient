package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class PlayerCredentials extends ModelFromTO {
	String encryptionKey;
	double gameID;
	int lastSelectedSkinIndex;
	String serverIP;
	boolean observer;
	double summonerID;
	Object futureData;
	String observerServerIP;
	int dataVersion;
	String handshakeToken;
	double playerID;
	int serverPort;
	int observerPort;
	String summonerName;
	String observerEncryptionKey;
	int championID;
	
	public PlayerCredentials(TypedObject ito) {
		super(ito);
		encryptionKey = getString("encryptionKey");
		gameID = getDouble("gameId");
		lastSelectedSkinIndex = getInt("lastSelectedSkinIndex");
		serverIP = getString("serverIp");
		observer = getBool("observer");
		summonerID = getDouble("summonerId");
		futureData = getObject("futureData");
		observerServerIP = getString("observerServerIp");
		dataVersion = getInt("dataVersion");
		handshakeToken = getString("handshakeToken");
		playerID = getDouble("playerId");
		serverPort = getInt("serverPort");
		observerPort = getInt("observerServerPort");
		summonerName = getString("summonerName");
		observerEncryptionKey = getString("observerEncryptionKey");
		championID = getInt("championId");
		checkFields();
	}

	public String getEncryptionKey() {
		return encryptionKey;
	}

	public double getGameID() {
		return gameID;
	}

	public int getLastSelectedSkinIndex() {
		return lastSelectedSkinIndex;
	}

	public String getServerIP() {
		return serverIP;
	}

	public boolean isObserver() {
		return observer;
	}

	public double getSummonerID() {
		return summonerID;
	}

	public Object getFutureData() {
		return futureData;
	}

	public String getObserverServerIP() {
		return observerServerIP;
	}

	public int getDataVersion() {
		return dataVersion;
	}

	public String getHandshakeToken() {
		return handshakeToken;
	}

	public double getPlayerID() {
		return playerID;
	}

	public int getServerPort() {
		return serverPort;
	}

	public int getObserverPort() {
		return observerPort;
	}

	public String getSummonerName() {
		return summonerName;
	}

	public String getObserverEncryptionKey() {
		return observerEncryptionKey;
	}

	public int getChampionID() {
		return championID;
	}

	@Override
	public String toString() {
		return "PlayerCredentials [encryptionKey=" + encryptionKey
				+ ", gameID=" + gameID + ", lastSelectedSkinIndex="
				+ lastSelectedSkinIndex + ", serverIP=" + serverIP
				+ ", observer=" + observer + ", summonerID=" + summonerID
				+ ", futureData=" + futureData + ", observerServerIP="
				+ observerServerIP + ", dataVersion=" + dataVersion
				+ ", handshakeToken=" + handshakeToken + ", playerID="
				+ playerID + ", serverPort=" + serverPort + ", observerPort="
				+ observerPort + ", summonerName=" + summonerName
				+ ", observerEncryptionKey=" + observerEncryptionKey
				+ ", championID=" + championID + "]";
	}
}
