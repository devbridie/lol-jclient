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

    //TODO: generate getters, i don't know how #javanoob
}
