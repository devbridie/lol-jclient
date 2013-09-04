package com.kolakcc.loljclient.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import com.kolakcc.loljclient.model.ServerInfo;

public class RegionsXMLReader {
	public static ArrayList<ServerInfo> getServerInfo() {
		ArrayList<ServerInfo> ret = new ArrayList<ServerInfo>();
		XMLConfiguration config;
		try {
			config = new XMLConfiguration(FileSystem.getFile("app://servers.xml"));
			List<HierarchicalConfiguration> servers = config.configurationsAt("server");
			for (HierarchicalConfiguration server : servers)
			{
				ServerInfo toBeAdded = new ServerInfo();
				toBeAdded.region = server.getString("region");
				toBeAdded.platform = server.getString("platform");
				toBeAdded.name = server.getString("name");
				toBeAdded.server = server.getString("server");
				toBeAdded.loginQueue = server.getString("loginQueue");
				toBeAdded.newsURL = server.getString("newsURL");
				toBeAdded.XMPPserver = server.getString("XMPPserver");
				toBeAdded.useGarena = server.getString("garena") != null;
				ret.add(toBeAdded);
			}
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
