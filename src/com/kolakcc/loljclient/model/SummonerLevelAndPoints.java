package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class SummonerLevelAndPoints extends ModelFromTO {
    int dataVersion;
    Object futureData;
    double infPoints, expPoints, summonerLevel, summonerId;

    public SummonerLevelAndPoints(TypedObject ito) {
        super(ito);
        dataVersion = getInt("dataVersion");
        futureData = getProbablyNull("futureData");
        infPoints = getDouble("infPoints");
        expPoints = getDouble("expPoints");
        summonerLevel = getDouble("summonerLevel");
        summonerId = getDouble("summonerId");
        checkFields();
    }

	public int getDataVersion() {
		return dataVersion;
	}

	public Object getFutureData() {
		return futureData;
	}

	public double getInfPoints() {
		return infPoints;
	}

	public double getExpPoints() {
		return expPoints;
	}

	public double getSummonerLevel() {
		return summonerLevel;
	}

	public double getSummonerId() {
		return summonerId;
	}

	@Override
	public String toString() {
		return "SummonerLevelAndPoints [dataVersion=" + dataVersion
				+ ", futureData=" + futureData + ", infPoints=" + infPoints
				+ ", expPoints=" + expPoints + ", summonerLevel="
				+ summonerLevel + ", summonerId=" + summonerId + "]";
	}
}
