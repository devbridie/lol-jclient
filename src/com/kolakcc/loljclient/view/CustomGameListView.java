package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CustomGameListView extends KolaView {
	public JTable gamesListTable;
	public JButton refreshButton;
	public JButton filterButton;
	
	public CustomGameListView() {
		super();
		this.setTitle("Custom Games List");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		refreshButton = new JButton("Loading...");
		this.add(refreshButton,BorderLayout.NORTH);
		
		gamesListTable = new JTable();
		JScrollPane scroller = new JScrollPane(this.gamesListTable);
		this.add(scroller, BorderLayout.CENTER);
		
		filterButton = new JButton("Filters");
		this.add(filterButton, BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
	}
}
