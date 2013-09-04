package com.kolakcc.loljclient.model;

import java.util.EnumSet;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class Game extends ModelFromTO {
	protected int gameMapID;
	public enum Map {
		SUMMONERS_RIFT, TWISTED_TREELINE, DOMINION, PROVING_GROUNDS, TUTORIAL, HOWLING_ABYSS, UNKNOWN;
		public static final EnumSet<Map> MapEnumset = EnumSet.allOf(Map.class);
	}
	public Map getMap() {
		
		//TODO: all map ids
		switch (gameMapID) {
			case 1: return Map.SUMMONERS_RIFT;
			case 7: return Map.PROVING_GROUNDS;
			case 12: return Map.HOWLING_ABYSS;
			default: return Map.UNKNOWN;
		}
	}
	protected Game() {}
	public Game(TypedObject to) { super(to); }
}
