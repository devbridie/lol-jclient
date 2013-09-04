package com.kolakcc.loljclient.model;

import java.io.Serializable;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class FellowPlayer extends ModelFromTO implements Serializable {
	private static final long serialVersionUID = -1299680716772684806L;
	int dataVersion;
	double championID;
	int teamID;
	Object futureData;
	double summonerID;
	
	@SuppressWarnings("unused")
	private FellowPlayer() {}
	
	public FellowPlayer(TypedObject ito) {
		super(ito);
		
		dataVersion = getInt("dataVersion");
		championID = getDouble("championId");
		teamID = getInt("teamId");
		futureData = getProbablyNull("futureData");
		summonerID = getDouble("summonerId");
		
		checkFields();
	}

}
