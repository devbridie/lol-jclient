package com.kolakcc.loljclient.model;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteJob;
import com.almworks.sqlite4java.SQLiteQueue;
import com.almworks.sqlite4java.SQLiteStatement;
import com.kolakcc.loljclient.util.FileSystem;

public class Champion {
	int ID;
	String name;
	String displayName;
	String title;
	String iconPath;
	String portraitPath;
	String splashPath;
	String danceVideoPath; // o_o
	LinkedList<String> tags;
	String description;
	String quote;
	String quoteAuthor;
	int range;
	int movementSpeed;
	double armorBase;
	double armorLevel;
	int manaBase;
	int manaLevel;
	int criticalChanceBase;
	int criticalChanceLevel;
	double manaRegenBase;
	double manaRegenLevel;
	double healthRegenBase;
	double healthRegenLevel;
	int magicResistBase;
	double magicResistLevel;

	int healthBase;
	double healthLevel;

	double attackBase;
	double attackLevel;
	int ratingDefense;
	int ratingMagic;

	int ratingDifficulty;
	int ratingAttack;

	String tips;

	String opponentTips;

	String selectSoundPath;
	LinkedList<ChampionSkin> skins = new LinkedList<ChampionSkin>();
	// Vector<RiotRecommendedItem> riotRecommendedItems = new
	// Vector<RiotRecommendedItem>();
	private static LinkedList<Champion> champions;
	boolean rankedPlayEnabled;
	double purchaseDate;
	boolean active;
	boolean botEnabled;
	int winCountRemaining;
	double endDate;
	boolean freeToPlay;

	boolean freeToPlayReward;

	boolean owned;
	
	public static LinkedList<Champion> getChampions() { return champions; }
	private Champion() { }
	public static Champion getChampionFromID(int ID) {
		for (Champion c : champions) {
			if (c.ID == ID) {
				return c;
			}
		}
		new Exception("Champion #" + ID + " not found.").printStackTrace();
		return null;
	}

	public static void initializeChampions() {
		if (champions == null) {
			SQLiteQueue queue = GameStatsDBWrapper.getQueue();
			champions = new LinkedList<Champion>();
			queue.execute(new SQLiteJob<Object>() {
				protected Object job(SQLiteConnection connection) throws Throwable {
					SQLiteStatement st = connection
							.prepare("SELECT * FROM champions");
					while (st.step()) {
						Champion newChamp = new Champion();
						int i = 0; // first column is id
						newChamp.ID = st.columnInt(i); i++;
						newChamp.name = st.columnString(i); i++;
						newChamp.displayName = st.columnString(i); i++;
						newChamp.title = st.columnString(i); i++;
						newChamp.iconPath = st.columnString(i); i++;
						newChamp.portraitPath = st.columnString(i); i++;
						newChamp.splashPath = st.columnString(i); i++;
						newChamp.danceVideoPath = st.columnString(i); i++; i++; // TODO: tags
						newChamp.description = st.columnString(i); i++;
						newChamp.quote = st.columnString(i); i++;
						newChamp.quoteAuthor = st.columnString(i); i++;
						newChamp.range = st.columnInt(i); i++;
						newChamp.movementSpeed = st.columnInt(i); i++;
						newChamp.armorBase = st.columnDouble(i); i++;
						newChamp.armorLevel = st.columnDouble(i); i++;
						newChamp.manaBase = st.columnInt(i); i++;
						newChamp.manaLevel = st.columnInt(i); i++;
						newChamp.criticalChanceBase = st.columnInt(i); i++;
						newChamp.criticalChanceLevel = st.columnInt(i); i++;
						newChamp.manaRegenBase = st.columnDouble(i); i++;
						newChamp.manaRegenLevel = st.columnDouble(i); i++;
						newChamp.healthRegenBase = st.columnDouble(i); i++;
						newChamp.healthRegenLevel = st.columnDouble(i); i++;
						newChamp.magicResistBase = st.columnInt(i); i++;
						newChamp.magicResistLevel = st.columnDouble(i); i++;
						newChamp.healthBase = st.columnInt(i); i++;
						newChamp.healthLevel = st.columnDouble(i); i++;
						newChamp.attackBase = st.columnDouble(i); i++;
						newChamp.attackLevel = st.columnDouble(i); i++;
						newChamp.ratingAttack = st.columnInt(i); i++;
						newChamp.ratingDefense = st.columnInt(i); i++;
						newChamp.ratingDifficulty = st.columnInt(i); i++;
						newChamp.ratingMagic = st.columnInt(i); i++;
						newChamp.tips = st.columnString(i); i++;
						newChamp.opponentTips = st.columnString(i); i++;
						newChamp.selectSoundPath = st.columnString(i); i++;
	
						SQLiteStatement skinsDB = connection
								.prepare("SELECT * FROM championskins WHERE championId = ?");
						skinsDB.bind(1, newChamp.ID);
						while (skinsDB.step()) {
							ChampionSkin theSkin = new ChampionSkin();
							i = 0;
							theSkin.ID = skinsDB.columnInt(i); i++;
							theSkin.isDefault = skinsDB.columnInt(i) == 1; i++;
							theSkin.order = skinsDB.columnInt(i); i++;
							theSkin.championID = skinsDB.columnInt(i); i++;
							theSkin.name = skinsDB.columnString(i); i++;
							theSkin.displayName = skinsDB.columnString(i); i++;
							theSkin.portraitPath = skinsDB.columnString(i); i++;
							theSkin.splashPath = skinsDB.columnString(i); i++;
							newChamp.skins.add(theSkin);
						}
						skinsDB.dispose();
	
						/*
						 * SQLiteStatement itemsDB = connection.prepare(
						 * "SELECT * FROM championitems WHERE championId = ?");
						 * itemsDB.bind(1, newChamp.ID); while (itemsDB.step()) {
						 * RiotRecommendedItem recItem = new RiotRecommendedItem();
						 * recItem.ID = itemsDB.columnInt(0); recItem.championID =
						 * newChamp.ID; recItem.itemID = itemsDB.columnInt(2);
						 * recItem.gameMode = itemsDB.columnString(3);
						 * newChamp.riotRecommendedItems.add(recItem); }
						 * itemsDB.dispose();
						 */
						champions.add(newChamp);
					}
					st.dispose();
					return null;
				}
			}).complete();
		}
	}

