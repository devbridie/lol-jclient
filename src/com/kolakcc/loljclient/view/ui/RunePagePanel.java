package com.kolakcc.loljclient.view.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import com.camick.TableColumnAdjuster;
import com.kolakcc.loljclient.model.RunePage;
import com.kolakcc.loljclient.model.swing.RunePageTableModel;

public class RunePagePanel extends JPanel {
	public RunePagePanel(RunePage runePage) {
		super(new BorderLayout());

		JTable runesTable = new JTable();
		RunePageTableModel model = new RunePageTableModel(runePage.getSlots());
		runesTable.setModel(model);
		
		TableColumnAdjuster columnAdjuster = new TableColumnAdjuster(runesTable);
		int slotLength = Math.max(columnAdjuster.getColumnDataWidth(0), columnAdjuster.getColumnHeaderWidth(0));
		runesTable.getColumnModel().getColumn(0).setMaxWidth(slotLength);
		int nameLength = Math.max(columnAdjuster.getColumnDataWidth(1), columnAdjuster.getColumnHeaderWidth(1));
		runesTable.getColumnModel().getColumn(1).setMinWidth(nameLength);
		this.add(new VerticalJScrollPane(runesTable), BorderLayout.CENTER);
	}
}
