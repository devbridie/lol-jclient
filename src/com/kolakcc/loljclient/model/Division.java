package com.kolakcc.loljclient.model;

import java.util.ArrayList;

import com.gvaneyck.rtmp.encoding.TypedObject;

public class Division extends ModelFromTO {
	ArrayList<DivisionEntry> entries;
	String queue, name, tier, requestorsRank, requestName;
	
	public Division(TypedObject ito) {
		super(ito);
		queue = getString("queue");
		name = getString("name");
		tier = getString("tier");
		requestorsRank = getString("requestorsRank");
		requestName = getString("requestorsName");
		entries = new ArrayList<DivisionEntry>();
		for (TypedObject entry : getArray("entries")) {
			entries.add(new DivisionEntry(entry));
		}
		checkFields();
	}

	public ArrayList<DivisionEntry> getEntries() {
		return entries;
	}

	public String getQueue() {
		return queue;
	}

	public String getName() {
		return name;
	}

	public String getTier() {
		return tier;
	}

	public String getRequestorsRank() {
		return requestorsRank;
	}

	public String getRequestName() {
		return requestName;
	}

	@Override
	public String toString() {
		return "Division [entries=" + entries + ", queue=" + queue + ", name="
				+ name + ", tier=" + tier + ", requestorsRank="
				+ requestorsRank + ", requestName=" + requestName + "]";
	}
}
