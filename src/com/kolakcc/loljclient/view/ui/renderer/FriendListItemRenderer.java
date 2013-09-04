package com.kolakcc.loljclient.view.ui.renderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.packet.Presence;

import com.kolakcc.loljclient.model.XMPPWrapper;
import com.kolakcc.loljclient.util.FontUtils;
import com.kolakcc.loljclient.util.StatusIcon;

public class FriendListItemRenderer implements ListCellRenderer<RosterEntry> {

	public Component getListCellRendererComponent(
			JList<? extends RosterEntry> list, RosterEntry value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel ret = new JPanel(new BorderLayout());
		ret.setOpaque(false);
		ret.setBorder(new EmptyBorder(0,0,5,0));
	    Presence p = XMPPWrapper.getConnection().getRoster().getPresence(value.getUser().split("/")[0]);
	    JLabel statusIconLabel = new JLabel(StatusIcon.getOffline());
	    statusIconLabel.setBorder(new EmptyBorder(0,5,0,5));
	    
	    JLabel nameLabel = new JLabel(value.getName());
	    nameLabel.setFont(FontUtils.emSize(nameLabel.getFont(), Font.BOLD, 1));
	    ret.add(nameLabel,BorderLayout.CENTER);
	    
	    String statusMsg = "<no message>";
	    if (p.getStatus() != null) {
	    	Matcher statusMatcher = Pattern.compile("<statusMsg>(.+?)</statusMsg>").matcher(p.getStatus());
		    while (statusMatcher.find()) {
		    	statusMsg = statusMatcher.group(1);
		    }
	    }
	    statusIconLabel.setIcon(StatusIcon.fromPresence(p));
	    ret.add(statusIconLabel,BorderLayout.WEST);
	    if (!p.getType().equals(Presence.Type.unavailable)) {
		    JLabel statusLabel = new JLabel(statusMsg);
		    statusLabel.setFont(FontUtils.emSize(statusLabel.getFont(), Font.PLAIN, 0.7));
		    statusLabel.setForeground(Color.DARK_GRAY);
		    ret.add(statusLabel,BorderLayout.SOUTH);
	    }
		return ret;
	}
}