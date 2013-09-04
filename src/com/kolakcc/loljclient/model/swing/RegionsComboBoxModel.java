package com.kolakcc.loljclient.model.swing;

import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import com.kolakcc.loljclient.model.ServerInfo;
import com.kolakcc.loljclient.util.Configuration;
import com.kolakcc.loljclient.util.RegionsXMLReader;

public class RegionsComboBoxModel implements ComboBoxModel<String> {
	protected ArrayList<ServerInfo> regionList;
	protected ServerInfo selectedRegion;

	public RegionsComboBoxModel() {
		this.regionList = RegionsXMLReader.getServerInfo();
		//System.exit(0);
		
	}
	public ServerInfo getRegion(String abbreviation) {
		for (ServerInfo region : regionList) {
			if (region.getRegion().equals(abbreviation)) { return region; }
		}
		return null;
	}

	@Override
	public void addListDataListener(ListDataListener l) {
	}

	@Override
	public String getElementAt(int index) {
		return this.regionList.get(index).getName();
	}

	@Override
	public Object getSelectedItem() {
		if (selectedRegion == null) {
			for (ServerInfo info : regionList) {
				if (info.getRegion().equals(Configuration.getDefaultRegion())) { 
					selectedRegion = info; 
					break; 
				}
			}
		}
		return this.selectedRegion.getName();
	}

	public ServerInfo getSelectedRegion() {
		return this.selectedRegion;
	}

	@Override
	public int getSize() {
		return this.regionList.size();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
	}

	@Override
	public void setSelectedItem(Object anItem) {
		String search = (String) anItem;
		for (ServerInfo r : this.regionList) {
			if (r.getName().equals(search)) {
				this.selectedRegion = r;
			}
		}
	}
}
