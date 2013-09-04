package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class ChampionSelectPlayer extends ModelFromTO {
	PlayerChampionSelection championSelection;
	public ChampionSelectPlayer(TypedObject ito) {
		super(ito);
	}
	public PlayerChampionSelection getChampionSelection() {
		return championSelection;
	}
	public void setChampionSelection(PlayerChampionSelection championSelection) {
		this.championSelection = championSelection;
	}
}
