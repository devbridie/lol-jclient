package com.kolakcc.loljclient.view.ui;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.kolakcc.loljclient.StartupClass;
import com.kolakcc.loljclient.model.Champion;

public class ChampionBox extends JLabel implements MouseListener {
	protected ImageIcon icon;
	protected Champion champion;
	public ChampionBox() {
		super();
		this.addMouseListener(this);
	}
	
	public ChampionBox(Champion c) throws Exception {
		this();
		champion = c;
		icon = new ImageIcon(c.getIcon());
		super.setIcon(this.icon);
	}
	
	public ChampionBox(Champion c, int w, int h) throws Exception {
		this(c);
		this.setSize(w, h);
	}
	public void setChampion(Champion c) throws Exception {
		champion = c;
		icon = new ImageIcon(c.getIcon());
		super.setIcon(this.icon);
	}
	
	public void setSize(int w, int h) {
		if (w == 0 || h == 0) { w = 120; h = 120; }
		if (this.icon != null) this.setIcon(new ImageIcon(this.icon.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
		super.setSize(w, h);
		this.revalidate();
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			StartupClass.openChampionDetails(this.champion);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public Champion getChampion() {
		return champion;
	}
}
