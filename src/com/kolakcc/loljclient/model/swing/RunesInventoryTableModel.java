package com.kolakcc.loljclient.model.swing;


import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import com.kolakcc.loljclient.model.InventoryRune;


public class RunesInventoryTableModel extends AbstractTableModel {
	ArrayList<InventoryRune> runes = new ArrayList<InventoryRune>();
	private String[] columnNames = { "#", "Name" };

	public RunesInventoryTableModel() {
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

	public InventoryRune getItemAt(int row) {
		return this.runes.get(row);
	}

	public int getRowCount() {
		return this.runes.size();
	}

	public Object getValueAt(int row, int col) {
		InventoryRune selectedObject = this.runes.get(row);
		switch (columnNames[col]) {
			case "#": return selectedObject.getAmount();
			case "Name": return selectedObject.getRune().getName();
		}
		return "???";
	}
	public void clear() {
		this.runes.clear();
		this.fireTableDataChanged();
	}
	public void add(InventoryRune rune) {
		this.runes.add(rune);
		this.fireTableDataChanged();
	}
}