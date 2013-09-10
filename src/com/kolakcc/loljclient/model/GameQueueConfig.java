package com.kolakcc.loljclient.model;

import java.util.ArrayList;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class GameQueueConfig extends ModelFromTO {
	int blockedMinutesThreshold, minimumParticipantSize, maxLevel, gameTypeConfigID, minLevel, numPlayersPerTeam, maximumParticipantListSize, dataVersion;
	boolean ranked, thresholdEnabled, teamOnly;
	String pointsConfigKey, type, typeString, cacheName, gameMode, queueBonusKey, queueStateString;
	ArrayList<Integer> supportedMapIDs;
	double ID, thresholdSize;
	MatchingThrottleConfig matchingThrottleConfig;
	
	Object futureData;
	
	public GameQueueConfig(TypedObject ito) {
		super(ito);
		if (!ito.type.equals("com.riotgames.platform.matchmaking.GameQueueConfig")) {
			new Exception("Expected com.riotgames.platform.matchmaking.GameQueueConfig got " + ito.type).printStackTrace();
		} else {
			blockedMinutesThreshold = getInt("blockedMinutesThreshold");
			minimumParticipantSize = getInt("minimumParticipantListSize");
			maxLevel = getInt("maxLevel");
			gameTypeConfigID = getInt("gameTypeConfigId");
			minLevel = getInt("minLevel");
			numPlayersPerTeam = getInt("numPlayersPerTeam");
			maximumParticipantListSize = getInt("maximumParticipantListSize");
			dataVersion = getInt("dataVersion");
			ranked = getBool("ranked");
			thresholdEnabled = getBool("thresholdEnabled");
			teamOnly = getBool("teamOnly");
			pointsConfigKey = getString("pointsConfigKey");
			type = getString("type");
			typeString = getString("typeString");
			cacheName = getString("cacheName");
			gameMode = getString("gameMode");
			queueBonusKey = getString("queueBonusKey");
			queueStateString = getString("queueStateString");
			checkFields();
		}
	}

	public int getBlockedMinutesThreshold() {
		return blockedMinutesThreshold;
	}

	public int getMinimumParticipantSize() {
		return minimumParticipantSize;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public int getGameTypeConfigID() {
		return gameTypeConfigID;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public int getNumPlayersPerTeam() {
		return numPlayersPerTeam;
	}

	public int getMaximumParticipantListSize() {
		return maximumParticipantListSize;
	}

	public int getDataVersion() {
		return dataVersion;
	}

	public boolean isRanked() {
		return ranked;
	}

	public boolean isThresholdEnabled() {
		return thresholdEnabled;
	}

	public boolean isTeamOnly() {
		return teamOnly;
	}

	public String getPointsConfigKey() {
		return pointsConfigKey;
	}

	public String getType() {
		return type;
	}

	public String getTypeString() {
		return typeString;
	}

	public String getCacheName() {
		return cacheName;
	}

	public String getGameMode() {
		return gameMode;
	}

	public String getQueueBonusKey() {
		return queueBonusKey;
	}

	public String getQueueStateString() {
		return queueStateString;
	}

	public ArrayList<Integer> getSupportedMapIDs() {
		return supportedMapIDs;
	}

	public double getID() {
		return ID;
	}

	public double getThresholdSize() {
		return thresholdSize;
	}

	public MatchingThrottleConfig getMatchingThrottleConfig() {
		return matchingThrottleConfig;
	}

	public Object getFutureData() {
		return futureData;
	}

	@Override
	public String toString() {
		return "GameQueueConfig [blockedMinutesThreshold="
				+ blockedMinutesThreshold + ", minimumParticipantSize="
				+ minimumParticipantSize + ", maxLevel=" + maxLevel
				+ ", gameTypeConfigID=" + gameTypeConfigID + ", minLevel="
				+ minLevel + ", numPlayersPerTeam=" + numPlayersPerTeam
				+ ", maximumParticipantListSize=" + maximumParticipantListSize
				+ ", dataVersion=" + dataVersion + ", ranked=" + ranked
				+ ", thresholdEnabled=" + thresholdEnabled + ", teamOnly="
				+ teamOnly + ", pointsConfigKey=" + pointsConfigKey + ", type="
				+ type + ", typeString=" + typeString + ", cacheName="
				+ cacheName + ", gameMode=" + gameMode + ", queueBonusKey="
				+ queueBonusKey + ", queueStateString=" + queueStateString
				+ ", supportedMapIDs=" + supportedMapIDs + ", ID=" + ID
				+ ", thresholdSize=" + thresholdSize
				+ ", matchingThrottleConfig=" + matchingThrottleConfig
				+ ", futureData=" + futureData + "]";
	}

}
