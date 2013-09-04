package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class Effect extends ModelFromTO {
	int effectID;
	String gameCode;
	int dataVersion;
	Object categoryID;
	String name;
	RuneType runeType;
	Object futureData;
	public Effect(TypedObject ito) {
		super(ito);
		effectID = getInt("effectId");
		gameCode = getString("gameCode");
		dataVersion = getInt("dataVersion");
		categoryID = getProbablyNull("categoryId");
		name = getString("name");
		runeType = new RuneType(getTO("runeType"));
		futureData = getProbablyNull("futureData");
		checkFields();
	}
}
