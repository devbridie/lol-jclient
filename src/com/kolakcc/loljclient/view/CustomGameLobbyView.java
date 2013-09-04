package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import com.kolakcc.loljclient.model.CustomGamePlayer;
import com.kolakcc.loljclient.view.ui.renderer.CustomGameParticipantListItemRenderer;

public class CustomGameLobbyView extends KolaView {
	public JList<CustomGamePlayer> team1List;
	public JList<CustomGamePlayer> team2List;
	
	public JButton startGameButton;
	public JButton swapTeamButton;
	public JButton quitGameButton;
	public JButton addBotOnTeam1Button;
	public JButton addBotOnTeam2Button;
	
	public JPanel centerPanel;
	public JPanel bottomPanel;

	public CustomGameLobbyView() {
		super();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		centerPanel = new JPanel(new BorderLayout());
		JPanel team1Panel = new JPanel(new BorderLayout());
		this.team1List = new JList<CustomGamePlayer>();
		this.team1List.setCellRenderer(new CustomGameParticipantListItemRenderer());
		team1Panel.add(this.team1List, BorderLayout.CENTER);
		this.addBotOnTeam1Button = new JButton("Add bot");
		team1Panel.add(this.addBotOnTeam1Button,BorderLayout.SOUTH);
		
		JPanel team2Panel = new JPanel(new BorderLayout());
		this.team2List = new JList<CustomGamePlayer>();
		this.team2List.setCellRenderer(new CustomGameParticipantListItemRenderer());
		team2Panel.add(this.team2List, BorderLayout.CENTER);
		this.addBotOnTeam2Button = new JButton("Add bot");
		team2Panel.add(this.addBotOnTeam2Button,BorderLayout.SOUTH);
		centerPanel.add(team1Panel,BorderLayout.WEST);
		centerPanel.add(team2Panel,BorderLayout.EAST);
		this.add(centerPanel, BorderLayout.CENTER);
		
		bottomPanel = new JPanel(new GridLayout(3,1));
		this.startGameButton = new JButton("Start Game");
		bottomPanel.add(startGameButton);
		this.swapTeamButton = new JButton("Swap team");
		bottomPanel.add(this.swapTeamButton);
		this.quitGameButton = new JButton("Quit Game");
		bottomPanel.add(this.quitGameButton);
		this.add(bottomPanel,BorderLayout.SOUTH);
		this.setSize(600,400);
		this.setVisible(true);
	}
}