package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.kolakcc.loljclient.model.IconSize;
import com.kolakcc.loljclient.view.ui.ChampionBox;
import com.kolakcc.loljclient.view.ui.ChampionsPanel;
import com.kolakcc.loljclient.view.ui.VerticalJScrollPane;

public class ChampionsView extends KolaView {
	public ChampionsPanel championsPanel;
	public JComboBox<String> filterComboBox;
	public JComboBox<IconSize> sizeComboBox;

	public ChampionsView(ChampionsPanel panel) {
		super();
		this.setTitle("Champions");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		this.filterComboBox = new JComboBox<String>();
		topPanel.add(this.filterComboBox);
		this.sizeComboBox = new JComboBox<IconSize>();
		topPanel.add(this.sizeComboBox);
		this.add(topPanel, BorderLayout.NORTH);
		
		this.championsPanel = panel;
		this.add(new VerticalJScrollPane(this.championsPanel), BorderLayout.CENTER);

		this.setSize(500, 600);
	}

	public void setIconSizes(int size) {
		for (Component component : this.championsPanel.getComponents()) {
			ChampionBox icon = (ChampionBox) component;
			icon.setSize(size, size);
		}
		this.championsPanel.revalidate();
	}
}
