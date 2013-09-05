package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import com.kolakcc.loljclient.model.ModelFromTO;
import com.kolakcc.loljclient.view.ui.VerticalJScrollPane;

public class DebugView extends KolaView {
	JTextArea outArea;
	JTextArea errArea;
	
	static JTextArea objects;
	
	public DebugView() {
		super();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(700, 200);
		this.setTitle("Debug");
		this.setLayout(new BorderLayout());
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		outArea = new JTextArea();
		outArea.setEditable(false);
		outArea.setLineWrap(true);
		tabbedPane.addTab("System.out", new VerticalJScrollPane(outArea));
		
		errArea = new JTextArea();
		errArea.setEditable(false);
		errArea.setLineWrap(true);
		tabbedPane.addTab("System.err", new VerticalJScrollPane(errArea));
		
		objects = new JTextArea();
		objects.setEditable(false);
		objects.setLineWrap(true);
		tabbedPane.addTab("Objects", new VerticalJScrollPane(objects));

		this.add(tabbedPane, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void addOutLine(String line) {
		outArea.append(line);
	}
	public void addErrLine(String line) {
		errArea.append(line);
	}
	
	public static void addObject(ModelFromTO model) {
		objects.append(model.toString());
	}
}
