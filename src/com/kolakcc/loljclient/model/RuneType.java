package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class RuneType extends ModelFromTO {
	int runeTypeID;
	int dataVersion;
	String name;
	Object futureData;
	
	public RuneType(TypedObject ito) {
		super(ito);
		runeTypeID = getInt("runeTypeId");
		dataVersion = getInt("dataVersion");
		name = getString("name");
		futureData = getObject("futureData");
		checkFields();
	}

}
