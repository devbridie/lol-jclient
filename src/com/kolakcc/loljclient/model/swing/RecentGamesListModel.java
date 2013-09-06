package com.kolakcc.loljclient.model.swing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.model.RecentGame;
import com.kolakcc.loljclient.model.Summoner;
import com.kolakcc.loljclient.util.SavedRecentGamesProvider;

public class RecentGamesListModel implements ListModel<RecentGame> {
	private ArrayList<RecentGame> data = new ArrayList<RecentGame>();

	public RecentGamesListModel(TypedObject[] recentGamesList) {
		for (TypedObject game : recentGamesList) {
			RecentGame g = new RecentGame(game);
			this.data.add(g);
		}
		Collections.sort(this.data);
		Collections.reverse(this.data);
	}

	public RecentGame getElementAt(int index) {
		return this.data.get(index);
	}

	public RecentGame getGameAt(int index) {
		return this.data.get(index);
	}

	public int getSize() {
		return this.data.size();
	}
	
	public void addGameList(ArrayList<RecentGame> list) {
		for (RecentGame game : list) {
			if (!data.contains(game)) data.add(game);
		}
		Collections.sort(this.data);
		Collections.reverse(this.data);
	}
	
	public void saveGames(Summoner summoner) {
		try {
			for (RecentGame game : data) {
				SavedRecentGamesProvider.save(game, summoner);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addListDataListener(ListDataListener l) {
	}
	
	public void removeListDataListener(ListDataListener l) {
	}
}
