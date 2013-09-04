package com.kolakcc.loljclient.model.swing;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import com.kolakcc.loljclient.model.SummonerSpell;

public class SpellComboBoxModel implements ComboBoxModel<SummonerSpell> {
	SummonerSpell selected;
	public SpellComboBoxModel(SummonerSpell defaultSpell) {
		selected = defaultSpell;
	}
	@Override
	public int getSize() {
		return SummonerSpell.spells.size();
	}

	@Override
	public SummonerSpell getElementAt(int index) {
		return SummonerSpell.spells.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selected = (SummonerSpell) anItem;
	}

	//@Override
	public SummonerSpell getSelectedItem() {
		return selected;
	}

}
