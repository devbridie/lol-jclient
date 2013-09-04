package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class SummonerLevel extends ModelFromTO {
	Object futureData;
	double expTierMod, grantRP, expForLoss, infTierMod, summonerTier, expToNextLevel, expForWin, summonerLevel;
	int dataVersion;
	
	public SummonerLevel(TypedObject ito) {
		super(ito);
		futureData = getProbablyNull("futureData");
		dataVersion = getInt("dataVersion");
		expTierMod = getDouble("expTierMod");
		grantRP = getDouble("grantRp");
		expForLoss = getDouble("expForLoss");
		infTierMod = getDouble("infTierMod");
		summonerTier = getDouble("summonerTier");
		expToNextLevel = getDouble("expToNextLevel");
		expForWin = getDouble("expForWin");
		summonerLevel = getDouble("summonerLevel");
		checkFields();
	}

	public Object getFutureData() {
		return futureData;
	}

	public double getExpTierMod() {
		return expTierMod;
	}

	public double getGrantRP() {
		return grantRP;
	}

	public double getExpForLoss() {
		return expForLoss;
	}

	public double getInfTierMod() {
		return infTierMod;
	}

	public double getSummonerTier() {
		return summonerTier;
	}

	public double getExpToNextLevel() {
		return expToNextLevel;
	}

	public double getExpForWin() {
		return expForWin;
	}

	public double getSummonerLevel() {
		return summonerLevel;
	}

	public int getDataVersion() {
		return dataVersion;
	}	
}
