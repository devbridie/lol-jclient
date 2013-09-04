package com.kolakcc.loljclient.model.swing;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.model.CustomGameListItem;
import com.kolakcc.loljclient.util.TOUtils;

public class CustomGameTableModel extends AbstractTableModel {
	private String[] columnNames = { "Game Name", "Creator" };
	private ArrayList<CustomGameListItem> data;
	private ArrayList<CustomGameListItem> filtered;
	private String gameNameFilter;
	public boolean showPrivateGames;
	
	public ArrayList<Integer> showGameMaps;

	public CustomGameTableModel(TypedObject customGamesList) {
		data = new ArrayList<CustomGameListItem>();
		filtered = new ArrayList<CustomGameListItem>();
		
		gameNameFilter = "";
		showGameMaps = new ArrayList<Integer>();
		showGameMaps.add(7);
		showGameMaps.add(1);
		showGameMaps.add(8);
		showGameMaps.add(10);
		showPrivateGames = true;
		add(customGamesList);
	}

	public int getColumnCount() {
		return this.columnNames.length;
	}

	public String getColumnName(int col) {
		return this.columnNames[col];
	}

	public CustomGameListItem getGameAt(int row) {
		return this.filtered.get(row);
	}

	public int getRowCount() {
		return this.filtered.size();
	}

	public Object getValueAt(int row, int col) {
		CustomGameListItem selectedObject = this.filtered.get(row);
		if (col == 0) {
			return selectedObject.getName();
		}
		if (col == 1) {
			return selectedObject.getOwner().getSummonerName();
		}
		return "???";
	}
	public void removeAll() {
		data.clear();
		this.fireTableDataChanged();
	}
	public void reapplyFilters() {
		filtered.clear();
		this.filterGameName(gameNameFilter);
		for (CustomGameListItem item : data) {
			if (showItem(item)) {
				filtered.add(item);
			}
		}
		this.fireTableDataChanged();
	}
	private boolean showItem(CustomGameListItem item) {
		boolean show = true;
		if (!item.getName().toLowerCase().contains(gameNameFilter.toLowerCase())) show = false;
		if (!showGameMaps.contains(item.getGameMapID())) show = false;
		if ((!this.showPrivateGames) && (item.isPrivateGame() == true)) show = false;
		return show;
	}
	public void filterGameName(String newFilter) {
		gameNameFilter = newFilter;
	}
	public void clear() {
		data.clear();
		filtered.clear();
		this.fireTableDataChanged();
	}
	public void add(TypedObject customGamesList) {
		for (TypedObject game : TOUtils.ArrayToTOArray(customGamesList.getArray("array"))) {
			this.data.add(new CustomGameListItem(game));
		}
		filtered = (ArrayList<CustomGameListItem>) data.clone();
		this.fireTableDataChanged();
		this.reapplyFilters();
	}
}
