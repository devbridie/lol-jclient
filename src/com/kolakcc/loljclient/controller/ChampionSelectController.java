package com.kolakcc.loljclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.Timer;

import com.gvaneyck.rtmp.RTMPCallback;
import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.StartupClass;
import com.kolakcc.loljclient.model.LoggedInSummoner;
import com.kolakcc.loljclient.model.MasteryPage;
import com.kolakcc.loljclient.model.RunePage;
import com.kolakcc.loljclient.model.swing.MasteryPageComboBoxModel;
import com.kolakcc.loljclient.model.swing.RunePageComboBoxModel;
import com.kolakcc.loljclient.model.swing.SpellComboBoxModel;
import com.kolakcc.loljclient.model.swing.TeamListModel;
import com.kolakcc.loljclient.view.ChampionSelectView;
import com.kolakcc.loljclient.view.ui.ChampionBox;

public class ChampionSelectController extends KolaController implements MouseListener, ActionListener {
	ChampionSelectView view;
	SpellComboBoxModel spell1Model;
	SpellComboBoxModel spell2Model;
	RunePageComboBoxModel runePageModel;
	MasteryPageComboBoxModel masteryPageModel;
	
	int countFrom;
	int count;
	Timer currentTimer;
	
	public ChampionSelectController(TeamListModel team1, TeamListModel team2) {
		this.view = new ChampionSelectView();
		this.view.team1List.setModel(team1);
		this.view.team2List.setModel(team2);
		this.view.lockInButton.addActionListener(this);
		this.view.championsPanel.addChampionMouseListener(this);
		this.view.addActionListeners(this);
		
		spell1Model = new SpellComboBoxModel(LoggedInSummoner.summonerData.getClassicDefaultSpell1());
		this.view.summonerSpell1ComboBox.setModel(spell1Model);
		
		spell2Model = new SpellComboBoxModel(LoggedInSummoner.summonerData.getClassicDefaultSpell2());
		this.view.summonerSpell2ComboBox.setModel(spell2Model);
		
		RunePage currentRunePage = LoggedInSummoner.summonerData.getRunePages().get(0);
		for (RunePage runePage : LoggedInSummoner.summonerData.getRunePages()) {
			if (runePage.isCurrent()) { 
				currentRunePage = runePage;
				break;
			}
		}
		runePageModel = new RunePageComboBoxModel(currentRunePage);
		this.view.runePageList.setModel(runePageModel);
		
		MasteryPage currentMasteryPage = LoggedInSummoner.summonerData.getMasteryBook().getPage(0);
		for (MasteryPage masteryPage : LoggedInSummoner.summonerData.getMasteryBook()) {
			if (masteryPage.isCurrent()) { 
				currentMasteryPage = masteryPage;
				break;
			}
		}
		masteryPageModel = new MasteryPageComboBoxModel(currentMasteryPage);
		this.view.masteryPageList.setModel(masteryPageModel);
		countDown(90);
	}
	public void countDown(int from) {
		count = 0;
		countFrom = from;
		if (currentTimer != null) currentTimer.stop();
		currentTimer = new Timer(1000, this);
		currentTimer.start();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			ChampionBox box = (ChampionBox) e.getSource();
			try {
				int id = StartupClass.Client.invoke("gameService",
						"selectChampion", new Object[] { box.getChampion().getID() });
				TypedObject result = StartupClass.Client.getResult(id).getTO("data");
				System.out.println(result);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if ((e.getSource().equals(view.summonerSpell1ComboBox)) || (e.getSource().equals(view.summonerSpell2ComboBox))) {
				StartupClass.Client.invoke("gameService",
							"selectSpells", new Object[] { spell1Model.getSelectedItem().id, spell2Model.getSelectedItem().id  });
			} else if (e.getSource().equals(view.lockInButton)) {
				StartupClass.Client.invoke("gameService",
							"championSelectCompleted", new Object[] {  });
			} else if (e.getSource().equals(view.runePageList)) {
				StartupClass.Client.invokeWithCallback("spellBookService", "selectDefaultSpellBookPage", new Object[] { runePageModel.getSelectedItem().getiTO() }, new RTMPCallback() {
					public void callback(TypedObject result) {
						System.out.println(result);
					}
					
				});
			} else if (e.getSource().equals(view.masteryPageList)) {
				for (MasteryPage page : masteryPageModel) {
					page.setCurrent(false);
				}
				masteryPageModel.getSelectedItem().setCurrent(true);
				StartupClass.Client.invokeWithCallback("masteryBookService", "saveMasteryBook", new Object[] { LoggedInSummoner.summonerData.getMasteryBook().getiTO() }, new RTMPCallback() {
					public void callback(TypedObject result) {
						System.out.println(result);
					}
					
				});
			} else if (e.getSource() instanceof Timer) {
				if (count < countFrom) {
					count++;
					view.setTimer(countFrom - count);
				} else {
					((Timer) e.getSource()).stop();
				}
			}
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	@Override
	public void mouseEntered(MouseEvent e) {

	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
