package com.kolakcc.loljclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javazoom.jl.player.Player;

import com.kolakcc.loljclient.model.Champion;
import com.kolakcc.loljclient.view.ChampionDetailedView;

public class ChampionDetailedController extends KolaController implements
		ActionListener {
	Champion champion;
	ChampionDetailedView view;

	public ChampionDetailedController(Champion c) {
		this.champion = c;

		this.view = new ChampionDetailedView();
		this.setView(this.view);
		this.view.setChampion(c);

		this.view.addActionListener(this);
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
