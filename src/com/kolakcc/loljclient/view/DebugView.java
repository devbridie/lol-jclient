package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import com.kolakcc.loljclient.model.ModelFromTO;
import com.kolakcc.loljclient.view.ui.VerticalJScrollPane;

public class DebugView extends KolaView {
	JTextArea area;
	
	static JTextArea objects;
	
	public DebugView() {
		super();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(700, 200);
		this.setTitle("Debug");
		this.setLayout(new BorderLayout());
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		area = new JTextArea();
		area.setEditable(false);
		area.setLineWrap(true);
		tabbedPane.addTab("Console", new VerticalJScrollPane(area));
		
		objects = new JTextArea();
		objects.setEditable(false);
		objects.setLineWrap(true);
		tabbedPane.addTab("Objects", new VerticalJScrollPane(objects));

		this.add(tabbedPane, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void addLine(String line) {
		area.append(line);
	}
	
	public static void addObject(ModelFromTO model) {
		objects.append(model.toString());
	}
}
