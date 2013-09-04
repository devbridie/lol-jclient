package com.kolakcc.loljclient.model;

import java.util.ArrayList;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class Rune extends ModelFromTO {
	//There are more fields, but they don't seem to be used
	String toolTip;
	int tier;
	double trueBonus;
	String description;
	String name;
	RuneType type;
	ArrayList<RuneItemEffect> itemEffects;
	Object futureData;
	int dataVersion;
	int duration;
	int gameCode;
	String baseType;
	int itemID;
	Object uses;
	Object imagePath;
	
	
	public Rune(TypedObject to) {
		super(to);
		if (!to.type.equals("com.riotgames.platform.catalog.runes.Rune")) {
			new Exception("Expected com.riotgames.platform.catalog.runes.Rune got " + to.type).printStackTrace();
		} else {
			tier = getInt("tier");
			//trueBonus = to.getTO("itemEffects").getDouble("value");
			description = getString("description");
			name = getString("name");
			tier = getInt("tier");
			futureData = getProbablyNull("futureData");
			dataVersion = getInt("dataVersion");
			type = new RuneType(getTO("runeType"));
			toolTip = getString("toolTip");
			itemID = getInt("itemId");
			uses = getProbablyNull("uses");
			imagePath = getProbablyNull("imagePath");
			gameCode = getInt("gameCode");
			duration = getInt("duration");
			baseType = getString("baseType");
			itemEffects = new ArrayList<RuneItemEffect>();
			for (TypedObject effectTO : getArray("itemEffects")) {
				itemEffects.add(new RuneItemEffect(effectTO));
			}
			checkFields();
		}
	}

	@Override
	public String toString() {
		return "Rune [toolTip=" + toolTip + ", tier=" + tier + ", trueBonus="
				+ trueBonus + ", description=" + description + ", name=" + name
				+ "]";
	}

	public String getToolTip() {
		return toolTip;
	}

	public int getTier() {
		return tier;
	}

	public double getTrueBonus() {
		return trueBonus;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}
}
