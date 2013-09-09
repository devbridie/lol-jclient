package com.kolakcc.loljclient.view.ui;

import java.awt.BorderLayout;
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

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextField; 
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import com.kolakcc.loljclient.StartupClass;
import com.kolakcc.loljclient.controller.CustomGameLobbyController;

public class ChatComponent extends JPanel implements ActionListener, MessageListener, HyperlinkListener {
	JEditorPane chatArea;
	JTextField outgoingChatField;
	LocalizedJButton sendButton;
	Chat chat;
	
	static DateFormat userFormat = new SimpleDateFormat("HH:mm:ss");
	
	public ChatComponent() {
		super(new BorderLayout());
		
		chatArea = new JEditorPane();
		chatArea.setEditorKit(new HTMLEditorKit());
		chatArea.setDocument(new HTMLDocument());
		chatArea.setEditable(false);
		chatArea.addHyperlinkListener(this);
		this.add(new VerticalJScrollPane(chatArea), BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout());
		outgoingChatField = new JTextField();
		outgoingChatField.addActionListener(this);
		bottomPanel.add(outgoingChatField, BorderLayout.CENTER);
		
		sendButton = new LocalizedJButton("send");
		sendButton.addActionListener(this);
		bottomPanel.add(sendButton, BorderLayout.EAST);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public void setChat(Chat c) {
		chat = c;
	}
	
	public void actionPerformed(ActionEvent event) {
		try {
			this.chat.sendMessage(outgoingChatField.getText());
			this.addLine(ChatComponent.userFormat.format(new Date()) + " you: "
					+ outgoingChatField.getText());
		} catch (XMPPException e) {
			this.addLine("Error delivering message: " + outgoingChatField.getText());
		}
		outgoingChatField.setText("");
	}
	
	public void addLine(String line) {
		HTMLDocument chatDocument = (HTMLDocument) chatArea.getDocument();
		HTMLEditorKit chatKit = (HTMLEditorKit) chatArea.getEditorKit();
		try {
			chatKit.insertHTML(chatDocument, chatDocument.getLength(), line, 0, 0, null);
		} catch (BadLocationException | IOException e) {
			e.printStackTrace();
		}
		chatArea.setCaretPosition(chatArea.getDocument().getLength());
	}
	
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
	public void processMessage(Chat chat, final Message message) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
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
				String displayText = message.getFrom() + ": " + formattedMessage;
				if (message.getBody().contains("<body>")) {
					System.out.println("Recieved probable xml message:");
					System.out.println(message.getBody());
					Matcher inviteMatcher = Pattern.compile("<gameType>(.+?)</gameType>.*<gameId>(.+?)</gameId>").matcher(message.getBody());
					while (inviteMatcher.find()) {
						displayText = message.getFrom() + " invited you to game #<u><a href=\"invite/" + inviteMatcher.group(2) + "/" + inviteMatcher.group(1) + "\">" + inviteMatcher.group(2) + "</a></u>";
					}
				}
				addLine(ChatComponent.userFormat.format(new Date()) + " "+ displayText);
			}
		});
	}
}
