package com.kolakcc.loljclient.controller;

import java.awt.Desktop;
import java.net.URI;
import java.util.concurrent.ExecutionException;

import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.StartupClass;
import com.kolakcc.loljclient.util.NamedSwingWorker;

public class StoreController extends KolaController {
	public StoreController() {
		new NamedSwingWorker<TypedObject,Void>("Store URL") {
			@Override
			protected TypedObject doInBackground() throws Exception {
				int id = StartupClass.Client.invoke("loginService", "getStoreUrl", new Object[] { });
				return StartupClass.Client.getResult(id);
			}
			@Override
			protected void done() {
				try {
					String url = get().getTO("data").getString("body");
					System.out.println(url);
					
					//TODO: write a better client for the shop.... will probably be a pain
					Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
				    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				        try {
				            desktop.browse(new URI(url));
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
				    }
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}.execute();
	}
}
