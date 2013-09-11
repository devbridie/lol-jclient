package com.kolakcc.loljclient.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import com.gvaneyck.rtmp.LoLRTMPSClient;
import com.kolakcc.loljclient.StartupClass;
import com.kolakcc.loljclient.model.ServerInfo;
import com.kolakcc.loljclient.model.swing.LoLNewsTableModel;
import com.kolakcc.loljclient.model.swing.RegionsComboBoxModel;
import com.kolakcc.loljclient.util.Configuration;
import com.kolakcc.loljclient.view.LoginView;

public class LoginController extends KolaController implements ActionListener,
		MouseListener {
	protected SwingWorker<LoLRTMPSClient, String> loginWorker;
	protected LoginView view;

	protected RegionsComboBoxModel regionsModel;
	protected LoLNewsTableModel newsModel;

	public LoginController() {
		this.initalizeWorkers();
		this.view = new LoginView();
		this.setView(this.view);
		this.view.usernameField.addActionListener(this);
		this.view.passwordField.addActionListener(this);
		this.view.loginButton.addActionListener(this);
		this.view.newsList.addMouseListener(this);
		this.view.regionField.addActionListener(this);
		this.view.rememberUsername.addActionListener(this);
		this.view.rememberPassword.addActionListener(this);
		
		this.regionsModel = new RegionsComboBoxModel();
		this.view.regionField.setModel(this.regionsModel);
		this.newsModel = new LoLNewsTableModel(this.regionsModel.getSelectedRegion().getNewsURL());
		this.view.newsList.setModel(this.newsModel);
		
		if (!Configuration.getDefaultUsername().isEmpty()) {
			this.view.rememberUsername.setSelected(true);
			this.view.usernameField.setText(Configuration.getDefaultUsername());
		} else { this.view.rememberPassword.setEnabled(false); }
		if (!Configuration.getDefaultPassword().isEmpty()) {
			this.view.rememberPassword.setEnabled(true);
			this.view.rememberPassword.setSelected(true);
			this.view.passwordField.setText(Configuration.getDefaultPassword());
		}
		if (!Configuration.getDefaultRegion().isEmpty()) {
			this.view.regionField.setSelectedItem(regionsModel.getRegion(Configuration.getDefaultRegion()));
		}
		if ((!Configuration.getDefaultPassword().isEmpty()) && (!Configuration.getDefaultUsername().isEmpty())) {
			this.actionPerformed(new ActionEvent(this.view.passwordField,ActionEvent.ACTION_PERFORMED,""));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.view.regionField) {
			//this.newsModel.clear();
			//this.newsModel.populateModel(this.regionsModel.getSelectedRegion().newsURL);
		} else if (e.getSource().equals(this.view.rememberUsername)) {
			if (this.view.rememberUsername.isSelected()) {
				this.view.rememberPassword.setEnabled(true);
			} else {
				this.view.rememberPassword.setEnabled(false);
				this.view.rememberPassword.setSelected(false);
				Configuration.set("username", "");
				Configuration.set("password", "");
			}
		} else if (e.getSource().equals(this.view.rememberPassword)) {
			if (!this.view.rememberPassword.isEnabled()) Configuration.set("password", "");
		} else {
			this.view.loginButton.setEnabled(false);
			this.loginWorker.execute();
		}
	}

	public void dispose() {
		this.view.dispose();
	}

	public void initalizeWorkers() {
		this.loginWorker = new SwingWorker<LoLRTMPSClient, String>() {
			public LoLRTMPSClient doInBackground() throws Exception {
				this.publish("Connecting to the login server...");
				String username = LoginController.this.view.usernameField.getText();
				String password = new String(LoginController.this.view.passwordField.getPassword());
				LoLRTMPSClient client = new LoLRTMPSClient(regionsModel.getSelectedRegion(), Configuration.PVPVersion, username, password);
				client.connect();
				this.publish("Logging in...");
				client.login();
				this.publish("Sucessfully logged in!");
				return client;
			}

			public void done() {
				try {
					if (LoginController.this.view.rememberUsername.isSelected()) Configuration.set("username", LoginController.this.view.usernameField.getText());
					if (LoginController.this.view.rememberPassword.isSelected()) Configuration.set("password", new String(LoginController.this.view.passwordField.getPassword()));
					Configuration.set("region", LoginController.this.regionsModel.getSelectedRegion().getRegion());
					ServerInfo.currentServerInfo = LoginController.this.regionsModel.getSelectedRegion();
					
					StartupClass.Client = this.get();
					StartupClass.LoggedIn();
					StartupClass.openFriends(
							LoginController.this.view.usernameField.getText(),
							new String(LoginController.this.view.passwordField.getPassword())
                    );
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
					LoginController.this.view.loginButton.setText("Login");
					LoginController.this.view.loginButton.setEnabled(true);
					LoginController.this.initalizeWorkers();
					LoginController.this.HandleException("Invalid username/password.",e);
				}
			}

			public void process(List<String> progress) {
				LoginController.this.view.loginButton.setText(progress.get(progress.size() - 1));
			}
		};
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			StartupClass.openNewsItem(this.newsModel.getItemAt(this.view.newsList.getSelectedRow()));
		}
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
