package com.kolakcc.loljclient.view.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.kolakcc.loljclient.model.Division;
import com.kolakcc.loljclient.model.swing.DivisionEntryTableModel;
import com.kolakcc.loljclient.util.FontUtils;

public class DivisionPanel extends JPanel {
	DivisionEntryTableModel tableModel;
	JComboBox<String> tierList;
	
	public DivisionPanel(Division division, MouseListener ml, ActionListener al) {
		super(new BorderLayout());
		
		JPanel topPanel = new JPanel(new BorderLayout());
		
		char[] tierArray = division.getTier().toLowerCase().toCharArray();
		tierArray[0] = Character.toUpperCase(tierArray[0]);
		
		JLabel nameLabel = new JLabel(String.format("(%s) %s", new String(tierArray), division.getName()));
		nameLabel.setFont(FontUtils.emSize(nameLabel.getFont(), 2));
		topPanel.add(nameLabel, BorderLayout.WEST);
		
		tierList = new JComboBox<String>(new String[] { "I", "II", "III", "IV", "V" });
		tierList.setPrototypeDisplayValue("XX");
		tierList.setSelectedItem(division.getTier());
		tierList.addActionListener(al);
		topPanel.add(tierList, BorderLayout.EAST);
		
		this.add(topPanel, BorderLayout.NORTH);
		
		JTable entryTable = new JTable();
		entryTable.addMouseListener(ml);
		tableModel = new DivisionEntryTableModel(division);
		entryTable.setModel(tableModel);
		this.add(new VerticalJScrollPane(entryTable),BorderLayout.CENTER);
	}

	public DivisionEntryTableModel getTableModel() {
		return tableModel;
	}

	public JComboBox<String> getTierList() {
		return tierList;
	}
}
