package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.jivesoftware.smack.RosterEntry;

import com.kolakcc.loljclient.model.Status;
import com.kolakcc.loljclient.model.swing.StatusComboBoxModel;
import com.kolakcc.loljclient.view.ui.renderer.FriendListItemRenderer;
import com.kolakcc.loljclient.view.ui.renderer.StatusComboBoxRenderer;

public class FriendsListView extends KolaView {
	public JList<RosterEntry> onlineList;
	public JList<RosterEntry> offlineList;
	public JComboBox<Status> presenceComboBox;
	public JTextField presenceStatusField;

	public FriendsListView() {
		super();
		this.setTitle("Friends");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(400, 800);
		this.setLayout(new BorderLayout());
		JSplitPane splitter = new JSplitPane();
		splitter.setResizeWeight(0.5);

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(new JLabel("Online:"), BorderLayout.NORTH);
		this.onlineList = new JList<RosterEntry>();
		this.onlineList.setCellRenderer(new FriendListItemRenderer());
		topPanel.add(new JScrollPane(this.onlineList), BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(new JLabel("Offline:"), BorderLayout.NORTH);
		this.offlineList = new JList<RosterEntry>();
		this.offlineList.setCellRenderer(new FriendListItemRenderer());
		bottomPanel.add(new JScrollPane(this.offlineList), BorderLayout.CENTER);

		splitter.setTopComponent(topPanel);
		splitter.setBottomComponent(bottomPanel);
		this.add(splitter,BorderLayout.CENTER);
    
	    JPanel presencePanel = new JPanel(new GridLayout(2,1));
	    presenceComboBox = new JComboBox<Status>();
	    presenceComboBox.setModel(new StatusComboBoxModel());
	    presenceComboBox.setRenderer(new StatusComboBoxRenderer());
	    presencePanel.add(presenceComboBox);
	    presenceStatusField = new JTextField();
	    presencePanel.add(presenceStatusField);
	    this.add(presencePanel,BorderLayout.SOUTH);
    
		this.setVisible(true);
	}
}
