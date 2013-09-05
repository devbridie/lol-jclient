package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.kolakcc.loljclient.util.LocaleMessages;
import com.kolakcc.loljclient.view.ui.LocalizedJButton;
import com.kolakcc.loljclient.view.ui.LocalizedJLabel;

public class MainView extends KolaView {
	public LocalizedJLabel IPBalance, RPBalance;

	public LocalizedJButton customGameButton, profileButton, championsButton, storeButton, launch;
	
	static LocaleMessages mainViewMessages = new LocaleMessages("mainViewBundle");

	public MainView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setTitle("lol-jclient");
		this.setSize(800, 600);

		JPanel topPanel = new JPanel();

		topPanel.add(new LocalizedJLabel(mainViewMessages, "ip"));
		this.IPBalance = new LocalizedJLabel();
		topPanel.add(this.IPBalance);

		topPanel.add(new LocalizedJLabel(mainViewMessages, "rp"));
		this.RPBalance = new LocalizedJLabel();
		topPanel.add(this.RPBalance);

		this.customGameButton = new LocalizedJButton(mainViewMessages, "custom games");
		topPanel.add(this.customGameButton);

		this.profileButton = new LocalizedJButton(mainViewMessages, "profile");
		topPanel.add(this.profileButton);

		this.championsButton = new LocalizedJButton(mainViewMessages, "champions");
		topPanel.add(this.championsButton);
		
		this.storeButton = new LocalizedJButton(mainViewMessages, "store");
		topPanel.add(this.storeButton);
		
		this.launch = new LocalizedJButton(mainViewMessages,"launch lol");
		topPanel.add(this.launch);

		this.add(topPanel, BorderLayout.NORTH);

		this.setVisible(true);
	}
}
