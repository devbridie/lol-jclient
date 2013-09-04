package com.kolakcc.loljclient.model;

import java.util.ArrayList;
import java.util.Date;

import com.gvaneyck.rtmp.encoding.TypedObject;
import com.kolakcc.loljclient.view.DebugView;

public class ModelFromTO { 
	TypedObject internalTypedObject;
	ArrayList<String> usedKeys;
	
	protected ModelFromTO() {}
	
	public ModelFromTO(TypedObject ito) {
		if (ito == null) {
			System.out.println("This internal typed object is null! " + this.getClass().getName());
		}
		internalTypedObject = ito;
		usedKeys = new ArrayList<String>();
	}
	
	protected void checkFields() {
		if (internalTypedObject != null) {
			for (String key : internalTypedObject.keySet()) {
				if (!usedKeys.contains(key)) {
					System.out.println(String.format("%s: Field %s = %s of type %s is not mapped!", getClass().getName(), key, internalTypedObject.get(key), internalTypedObject.type));
				}
			}
		}
	}
	protected boolean containsKey(String key) { return internalTypedObject.keySet().contains(key); }
	
	protected void checkAndAddKey(String key) {
		if (internalTypedObject != null)
		if (!containsKey(key)) { 
			System.out.println(String.format("%s: Field %s of type %s does not exist!", getClass().getName(), key, internalTypedObject.type));
			System.out.println(internalTypedObject);
		}
		usedKeys.add(key);
		
	}
	
	protected Integer getInt(String key) {
		checkAndAddKey(key);
		return internalTypedObject.getInt(key);
	}
	
	protected Double getDouble(String key) {
		checkAndAddKey(key);
		return internalTypedObject.getDouble(key);
	}
	
	protected Date getDate(String key) {
		checkAndAddKey(key);
		return internalTypedObject.getDate(key);
	}
	
	protected boolean getBool(String key) {
		checkAndAddKey(key);
		return internalTypedObject.getBool(key);
	}
	
	protected String getString(String key) {
		checkAndAddKey(key);
		return internalTypedObject.getString(key);
	}
	
	protected TypedObject[] getArray(String key) {
		checkAndAddKey(key);
		Object[] array = internalTypedObject.getArray(key);
		TypedObject[] ret = new TypedObject[array.length];
		for (int i = 0; i < array.length; i++) {
			ret[i] = (TypedObject) array[i];
		}
		return ret;
	}
	
	protected TypedObject getTO(String key) {
		checkAndAddKey(key);
		return internalTypedObject.getTO(key);
	}
	
	protected Object getObject(String key) {
		checkAndAddKey(key);
		return internalTypedObject.get(key);
	}
	
	protected Object getProbablyNull(String key) {
		checkAndAddKey(key);
		Object ret = internalTypedObject.get(key);
		if (ret != null) {
			System.out.println("HEY! There's some data here that you thought was null.");
			System.out.println(String.format("Field %s was expected to be null, is actually %s (%s)", key, ret, internalTypedObject.type));
		}
		return ret;
	}
	
	public TypedObject getiTO() { return internalTypedObject; }
	public void debug() { DebugView.addObject(this); }
}
