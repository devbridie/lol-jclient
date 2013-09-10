package com.kolakcc.loljclient.model;

import java.util.ArrayList;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class MatchingThrottleConfig extends ModelFromTO {
	String limit;
	ArrayList<Object> matchingThrottleProperties;

	public MatchingThrottleConfig(TypedObject ito) {
		super(ito);
		limit = getString("limit");
		
		//not sure on this - working offline
		TypedObject[] propertiesFromModel = getArray("matchingThrottleProperties");
		for (TypedObject property : propertiesFromModel) {
			matchingThrottleProperties.add(property);
		}
		checkFields();
	}

}
