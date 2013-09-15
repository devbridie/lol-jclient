package com.kolakcc.loljclient.model;

import java.util.ArrayList;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class SearchingForMatchNotification extends ModelFromTO {
	Object playerJoinFailures;
	Object ghostGameSummoners;
	ArrayList<QueueInfo> joinedQueues;
	public SearchingForMatchNotification(TypedObject ito) {
		super(ito);
		if (!ito.type.equals("com.riotgames.platform.matchmaking.SearchingForMatchNotification")) {
			new Exception("Expected com.riotgames.platform.matchmaking.SearchingForMatchNotification got " + ito.type).printStackTrace();
		} else {
			playerJoinFailures = getProbablyNull("playerJoinFailures");
			ghostGameSummoners = getProbablyNull("ghostGameSummoners");
			joinedQueues = new ArrayList<QueueInfo>();
			for (TypedObject queueEntry : getArray("joinedQueues")) {
				joinedQueues.add(new QueueInfo(queueEntry));
			}
			checkFields();
		}
	}
}
