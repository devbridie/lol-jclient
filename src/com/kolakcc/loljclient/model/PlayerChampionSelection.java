package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class PlayerChampionSelection extends ModelFromTO {
	int spell1ID;
	int spell2ID;
	int championID;
	int selectedSkinIndex;
	int dataVersion;
	String summonerInternalName;
	Object futureData;
	
	public PlayerChampionSelection(TypedObject to) {
		super(to);
		spell1ID = getInt("spell1Id");
		spell2ID = getInt("spell2Id");
		championID = getInt("championId");
		selectedSkinIndex = getInt("selectedSkinIndex");
		summonerInternalName = getString("summonerInternalName");
		dataVersion = getInt("dataVersion");
		futureData = getProbablyNull("futureData");
		checkFields();
	}

	@Override
	public String toString() {
		return "PlayerChampionSelection [spell1ID=" + spell1ID
				+ ", spell2ID=" + spell2ID + ", championID=" + championID
				+ ", selectedSkinIndex=" + selectedSkinIndex + ", dataVersion="
				+ dataVersion + ", summonerInternalName="
				+ summonerInternalName + ", futureData=" + futureData + "]";
	}

	public int getSpell1ID() {
		return spell1ID;
	}

	public int getSpell2ID() {
		return spell2ID;
	}

	public int getChampionID() {
		return championID;
	}

	public int getSelectedSkinIndex() {
		return selectedSkinIndex;
	}

	public int getDataVersion() {
		return dataVersion;
	}

	public String getSummonerInternalName() {
		return summonerInternalName;
	}

	public Object getFutureData() {
		return futureData;
	}
}