	public double getArmorBase() {
		return this.armorBase;
	}

	public double getArmorLevel() {
		return this.armorLevel;
	}

	public double getAttackBase() {
		return this.attackBase;
	}

	public double getAttackLevel() {
		return this.attackLevel;
	}

	public int getCriticalChanceBase() {
		return this.criticalChanceBase;
	}

	public int getCriticalChanceLevel() {
		return this.criticalChanceLevel;
	}

	public String getDanceVideoPath() {
		return this.danceVideoPath;
	}

	public String getDescription() {
		return this.description;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public double getEndDate() {
		return this.endDate;
	}

	public int getHealthBase() {
		return this.healthBase;
	}

	public double getHealthLevel() {
		return this.healthLevel;
	}

	public double getHealthRegenBase() {
		return this.healthRegenBase;
	}

	public double getHealthRegenLevel() {
		return this.healthRegenLevel;
	}

	public BufferedImage getIcon() throws Exception {
		return ImageIO.read(FileSystem.getRADSFile("assets/images/champions/"+this.iconPath));
	}

	public String getIconPath() {
		return this.iconPath;
	}

	public int getID() {
		return this.ID;
	}

	public int getMagicResistBase() {
		return this.magicResistBase;
	}

	public double getMagicResistLevel() {
		return this.magicResistLevel;
	}

	public int getManaBase() {
		return this.manaBase;
	}

	public int getManaLevel() {
		return this.manaLevel;
	}

	public double getManaRegenBase() {
		return this.manaRegenBase;
	}

	public double getManaRegenLevel() {
		return this.manaRegenLevel;
	}

	public int getMovementSpeed() {
		return this.movementSpeed;
	}

	public String getName() {
		return this.name;
	}

	public String getOpponentTips() {
		return this.opponentTips;
	}

	public String getPortraitPath() {
		return this.portraitPath;
	}

	public double getPurchaseDate() {
		return this.purchaseDate;
	}

	public String getQuote() {
		return this.quote;
	}

	public String getQuoteAuthor() {
		return this.quoteAuthor;
	}

	public int getRange() {
		return this.range;
	}

	public int getRatingAttack() {
		return this.ratingAttack;
	}

	public int getRatingDefense() {
		return this.ratingDefense;
	}

	public int getRatingDifficulty() {
		return this.ratingDifficulty;
	}

	public int getRatingMagic() {
		return this.ratingMagic;
	}

	public BufferedInputStream getSelectionAudio() throws Exception {
		return RADSAirClientWrapper.getBIS("/assets/sounds/en_US/champions/"
				+ this.name + ".mp3");
	}

	public String getSelectSoundPath() {
		return this.selectSoundPath;
	}

	public LinkedList<ChampionSkin> getSkins() {
		return this.skins;
	}

	/*
	 * public Vector<RiotRecommendedItem> getRiotRecommendedItems() { return
	 * riotRecommendedItems; }
	 */
	public String getSplashPath() {
		return this.splashPath;
	}

	public LinkedList<String> getTags() {
		return this.tags;
	}

	public String getTips() {
		return this.tips;
	}

	public String getTitle() {
		return this.title;
	}

	public int getWinCountRemaining() {
		return this.winCountRemaining;
	}

	public boolean isActive() {
		return this.active;
	}

	public boolean isBotEnabled() {
		return this.botEnabled;
	}

	public boolean isFreeToPlay() {
		return this.freeToPlay;
	}

	public boolean isFreeToPlayReward() {
		return this.freeToPlayReward;
	}

	public boolean isOwned() {
		return this.owned;
	}

	public boolean isRankedPlayEnabled() {
		return this.rankedPlayEnabled;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setBotEnabled(boolean botEnabled) {
		this.botEnabled = botEnabled;
	}

	public void setEndDate(double endDate) {
		this.endDate = endDate;
	}

	public void setFreeToPlay(boolean freeToPlay) {
		this.freeToPlay = freeToPlay;
	}

	public void setFreeToPlayReward(boolean freeToPlayReward) {
		this.freeToPlayReward = freeToPlayReward;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}

	public void setPurchaseDate(double purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public void setRankedPlayEnabled(boolean rankedPlayEnabled) {
		this.rankedPlayEnabled = rankedPlayEnabled;
	}

	public void setWinCountRemaining(int winCountRemaining) {
		this.winCountRemaining = winCountRemaining;
	}
}
