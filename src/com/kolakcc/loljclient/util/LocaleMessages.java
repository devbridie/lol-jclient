package com.kolakcc.loljclient.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleMessages {
	String bundleName;
	ResourceBundle internalBundle;
	
	public LocaleMessages(String bundleName) {
		internalBundle = ResourceBundle.getBundle("com.kolakcc.loljclient.view.locale."+bundleName, Locale.getDefault());
		this.bundleName = bundleName;
	}
	
	public String getString(String key) {
		try {
			return internalBundle.getString(key);
		} catch (Exception e) {
			System.err.printf("Key %s not found in bundle %s with locale %s %n", key, bundleName, internalBundle.getLocale());
			return key;
		}
	}
}
