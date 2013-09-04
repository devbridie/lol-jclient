package com.kolakcc.loljclient.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteJob;
import com.almworks.sqlite4java.SQLiteQueue;
import com.almworks.sqlite4java.SQLiteStatement;
import com.kolakcc.loljclient.util.FileSystem;

public class Item {
	public static Item getItem(RiotRecommendedItem recItem) {
		return getItemFromID(recItem.ID);
	}

	protected int ID;
	protected String name;
	protected String description;
	protected String iconPath;
	protected int price;
	protected int flatHPPoolMod;
	protected int flatMPPoolMod;
	protected int percentHPPoolMod;
	protected int percentMPPoolMod;
	protected int flatHPRegenMod;
	protected int percentHPRegenMod;
	protected int flatMPRegenMod;
	protected int percentMPRegenMod;
	protected int flatArmorMod;
	protected int percentArmorMod;
	protected int flatAttackDamageMod;
	protected int percentAttackDamageMod;
	protected int flatAbilityPowerMod;
	protected int percentAbilityPowerMod;
	protected int flatMovementSpeedMod;
	protected int percentMovementSpeedMod;
	protected int flatAttackSpeedMod;
	protected int percentAttackSpeedMod;
	protected int flatDodgeMod;
	protected int percentDodgeMod;
	protected int flatCritChanceMod;
	protected int percentCritChanceMod;
	protected int flatCritDamageMod;
	protected int percentCritDamageMod;
	protected int flatMagicResistMod;
	protected int percentMagicResistMod;
	protected int flatEXPBonus;
	protected int percentEXPBonus;

	protected int epicness;

	protected boolean legacy;

	protected RiotRecommendedItem fromRiotRecommendedItem;
	private static LinkedList<Item> items;

	public static Item getItemFromID(int ID) {
		for (Item i : items) {
			if (i.ID == ID) {
				return i;
			}
		}
		new Exception("Item #" + ID + " not found.").printStackTrace();
		return null;
	}

	public static void initializeItems() {
		if (items != null) {
			new Exception(
					"Tried to initialize already initalized champion list")
					.printStackTrace();
		}

		SQLiteQueue queue = GameStatsDBWrapper.getQueue();
		items = new LinkedList<Item>();
		queue.execute(new SQLiteJob<Object>() {
			protected Object job(SQLiteConnection connection) throws Throwable {
				SQLiteStatement st = connection.prepare("SELECT * FROM items");
				while (st.step()) {
					Item newItem = new Item();
					int i = 0;
					newItem.ID = st.columnInt(i);
					i++;
					newItem.name = st.columnString(i);
					i++;
					newItem.description = st.columnString(i);
					i++;
					newItem.iconPath = st.columnString(i);
					i++;
					newItem.price = st.columnInt(i);
					i++;
					newItem.flatHPPoolMod = st.columnInt(i);
					i++;
					newItem.flatMPPoolMod = st.columnInt(i);
					i++;
					newItem.percentHPPoolMod = st.columnInt(i);
					i++;
					newItem.percentMPPoolMod = st.columnInt(i);
					i++;
					newItem.flatHPRegenMod = st.columnInt(i);
					i++;
					newItem.percentHPRegenMod = st.columnInt(i);
					i++;
					newItem.flatMPRegenMod = st.columnInt(i);
					i++;
					newItem.percentMPRegenMod = st.columnInt(i);
					i++;
					newItem.flatArmorMod = st.columnInt(i);
					i++;
					newItem.percentArmorMod = st.columnInt(i);
					i++;
					newItem.flatAttackDamageMod = st.columnInt(i);
					i++;
					newItem.percentAttackDamageMod = st.columnInt(i);
					i++;
					newItem.flatAbilityPowerMod = st.columnInt(i);
					i++;
					newItem.percentAbilityPowerMod = st.columnInt(i);
					i++;
					newItem.flatMovementSpeedMod = st.columnInt(i);
					i++;
					newItem.percentMovementSpeedMod = st.columnInt(i);
					i++;
					newItem.flatAttackSpeedMod = st.columnInt(i);
					i++;
					newItem.percentAttackSpeedMod = st.columnInt(i);
					i++;
					newItem.flatDodgeMod = st.columnInt(i);
					i++;
					newItem.percentDodgeMod = st.columnInt(i);
					i++;
					newItem.flatCritChanceMod = st.columnInt(i);
					i++;
					newItem.percentCritChanceMod = st.columnInt(i);
					i++;
					newItem.flatCritDamageMod = st.columnInt(i);
					i++;
					newItem.percentCritDamageMod = st.columnInt(i);
					i++;
					newItem.flatMagicResistMod = st.columnInt(i);
					i++;
					newItem.percentMagicResistMod = st.columnInt(i);
					i++;
					newItem.flatEXPBonus = st.columnInt(i);
					i++;
					newItem.percentEXPBonus = st.columnInt(i);
					i++;
					newItem.epicness = st.columnInt(i);
					i++;
					items.add(newItem);
				}
				st.dispose();

				// TODO: add all legacy items
				items.add(new Item(0, "Empty", "EmptyIcon.png"));
				items.add(new Item(1005, "Meki Pendant",
						"3013_Disruption_Rod.png"));
				items.add(new Item(3178, "Ionic Spark", "3178_IonicSpark.png"));
				items.add(new Item(3132, "Heart of Gold",
						"3051_Renewal_Tunic.png"));
				items.add(new Item(3040, "Seraph's Embrace",
						"3040_Seraphs_Embrace.png"));

				return null;
			}
		}).complete();
	}

