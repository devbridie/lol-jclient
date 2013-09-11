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

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
                String type = result.getTO("data").getTO("body").type;
				if (result.getString("result").equals("receive")) {
					if (type.equals("com.riotgames.platform.game.GameDTO")) {
						StartupClass.customGameLobbyController.receivePacket(result
								.getTO("data").getTO("body"));
                    } else if (type.equals("com.riotgames.platform.messaging.ClientLoginKickNotification")){
                        System.out.println("Kicked out of client");
                        HandleException("You got kicked out of the client", "Kicked out of client");
                        System.exit(0);
					} else if (type.equals("com.riotgames.platform.game.PlayerCredentialsDto")) {
						System.out.println(result.getTO("data").getTO("body"));
						GameClient.start(new PlayerCredentials(result.getTO("data").getTO("body")));
					} else {
						System.out.println("Received packet: ");
						System.out.println(result);
					}
				}
			}
		});
	}
}
