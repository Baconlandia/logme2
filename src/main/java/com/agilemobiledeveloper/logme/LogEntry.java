package com.agilemobiledeveloper.logme;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="logs")
public class LogEntry implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2166539728939295098L;

	@Id private String id;
	
	private String caller;
	
	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	private String logSubject;
	
	private String logMessage;
	
	public void setLogSubject(String l) { 
		logSubject = l;
	}
	
	public String getLogSubject() {
		return this.logSubject;
	}
	
	public void setLogMessage(String m) {
		logMessage = m;
	}
	
	public String getLogMessage() { 
		return this.logMessage;
	}

	public LogEntry() {
		super();
	}

	public LogEntry(String caller, String logSubject, String logMessage) {
		super();
		this.caller = caller;
		this.logSubject = logSubject;
		this.logMessage = logMessage;
	}

	@Override
	public String toString() {
		return "LogEntry [id=" + id + ", caller=" + caller + ", logSubject="
				+ logSubject + ", logMessage=" + logMessage + "]";
	}
		
}