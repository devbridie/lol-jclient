package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class DivisionSeries extends ModelFromTO {
	int target, losses, wins;
	double timeLeftToPlay; //in milliseconds
	
	public DivisionSeries(TypedObject ito) {
		super(ito);
		target = getInt("target");
		losses = getInt("losses");
		wins = getInt("wins");
		timeLeftToPlay = getDouble("timeLeftToPlayMillis");
		checkFields();
	}

	public int getTarget() {
		return target;
	}

	public int getLosses() {
		return losses;
	}

	public int getWins() {
		return wins;
	}

	public double getTimeLeftToPlay() {
		return timeLeftToPlay;
	}

	@Override
	public String toString() {
		return "DivisionSeries [target=" + target + ", losses=" + losses
				+ ", wins=" + wins + ", timeLeftToPlay=" + timeLeftToPlay + "]";
	}
}
