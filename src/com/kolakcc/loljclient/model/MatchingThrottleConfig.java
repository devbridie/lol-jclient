package com.kolakcc.loljclient.model;

import java.util.ArrayList;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class MatchingThrottleConfig extends ModelFromTO {
	double limit;
	ArrayList<Object> matchingThrottleProperties;
	int dataVersion;
	Object futureData;
	String cacheName;

	public MatchingThrottleConfig(TypedObject ito) {
		super(ito);
		limit = getDouble("limit");
		
		TypedObject[] propertiesFromModel = getArray("matchingThrottleProperties");
		for (TypedObject property : propertiesFromModel) {
			matchingThrottleProperties.add(property);
		}
		
		dataVersion = getInt("dataVersion");
		futureData = getProbablyNull("futureData");
		cacheName = getString("cacheName");
		
		checkFields();
	}

	public double getLimit() {
		return limit;
	}

	public ArrayList<Object> getMatchingThrottleProperties() {
		return matchingThrottleProperties;
	}

	@Override
	public String toString() {
		return "MatchingThrottleConfig [limit=" + limit
				+ ", matchingThrottleProperties=" + matchingThrottleProperties
				+ "]";
	}
}
