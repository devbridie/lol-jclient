package com.kolakcc.loljclient.model;
public class ServerInfo {
	public static ServerInfo currentServerInfo;
    public String region, platform, name, server, loginQueue, newsURL, XMPPserver;
    public boolean useGarena;

    public ServerInfo() {}

    public String toString() {
        return name;
    }

	public String getRegion() {
		return region;
	}

	public String getPlatform() {
		return platform;
	}

	public String getName() {
		return name;
	}

	public String getServer() {
		return server;
	}

	public String getLoginQueue() {
		return loginQueue;
	}

	public boolean isUseGarena() {
		return useGarena;
	}

	public String getNewsURL() {
		return newsURL;
	}

	public String getXMPPserver() {
		return XMPPserver;
	}
}