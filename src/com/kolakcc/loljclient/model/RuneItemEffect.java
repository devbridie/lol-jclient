package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class RuneItemEffect extends ModelFromTO {
	int effectID;
	int itemEffectID;
	int dataVersion;
	String value; // ???
	int itemID;
	Object futureData;
	Effect effect;
	public RuneItemEffect(TypedObject ito) {
		super(ito);
		effectID = getInt("effectId");
		itemEffectID = getInt("itemEffectId");
		dataVersion = getInt("dataVersion");
		value = getString("value");
		itemID = getInt("itemId");
		futureData = getProbablyNull("futureData");
		effect = new Effect(getTO("effect"));
		checkFields();
	}
}
