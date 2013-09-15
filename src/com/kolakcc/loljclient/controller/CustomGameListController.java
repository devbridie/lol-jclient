package com.kolakcc.loljclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.StartupClass;
import com.kolakcc.loljclient.model.CustomGameListItem;
import com.kolakcc.loljclient.model.swing.CustomGameTableModel;
import com.kolakcc.loljclient.view.CustomGameListView;

public class CustomGameListController extends KolaController implements
		MouseListener, ActionListener {
	CustomGameListView view;
	SwingWorker<TypedObject, Void> gameListWorker;
	CustomGameTableModel gamesModel;
	CustomGameFilterController filterController;

	public CustomGameListController() {
		this.view = new CustomGameListView();
		this.setView(this.view);
		this.initializeWorkers();
		this.gameListWorker.execute();
		this.view.gamesListTable.addMouseListener(this);
		this.view.refreshButton.addActionListener(this);
        this.view.createButton.addActionListener(this);
		this.view.filterButton.addActionListener(this);
		
	}

	public void initializeWorkers() {
		this.gameListWorker = new SwingWorker<TypedObject, Void>() {
			protected TypedObject doInBackground() throws Exception {
				int id = StartupClass.Client.invoke("gameService",
						"listAllPracticeGames", new Object[] {});
				TypedObject gamesList = StartupClass.Client.getResult(id)
						.getTO("data").getTO("body");
				return gamesList;
			}

			protected void done() {
				try {
					TypedObject gamesList = this.get();
					if (CustomGameListController.this.gamesModel == null) {
						CustomGameListController.this.gamesModel = new CustomGameTableModel(gamesList);
						CustomGameListController.this.view.gamesListTable.setModel(CustomGameListController.this.gamesModel);
					}
					else {
						CustomGameListController.this.gamesModel.clear();
						CustomGameListController.this.gamesModel.add(gamesList);
					}

					CustomGameListController.this.view.refreshButton.setText("Refresh");
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		CustomGameListItem game = this.gamesModel
				.getGameAt(this.view.gamesListTable.getSelectedRow());
		StartupClass.customGameLobbyController = new CustomGameLobbyController(
				game);
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == this.view.refreshButton) {
			CustomGameListController.this.view.refreshButton.setText("Loading...");
			initializeWorkers();
			this.gamesModel.removeAll();
			gameListWorker.execute();
        } else if (e.getSource() == this.view.createButton) {
            TypedObject practiceGameConfig = new TypedObject("com.riotgames.platform.game.PracticeGameConfig");
            TypedObject map = new TypedObject("com.riotgames.platform.game.map.GameMap");
            map.put("totalPlayers", 10);
            map.put("minCustomPlayers", 1);
            map.put("mapId", 1);
            map.put("displayName", "Summoner's Rift");
            map.put("name", "SummonersRift");
            map.put("description", "The oldest and most venerated Field of Justice is known as Summoner's Rift.  This battleground is known for the constant conflicts fought between two opposing groups of Summoners.  Traverse down one of three different paths in order to attack your enemy at their weakest point.  Work with your allies to siege the enemy base and destroy their Headquarters!");
            map.put("dataVersion", null);
            map.put("futureData", null);

            practiceGameConfig.put("gamePassword", "");
            practiceGameConfig.put("allowSpectators", "NONE");
            practiceGameConfig.put("region", "");
            practiceGameConfig.put("gameName", "my game");
            practiceGameConfig.put("maxNumPlayers", 10);
            practiceGameConfig.put("gameTypeConfig", 1);
            practiceGameConfig.put("gameMap", map);
            practiceGameConfig.put("passbackDataPacket", null);
            practiceGameConfig.put("passbackUrl", null);
            practiceGameConfig.put("gameMode", "CLASSIC");
            try{
                int id = StartupClass.Client.invoke("gameService", "createPracticeGame", new Object[] { practiceGameConfig });
                TypedObject result = StartupClass.Client.getResult(id);
                System.out.println(result.toPrettyString());
                StartupClass.customGameLobbyController = new CustomGameLobbyController(
                        result.getTO("data").getTO("body")
                );
            }catch(Exception ex){
                ex.printStackTrace();
            }
		} else {
			if (gamesModel != null) filterController = new CustomGameFilterController(gamesModel);
		}
	}
}
