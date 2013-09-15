package com.kolakcc.loljclient.model;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class SummonerActiveBoosts extends ModelFromTO {
    int xpBoostPerWinCount, xpLoyaltyBoost, ipBoostPerWinCount, ipLoyaltyBoost;
    double xpBoostEndDate, ipBoostEndDate, summonerId;

    public SummonerActiveBoosts(TypedObject ito) {
        super(ito);
        xpBoostPerWinCount = getInt("xpBoostPerWinCount");
        xpLoyaltyBoost = getInt("xpLoyaltyBoost");
        ipBoostPerWinCount = getInt("ipBoostPerWinCount");
        ipLoyaltyBoost = getInt("ipLoyaltyBoost");
        xpBoostEndDate = getDouble("xpBoostEndDate");
        ipBoostEndDate = getDouble("ipBoostEndDate");
        summonerId = getDouble("summonerId");
        checkFields();
    }

    public int getXpBoostPerWinCount() {
        return xpBoostPerWinCount;
    }

    public int getXpLoyaltyBoost() {
        return xpLoyaltyBoost;
    }

    public int getIpBoostPerWinCount() {
        return ipBoostPerWinCount;
    }

    public int getIpLoyaltyBoost() {
        return ipLoyaltyBoost;
    }

    public double getXpBoostEndDate() {
        return xpBoostEndDate;
    }

    public double getIpBoostEndDate() {
        return ipBoostEndDate;
    }

    public double getSummonerId() {
        return summonerId;
    }
}
