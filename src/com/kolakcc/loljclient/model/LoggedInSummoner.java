package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class LoggedInSummoner {
	protected static TypedObject internalTypedObject;

	public static int RPBalance;
	public static int IPBalance;
	
	public static Summoner summonerData;
	public static void populateData(TypedObject summoner) { //TODO: all getLoginDataPacket fields
		internalTypedObject = summoner;
		summonerData = new Summoner(summoner.getTO("allSummonerData"));
		RPBalance = summoner.getDouble("rpBalance").intValue();
		IPBalance = summoner.getDouble("ipBalance").intValue();
	}
}
