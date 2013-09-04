package com.kolakcc.loljclient.view.ui;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.camick.WrapLayout;
import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.StartupClass;
import com.kolakcc.loljclient.model.Champion;
import com.kolakcc.loljclient.model.IconSize;
import com.kolakcc.loljclient.util.TOUtils;

public class ChampionsPanel extends JPanel {
	public ArrayList<Champion> champions;
	public int size;
	public String filter;
	protected MouseListener optionalMouseListener;
	
	public ChampionsPanel(IconSize size, String filter) {
		super(new WrapLayout());
		champions = new ArrayList<Champion>();
		this.size = size.number;
		this.filter = filter;
		populateChampions();
	}
	public void addChampionMouseListener(MouseListener l) {
		optionalMouseListener = l;
	}
	public void populateChampions() {
		new SwingWorker<Void, Champion>() {
			public Void doInBackground() throws Exception {
				int id = StartupClass.Client.invoke("inventoryService",
						"getAvailableChampions", new Object[] {});
				TypedObject championData = StartupClass.Client.getResult(id)
						.getTO("data");
				for (TypedObject champion : TOUtils.ArrayToTOArray(championData.getArray("body"))) {
					Champion fromDatabase = Champion.getChampionFromID(champion.getInt("championId"));
					fromDatabase.setOwned(champion.getBool("owned")); // TODO: model all champion fields
					fromDatabase.setFreeToPlay(champion.getBool("freeToPlay"));
					this.publish(fromDatabase);
				}
				return null;
			}
	
			public void done() {
				resizeAndRefilter();
			}
	
			public void process(List<Champion> progress) {
				ChampionsPanel.this.champions.addAll(progress);
			}
		}.execute();
	}
	public void resizeAndRefilter() {
		this.removeAll();
		for (Champion c : this.champions) {
			try {
				if (addThisChampion(c)) {
					ChampionBox cb = new ChampionBox(c);
					cb.setSize(size, size);
					if (optionalMouseListener != null) cb.addMouseListener(optionalMouseListener);
					this.add(cb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.revalidate();
		this.repaint();
	}
	public boolean addThisChampion(Champion c) {
		if (filter == "All") return true;
		else if ((filter == "Bought") && (c.isOwned())) return true;
		else if ((filter == "Not bought") && (!c.isOwned())) return true; 
		else if ((filter == "Playable") && (c.isOwned() || c.isFreeToPlay())) return true;
		else if ((filter == "Free this week") && (c.isFreeToPlay())) return true;
		return false;
	}
}
