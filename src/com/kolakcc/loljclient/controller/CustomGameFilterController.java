package com.kolakcc.loljclient.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.kolakcc.loljclient.view.CustomGameFilterView;
import com.kolakcc.loljclient.model.swing.CustomGameTableModel;

public class CustomGameFilterController extends KolaController implements DocumentListener, ItemListener {

	CustomGameFilterView view;
	CustomGameTableModel model;
	public CustomGameFilterController(CustomGameTableModel model) {
		this.model = model;
		this.view = new CustomGameFilterView();
		this.view.filterField.getDocument().addDocumentListener(this);
		this.view.addCheckboxListeners(this);
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		this.model.filterGameName(this.view.filterField.getText());
		this.model.reapplyFilters();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		this.model.filterGameName(this.view.filterField.getText());
		this.model.reapplyFilters();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		model.showGameMaps.clear();
		if (view.pgBox.isSelected()) model.showGameMaps.add(7);
		if (view.srBox.isSelected()) model.showGameMaps.add(1);
		if (view.ttBox.isSelected()) model.showGameMaps.add(10);
		if (view.doBox.isSelected()) model.showGameMaps.add(8);
		if (view.privateBox.isSelected()) model.showPrivateGames = true;
		else model.showPrivateGames = false;
		model.reapplyFilters();
	}
}
