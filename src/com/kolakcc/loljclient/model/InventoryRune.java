package com.kolakcc.loljclient.model;

import java.util.Date;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class InventoryRune extends ModelFromTO {
	int amount;
	Rune rune;
	Date purchaseDate; 
	Date purchased; // difference?
	int runeID;
	Object futureData;
	int dataVersion;
	double summonerID;
	
	public InventoryRune(TypedObject to) {
		super(to);
		if (!to.type.equals("com.riotgames.platform.summoner.runes.SummonerRune")) {
			new Exception("Expected com.riotgames.platform.summoner.runes.SummonerRune got " + to.type).printStackTrace();
		} else {
			amount = getInt("quantity");
			rune = new Rune(getTO("rune"));
			purchaseDate = getDate("purchaseDate");
			purchased = getDate("purchased");
			runeID = getInt("runeId");
			dataVersion = getInt("dataVersion");
			futureData = getProbablyNull("futureData");
			summonerID = getDouble("summonerId");
			if (!purchased.equals(purchaseDate)) { System.out.println("INFO: purchaseDate does not match purchased"); }
		}
		checkFields();
	}
	public int getAmount() {
		return amount;
	}
	public Rune getRune() {
		return rune;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public Date getPurchased() {
		return purchased;
	}
	public int getRuneID() {
		return runeID;
	}
}
