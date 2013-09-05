package com.kolakcc.loljclient.view.ui;

import javax.swing.JLabel;

import com.kolakcc.loljclient.util.LocaleMessages;

public class LocalizedJLabel extends JLabel {
	String key;
	
	public LocalizedJLabel() {
		super(LocaleMessages.generalMessages.getString("loading"));
		this.key = "loading";
	}
	
	public LocalizedJLabel(String key) {
		super(LocaleMessages.generalMessages.getString(key));
		this.key = key;
	}
	
	public LocalizedJLabel(LocaleMessages messages, String key) {
		super(messages.getString(key));
		this.key = key;
	}
	
	public void setKey(String key) {
		super.setText(LocaleMessages.generalMessages.getString(key));
		this.key = key;
	}
	
	public void setKey(LocaleMessages messages, String key) {
		super.setText(messages.getString(key));
		this.key = key;
	}
	
	public String getKey() { return key; }
}
