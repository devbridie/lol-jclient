package com.kolakcc.loljclient.model.swing;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class FilterComboBoxModel implements ComboBoxModel<String> {
	protected String[] sortChoices = { "All", "Playable", "Bought",
			"Not bought", "Free this week" };
	protected String selected = "All";

	@Override
	public void addListDataListener(ListDataListener l) {
	}

	@Override
	public String getElementAt(int index) {
		return this.sortChoices[index];
	}

	@Override
	public String getSelectedItem() {
		return this.selected;
	}

	@Override
	public int getSize() {
		return this.sortChoices.length;
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.selected = (String) anItem;
	}

}
