package com.gvaneyck.rtmp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LCDSHeartbeat {
    private static Thread curThread;
    private int heartbeat;
    private SimpleDateFormat sdf = new SimpleDateFormat("ddd MMM d yyyy HH:mm:ss 'GMTZ'");
    private LoLRTMPSClient client;

    public LCDSHeartbeat(LoLRTMPSClient client) {
        heartbeat = 1;
        this.client = client;
        curThread = new Thread() {
            public void run() {
                beatHeart(this);
            }
        };
        curThread.setName("LCDSHeartbeat (beatHeart)");
        curThread.setDaemon(true);
        curThread.start();
    }

    private void beatHeart(Thread thread) {
        int accountID = client.getAccountID();
        String sessionToken = client.getSessionToken();
        try {
            while (curThread == thread) {
                long hbTime = System.currentTimeMillis();

                int id = client.invoke("loginService", "performLCDSHeartBeat", new Object[] { accountID, sessionToken, heartbeat, sdf.format(new Date()) });
                client.cancel(id); // Ignore result

                heartbeat++;

                // Quick sleeps to shutdown the heartbeat quickly on a reconnect
                while (curThread == thread && System.currentTimeMillis() - hbTime < 120000)
                    Thread.sleep(100);
            }
        }
        catch (Exception e) {
            client.doReconnect();
        }
    }
}
