package com.kolakcc.loljclient.util;

import javax.swing.SwingWorker;

public abstract class NamedSwingWorker<T, V> extends SwingWorker<T, V> {
	private final String taskName;
	public NamedSwingWorker(String name) {
		this.taskName = name;
	}
	public String getTaskName() {
		return taskName;
	}
	public void handleWorkerException(String description, Exception e) {
		//Logger.getLogger(getClass().getName())
		
	}
}
