package com.kolakcc.loljclient.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleMessages {
	public static LocaleMessages generalMessages = new LocaleMessages("generalBundle");
	
	String bundleName;
	ResourceBundle internalBundle;
	
	public LocaleMessages(String bundleName) {
		internalBundle = ResourceBundle.getBundle("com.kolakcc.loljclient.view.locale."+bundleName, Locale.getDefault());
		this.bundleName = bundleName;
	}
	
	public String getString(String key) {
		try {
			return internalBundle.getString(key.replaceAll(" ", "_"));
		} catch (Exception e) {
			System.err.printf("Key %s not found in bundle %s with locale %s %n", key, bundleName, internalBundle.getLocale());
			return key;
		}
	}
	
	public String getComplexString(String key, Object[] messageArguments) {
		return new MessageFormat(getString(key)).format(messageArguments);
	}
}
