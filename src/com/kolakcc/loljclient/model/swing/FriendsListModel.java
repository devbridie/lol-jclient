package com.kolakcc.loljclient.model.swing;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractListModel;
import javax.swing.event.ListDataListener;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Presence;

import com.kolakcc.loljclient.model.XMPPWrapper;

public class FriendsListModel extends AbstractListModel<RosterEntry> {
	public enum ShowMode {
		ONLINE, OFFLINE
	};

	public ArrayList<RosterEntry> filteredEntries;

	protected ShowMode displayMode;

	public FriendsListModel(ShowMode sm) {
		this.displayMode = sm;
		this.filteredEntries = new ArrayList<RosterEntry>();
		this.refreshFriends();
	}

	public void refreshFriends() {
		this.filteredEntries.clear();
		Roster roster = XMPPWrapper.getConnection().getRoster();
		Iterator<RosterEntry> it = roster.getEntries().iterator();
		while (it.hasNext()) {
			RosterEntry friend = it.next();
			Presence p = roster.getPresence(friend.getUser());
			if ((this.displayMode == ShowMode.ONLINE) && (p.isAvailable())) {
				this.filteredEntries.add(friend);
			}
			if ((this.displayMode == ShowMode.OFFLINE) && (!p.isAvailable())) {
				this.filteredEntries.add(friend);
			}
		}
	}

	public RosterEntry getElementAt(int index) {
		return this.filteredEntries.get(index);
	}

	public int getSize() {
		return this.filteredEntries.size();
	}

	public void addListDataListener(ListDataListener l) {
	}
	public void removeListDataListener(ListDataListener l) {
	}
}