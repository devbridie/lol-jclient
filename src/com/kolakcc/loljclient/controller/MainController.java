package com.kolakcc.loljclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.StartupClass;
import com.kolakcc.loljclient.model.Champion;
import com.kolakcc.loljclient.model.Item;
import com.kolakcc.loljclient.model.LoggedInSummoner;
import com.kolakcc.loljclient.model.SummonerSpell;
import com.kolakcc.loljclient.util.GameClient;
import com.kolakcc.loljclient.view.MainView;
import com.kolakcc.loljclient.view.ui.LocalizedJButton;

public class MainController extends KolaController implements ActionListener {
	MainView view;
	SwingWorker<TypedObject, String> requestWorker;
	SwingWorker<Object, String> assetLoaderWorker;

	public MainController() {
		this.initializeWorkers();
		this.view = new MainView();
		this.setView(this.view);
		this.view.customGameButton.addActionListener(this);
		this.view.profileButton.addActionListener(this);
		this.view.championsButton.addActionListener(this);
		this.view.storeButton.addActionListener(this);
		this.view.launch.addActionListener(this);
		this.assetLoaderWorker.execute();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (((LocalizedJButton) e.getSource()).getKey()) {
			case "champions":
				StartupClass.openChampions();
				break;
			case "profile":
				StartupClass.openProfile();
				break;
			case "custom games":
				StartupClass.openCustomGames();
				break;
			case "store":
				StartupClass.openStore();
				break;
			case "launch lol":
				GameClient.start();
				break;
		}
	}

	protected void initializeWorkers() {
		this.requestWorker = new SwingWorker<TypedObject, String>() {
			public TypedObject doInBackground() throws Exception {
				int id = StartupClass.Client.invoke("clientFacadeService",
						"getLoginDataPacketForUser", new Object[] {});
				return StartupClass.Client.getResult(id).getTO("data")
						.getTO("body");
			}

			public void done() {
				try {
					TypedObject summoner = this.get();
					LoggedInSummoner.populateData(summoner);
					MainController.this.view.RPBalance
							.setText(String.valueOf(LoggedInSummoner.RPBalance));
					MainController.this.view.IPBalance
							.setText(String.valueOf(LoggedInSummoner.IPBalance));
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
		this.assetLoaderWorker = new SwingWorker<Object, String>() {
			protected Object doInBackground() throws Exception {
				//TODO: localize this
				this.publish("Loading champions...");
				Champion.initializeChampions();
				
				this.publish("Loading items...");
				Item.initializeItems();
				
				this.publish("Loading summoner spells...");
				SummonerSpell.initializeSpells();
				MainController.this.requestWorker.execute();
				return null;
			}

			protected void process(List<String> publishedInfo) {
				for (String listItem : publishedInfo) {
					System.out.println(listItem);
				}
			}
		};
	}
}
