package com.kolakcc.loljclient.model;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.kolakcc.loljclient.util.FileSystem;

public class ChampionSkin {
	protected int ID;
	protected boolean isDefault;
	protected int order;
	protected int championID;
	protected String name;
	protected String displayName;
	protected String portraitPath;
	protected String splashPath;

	public int getChampionID() {
		return this.championID;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public int getID() {
		return this.ID;
	}

	public String getName() {
		return this.name;
	}

	public int getOrder() {
		return this.order;
	}

	public BufferedImage getPortrait() throws Exception {
		return ImageIO.read(FileSystem.getRADSFile("assets/images/champions/" + this.portraitPath));
	}

	public String getPortraitPath() {
		return this.portraitPath;
	}

	public BufferedImage getSplash() throws Exception {
		return ImageIO.read(FileSystem.getRADSFile("assets/images/champions/" + this.splashPath));
	}

	public String getSplashPath() {
		return this.splashPath;
	}

	public boolean isDefault() {
		return this.isDefault;
	}
}
