package com.kolakcc.loljclient.util;

import java.awt.Font;

public class FontUtils {
	public static Font emSize(Font font, double em) {
		return new Font(font.getName(), font.getStyle(), (int) (font.getSize() * em));
	}
	public static Font emSize(Font font, int style, double em) {
		return new Font(font.getName(), style, (int) (font.getSize() * em));
	}
}
