package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class QueueInfo extends ModelFromTO {
	double waitTime, queueID;
	int queueLength;

	public QueueInfo() {
		// TODO Auto-generated constructor stub
	}

	public QueueInfo(TypedObject ito) {
		super(ito);
		if (!ito.type.equals("com.riotgames.platform.matchmaking.QueueInfo")) {
			new Exception("Expected com.riotgames.platform.matchmaking.QueueInfo got " + ito.type).printStackTrace();
		} else {
			waitTime = getDouble("waitTime");
			queueID = getDouble("queueId");
			queueLength = getInt("queueLength");
			checkFields();
		}
	}
}
