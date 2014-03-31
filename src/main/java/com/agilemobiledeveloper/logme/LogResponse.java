package com.agilemobiledeveloper.logme;

import java.io.Serializable;

public class LogResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5460419258257082894L;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LogResponse [status=" + status + "]";
	}

	public LogResponse() {
		super();
	}

	public LogResponse(String status) {
		super();
		this.status = status;
	}
	

}
