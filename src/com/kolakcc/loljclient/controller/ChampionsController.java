package com.kolakcc.loljclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.kolakcc.loljclient.model.swing.FilterComboBoxModel;
import com.kolakcc.loljclient.model.swing.IconSizeComboBoxModel;
import com.kolakcc.loljclient.view.ChampionsView;
import com.kolakcc.loljclient.view.ui.ChampionsPanel;

public class ChampionsController extends KolaController implements
		ActionListener {
	protected ChampionsView view;
	protected FilterComboBoxModel filterModel;
	protected IconSizeComboBoxModel sizeModel;

	public ChampionsController() {
		this.filterModel = new FilterComboBoxModel();
		this.sizeModel = new IconSizeComboBoxModel();
		this.view = new ChampionsView(new ChampionsPanel(sizeModel.getSelectedItem(),filterModel.getSelectedItem()));
		this.setView(this.view);
		
		this.view.filterComboBox.setModel(this.filterModel);
		this.view.filterComboBox.addActionListener(this);
		
		this.view.sizeComboBox.setModel(this.sizeModel);
		this.view.sizeComboBox.addActionListener(this);
		this.view.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		this.view.championsPanel.size = this.sizeModel.getSelectedItem().number;
		this.view.championsPanel.filter = this.filterModel.getSelectedItem();
		this.view.championsPanel.resizeAndRefilter();
	}
}
