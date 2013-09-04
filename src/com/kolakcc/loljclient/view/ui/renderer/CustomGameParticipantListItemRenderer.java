package com.kolakcc.loljclient.view.ui.renderer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.kolakcc.loljclient.model.Champion;
import com.kolakcc.loljclient.model.CustomGamePlayer;
import com.kolakcc.loljclient.model.SummonerSpell;
import com.kolakcc.loljclient.view.ui.ChampionBox;

public class CustomGameParticipantListItemRenderer implements ListCellRenderer<CustomGamePlayer> {

	@Override
	public Component getListCellRendererComponent(
			JList<? extends CustomGamePlayer> list, CustomGamePlayer value,
			int index, boolean isSelected, boolean cellHasFocus) {
		JPanel ret = new JPanel(new BorderLayout());
		ret.setMinimumSize(new Dimension(400,300));
		ret.setOpaque(false);
		
		if ((value.getChampionSelection() != null) && (value.getChampionSelection().getChampionID() != 0)) {
			JPanel centerPanel = new JPanel(new BorderLayout());
			centerPanel.setOpaque(false);
			Champion champion = Champion.getChampionFromID(value.getChampionSelection().getChampionID());
			try {
				ChampionBox cb = new ChampionBox(champion);
				cb.setSize(60, 60);
				centerPanel.add(cb,BorderLayout.WEST);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JPanel selectionPanel = new JPanel(new BorderLayout());
			selectionPanel.setOpaque(false);
			selectionPanel.add(new JLabel(champion.getDisplayName()), BorderLayout.NORTH);
			
			JPanel spellsPanel = new JPanel(new GridLayout(2,1));
			JLabel spell1Panel = new JLabel(SummonerSpell.getSpell(value.getChampionSelection().getSpell1ID()).displayName);
			spellsPanel.add(spell1Panel);
			JLabel spell2Panel = new JLabel(SummonerSpell.getSpell(value.getChampionSelection().getSpell2ID()).displayName);
			spellsPanel.add(spell2Panel);
			spellsPanel.setOpaque(false);
			
			selectionPanel.add(spellsPanel,BorderLayout.CENTER);
			centerPanel.add(selectionPanel,BorderLayout.CENTER);
			
			ret.add(centerPanel,BorderLayout.CENTER);
		}
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(new JLabel(value.getSummonerName(),JLabel.CENTER), BorderLayout.CENTER);
		JButton banButton = new JButton("Kick and ban");
		bottomPanel.add(banButton,BorderLayout.SOUTH);
		ret.add(bottomPanel,BorderLayout.SOUTH);
		return ret;
		
	}

}
