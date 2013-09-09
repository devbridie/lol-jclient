package com.kolakcc.loljclient.controller;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.RosterEntry;

import com.kolakcc.loljclient.model.XMPPWrapper;
import com.kolakcc.loljclient.view.FriendChatView;

public class FriendChatController extends KolaController {
	FriendChatView view;
	String participantName;
	Chat chat;

	public FriendChatController(Chat c) {
		this.view = new FriendChatView();
		this.setView(FriendChatController.this.view);
		this.chat = c;
		this.initChatController();
	}

	public FriendChatController(RosterEntry e) {
		this.view = new FriendChatView();
		this.setView(FriendChatController.this.view);
		this.chat = XMPPWrapper.getConnection().getChatManager().createChat(e.getUser(), view.getMessageListener());
		this.initChatController();
	}
	
	public MessageListener getMessageListener() { return view.getMessageListener(); }

	private void initChatController() {
		view.setChat(chat);
		String friendAddress = FriendChatController.this.chat.getParticipant().split("/")[0];
		if (XMPPWrapper.getConnection().getRoster().contains(friendAddress)) { 
			participantName = XMPPWrapper.getConnection().getRoster().getEntry(friendAddress).getName();
			view.setInfo(XMPPWrapper.getConnection().getRoster().getPresence(friendAddress), participantName) ;
		}
		else participantName = "Unknown ("+friendAddress+")";
		view.setTitle(participantName);		
	}
}
