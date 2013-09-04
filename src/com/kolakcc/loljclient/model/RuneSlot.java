package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class RuneSlot extends ModelFromTO {
	int ID;
	int dataVersion;
	int minLevel;
	RuneType type;
	
	public RuneSlot(TypedObject ito) {
		super(ito);
		ID = getInt("id");
		dataVersion = getInt("dataVersion");
		minLevel = getInt("minLevel");
		type = new RuneType(getTO("runeType"));
	}	
}
