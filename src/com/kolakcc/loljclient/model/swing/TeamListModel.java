package com.kolakcc.loljclient.model.swing;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.event.ListDataListener;

import com.kolakcc.loljclient.model.CustomGameDetailed;
import com.kolakcc.loljclient.model.CustomGamePlayer;
import com.kolakcc.loljclient.model.PlayerChampionSelection;

public class TeamListModel extends AbstractListModel<CustomGamePlayer> {
	public static enum TEAM {
		TEAM1, TEAM2
	};

	protected ArrayList<CustomGamePlayer> players;
	protected TEAM teamDisplay;

	public TeamListModel(CustomGameDetailed game, TEAM teamDisplay) {
		this.teamDisplay = teamDisplay;
		this.players = new ArrayList<CustomGamePlayer>();
		if ((game.getTeam1() != null) && (teamDisplay == TEAM.TEAM1)) {
			this.players = game.getTeam1();
		}
		if ((game.getTeam2() != null) && (teamDisplay == TEAM.TEAM2)) {
			this.players = game.getTeam2();
		}
	}

	public CustomGamePlayer getElementAt(int index) {
		CustomGamePlayer selectedObject = this.players.get(index);
		return selectedObject;
	}

	public int getSize() {
		return this.players.size();
	}

	public void addChampionData(ArrayList<PlayerChampionSelection> arrayPlayerChampionSelectionDTO) {
		for (CustomGamePlayer player : players) {
			for (PlayerChampionSelection playerChampionSelectionDTO : arrayPlayerChampionSelectionDTO) {
				if (player.getSummonerInternalName().equals(playerChampionSelectionDTO.getSummonerInternalName())) {
					player.setChampionSelection(playerChampionSelectionDTO);
				}
			}
		}
		refreshList();
	}
	
	public void refreshList() {
		this.fireContentsChanged(this, 0, this.getSize());
	}
	
	public void addListDataListener(ListDataListener l) {
	}
	
	public void removeListDataListener(ListDataListener l) {
	}
}
