package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.kolakcc.loljclient.model.LoLNewsItem;

public class WebView extends KolaView {
	JEditorPane pane;
	LoLNewsItem item;

	public WebView(LoLNewsItem i) {
		super();
		this.item = i;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.setLayout(new BorderLayout());
		this.pane = new JEditorPane();
		this.pane.setEditable(false);
		this.add(new JScrollPane(this.pane), BorderLayout.CENTER);
		this.setTitle(this.item.title);
		this.setSize(600, 600);
		this.setVisible(true);
		this.setContent(this.item.content);
	}

	protected void setContent(String content) {
		this.pane.setContentType("text/html");
		this.pane.setText(content.replace('\u2019', '\'').replace('\u201C', '"'));
		this.pane.setCaretPosition(0);
	}
}
