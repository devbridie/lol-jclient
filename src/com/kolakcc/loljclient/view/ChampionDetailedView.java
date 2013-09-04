package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.camick.WrapLayout;
import com.kolakcc.loljclient.model.Champion;
import com.kolakcc.loljclient.model.ChampionSkin;
import com.kolakcc.loljclient.util.FontUtils;
import com.kolakcc.loljclient.view.ui.ChampionBox;
import com.kolakcc.loljclient.view.ui.SkinImageLabel;
import com.kolakcc.loljclient.view.ui.VerticalJScrollPane;

public class ChampionDetailedView extends KolaView {
	public JLabel championName;
	public JLabel championTitle;
	public ChampionBox championIcon;
	public JPanel skinsPanel;

	public Box classicRecommendedItems;
	public Box dominionRecommendedItems;
	public Box aramRecommendedItems;
	public JButton selectionAudioButton;
	protected JPanel topPanel;
	public JEditorPane loreTabPanel;
	public JLabel tipsPlayingTitle;
	public JLabel tipsPlayingTips;
	public JLabel tipsPlayingAgainstTitle;
	public JLabel tipsPlayingAgainstTips;

	public ChampionDetailedView() {
		super();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(700, 700);
		this.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new BorderLayout());
		this.championIcon = new ChampionBox();
		topPanel.add(championIcon,BorderLayout.WEST);
		
		JPanel nameTitlePanel = new JPanel(new BorderLayout());

		this.championName = new JLabel("Loading...", JLabel.CENTER);
		this.championName.setFont(FontUtils.emSize(championName.getFont(), Font.BOLD, 2));
		nameTitlePanel.add(this.championName, BorderLayout.NORTH);

		this.championTitle = new JLabel("Loading...", JLabel.CENTER);
		nameTitlePanel.add(this.championTitle, BorderLayout.CENTER);

		topPanel.add(nameTitlePanel, BorderLayout.CENTER);
		this.add(topPanel, BorderLayout.NORTH);

		JTabbedPane tabbedPane = new JTabbedPane();
		/*JPanel championStatsTabPanel = new JPanel();
		tabbedPane.addTab("Stats", championStatsTabPanel);*/

		/*
		 * JPanel recommendedItemsTabPanel = new JPanel();
		 * tabbedPane.addTab("Recommended Items", recommendedItemsTabPanel);
		 * 
		 * JPanel classicItemsPanel = new JPanel();
		 * classicItemsPanel.setLayout(new BorderLayout());
		 * classicItemsPanel.add(new
		 * JLabel("Classic",JLabel.CENTER),BorderLayout.NORTH);
		 * classicRecommendedItems = Box.createHorizontalBox();
		 * classicItemsPanel.add(classicRecommendedItems,BorderLayout.CENTER);
		 * recommendedItemsTabPanel.add(classicItemsPanel);
		 * 
		 * JPanel dominionItemsPanel = new JPanel();
		 * dominionItemsPanel.setLayout(new BorderLayout());
		 * dominionItemsPanel.add(new
		 * JLabel("Dominion",JLabel.CENTER),BorderLayout.NORTH);
		 * dominionRecommendedItems = Box.createHorizontalBox();
		 * dominionItemsPanel.add(dominionRecommendedItems,BorderLayout.CENTER);
		 * recommendedItemsTabPanel.add(dominionItemsPanel);
		 * 
		 * JPanel aramItemsPanel = new JPanel(); aramItemsPanel.setLayout(new
		 * BorderLayout()); aramItemsPanel.add(new
		 * JLabel("ARAM",JLabel.CENTER),BorderLayout.NORTH);
		 * aramRecommendedItems = Box.createHorizontalBox();
		 * aramItemsPanel.add(aramRecommendedItems,BorderLayout.CENTER);
		 * recommendedItemsTabPanel.add(aramItemsPanel);
		 */

		this.skinsPanel = new JPanel(new WrapLayout());
		JScrollPane skinScroller = new VerticalJScrollPane(this.skinsPanel);
		skinScroller.getVerticalScrollBar().setUnitIncrement(16);
		tabbedPane.addTab("Skins", skinScroller);

		this.loreTabPanel = new JEditorPane();
		this.loreTabPanel.setContentType("text/html");

		this.loreTabPanel.setEditable(false);

		JScrollPane loreScroller = new VerticalJScrollPane(this.loreTabPanel);
		loreScroller.getVerticalScrollBar().setUnitIncrement(16);

		tabbedPane.addTab("Lore", loreScroller);

		JPanel videoPanel = new JPanel();
		this.selectionAudioButton = new JButton("Selection audio");
		videoPanel.add(this.selectionAudioButton);

		JScrollPane scrollerVideo = new VerticalJScrollPane(videoPanel);
		skinScroller.getVerticalScrollBar().setUnitIncrement(16);
		tabbedPane.addTab("Audio & Video", scrollerVideo);

		JPanel tipsTabBox = new JPanel();
		tipsTabBox.setLayout(new BoxLayout(tipsTabBox, BoxLayout.Y_AXIS));

		JPanel playingAsPanel = new JPanel();
		playingAsPanel
				.setLayout(new BoxLayout(playingAsPanel, BoxLayout.Y_AXIS));

		this.tipsPlayingTitle = new JLabel("Loading...", JLabel.CENTER);
		playingAsPanel.add(this.tipsPlayingTitle);

		this.tipsPlayingTips = new JLabel("Loading...");
		playingAsPanel.add(this.tipsPlayingTips);

		tipsTabBox.add(playingAsPanel);

		JPanel playingAgainstPanel = new JPanel();
		playingAgainstPanel.setLayout(new BoxLayout(playingAgainstPanel,
				BoxLayout.Y_AXIS));

		this.tipsPlayingAgainstTitle = new JLabel("Loading...", JLabel.CENTER);
		playingAgainstPanel.add(this.tipsPlayingAgainstTitle);

		this.tipsPlayingAgainstTips = new JLabel("Loading...");
		playingAgainstPanel.add(this.tipsPlayingAgainstTips);

		tipsTabBox.add(playingAgainstPanel);

		tabbedPane.addTab("Tips", tipsTabBox);

		this.add(tabbedPane, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public void addSkinBox(String championName, ChampionSkin skin) {
		Box skinBox = Box.createVerticalBox();
		JLabel skinName = new JLabel(skin.isDefault() ? championName
				: skin.getDisplayName());
		skinBox.add(skinName);
		skinBox.add(new SkinImageLabel(skin,
				SkinImageLabel.DisplayType.PORTRAIT, championName));
		this.skinsPanel.add(skinBox);
	}

	public void setChampionIcon(Champion c) {
		try {
			this.topPanel.add(new JLabel(new ImageIcon(c.getIcon())),
					BorderLayout.WEST);
		} catch (Exception e) {
			System.out.println("Could not find Icon for " + c.getDisplayName());
		}
	}
}
