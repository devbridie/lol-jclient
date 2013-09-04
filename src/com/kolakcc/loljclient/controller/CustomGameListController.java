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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.view.refreshButton) {
			CustomGameListController.this.view.refreshButton.setText("Loading...");
			initializeWorkers();
			this.gamesModel.removeAll();
			gameListWorker.execute();
		} else {
			if (gamesModel != null) filterController = new CustomGameFilterController(gamesModel);
		}
	}
}
