package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainView extends KolaView {
	public JLabel IPBalance;
	public JLabel RPBalance;

	public JButton customGameButton;
	public JButton profileButton;
	public JButton championsButton;
	public JButton storeButton, launch;
	
	public JMenuItem settingsItem;
	public JMenuItem aboutItem;

	public MainView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setTitle("loljclient");
		this.setSize(800, 600);

		JPanel topPanel = new JPanel();

		topPanel.add(new JLabel("IP:"));
		this.IPBalance = new JLabel("Loading...");
		topPanel.add(this.IPBalance);

		topPanel.add(new JLabel("RP:"));
		this.RPBalance = new JLabel("Loading...");
		topPanel.add(this.RPBalance);

		this.customGameButton = new JButton("Custom Games");
		topPanel.add(this.customGameButton);

		this.profileButton = new JButton("Profile");
		topPanel.add(this.profileButton);

		this.championsButton = new JButton("Champions");
		topPanel.add(this.championsButton);
		
		this.storeButton = new JButton("Store");
		topPanel.add(this.storeButton);
		
		this.launch = new JButton("Launch LoL");
		topPanel.add(this.launch);

		this.add(topPanel, BorderLayout.NORTH);

		JMenuBar menuBar = new JMenuBar();

		JMenu toolMenu = new JMenu("Tools");
		this.settingsItem = new JMenuItem("Settings");
		toolMenu.add(this.settingsItem);

		JMenu helpMenu = new JMenu("Help");
		this.aboutItem = new JMenuItem("About");
		helpMenu.add(this.aboutItem);

		menuBar.add(toolMenu);
		menuBar.add(helpMenu);
		//this.setJMenuBar(menuBar);

		this.setVisible(true);
	}
}
