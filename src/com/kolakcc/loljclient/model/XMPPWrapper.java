package com.kolakcc.loljclient.model;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import com.gvaneyck.rtmp.DummySSLSocketFactory;
import com.sun.corba.se.spi.activation.Server;

public class XMPPWrapper {
	private static XMPPConnection connection;

	public static XMPPConnection getConnection() {
		if (connection == null) {
			//Connection.DEBUG_ENABLED = true;
			ConnectionConfiguration config = new ConnectionConfiguration(
					ServerInfo.currentServerInfo.XMPPserver, 5223);
			config.setSecurityMode(SecurityMode.enabled);
			config.setSocketFactory(new DummySSLSocketFactory());
			config.setCompressionEnabled(true);
			config.setServiceName("pvp.net");
			connection = new XMPPConnection(config);
			
		}
		return connection;
	}

	public static void login(String username, String password)
			throws XMPPException {
		if (ServerInfo.currentServerInfo.XMPPserver != null) {
			getConnection().connect();
			getConnection().login(username, "AIR_" + password);
		}
	}
}
