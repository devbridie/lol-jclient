package com.kolakcc.loljclient.model.swing;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.table.AbstractTableModel;

import com.kolakcc.loljclient.model.Division;
import com.kolakcc.loljclient.model.DivisionEntry;

public class DivisionEntryTableModel extends AbstractTableModel {
	ArrayList<DivisionEntry> data, filtered;
	String[] columnNames = { "Difference", "#", "LP", "Summoner name" };
	String filter;
	
	public DivisionEntryTableModel(Division d) {
		data = d.getEntries();
		Collections.sort(data);
		Collections.reverse(data);
		filtered = (ArrayList<DivisionEntry>) data.clone();
		filterByTier(d.getRequestorsRank());
	}
	
	public void filterByTier(String tier) {
		filter = tier;
		filtered.clear();
		for (DivisionEntry entry : data) {
			if (entry.getRank().equals(filter)) filtered.add(entry);
		}
		this.fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return filtered.size();
	}

	public String getColumnName(int col) {
		return this.columnNames[col];
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		DivisionEntry entryAtRow = filtered.get(rowIndex);
		switch (columnIndex) {
			case 0: return entryAtRow.getPreviousDayLeaguePosition() - rowIndex+1;
			case 1: return rowIndex+1;
			case 2:
				if (entryAtRow.getLeaguePoints() == 100) {
					// Find out why getSeries is sometimes null when you have no series
					int target = (entryAtRow.getSeries() == null) ? 0 : entryAtRow.getSeries().getTarget() + entryAtRow.getSeries().getTarget()-1;
					int wins = (entryAtRow.getSeries() == null) ? 0 : entryAtRow.getSeries().getLosses();
					int losses = (entryAtRow.getSeries() == null) ? 0 : entryAtRow.getSeries().getWins();
					return String.format("%d: %dW %dL", target, wins, losses);
				}
				return entryAtRow.getLeaguePoints();
			case 3: return entryAtRow.getPlayerOrTeamName();
		}
		return null;
	}
	
	public DivisionEntry getEntryAt(int rowIndex) {
		return filtered.get(rowIndex);
	}
	
	public void clear() {
		data.clear();
		filtered.clear();
	}
}
