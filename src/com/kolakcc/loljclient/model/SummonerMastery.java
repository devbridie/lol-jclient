package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class SummonerMastery extends ModelFromTO {
	int rank;
	int id;
	int dataVersion;
	Mastery mastery;
	Object futureData;
	double summonerID; //always -1.0?

	public SummonerMastery(TypedObject ito) {
		super(ito);
		rank = getInt("rank");
		id = getInt("talentId");
		dataVersion = getInt("dataVersion");
		futureData = getProbablyNull("futureData");
		summonerID = getDouble("summonerId");
		mastery = new Mastery(getTO("talent"));
		checkFields();
	}
}
