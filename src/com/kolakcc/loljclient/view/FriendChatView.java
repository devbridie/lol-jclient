package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.jivesoftware.smack.packet.Presence;

import com.kolakcc.loljclient.util.FontUtils;
import com.kolakcc.loljclient.util.StatusIcon;

public class FriendChatView extends KolaView {
	public JScrollPane chatScroller;
	public JEditorPane chatArea;
	public JTextField talkArea;
	public JButton send;
	
	JLabel statusIcon, chatName;

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

		this.chatArea = new JEditorPane();
		this.chatArea.setEditorKit(new HTMLEditorKit());
		this.chatArea.setDocument(new HTMLDocument());
		this.chatArea.setEditable(false);
		chatScroller = new JScrollPane(this.chatArea);
		this.add(chatScroller, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout());
		this.talkArea = new JTextField();
		bottomPanel.add(this.talkArea, BorderLayout.CENTER);
		this.send = new JButton("Send");
		bottomPanel.add(this.send, BorderLayout.EAST);

		this.add(bottomPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public void addLine(String line) {
		HTMLDocument chatDocument = (HTMLDocument) this.chatArea.getDocument();
		HTMLEditorKit chatKit = (HTMLEditorKit) this.chatArea.getEditorKit();
		try {
			chatKit.insertHTML(chatDocument, chatDocument.getLength(), line, 0, 0, null);
		} catch (BadLocationException | IOException e) {
			e.printStackTrace();
		}
		chatArea.setCaretPosition(chatArea.getDocument().getLength());
	}
	
	public void setInfo(Presence presence, String name) {
		chatName.setText(name);
		statusIcon.setIcon(StatusIcon.fromPresence(presence));
	}
}
