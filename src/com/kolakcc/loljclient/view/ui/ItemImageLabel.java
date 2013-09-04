package com.kolakcc.loljclient.view.ui;

import javax.swing.ImageIcon;

import com.kolakcc.loljclient.model.Item;
import com.kolakcc.loljclient.model.RiotRecommendedItem;
import com.kolakcc.loljclient.util.FontUtils;

public class ItemImageLabel extends javax.swing.JLabel {
	protected Item item;
	protected ImageIcon icon;

	public ItemImageLabel() {
		super();
		this.setFont(FontUtils.emSize(this.getFont(), 0.7));
		this.setText("Loading..");
	}

	public ItemImageLabel(Item item) {
		this();
		setItem(item);
	}
	
	public void setItem(Item item) {
		setText("");
		this.item = item;
		this.setOpaque(true);
		try {
			this.icon = new ImageIcon(this.item.getIcon());
			this.setIcon(this.icon);
		} catch (Exception e) {
			System.out.println("Could not find image for "
					+ this.item.getName());
			e.printStackTrace();
			this.setText(this.item.getName());
		}
		this.repaint();
		this.revalidate();
	}

	public ItemImageLabel(RiotRecommendedItem recommendedItem) {
		this(Item.getItem(recommendedItem));
	}
}
