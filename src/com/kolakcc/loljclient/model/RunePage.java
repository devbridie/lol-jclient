package com.kolakcc.loljclient.model;

import java.util.ArrayList;
import java.util.Date;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class RunePage extends ModelFromTO {
	double pageID;
	String name;
	Date dateCreated;
	boolean current;
	ArrayList<RuneSlotEntry> slots;
	Object futureData;
	double summonerID;
	int dataVersion;
	
	public RunePage(TypedObject to) {
		super(to);
		if (!to.type.equals("com.riotgames.platform.summoner.spellbook.SpellBookPageDTO")) {
			new Exception("Expected com.riotgames.platform.summoner.spellbook.SpellBookPageDTO got " + to.type).printStackTrace();
		} else {
			pageID = getDouble("pageId");
			name = getString("name");
			current = getBool("current");
			dateCreated = getDate("createDate");
			slots = new ArrayList<RuneSlotEntry>();
			for (TypedObject slotEntry : getArray("slotEntries")) {
				this.slots.add(new RuneSlotEntry(slotEntry));
			}
			futureData = getProbablyNull("futureData");
			summonerID = getDouble("summonerId");
			dataVersion = getInt("dataVersion");
			checkFields();
		}
	}

	public double getPageID() {
		return pageID;
	}

	public String getName() {
		return name;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public boolean isCurrent() {
		return current;
	}

	public ArrayList<RuneSlotEntry> getSlots() {
		return slots;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
