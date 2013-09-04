package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.camick.WrapLayout;

public class CustomGameFilterView extends KolaView {
	public JTextField filterField;
	
	public JCheckBox privateBox;
	public JCheckBox srBox;
	public JCheckBox ttBox;
	public JCheckBox pgBox;
	public JCheckBox doBox;
	
	
	public CustomGameFilterView() {
		super();
		this.setTitle("Custom Games Filter");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		filterField = new JTextField(20);
		this.add(filterField,BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel(new WrapLayout());
		
		privateBox = new JCheckBox("Private",true);
		centerPanel.add(privateBox);
		
		srBox = new JCheckBox("Summoner's Rift",true);
		centerPanel.add(srBox);
		
		ttBox = new JCheckBox("Twisted Treeline",true);
		centerPanel.add(ttBox);
		
		pgBox = new JCheckBox("Proving Grounds",true);
		centerPanel.add(pgBox);
		
		doBox = new JCheckBox("Dominion",true);
		centerPanel.add(doBox);
		this.add(centerPanel, BorderLayout.CENTER);
		
		this.setSize(350,150);
		this.setVisible(true);
	}
	public void addCheckboxListeners(ItemListener l) {
		privateBox.addItemListener(l);
		srBox.addItemListener(l);
		ttBox.addItemListener(l);
		pgBox.addItemListener(l);
		doBox.addItemListener(l);
	}
}
