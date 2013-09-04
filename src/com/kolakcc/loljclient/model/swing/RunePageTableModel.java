package com.kolakcc.loljclient.model.swing;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.table.AbstractTableModel;

import com.kolakcc.loljclient.model.RuneSlotEntry;

public class RunePageTableModel extends AbstractTableModel {
	ArrayList<RuneSlotEntry> runes = new ArrayList<RuneSlotEntry>();
	private String[] columnNames = { "Slot #", "Name" };

	public RunePageTableModel(ArrayList<RuneSlotEntry> data) {
		Collections.sort(data);
		for (RuneSlotEntry entry : data) {
			this.add(entry);
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
		return String.class;
	}

	public int getColumnCount() {
		return this.columnNames.length;
	}

	public String getColumnName(int col) {
		return this.columnNames[col];
	}

	public RuneSlotEntry getItemAt(int row) {
		return this.runes.get(row);
	}

	public int getRowCount() {
		return this.runes.size();
	}

	public Object getValueAt(int row, int col) {
		RuneSlotEntry selectedObject = this.runes.get(row);
		switch (columnNames[col]) {
			case "Slot #": return selectedObject.getSlotID();
			case "Name": return selectedObject.getRune().getName();
		}
		return "???";
	}
	public void clear() {
		this.runes.clear();
		this.fireTableDataChanged();
	}
	public void add(RuneSlotEntry rune) {
		this.runes.add(rune);
		this.fireTableDataChanged();
	}
}