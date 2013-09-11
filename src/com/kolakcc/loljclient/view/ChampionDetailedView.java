package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

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
import com.kolakcc.loljclient.util.LocaleMessages;
import com.kolakcc.loljclient.view.ui.ChampionBox;
import com.kolakcc.loljclient.view.ui.LocalizedJButton;
import com.kolakcc.loljclient.view.ui.LocalizedJLabel;
import com.kolakcc.loljclient.view.ui.SkinImageLabel;
import com.kolakcc.loljclient.view.ui.VerticalJScrollPane;

public class ChampionDetailedView extends KolaView {
	LocalizedJLabel championName, championTitle, tipsPlayingTitle, tipsPlayingTips, tipsPlayingAgainstTitle, tipsPlayingAgainstTips;
	ChampionBox championIcon;
	JPanel skinsPanel;

	JButton selectionAudioButton;
	JPanel topPanel;
	JEditorPane loreTabPanel;
	
	static LocaleMessages championDetailedViewMessages = new LocaleMessages("championDetailedViewBundle");

	public ChampionDetailedView() {
		super();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(700, 700);
		this.setLayout(new BorderLayout());

		topPanel = new JPanel(new BorderLayout());
		this.championIcon = new ChampionBox();
		topPanel.add(championIcon,BorderLayout.WEST);
		
		JPanel nameTitlePanel = new JPanel(new BorderLayout());

		this.championName = new LocalizedJLabel(); 
		this.championName.setAlignmentX(JLabel.CENTER);
		this.championName.setFont(FontUtils.emSize(championName.getFont(), Font.BOLD, 2));
		nameTitlePanel.add(this.championName, BorderLayout.NORTH);

		this.championTitle = new LocalizedJLabel();
		this.championTitle.setAlignmentX(JLabel.CENTER);
		nameTitlePanel.add(this.championTitle, BorderLayout.CENTER);

		topPanel.add(nameTitlePanel, BorderLayout.CENTER);
		this.add(topPanel, BorderLayout.NORTH);

		JTabbedPane tabbedPane = new JTabbedPane();

		this.skinsPanel = new JPanel(new WrapLayout());
		JScrollPane skinScroller = new VerticalJScrollPane(this.skinsPanel);
		skinScroller.getVerticalScrollBar().setUnitIncrement(16);
		tabbedPane.addTab(championDetailedViewMessages.getString("skins"), skinScroller);

		this.loreTabPanel = new JEditorPane();
		this.loreTabPanel.setContentType("text/html");

		this.loreTabPanel.setEditable(false);

		JScrollPane loreScroller = new VerticalJScrollPane(this.loreTabPanel);
		loreScroller.getVerticalScrollBar().setUnitIncrement(16);

		tabbedPane.addTab(championDetailedViewMessages.getString("lore"), loreScroller);

		JPanel videoPanel = new JPanel();
		this.selectionAudioButton = new LocalizedJButton(championDetailedViewMessages, "selection audio");
		videoPanel.add(this.selectionAudioButton);

		JScrollPane scrollerVideo = new VerticalJScrollPane(videoPanel);
		skinScroller.getVerticalScrollBar().setUnitIncrement(16);
		tabbedPane.addTab(championDetailedViewMessages.getString("audio_video"), scrollerVideo);

		JPanel tipsTabBox = new JPanel();
		tipsTabBox.setLayout(new BoxLayout(tipsTabBox, BoxLayout.Y_AXIS));

		JPanel playingAsPanel = new JPanel();
		playingAsPanel
				.setLayout(new BoxLayout(playingAsPanel, BoxLayout.Y_AXIS));

		tipsPlayingTitle = new LocalizedJLabel();
		playingAsPanel.add(this.tipsPlayingTitle);

		this.tipsPlayingTips = new LocalizedJLabel();
		playingAsPanel.add(this.tipsPlayingTips);

		tipsTabBox.add(playingAsPanel);

		JPanel playingAgainstPanel = new JPanel();
		playingAgainstPanel.setLayout(new BoxLayout(playingAgainstPanel,
				BoxLayout.Y_AXIS));

		this.tipsPlayingAgainstTitle = new LocalizedJLabel();
		this.tipsPlayingAgainstTitle.setAlignmentX(JLabel.CENTER);
		playingAgainstPanel.add(this.tipsPlayingAgainstTitle);

		this.tipsPlayingAgainstTips = new LocalizedJLabel();
		playingAgainstPanel.add(this.tipsPlayingAgainstTips);

		tipsTabBox.add(playingAgainstPanel);

		tabbedPane.addTab(championDetailedViewMessages.getString("tips"), tipsTabBox);

		this.add(tabbedPane, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public void setChampion(Champion champion) {
		this.setTitle(String.format("%s, %s", champion.getDisplayName(), champion.getTitle()));
		championName.setText(champion.getDisplayName());
		championTitle.setText(champion.getTitle());
		loreTabPanel.setText("<html>"
				+ champion.getDescription() + "<br><br><i>"
				+ champion.getQuote() + "<br>-"
				+ champion.getQuoteAuthor());
		tipsPlayingTitle.setText(championDetailedViewMessages.getComplexString("playing as", new Object[] { champion.getDisplayName() }));
		tipsPlayingTips.setText("<html>" + champion.getTips().replace("*", "<li>"));
		tipsPlayingAgainstTitle.setText(championDetailedViewMessages.getComplexString("playing against", new Object[] { champion.getDisplayName() }));
		tipsPlayingAgainstTips.setText("<html>" + champion.getOpponentTips().replace("*", "<li>"));
		for (ChampionSkin skin : champion.getSkins()) {
			Box skinBox = Box.createVerticalBox();
			JLabel skinName = new JLabel(skin.isDefault() ? champion.getDisplayName() : skin.getDisplayName());
			skinBox.add(skinName);
			skinBox.add(new SkinImageLabel(skin, SkinImageLabel.DisplayType.PORTRAIT, champion.getDisplayName()));
			this.skinsPanel.add(skinBox);
		}
		try {
			championIcon.setIcon(new ImageIcon(champion.getIcon()));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not find Icon for " + champion.getDisplayName());
		}
	}
	public void addActionListener(ActionListener l) {
		this.selectionAudioButton.addActionListener(l);
	}
}
