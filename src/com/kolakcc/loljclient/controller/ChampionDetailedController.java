package com.kolakcc.loljclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javazoom.jl.player.Player;

import com.kolakcc.loljclient.model.Champion;
import com.kolakcc.loljclient.model.ChampionSkin;
import com.kolakcc.loljclient.view.ChampionDetailedView;

public class ChampionDetailedController extends KolaController implements
		ActionListener {
	Champion champion;
	ChampionDetailedView view;

	public ChampionDetailedController(Champion c) {
		this.champion = c;

		this.view = new ChampionDetailedView();
		this.setView(this.view);
		this.view.setTitle(c.getDisplayName());
		this.view.championName.setText(this.champion.getDisplayName());
		this.view.championTitle.setText(this.champion.getTitle());
		this.view.loreTabPanel.setText("<html>"
				+ this.champion.getDescription() + "<br><br><i>"
				+ this.champion.getQuote() + "<br>-"
				+ this.champion.getQuoteAuthor());
		this.view.tipsPlayingTitle.setText("Playing as "
				+ this.champion.getDisplayName() + ":");
		this.view.tipsPlayingTips.setText("<html>"
				+ this.champion.getTips().replace("*", "<li>"));
		this.view.tipsPlayingAgainstTitle.setText("Playing against "
				+ this.champion.getDisplayName() + ":");
		this.view.tipsPlayingAgainstTips.setText("<html>"
				+ this.champion.getOpponentTips().replace("*", "<li>"));

		for (ChampionSkin skin : this.champion.getSkins()) {
			this.view.addSkinBox(this.champion.getName(), skin);
		}
		try {
			this.view.championIcon.setChampion(this.champion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.view.revalidate();

		this.view.selectionAudioButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new Thread() {
			public void run() {
				try {
					Player p = new Player(
							ChampionDetailedController.this.champion
									.getSelectionAudio());
					p.play();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
