package com.kolakcc.loljclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.SwingWorker;

import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.StartupClass;
import com.kolakcc.loljclient.model.Champion;
import com.kolakcc.loljclient.model.Item;
import com.kolakcc.loljclient.model.LoggedInSummoner;
import com.kolakcc.loljclient.model.SummonerSpell;
import com.kolakcc.loljclient.util.GameClient;
import com.kolakcc.loljclient.view.MainView;

public class MainController extends KolaController implements ActionListener {
	protected MainView view;
	protected SwingWorker<TypedObject, String> requestWorker;
	protected SwingWorker<Object, String> assetLoaderWorker;

	public MainController() {
		this.initializeWorkers();
		this.view = new MainView();
		this.setView(this.view);
		this.view.customGameButton.addActionListener(this);
		this.view.profileButton.addActionListener(this);
		this.view.championsButton.addActionListener(this);
		this.view.settingsItem.addActionListener(this);
		this.view.aboutItem.addActionListener(this);
		this.view.storeButton.addActionListener(this);
		this.view.launch.addActionListener(this);
		this.assetLoaderWorker.execute();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonText;
		if (e.getSource() instanceof JButton) buttonText = ((JButton) e.getSource()).getText();
		else buttonText = ((JMenuItem) e.getSource()).getText();
		switch (buttonText) {
			case "Champions":
				StartupClass.openChampions();
				break;
			case "Profile":
				StartupClass.openProfile();
				break;
			case "Custom Games":
				StartupClass.openCustomGames();
				break;
			case "About":
				StartupClass.openAbout();
				break;
			case "Store":
				StartupClass.openStore();
				break;
			case "Launch LoL":
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
				this.publish("Loading champions...");
				Champion.initializeChampions();
				
				this.publish("Loading items...");
				Item.initializeItems();
				
				this.publish("Loading summoner spells...");
				SummonerSpell.initializeSpells();
				MainController.this.requestWorker.execute();
				return null;
			}

			protected void process(List<String> l) {
				System.out.println(l.get(l.size() - 1));
			}
		};
	}
}
