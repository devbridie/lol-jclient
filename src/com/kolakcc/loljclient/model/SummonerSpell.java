package com.kolakcc.loljclient.model;

import java.util.ArrayList;

public class SummonerSpell {
	public static ArrayList<SummonerSpell> spells;
	public int id;
	public String displayName;
	private SummonerSpell(int ID, String displayName) {
		this.id = ID;
		this.displayName = displayName;
	}
	public static void initializeSpells() {
		//spells.add(new SummonerSpell(0))
		//CLARITY, GARRISON, GHOST, HEAL, REVIVE, SMITE, CLEANSE, TELEPORT, BARRIER, EXHAUST, IGNITE, CLAIRVOYANCE, FLASH
		spells = new ArrayList<SummonerSpell>();
		spells.add(new SummonerSpell(10,"Revive"));
		spells.add(new SummonerSpell(11,"Smite"));
		spells.add(new SummonerSpell(4,"Flash"));
		spells.add(new SummonerSpell(13,"Clarity"));
		spells.add(new SummonerSpell(7,"Heal"));
		spells.add(new SummonerSpell(6,"Ghost"));
		spells.add(new SummonerSpell(1,"Cleanse"));
		spells.add(new SummonerSpell(12,"Teleport"));
		spells.add(new SummonerSpell(21,"Barrier"));
		spells.add(new SummonerSpell(3,"Exhaust"));
		spells.add(new SummonerSpell(14,"Ignite"));
		spells.add(new SummonerSpell(2,"Clairvoyance"));
	}
	public static SummonerSpell getSpell(int id) {
		for (SummonerSpell i : spells) {
			if (i.id == id) return i;
		}
		new Exception("Could not find spell ID: "+id).printStackTrace();
		return null;
	}
	@Override
	public String toString() {
		return displayName;
	}
}
