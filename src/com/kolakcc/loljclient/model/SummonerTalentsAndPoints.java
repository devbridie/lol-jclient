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

	public int getDataVersion() {
		return dataVersion;
	}

	public Object getFutureData() {
		return futureData;
	}

	public int getTalentPoints() {
		return talentPoints;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public double getSummonerId() {
		return summonerId;
	}

	@Override
	public String toString() {
		return "SummonerTalentsAndPoints [dataVersion=" + dataVersion
				+ ", futureData=" + futureData + ", talentPoints="
				+ talentPoints + ", modifyDate=" + modifyDate + ", createDate="
				+ createDate + ", summonerId=" + summonerId + "]";
	}
}
