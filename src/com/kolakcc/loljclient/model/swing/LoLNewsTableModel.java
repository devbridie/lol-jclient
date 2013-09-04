package com.kolakcc.loljclient.model.swing;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.kolakcc.loljclient.model.LoLNewsItem;

public class LoLNewsTableModel extends AbstractTableModel {
	ArrayList<LoLNewsItem> items = new ArrayList<LoLNewsItem>();
	private String[] columnNames = { "Title" };

	public LoLNewsTableModel(String URL) {
		populateModel(URL);
	}
	public void populateModel(String URL) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			HttpURLConnection httpcon = (HttpURLConnection) new URL(URL)
					.openConnection();
			httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
			Document doc = dBuilder.parse(httpcon.getInputStream());
			doc.getDocumentElement().normalize();
			NodeList articleItems = doc.getElementsByTagName("item");
			for (int i = 0; i < articleItems.getLength(); i++) {
				Node node = articleItems.item(i);
				if ((node.getNodeType() == Node.ELEMENT_NODE)
						&& (node.getNodeName() == "item")) {
					Element itemElement = (Element) node;
					this.items.add(new LoLNewsItem(itemElement));
				}
			}
		} catch (Exception e) {
			System.out.println("Error on reading the news.");
			e.printStackTrace();
		}
		this.fireTableDataChanged();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
		return String.class;
	}

	public int getColumnCount() {
		return this.columnNames.length;
	}

	public String getColumnName(int col) {
		return this.columnNames[col];
	}

	public LoLNewsItem getItemAt(int row) {
		return this.items.get(row);
	}

	public int getRowCount() {
		return this.items.size();
	}

	public Object getValueAt(int row, int col) {
		LoLNewsItem selectedObject = this.items.get(row);
		if (col == 0) {
			return selectedObject.title;
		}
		return "???";
	}
	public void clear() {
		this.items.clear();
	}
}