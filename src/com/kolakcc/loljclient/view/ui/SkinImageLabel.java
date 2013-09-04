package com.kolakcc.loljclient.view.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.kolakcc.loljclient.model.ChampionSkin;

public class SkinImageLabel extends JLabel {
	public static enum DisplayType {
		PORTRAIT, SPLASH
	}

	protected ChampionSkin skin;
	protected ImageIcon icon;;

	public SkinImageLabel(final ChampionSkin skin, DisplayType display,
			final String championName) {
		super();
		this.skin = skin;

		try {
			if (display == DisplayType.PORTRAIT) {
				this.icon = new ImageIcon(skin.getPortrait());
			} else {
				this.icon = new ImageIcon(skin.getSplash());
			}
			this.setIcon(this.icon);
		} catch (Exception e) {
			System.out.println("Could not find image for " + skin.getName());
			e.printStackTrace();
		}
		if (display == DisplayType.PORTRAIT) {
			this.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JFrame skinSplashFrame = new JFrame(
							skin.isDefault() ? championName : skin
									.getDisplayName());
					skinSplashFrame
							.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					skinSplashFrame.add(new SkinImageLabel(skin,
							DisplayType.SPLASH, championName));
					skinSplashFrame.pack();
					skinSplashFrame.setVisible(true);
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mouseExited(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseReleased(MouseEvent e) {
				}
			});
		}
	}
}
