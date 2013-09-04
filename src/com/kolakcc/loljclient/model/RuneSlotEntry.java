package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class RuneSlotEntry extends ModelFromTO implements Comparable<RuneSlotEntry> {
	int runeID;
	int slotID;
	Rune rune;
	Object futureData;
	int dataVersion;
	RuneSlot runeSlot;
	
	public RuneSlotEntry(TypedObject to) {
		super(to);
		if (!to.type.equals("com.riotgames.platform.summoner.spellbook.SlotEntry")) {
			new Exception("Expected com.riotgames.platform.summoner.spellbook.SlotEntry got " + to.type).printStackTrace();
		} else {
			runeID = getInt("runeId");
			slotID = getInt("runeSlotId");
			rune = new Rune(getTO("rune"));
			futureData = getProbablyNull("futureData");
			dataVersion = getInt("dataVersion");
			runeSlot = new RuneSlot(getTO("runeSlot"));
			checkFields();
		}
	}

	public int getRuneID() {
		return runeID;
	}
	public int getSlotID() {
		return slotID;
	}
	public Rune getRune() {
		return rune;
	}
	@Override
	public String toString() {
		return "RuneSlotEntry [runeID=" + runeID + ", slotID=" + slotID + ", rune="
				+ rune + "]";
	}
	@Override
	public int compareTo(RuneSlotEntry o) {
		return this.getSlotID() - o.getSlotID();
	}
}
