package com.kolakcc.loljclient.model;

import java.util.ArrayList;
import java.util.Date;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class MasteryPage extends ModelFromTO implements Comparable<MasteryPage> {
	ArrayList<SummonerMastery> masteries;
	double pageID;
	String name;
	boolean current;
	Date createDate;
	double summonerID;
	Object futureData;
	int dataVersion;
	
	public MasteryPage(TypedObject ito) {
		super(ito);
		masteries = new ArrayList<SummonerMastery>();
		for (TypedObject mastery : getArray("talentEntries")) {
			masteries.add(new SummonerMastery(mastery));
		}
		pageID = getDouble("pageId");
		name = getString("name");
		current = getBool("current");
		createDate = getDate("createDate");
		summonerID = getDouble("summonerId");
		futureData = getObject("futureData");
		dataVersion = getInt("dataVersion");
		checkFields();
	}
	public String toString() {
		return name;
	}
	public ArrayList<SummonerMastery> getMasteries() {
		return masteries;
	}
	public double getPageID() {
		return pageID;
	}
	public String getName() {
		return name;
	}
	public boolean isCurrent() {
		return current;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public double getSummonerID() {
		return summonerID;
	}
	public Object getFutureData() {
		return futureData;
	}
	
	@Override
	public int compareTo(MasteryPage o) {
		return new Double(this.pageID).compareTo(o.pageID);
	}
	public void setCurrent(boolean current) {
		internalTypedObject.put("current", current);
		this.current = current;
	}
}
