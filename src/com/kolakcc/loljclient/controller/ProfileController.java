package com.kolakcc.loljclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JTable;

import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.StartupClass;
import com.kolakcc.loljclient.model.Division;
import com.kolakcc.loljclient.model.InventoryRune;
import com.kolakcc.loljclient.model.LoggedInSummoner;
import com.kolakcc.loljclient.model.RecentGame;
import com.kolakcc.loljclient.model.RunePage;
import com.kolakcc.loljclient.model.Summoner;
import com.kolakcc.loljclient.model.swing.DivisionEntryTableModel;
import com.kolakcc.loljclient.model.swing.RecentGamesListModel;
import com.kolakcc.loljclient.model.swing.RunesInventoryTableModel;
import com.kolakcc.loljclient.util.NamedSwingWorker;
import com.kolakcc.loljclient.util.SavedRecentGamesProvider;
import com.kolakcc.loljclient.util.TOUtils;
import com.kolakcc.loljclient.view.ProfileView;

public class ProfileController extends KolaController implements MouseListener, ActionListener {
	ProfileView view;

	RecentGamesListModel gamesListModel;
	RunesInventoryTableModel runeInventoryModel;

	public ProfileController() {
		view = new ProfileView();
		this.setView(view);
		view.addRecentGameMouseListener(this);
		view.addSearchActionListener(this);
		
		runeInventoryModel = new RunesInventoryTableModel();
		view.setRuneInventoryModel(runeInventoryModel);

		loadProfileForPlayer(LoggedInSummoner.summonerData);
	}
	
	public void loadProfileForPlayer(final Summoner summoner) {
		view.setName(summoner.getSummonerName());
		view.setTitle(summoner.getSummonerName()+"'s profile");
		
		new NamedSwingWorker<TypedObject,Void>("getSummonerRunes") {
			protected TypedObject doInBackground() throws Exception {
				int id = StartupClass.Client.invoke("summonerRuneService", "getSummonerRuneInventory", new Object[] { summoner.getAccountID() }); //TODO: find out what this argument actually is for; you can't get other people's inventory
				return StartupClass.Client.getResult(id);
			}
			
			protected void done() {
				try {
					TypedObject result = get();
					for (Object rune : result.getTO("data").getTO("body").getArray("summonerRunes")) {
						InventoryRune iRune = new InventoryRune((TypedObject) rune);
						ProfileController.this.runeInventoryModel.add(iRune);
					}
					view.setColumnSizes();
				} catch (InterruptedException | ExecutionException e) {
					ProfileController.this.HandleException(e);
				}
			}
		}.execute();
		
		new NamedSwingWorker<TypedObject, Void>("Recent Games") {
			public TypedObject doInBackground() throws Exception {
				int id = StartupClass.Client.invoke("playerStatsService",
							"getRecentGames", new Object[] { summoner.getAccountID() });
				return StartupClass.Client.getResult(id).getTO("data")
						.getTO("body");
			}

			public void done() {
				try {
					ProfileController.this.gamesListModel = new RecentGamesListModel(TOUtils.ArrayToTOArray(this.get().getArray("gameStatistics")));
					int fromServer = ProfileController.this.gamesListModel.getSize();
					if (summoner.getAccountID() == LoggedInSummoner.summonerData.getAccountID()) {
						ProfileController.this.gamesListModel.addGameList(SavedRecentGamesProvider.getSavedGames(LoggedInSummoner.summonerData));
						ProfileController.this.gamesListModel.saveGames(LoggedInSummoner.summonerData);
					}
					int fromCache = ProfileController.this.gamesListModel.getSize() - fromServer;
					ProfileController.this.view.setAmountObject(new Object[] { new Integer(fromServer), new Integer(fromCache), new Integer(fromServer + fromCache) });
					ProfileController.this.view.setRecentGamesModel(ProfileController.this.gamesListModel);
					ProfileController.this.mouseClicked(null);
				} catch (Exception e) {
					ProfileController.this.HandleException(e);
				}
			}
		}.execute();
		
		new NamedSwingWorker<TypedObject, Void>("getAllLeaguesForPlayer") {
			public TypedObject doInBackground() throws Exception {
				int id = StartupClass.Client.invoke("leaguesServiceProxy",
							"getAllLeaguesForPlayer", new Object[] { summoner.getSummonerID() });
				return StartupClass.Client.getResult(id).getTO("data")
						.getTO("body");
			}

			public void done() {
				try {
					for (TypedObject league : TOUtils.ArrayToTOArray(get().getArray("summonerLeagues"))) {
						Division d = new Division(league);
						ProfileController.this.view.addLeague(d, ProfileController.this, ProfileController.this);
					}
				} catch (InterruptedException | ExecutionException e) {
					ProfileController.this.HandleException(e);
				}
			}
		}.execute();
		for (RunePage runePage : summoner.getRunePages()) {
			view.addRunePage(runePage);
		}
	}
	
	protected void searchSummoner(final String searchString) {
		//clear all the things!
		runeInventoryModel.clear();
		view.resetRunePages();
		view.clearLeagues();
		
		view.setName(searchString);
		
		new NamedSwingWorker<TypedObject, Void>("getSummonerByName") {
			@Override
			protected TypedObject doInBackground() throws Exception {
				int id = StartupClass.Client.invoke("summonerService", "getSummonerByName", new Object[] { searchString });
				return StartupClass.Client.getResult(id);
			}
			protected void done() {
				try {
					TypedObject result = get();
					getSummonerData(result.getTO("data").getTO("body").getDouble("acctId"));
				} catch (InterruptedException | ExecutionException e) {
					ProfileController.this.HandleException(e);
				}
			}
		}.execute();
	}
	
	protected void getSummonerData(final double accountID) { //maybe move to the summoner class
		new NamedSwingWorker<TypedObject, Void>("getAllSummonerDataByAccount") {
			@Override
			protected TypedObject doInBackground() throws Exception {
				int id = StartupClass.Client.invoke("summonerService", "getAllPublicSummonerDataByAccount", new Object[] { accountID });
				return StartupClass.Client.getResult(id);
			}
			protected void done() {
				try {
					TypedObject result = get();
					Summoner searched = new Summoner(result.getTO("data").getTO("body"));
					loadProfileForPlayer(searched);
				} catch (InterruptedException | ExecutionException e) {
					ProfileController.this.HandleException(e);
				}
			}
		}.execute();
	}
	
	public void mouseClicked(MouseEvent e) {
		if ((e == null) || (e.getSource() instanceof javax.swing.JList)) {
			if (this.gamesListModel.getSize() > 0) {
				RecentGame game = this.gamesListModel
						.getGameAt(view.getSelectedGameIndex());
				view.setSelectedGame(game);
			}
		} else {
			if (e.getClickCount() == 2) {
				JTable entryTable = (JTable) e.getSource();
				DivisionEntryTableModel model = (DivisionEntryTableModel) entryTable.getModel();
				searchSummoner(model.getEntryAt(entryTable.getSelectedRow()).getPlayerOrTeamName()); // DivisionEntry.getID gives a summonerid
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if ((e.getActionCommand().equals("Search")) || (e.getSource() instanceof javax.swing.JTextField)) {
			searchSummoner(view.getSearchString());
		} else {
			view.filterCurrentDivisionTable();
		}
	}
	
	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}
