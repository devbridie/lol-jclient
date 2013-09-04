package com.kolakcc.loljclient.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import com.kolakcc.loljclient.StartupClass;
import com.kolakcc.loljclient.model.XMPPWrapper;
import com.kolakcc.loljclient.view.FriendChatView;

public class FriendChatController extends KolaController implements
		ActionListener, MessageListener, HyperlinkListener {
	FriendChatView view;
	RosterEntry entry;
	Chat chat;
	DateFormat userFormat; // TODO: user set timestamp
	String participantName;

	public FriendChatController(Chat c) {
		this.chat = c;
		this.initChatController();
	}

	public FriendChatController(RosterEntry e) {
		this.chat = XMPPWrapper.getConnection().getChatManager()
				.createChat(e.getUser(), this);
		this.initChatController();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try {
			this.chat.sendMessage(this.view.talkArea.getText());
			this.view.addLine(this.userFormat.format(new Date()) + " you: "
					+ this.view.talkArea.getText());
		} catch (XMPPException e) {
			this.view.addLine("Error delivering message "
					+ this.view.talkArea.getText());
		}
		this.view.talkArea.setText("");
	}

	private void initChatController() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				FriendChatController.this.view = new FriendChatView();
				FriendChatController.this.setView(FriendChatController.this.view);
				FriendChatController.this.view.send.addActionListener(FriendChatController.this);
				FriendChatController.this.view.talkArea.addActionListener(FriendChatController.this);
				FriendChatController.this.view.chatArea.addHyperlinkListener(FriendChatController.this);
				//TODO: find out where I get a nullpointer on this
				String friendAddress = FriendChatController.this.chat.getParticipant().split("/")[0];
				if (XMPPWrapper.getConnection().getRoster().contains(friendAddress)) { 
					FriendChatController.this.participantName = XMPPWrapper.getConnection().getRoster().getEntry(friendAddress).getName();
					FriendChatController.this.view.setInfo(XMPPWrapper.getConnection().getRoster().getPresence(friendAddress),FriendChatController.this.participantName) ;
				}
				else FriendChatController.this.participantName = "Unknown ("+friendAddress+")";
				FriendChatController.this.view.setTitle(FriendChatController.this.participantName);
				FriendChatController.this.userFormat = new SimpleDateFormat("HH:mm:ss");				
			}
		});
	}

	public void processMessage(Chat chat, final Message message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (!FriendChatController.this.view.isVisible()) {
					FriendChatController.this.view.setVisible(true);
				}
				FriendChatController.this.view.toFront();
				String unformattedMessage = message.getBody().replace("<", "&lt;").replace(">", "&gt;");
				String formattedMessage = unformattedMessage;
				Matcher urlMatcher = Pattern.compile("((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\\+\\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)",Pattern.CASE_INSENSITIVE).matcher(unformattedMessage);
				while (urlMatcher.find()) {
					//Maybe a bit too hasty
					System.out.println("found url:");
					System.out.println(urlMatcher.group(0));
					String actualURL = urlMatcher.group(0);
					if (!actualURL.contains("http://")) actualURL = "http://" + actualURL;
					formattedMessage = formattedMessage.replace(urlMatcher.group(0), "<u><a href='" + urlMatcher.group(0) + "'>" + urlMatcher.group(0) + "</a></u>");
				}
				String displayText = FriendChatController.this.participantName + ": " + formattedMessage;
				if (message.getBody().contains("<body>")) {
					System.out.println("Recieved probable xml message:");
					System.out.println(message.getBody());
					Matcher inviteMatcher = Pattern.compile("<gameType>(.+?)</gameType>.*<gameId>(.+?)</gameId>").matcher(message.getBody());
					while (inviteMatcher.find()) {
						displayText = FriendChatController.this.participantName + " invited you to game #<u><a href=\"invite/" + inviteMatcher.group(2) + "/" + inviteMatcher.group(1) + "\">" + inviteMatcher.group(2) + "</a></u>";
					}
				}
				FriendChatController.this.view.addLine(FriendChatController.this.userFormat.format(new Date()) + " "
						+ displayText);
			}
		});
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			System.out.println(e.getDescription());
			String[] cutDescription = e.getDescription().split("/");
			if (cutDescription[0].equals("invite")) {
				StartupClass.customGameLobbyController = new CustomGameLobbyController(Integer.valueOf(cutDescription[1]));
			} else {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(e.getURL().toURI());
					} catch (IOException | URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}
