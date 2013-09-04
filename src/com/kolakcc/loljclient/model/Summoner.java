package com.kolakcc.loljclient.model;

import java.util.ArrayList;

import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.util.TOUtils;

public class Summoner extends ModelFromTO {
	String summonerName;

	SummonerSpell classicDefaultSpell1, classicDefaultSpell2, 
	              dominionDefaultSpell1, dominionDefaultSpell2, 
	              aramDefaultSpell1, aramDefaultSpell2, 
	              tutorialDefaultSpell1, tutorialDefaultSpell2;
	
	
	double accountID, summonerID;
	
	ArrayList<RunePage> runePages;
	MasteryBook masteryBook;
	
	int dataVersion;
	Object futureData;
	
	SummonerLevel summonerLevel;
	
	public Summoner(TypedObject allSummonerData) {
		super(allSummonerData);
		if ((!allSummonerData.type.equals("com.riotgames.platform.summoner.AllSummonerData")) && (!allSummonerData.type.equals("com.riotgames.platform.summoner.AllPublicSummonerDataDTO"))) {
			new Exception("Expected com.riotgames.platform.summoner.AllSummonerData got " + allSummonerData.type).printStackTrace();
		} else {
			runePages = new ArrayList<RunePage>();
			for (TypedObject runePage : TOUtils.ArrayToTOArray(getTO("spellBook").getArray("bookPages"))) {
				runePages.add(new RunePage(runePage));
			}
			accountID = allSummonerData.getTO("summoner").getDouble("acctId");
			summonerID = allSummonerData.getTO("summoner").getDouble("sumId");
			summonerName = allSummonerData.getTO("summoner").getString("name");
			TypedObject defaultSpellMap = getTO("summonerDefaultSpells").getTO("summonerDefaultSpellMap");
			if (defaultSpellMap.containsKey("CLASSIC")) {
				classicDefaultSpell1 = SummonerSpell.getSpell(defaultSpellMap.getTO("CLASSIC").getInt("spell1Id"));
				classicDefaultSpell2 = SummonerSpell.getSpell(defaultSpellMap.getTO("CLASSIC").getInt("spell2Id"));
			} else if (defaultSpellMap.containsKey("ODIN")) {
				dominionDefaultSpell1 = SummonerSpell.getSpell(defaultSpellMap.getTO("ODIN").getInt("spell1Id"));
				dominionDefaultSpell2 = SummonerSpell.getSpell(defaultSpellMap.getTO("ODIN").getInt("spell2Id"));
			}
			if (containsKey("masteryBook")) masteryBook = new MasteryBook(getTO("masteryBook"));
			//TODO: find out where the public mastery book info is
			//futureData = getProbablyNull("futureData");
			//dataVersion = getInt("dataVersion");
			summonerLevel = new SummonerLevel(getTO("summonerLevel"));
			checkFields();
		}
	}

	public String getSummonerName() {
		return summonerName;
	}

	public SummonerSpell getClassicDefaultSpell1() {
		return classicDefaultSpell1;
	}

	public SummonerSpell getClassicDefaultSpell2() {
		return classicDefaultSpell2;
	}

	public SummonerSpell getDominionDefaultSpell1() {
		return dominionDefaultSpell1;
	}

	public SummonerSpell getDominionDefaultSpell2() {
		return dominionDefaultSpell2;
	}

	public SummonerSpell getAramDefaultSpell1() {
		return aramDefaultSpell1;
	}

	public SummonerSpell getAramDefaultSpell2() {
		return aramDefaultSpell2;
	}

	public SummonerSpell getTutorialDefaultSpell1() {
		return tutorialDefaultSpell1;
	}

	public SummonerSpell getTutorialDefaultSpell2() {
		return tutorialDefaultSpell2;
	}

	public double getAccountID() {
		return accountID;
	}

	public ArrayList<RunePage> getRunePages() {
		return runePages;
	}

	public MasteryBook getMasteryBook() {
		return masteryBook;
	}

	public double getSummonerID() {
		return summonerID;
	}

	public int getDataVersion() {
		return dataVersion;
	}

	public Object getFutureData() {
		return futureData;
	}

	public SummonerLevel getSummonerLevel() {
		return summonerLevel;
	}
}
