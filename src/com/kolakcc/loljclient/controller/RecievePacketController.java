package com.kolakcc.loljclient.controller;

import javax.swing.SwingUtilities;

import com.gvaneyck.rtmp.RTMPCallback;
import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.StartupClass;
import com.kolakcc.loljclient.model.PlayerCredentials;
import com.kolakcc.loljclient.util.GameClient;

public class RecievePacketController extends KolaController implements RTMPCallback {
	public RecievePacketController() {
	}

	@Override
	public void callback(final TypedObject result) {
		//TODO: find out why this doesn't receive the "kicked out of the client" packet

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (result.getString("result").equals("receive")) {
					if (result.getTO("data").getTO("body").type.equals("com.riotgames.platform.game.GameDTO")) {
						StartupClass.customGameLobbyController.receivePacket(result
								.getTO("data").getTO("body"));
					} else if (result.getTO("data").getTO("body").type.equals("com.riotgames.platform.game.PlayerCredentialsDto")) {
						System.out.println(result.getTO("data").getTO("body"));
						GameClient.start(new PlayerCredentials(result.getTO("data").getTO("body")));
					} else {
						System.out.println("Recieved packet: ");
						System.out.println(result);
					}
				}
			}
		});
	}
}
