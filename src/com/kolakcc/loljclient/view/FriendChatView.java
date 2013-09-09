package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Presence;

import com.kolakcc.loljclient.util.FontUtils;
import com.kolakcc.loljclient.util.StatusIcon;
import com.kolakcc.loljclient.view.ui.ChatComponent;

public class FriendChatView extends KolaView {
	JLabel statusIcon, chatName;
	ChatComponent chatComponent;

	public FriendChatView() {
		super();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 800);
		this.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
		statusIcon = new JLabel();
		topPanel.add(statusIcon, BorderLayout.WEST);
		chatName = new JLabel();
		chatName.setFont(FontUtils.emSize(chatName.getFont(), 2));
		topPanel.add(chatName, BorderLayout.CENTER);
		this.add(topPanel, BorderLayout.NORTH);

		chatComponent = new ChatComponent();

		this.add(chatComponent, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public MessageListener getMessageListener() { return chatComponent; }

	public void addLine(String line) {
		chatComponent.addLine(line);
	}
	
	public void setInfo(Presence presence, String name) {
		chatName.setText(name);
		statusIcon.setIcon(StatusIcon.fromPresence(presence));
	}
	public void setChat(Chat chat) { chatComponent.setChat(chat); }
}
