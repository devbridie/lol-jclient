package com.kolakcc.loljclient.model;

import java.util.ArrayList;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class MatchMakerParams {
	String botDifficulty;
	ArrayList<Integer> queueIDs;
	Object invitationID;
	Object lastMaestroMessage;
	Object teamID;
	Object team;
	Object languages;

	public MatchMakerParams(GameQueueConfig gameQueueConfig) {
		botDifficulty = "";
		
		queueIDs = new ArrayList<Integer>();
		queueIDs.add((int) gameQueueConfig.getID());
		
		//TODO: find out where to get the rest of these fields
		invitationID = null;
		lastMaestroMessage = null;
		teamID = null;
		team = null;
		languages = null;
	}
	
	public TypedObject toTypedObject() {
		TypedObject ret = new TypedObject("com.riotgames.platform.matchmaking.MatchMakerParams");
		ret.put("botDifficulty", botDifficulty);
		ret.put("queueIds", queueIDs);
		ret.put("invitationId", invitationID);
		ret.put("lastMaestroMessage", lastMaestroMessage);
		ret.put("teamId", teamID);
		ret.put("team", team);
		ret.put("languages", languages);
		return ret;
	}

	@Override
	public String toString() {
		return "MatchMakerParams [botDifficulty=" + botDifficulty
				+ ", queueIDs=" + queueIDs + ", invitationID=" + invitationID
				+ ", lastMaestroMessage=" + lastMaestroMessage + ", teamID="
				+ teamID + ", team=" + team + ", languages=" + languages + "]";
	}
}
