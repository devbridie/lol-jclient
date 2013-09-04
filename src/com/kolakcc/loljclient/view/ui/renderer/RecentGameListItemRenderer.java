package com.kolakcc.loljclient.view.ui.renderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.kolakcc.loljclient.model.Champion;
import com.kolakcc.loljclient.model.RecentGame;
import com.kolakcc.loljclient.view.ui.ChampionBox;

public class RecentGameListItemRenderer implements ListCellRenderer<RecentGame> {
	public Component getListCellRendererComponent(
			JList<? extends RecentGame> list, RecentGame value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel ret = new JPanel(new BorderLayout());
		ret.setBackground(value.getStatistics().containsKey("WIN") ? Color.green : Color.red);
		Champion champion = Champion.getChampionFromID(value.getChampionID());
		try {
			ret.add(new ChampionBox(champion), BorderLayout.WEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ret.add(new JLabel(String.format("<html>%s %d/%d/%d<br>%s", champion.getDisplayName(), value.getStatistic("CHAMPIONS_KILLED"), value.getStatistic("NUM_DEATHS"), value.getStatistic("ASSISTS"), value.getMap().toString())),BorderLayout.CENTER);
		return ret;
	}
}
