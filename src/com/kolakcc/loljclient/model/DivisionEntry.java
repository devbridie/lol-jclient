package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class DivisionEntry extends ModelFromTO implements Comparable<DivisionEntry> {
	boolean hotStreak, inactive, recruit, veteran;
	double lastPlayed, timeUntilDecay, timeLastDecayMessageShown;
	int leaguePoints, losses, previousDayLeaguePosition, wins;
	DivisionSeries series;
	String playerOrTeamName, playerOrTeamID, queueType, rank, tier, leagueName;
	
	public DivisionEntry(TypedObject ito) {
		super(ito);
		hotStreak = getBool("hotStreak");
		inactive = getBool("inactive");
		recruit = getBool("freshBlood");
		veteran = getBool("veteran");
		
		lastPlayed = getDouble("lastPlayed");
		timeLastDecayMessageShown = getDouble("timeLastDecayMessageShown");
		timeUntilDecay = getDouble("timeUntilDecay");
		
		leaguePoints = getInt("leaguePoints");
		losses = getInt("losses");
		previousDayLeaguePosition = getInt("previousDayLeaguePosition");
		wins = getInt("wins");
		
		series = (getObject("miniSeries") == null) ? null : new DivisionSeries(getTO("miniSeries"));
		
		playerOrTeamName = getString("playerOrTeamName");
		playerOrTeamID = getString("playerOrTeamId");
		queueType = getString("queueType");
		rank = getString("rank"); // I, II, III, IV, V
		tier = getString("tier"); // SILVER, etc
		leagueName = getString("leagueName");
		
		checkFields();
	}

	public boolean onHotStreak() {
		return hotStreak;
	}

	public boolean isInactive() {
		return inactive;
	}

	public boolean isRecruit() {
		return recruit;
	}

	public boolean isVeteran() {
		return veteran;
	}

	public double getLastPlayed() {
		return lastPlayed;
	}

	public int getLeaguePoints() {
		return leaguePoints;
	}

	public int getLosses() {
		return losses;
	}

	public int getPreviousDayLeaguePosition() {
		return previousDayLeaguePosition;
	}

	public int getWins() {
		return wins;
	}

	public DivisionSeries getSeries() {
		return series;
	}

	public String getPlayerOrTeamName() {
		return playerOrTeamName;
	}

	public String getPlayerOrTeamID() {
		return playerOrTeamID;
	}

	public String getQueueType() {
		return queueType;
	}

	public String getRank() {
		return rank;
	}

	public String getTier() {
		return tier;
	}

	@Override
	public String toString() {
		return "DivisionEntry [hotStreak=" + hotStreak + ", inactive="
				+ inactive + ", recruit=" + recruit + ", veteran=" + veteran
				+ ", lastPlayed=" + lastPlayed + ", leaguePoints="
				+ leaguePoints + ", losses=" + losses
				+ ", previousDayLeaguePosition=" + previousDayLeaguePosition
				+ ", wins=" + wins + ", series=" + series
				+ ", playerOrTeamName=" + playerOrTeamName
				+ ", playerOrTeamID=" + playerOrTeamID + ", queueType="
				+ queueType + ", rank=" + rank + ", tier=" + tier + "]";
	}

	@Override
	public int compareTo(DivisionEntry o) {
		return Integer.compare(this.getLeaguePoints(), o.getLeaguePoints());
	}

	public boolean isHotStreak() {
		return hotStreak;
	}

	public double getTimeUntilDecay() {
		return timeUntilDecay;
	}

	public double getTimeLastDecayMessageShown() {
		return timeLastDecayMessageShown;
	}

	public String getLeagueName() {
		return leagueName;
	}
}
