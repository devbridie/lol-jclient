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

}
