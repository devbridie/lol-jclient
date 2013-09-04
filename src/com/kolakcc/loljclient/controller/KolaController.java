package com.kolakcc.loljclient.controller;

import javax.swing.JOptionPane;

import com.kolakcc.loljclient.view.KolaView;

public abstract class KolaController {
	private KolaView _view;

	protected void HandleException(Exception e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(this._view, e.getMessage(),
				e.getMessage(), JOptionPane.ERROR_MESSAGE);
	}

	protected void HandleException(String description, Exception e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(this._view,
				description + "\r\n" + e.getMessage(), e.getMessage(),
				JOptionPane.ERROR_MESSAGE);
	}
	protected void HandleException(String description, String extra) {
		JOptionPane.showMessageDialog(this._view,
				description, extra,
				JOptionPane.ERROR_MESSAGE);
	}

	protected void setView(KolaView v) {
		this._view = v;
	}
}
