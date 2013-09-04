package com.kolakcc.loljclient.model;

import javax.swing.ImageIcon;

public class Status {
	protected String display;
	protected ImageIcon icon;
	public Status(String display, ImageIcon icon) {
		this.display = display;
		this.icon = icon;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
}