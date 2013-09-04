package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class Mastery extends ModelFromTO {
	int index;
	int minLevel;
	int maxRank;
	int id2;
	int groupID;
	int gameCode;
	int minTier;
	Integer preRequisite;
	int dataVersion2;
	String name;
	int rowID;
	String level1Desc;
	String level2Desc;
	String level3Desc;
	String level4Desc;
	String level5Desc;
	
	public Mastery(TypedObject ito) {
		super(ito);
		//rank = getInt("rank");
		index = getInt("index");
		minLevel = getInt("minLevel");
		maxRank = getInt("maxRank");
		id2 = getInt("tltId");
		groupID = getInt("talentGroupId");
		gameCode = getInt("gameCode");
		minTier = getInt("minTier");
		preRequisite = getInt("prereqTalentGameCode");
		dataVersion2 = getInt("dataVersion");
		name = getString("name");
		rowID = getInt("talentRowId");
		level1Desc = getString("level1Desc");
		level2Desc = getString("level2Desc");
		level3Desc = getString("level3Desc");
		level4Desc = getString("level4Desc");
		level5Desc = getString("level5Desc");
	}

}
