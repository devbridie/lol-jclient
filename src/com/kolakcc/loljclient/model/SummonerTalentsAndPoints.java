package com.kolakcc.loljclient.model;

import java.util.Date;
import com.gvaneyck.rtmp.encoding.TypedObject;

public class SummonerTalentsAndPoints extends ModelFromTO {
    int dataVersion;
    Object futureData;
    int talentPoints;
    Date modifyDate, createDate;
    double summonerId;

    public SummonerTalentsAndPoints(TypedObject ito) {
        super(ito);
        dataVersion = getInt("dataVersion");
        futureData = getProbablyNull("futureData");
        talentPoints = getInt("talentPoints");
        modifyDate = getDate("modifyDate");
        createDate = getDate("createDate");
        summonerId = getDouble("summonerId");
        checkFields();
    }

    //TODO: generate getters, i don't know how lol #javanoob
}
