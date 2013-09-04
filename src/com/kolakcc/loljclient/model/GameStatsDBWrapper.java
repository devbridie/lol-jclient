package com.kolakcc.loljclient.model;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.almworks.sqlite4java.SQLiteQueue;

public class GameStatsDBWrapper {
	private static SQLiteQueue _queue;

	public static SQLiteQueue getQueue() {
		if (_queue == null) {
			try {
				Logger.getLogger("com.almworks.sqlite4java").setLevel(Level.OFF);
				_queue = new SQLiteQueue(
						RADSAirClientWrapper
								.getFile("assets/data/gameStats/gameStats_en_US.sqlite"));
				_queue.start();
			} catch (Exception e) {
				System.out.println("Unable to open gameStats");
				e.printStackTrace();
			}
		}
		return _queue;
	}
}
