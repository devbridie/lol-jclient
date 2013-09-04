package com.kolakcc.loljclient.model.swing;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import com.kolakcc.loljclient.model.IconSize;

public class IconSizeComboBoxModel implements ComboBoxModel<IconSize> {
	protected ArrayList<IconSize> sizeChoices;
	protected IconSize selected;
	public static IconSize SMALLER = new IconSize("Smaller", 40);
	public static IconSize SMALL = new IconSize("Small", 60);
	public static IconSize MEDIUM = new IconSize("Medium", 80);
	public static IconSize LARGE = new IconSize("Large", 100);
	public static IconSize LARGER = new IconSize("Larger", 120);

	public IconSizeComboBoxModel() {
		this.sizeChoices = new ArrayList<IconSize>();
		this.sizeChoices.add(SMALLER);
		this.sizeChoices.add(SMALL);
		this.sizeChoices.add(MEDIUM);
		this.sizeChoices.add(LARGE);
		this.sizeChoices.add(LARGE);
		this.selected = MEDIUM;
	}

	@Override
	public void addListDataListener(ListDataListener l) {
	}

	@Override
	public IconSize getElementAt(int index) {
		return this.sizeChoices.get(index);
	}

	@Override
	public IconSize getSelectedItem() {
		return this.selected;
	}

	@Override
	public int getSize() {
		return this.sizeChoices.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.selected = (IconSize) anItem;
	}

}
