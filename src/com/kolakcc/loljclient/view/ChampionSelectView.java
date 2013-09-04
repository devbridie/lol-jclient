package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.kolakcc.loljclient.model.CustomGamePlayer;
import com.kolakcc.loljclient.model.MasteryPage;
import com.kolakcc.loljclient.model.RunePage;
import com.kolakcc.loljclient.model.SummonerSpell;
import com.kolakcc.loljclient.model.swing.IconSizeComboBoxModel;
import com.kolakcc.loljclient.view.ui.ChampionsPanel;
import com.kolakcc.loljclient.view.ui.VerticalJScrollPane;
import com.kolakcc.loljclient.view.ui.renderer.CustomGameParticipantListItemRenderer;

public class ChampionSelectView extends KolaView {
	JTextArea teamChatArea;
	JTextField chatField;
	public ChampionsPanel championsPanel;
	public JList<CustomGamePlayer> team1List;
	public JList<CustomGamePlayer> team2List;
	public JComboBox<SummonerSpell> summonerSpell1ComboBox;
	public JComboBox<SummonerSpell> summonerSpell2ComboBox;
	public JComboBox<RunePage> runePageList;
	public JComboBox<MasteryPage> masteryPageList;
	
	public JButton lockInButton;
	JLabel timerLabel;
	
	public ChampionSelectView() {
		super();
		this.setTitle("Champions");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		timerLabel = new JLabel("Countdown");
		this.add(timerLabel,BorderLayout.NORTH);
		
		JPanel team1Panel = new JPanel(new BorderLayout());
		//TODO: find out what puts you on what team
		team1Panel.add(new JLabel("Team 1 (blue side)"),BorderLayout.NORTH);
		team1List = new JList<CustomGamePlayer>();
		team1List.setCellRenderer(new CustomGameParticipantListItemRenderer());
		team1Panel.add(team1List, BorderLayout.CENTER);
		this.add(team1Panel,BorderLayout.WEST);
		
		JPanel team2Panel = new JPanel(new BorderLayout());
		team2Panel.add(new JLabel("Team 2 (purple side)"),BorderLayout.NORTH);
		team2List = new JList<CustomGamePlayer>();
		team2List.setCellRenderer(new CustomGameParticipantListItemRenderer());
		team2Panel.add(team2List,BorderLayout.CENTER);
		this.add(team2Panel,BorderLayout.EAST);
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		
		championsPanel = new ChampionsPanel(IconSizeComboBoxModel.MEDIUM,"Playable");
		centerPanel.add(new VerticalJScrollPane(championsPanel), BorderLayout.CENTER);
		
		JPanel customizePanel = new JPanel(new BorderLayout());
		
		JPanel runesAndMasteries = new JPanel(new GridLayout(2,1));
		runePageList = new JComboBox<RunePage>();
		runesAndMasteries.add(runePageList);
		
		masteryPageList = new JComboBox<MasteryPage>();
		runesAndMasteries.add(masteryPageList);
		
		customizePanel.add(runesAndMasteries, BorderLayout.CENTER);
		
		JPanel spellsPanel = new JPanel(new GridLayout(2,1));
		summonerSpell1ComboBox = new JComboBox<SummonerSpell>();
		spellsPanel.add(summonerSpell1ComboBox);
		summonerSpell2ComboBox = new JComboBox<SummonerSpell>();
		spellsPanel.add(summonerSpell2ComboBox);
		customizePanel.add(spellsPanel, BorderLayout.EAST);
		
		lockInButton = new JButton("Lock in");
		customizePanel.add(lockInButton,BorderLayout.SOUTH);
		
		centerPanel.add(customizePanel,BorderLayout.SOUTH);
		this.add(centerPanel,BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		teamChatArea = new JTextArea();
		bottomPanel.add(teamChatArea, BorderLayout.CENTER);
		chatField = new JTextField();
		bottomPanel.add(chatField,BorderLayout.SOUTH);
		this.add(bottomPanel,BorderLayout.SOUTH);
		this.setSize(500,500);
		this.setVisible(true);
	}
	public void addActionListeners(ActionListener l) {
		summonerSpell1ComboBox.addActionListener(l);
		summonerSpell2ComboBox.addActionListener(l);
		runePageList.addActionListener(l);
		masteryPageList.addActionListener(l);
	}
	public void setTimer(int number) {
		timerLabel.setText(Integer.toString(number));
	}
}

