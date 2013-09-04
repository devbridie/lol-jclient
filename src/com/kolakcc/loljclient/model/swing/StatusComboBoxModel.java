package com.kolakcc.loljclient.model.swing;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import com.kolakcc.loljclient.model.Status;
import com.kolakcc.loljclient.util.StatusIcon;

public class StatusComboBoxModel implements ComboBoxModel<Status> {
	ArrayList<Status> data;
	Status selected;
	public StatusComboBoxModel() {
		data = new ArrayList<Status>();
		Status available = new Status("Available", StatusIcon.getAvailable());
		data.add(available);
		data.add(new Status("Away", StatusIcon.getAway()));
		selected = available;
	}
	@Override
	public int getSize() {
		return data.size();
	}

	@Override
	public Status getElementAt(int index) {
		return data.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selected = (Status) anItem;
	}

	@Override
	public Object getSelectedItem() {
		return selected;
	}

}

