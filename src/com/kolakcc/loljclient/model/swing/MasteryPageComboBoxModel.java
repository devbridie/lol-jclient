package com.kolakcc.loljclient.model.swing;

import java.util.Iterator;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import com.kolakcc.loljclient.model.LoggedInSummoner;
import com.kolakcc.loljclient.model.MasteryPage;

public class MasteryPageComboBoxModel implements ComboBoxModel<MasteryPage>, Iterable<MasteryPage> {
	private MasteryPage selected;
	public MasteryPageComboBoxModel(MasteryPage defaultRunePage) {
		selected = defaultRunePage;
	}
	
	@Override
	public int getSize() {
		return LoggedInSummoner.summonerData.getMasteryBook().size();
	}

	@Override
	public MasteryPage getElementAt(int index) {
		return LoggedInSummoner.summonerData.getMasteryBook().getPage(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
	}

	@Override
	public void setSelectedItem(Object anItem) {
		//TODO: send server set
		selected = (MasteryPage) anItem;
	}

	@Override
	public MasteryPage getSelectedItem() {
		return selected;
	}

	@Override
	public Iterator<MasteryPage> iterator() {
		return LoggedInSummoner.summonerData.getMasteryBook().iterator();
	}
}