	private Item() {
	}

	public Item(int ID, String name, String iconPath) {
		this.ID = ID;
		this.name = name;
		this.iconPath = iconPath;
		this.legacy = true;
	}

	public String getDescription() {
		return this.description;
	}

	public int getEpicness() {
		return this.epicness;
	}

	public int getFlatAbilityPowerMod() {
		return this.flatAbilityPowerMod;
	}

	public int getFlatArmorMod() {
		return this.flatArmorMod;
	}

	public int getFlatAttackDamageMod() {
		return this.flatAttackDamageMod;
	}

	public int getFlatAttackSpeedMod() {
		return this.flatAttackSpeedMod;
	}

	public int getFlatCritChanceMod() {
		return this.flatCritChanceMod;
	}

	public int getFlatCritDamageMod() {
		return this.flatCritDamageMod;
	}

	public int getFlatDodgeMod() {
		return this.flatDodgeMod;
	}

	public int getFlatEXPBonus() {
		return this.flatEXPBonus;
	}

	public int getFlatHPPoolMod() {
		return this.flatHPPoolMod;
	}

	public int getFlatHPRegenMod() {
		return this.flatHPRegenMod;
	}

	public int getFlatMagicResistMod() {
		return this.flatMagicResistMod;
	}

	public int getFlatMovementSpeedMod() {
		return this.flatMovementSpeedMod;
	}

	public int getFlatMPPoolMod() {
		return this.flatMPPoolMod;
	}

	public int getFlatMPRegenMod() {
		return this.flatMPRegenMod;
	}

	public RiotRecommendedItem getFromRiotRecommendedItem() {
		return this.fromRiotRecommendedItem;
	}

	public BufferedImage getIcon() throws Exception {
		String relativeFilePath = this.getIconPath().replace('/', File.separatorChar);
		// some files are not in the same captials as in riots database
		// there also seems to be something wrong with - and _, f.ex 073_Zettas_Mana-Stick should be 073_Zettas_Mana_Stick
		File f = FileSystem.getFile("app://img/items/"+relativeFilePath);
		if (!f.isFile()) {
			String id = relativeFilePath.split("_")[0];
			for (File compare : f.getParentFile().listFiles()) {
				if (compare.getName().startsWith(id + "_")) {
					f = compare;
					break;
				}
			}
		}

		if (!f.canRead()) {
			throw new Exception(String.format("Not found: %s",f.getAbsolutePath()));
		}
		
		if (!f.canRead()) {
			throw new Exception(String.format("Unreadable: %s",f.getAbsolutePath()));
		}
		if ((f.isFile()) && (f.canRead())) {
			return ImageIO.read(f);
		}

		throw new Exception(String.format("Unknown error opening %s",f.getAbsolutePath()));
	}

	public String getIconPath() {
		return this.iconPath;
	}

	public int getID() {
		return this.ID;
	}

	public String getName() {
		return this.name;
	}

	public int getPercentAbilityPowerMod() {
		return this.percentAbilityPowerMod;
	}

	public int getPercentArmorMod() {
		return this.percentArmorMod;
	}

	public int getPercentAttackDamageMod() {
		return this.percentAttackDamageMod;
	}

	public int getPercentAttackSpeedMod() {
		return this.percentAttackSpeedMod;
	}

	public int getPercentCritChanceMod() {
		return this.percentCritChanceMod;
	}

	public int getPercentCritDamageMod() {
		return this.percentCritDamageMod;
	}

	public int getPercentDodgeMod() {
		return this.percentDodgeMod;
	}

	public int getPercentEXPBonus() {
		return this.percentEXPBonus;
	}

	public int getPercentHPPoolMod() {
		return this.percentHPPoolMod;
	}

	public int getPercentHPRegenMod() {
		return this.percentHPRegenMod;
	}

	public int getPercentMagicResistMod() {
		return this.percentMagicResistMod;
	}

	public int getPercentMovementSpeedMod() {
		return this.percentMovementSpeedMod;
	}

	public int getPercentMPPoolMod() {
		return this.percentMPPoolMod;
	}

	public int getPercentMPRegenMod() {
		return this.percentMPRegenMod;
	}

	public int getPrice() {
		return this.price;
	}

}