package com.kolakcc.loljclient.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.FocusTraversalPolicy;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.kolakcc.loljclient.model.swing.LoLNewsTableModel;

public class LoginView extends KolaView {
	public JTextField usernameField;
	public JPasswordField passwordField;
	public JButton loginButton;
	public JComboBox<String> regionField;
	public JTable newsList;
	public JLabel statusLabel;

	public JCheckBox rememberUsername, rememberPassword;
	LoLNewsTableModel newsModel;

	public LoginView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("loljclient");
		this.setSize(400,600);
		
		this.newsList = new JTable();
		JScrollPane newsScrollPanel = new JScrollPane(this.newsList);
		this.add(newsScrollPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel formPanel = new JPanel(new GridLayout(6, 1));
		
		JPanel regionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		regionPanel.add(new JLabel("Region:"));
		formPanel.add(regionPanel);
		
		this.regionField = new JComboBox<String>();
		formPanel.add(regionField);

		JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		usernamePanel.add(new JLabel("Username:"));
		rememberUsername = new JCheckBox("Remember");
		usernamePanel.add(rememberUsername);
		formPanel.add(usernamePanel);
		
		this.usernameField = new JTextField(20);
		formPanel.add(usernameField);

		JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		passwordPanel.add(new JLabel("Password:"));
		rememberPassword = new JCheckBox("Remember");
		passwordPanel.add(rememberPassword);
		formPanel.add(passwordPanel);
		
		this.passwordField = new JPasswordField(20);
		formPanel.add(passwordField);
		
		bottomPanel.add(formPanel, BorderLayout.NORTH);

		this.loginButton = new JButton("Login");
		bottomPanel.add(this.loginButton, BorderLayout.SOUTH);
		
		this.add(bottomPanel,BorderLayout.SOUTH);
		
		usernameField.requestFocusInWindow();
		ArrayList<Component> focusList = new ArrayList<Component>();
		focusList.add(usernameField);
		focusList.add(passwordField);
		focusList.add(loginButton);
		this.setFocusTraversalPolicy(new ListFocusTraversalPolicy(focusList));
		this.setVisible(true);
	}
}
class ListFocusTraversalPolicy extends FocusTraversalPolicy {
	ArrayList<Component> order;
	
	public ListFocusTraversalPolicy(ArrayList<Component> order) {
		this.order = new ArrayList<Component>(order.size());
		this.order.addAll(order);
	}
	public Component getComponentAfter(Container focusCycleRoot,
	                         Component aComponent)
	{
		int idx = (order.indexOf(aComponent) + 1) % order.size();
		return order.get(idx);
	}
	
	public Component getComponentBefore(Container focusCycleRoot,
	                          Component aComponent)
	{
		int idx = order.indexOf(aComponent) - 1;
		if (idx < 0) {
			idx = order.size() - 1;
		}
		return order.get(idx);
	}
	
	public Component getDefaultComponent(Container focusCycleRoot) {
		return order.get(0);
	}
	
	public Component getLastComponent(Container focusCycleRoot) {
		return order.get(order.size());
	}
	
	public Component getFirstComponent(Container focusCycleRoot) {
		return order.get(0);
	}
}