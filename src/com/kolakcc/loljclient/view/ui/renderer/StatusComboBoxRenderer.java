package com.kolakcc.loljclient.view.ui.renderer;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import com.kolakcc.loljclient.model.Status;

public class StatusComboBoxRenderer implements ListCellRenderer<Status> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Status> list,
			Status value, int index, boolean isSelected, boolean cellHasFocus) {
		if (value == null) return null;
		JPanel ret = new JPanel(new BorderLayout());
		ret.setOpaque(false);
	    JLabel statusIconLabel = new JLabel(value.getDisplay(),value.getIcon(),JLabel.LEFT);
	    statusIconLabel.setBorder(new EmptyBorder(0,5,0,5));
	    ret.add(statusIconLabel, BorderLayout.WEST);
		return ret;
	}
	
}
