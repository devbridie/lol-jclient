package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

public class AboutView extends KolaView {
	public String aboutText;

	public AboutView(String textToDisplay) {
		super();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500, 400);
		this.setTitle("About");
		this.setLayout(new BorderLayout());

		JEditorPane area = new JEditorPane("text/html",textToDisplay);
		area.setEditable(false);

		this.add(area, BorderLayout.CENTER);

		this.setVisible(true);
	}
}
