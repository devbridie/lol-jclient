package com.kolakcc.loljclient.model;

public class IconSize {
	public String description;
	public int number;

	public IconSize(String d, int n) {
		this.description = d;
		this.number = n;
	}

	public String toString() {
		return this.description;
	}
}