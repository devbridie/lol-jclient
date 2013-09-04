package com.kolakcc.loljclient.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.debugger.ConsoleDebugger;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;

import com.kolakcc.loljclient.model.ServerInfo;
import com.kolakcc.loljclient.model.Status;
import com.kolakcc.loljclient.model.XMPPWrapper;
import com.kolakcc.loljclient.model.swing.FriendsListModel;
import com.kolakcc.loljclient.model.swing.StatusComboBoxModel;
import com.kolakcc.loljclient.util.Configuration;
import com.kolakcc.loljclient.view.FriendsListView;

public class FriendsListController extends KolaController implements
		MouseListener, DocumentListener, RosterListener, ChatManagerListener {
	private FriendsListView view;
	protected FriendsListModel onlineModel;
	protected FriendsListModel offlineModel;
	SwingWorker<Void, Void> connectionWorker;

	public FriendsListController(String username, String password) {
		if (ServerInfo.currentServerInfo.XMPPserver == null) { 
			JOptionPane.showMessageDialog(null, "You're using a region of League of Legends that does not yet have a chat server configured - please contact KolakCC to get it added!");
			return; 
		}
		this.view = new FriendsListView();
		this.setView(this.view);
		this.initializeWorkers(username, password);
		
		this.onlineModel = new FriendsListModel(FriendsListModel.ShowMode.ONLINE);
		this.view.onlineList.setModel(onlineModel);
		this.view.onlineList.addMouseListener(this);
		
		this.offlineModel = new FriendsListModel(FriendsListModel.ShowMode.OFFLINE);
		this.view.offlineList.setModel(offlineModel);
		
		this.view.presenceComboBox.setModel(new StatusComboBoxModel());
		this.view.presenceStatusField.getDocument().addDocumentListener(this);
		this.connectionWorker.execute();
		
	}

	public void initializeWorkers(final String username, final String password) {
		this.connectionWorker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {
				XMPPWrapper.login(username, password);
				FriendsListController.this.updateStatus(Configuration.getXMPPStatus());
				FriendsListController.this.view.presenceStatusField.setText(Configuration.getXMPPStatus());
				FriendsListController.this.refreshLists();
				
				XMPPWrapper.getConnection().getRoster()
						.addRosterListener(FriendsListController.this);
				XMPPWrapper.getConnection().getChatManager().addChatListener(FriendsListController.this);
				MultiUserChat.addInvitationListener(XMPPWrapper.getConnection(), new InvitationListener() {
					
					@Override
					public void invitationReceived(Connection conn, String room, String inviter, String reason, String password, Message message) {
						
					}
				});
				new ConsoleDebugger(XMPPWrapper.getConnection(), new OutputStreamWriter(System.out), new InputStreamReader(System.in));
				return null;
			}
		};
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getClickCount() == 2) && (this.view.onlineList.getSelectedIndex() != -1)) {
			new FriendChatController(
					this.onlineModel.getElementAt(this.view.onlineList
							.getSelectedIndex()));
		}
		
	}

	public void refreshLists() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() { //TODO: find out why I can't update the model instead of replacing the model
				onlineModel = new FriendsListModel(FriendsListModel.ShowMode.ONLINE);
				FriendsListController.this.view.onlineList.setModel(onlineModel);
				offlineModel = new FriendsListModel(FriendsListModel.ShowMode.OFFLINE);
				FriendsListController.this.view.offlineList.setModel(offlineModel);
			}
		});
	}

	public void updateStatus(String status) {
		//TODO: totally editable messages
		Configuration.setStatus(status);
		Configuration.flushConfig();
		String statusXML = String.format("<body><level>99</level><profileIcon>28</profileIcon><wins>9001</wins><rankedWins>9001</rankedWins><statusMsg>%s</statusMsg></body>", status);
		Status selectedStatus = (Status) this.view.presenceComboBox.getSelectedItem();
		Mode presenceMode = Presence.Mode.chat;
		if (selectedStatus.getDisplay().equals("Away")) presenceMode = Presence.Mode.away;
		Presence newPresence = new Presence(Presence.Type.available,statusXML,1,presenceMode);
		XMPPWrapper.getConnection().sendPacket(newPresence);
	}
	public void presenceChanged(Presence presence) {
		this.refreshLists();
	}
	public void chatCreated(Chat chat, boolean createdLocally) {
		if (!createdLocally) {
			chat.addMessageListener(new FriendChatController(chat));
		}
	}
	@Override
	public void insertUpdate(DocumentEvent e) {
		updateStatus(this.view.presenceStatusField.getText());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		updateStatus(this.view.presenceStatusField.getText());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {}
	
	public void entriesAdded(Collection<String> addresses) {
	}

	public void entriesDeleted(Collection<String> addresses) {
	}

	public void entriesUpdated(Collection<String> addresses) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
