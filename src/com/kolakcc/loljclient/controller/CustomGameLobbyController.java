package com.kolakcc.loljclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.StartupClass;
import com.kolakcc.loljclient.model.CustomGameDetailed;
import com.kolakcc.loljclient.model.CustomGameListItem;
import com.kolakcc.loljclient.model.swing.TeamListModel;
import com.kolakcc.loljclient.model.swing.TeamListModel.TEAM;
import com.kolakcc.loljclient.view.CustomGameLobbyView;

public class CustomGameLobbyController extends KolaController implements
		ActionListener, WindowListener {
	CustomGameLobbyView view;
	SwingWorker<TypedObject, Void> joinGameWorker;
	int gameID;
	CustomGameDetailed gameDetailed;
	ChampionSelectController selectController;
	
	TeamListModel team1Model;
	TeamListModel team2Model;

	//TODO: data with a higher optimistcLock has a higher priority.
	public CustomGameLobbyController(CustomGameListItem game) {
		this(game.getID());
	}
	public CustomGameLobbyController(int gameID) {
		this.gameID = gameID;
		this.view = new CustomGameLobbyView();
		this.view.quitGameButton.addActionListener(this);
		this.view.addBotOnTeam1Button.addActionListener(this);
		this.view.addBotOnTeam2Button.addActionListener(this);
		this.view.swapTeamButton.addActionListener(this);
		this.view.startGameButton.addActionListener(this);
		this.view.addWindowListener(this);
		this.setView(this.view);
		this.initializeWorkers();
		this.joinGameWorker.execute();
	}

	public void actionPerformed(ActionEvent event) {
		try {
			if (event.getSource() == view.quitGameButton) {
				closeLobby();
			} else if (event.getSource() == view.swapTeamButton) {
				int id = StartupClass.Client.invoke("gameService", "switchTeams", new Object[] { new Double(gameID) });
				System.out.println(StartupClass.Client.getResult(id));
			} else if (event.getSource() == view.startGameButton) {
				int id = StartupClass.Client.invoke("gameService", "startChampionSelection", new Object[] { new Integer(gameID), 1 }); //TODO: what is this 1?
				TypedObject result = StartupClass.Client.getResult(id).getTO("data");
				System.out.println(result);
			}
		} catch (Exception e) { e.printStackTrace(); }
	}

	protected void initializeWorkers() {
		this.joinGameWorker = new SwingWorker<TypedObject, Void>() {
			protected TypedObject doInBackground() throws Exception {
				int id = StartupClass.Client
						.invoke("gameService", "joinGame",
								new Object[] { new Double(CustomGameLobbyController.this.gameID), null });
				return StartupClass.Client.getResult(id);
			}

			protected void done() {
				try {
					TypedObject result = this.get();
					if (result.get("result").equals("_error")) {
						TypedObject error = result.getTO("data").getTO(
								"rootCause");
						if (error.getString("rootCauseClassname")
								.equals("com.riotgames.platform.game.UserBannedException")) {
							CustomGameLobbyController.this.HandleException("You're banned from this game.","The game creator has banned you from this game.\r\n" + error.toString());
							System.out.println("You're banned from this game.");
						} else {
							System.out.println(error.getString("message"));
						}
					}
					System.out.println(result);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
	}

	public void receivePacket(TypedObject to) {
		this.gameDetailed = new CustomGameDetailed(to);
		this.updateGameLobby();
		System.out.println("game state: "+gameDetailed.getGameState());
		view.setTitle(gameDetailed.getName());
		if (gameDetailed.getGameState().equals("CHAMP_SELECT"))  {
			team1Model.addChampionData(gameDetailed.getPlayerChampionSelections());
			team2Model.addChampionData(gameDetailed.getPlayerChampionSelections());
			if (selectController == null) {
				selectController = new ChampionSelectController(team1Model,team2Model);
				try {
					StartupClass.Client.invoke("gameService", "setClientReceivedGameMessage", new Object[] { new Double(gameID), "CHAMP_SELECT_CLIENT" });
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//TODO: move this to championselectcontroller
		if (gameDetailed.getGameState().equals("POST_CHAMP_SELECT"))  {
			selectController.countDown(10);
		}
		if (gameDetailed.getGameState().equals("START_REQUESTED"))  {
			//TODO: start requested
			
		}
	}

	public void updateGameLobby() {
		team1Model = new TeamListModel(this.gameDetailed, TEAM.TEAM1);
		team2Model = new TeamListModel(this.gameDetailed, TEAM.TEAM2);
		this.view.team1List.setModel(team1Model);
		this.view.team2List.setModel(team2Model);
		if (this.selectController != null) {
			this.selectController.view.team1List.setModel(team1Model);
			this.selectController.view.team2List.setModel(team2Model);	
		}

		this.view.revalidate();
		this.view.repaint();
	}
	public void closeLobby() {
		try {
			StartupClass.Client.invoke("gameService", "quitGame",new Object[] {});
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.view.dispose();
	}
	@Override
	public void windowClosing(WindowEvent e) {
		closeLobby();
	}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
}
