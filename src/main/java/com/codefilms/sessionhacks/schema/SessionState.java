package com.codefilms.sessionhacks.schema;

public class SessionState {
	
	private String statusCode;
	private String runningSessionId;
	private String currentSessionId;
	private Integer timeToLive;
	private String message;


	public SessionState() {
		
	}
	
	

	public Integer getTimeToLive() {
		return timeToLive;
	}


	public void setTimeToLive(Integer theTimeToLive) {
		this.timeToLive = theTimeToLive;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getRunningSessionId() {
		return runningSessionId;
	}

	public void setRunningSessionId(String runningSessionId) {
		this.runningSessionId = runningSessionId;
	}

	public String getCurrentSessionId() {
		return currentSessionId;
	}

	public void setCurrentSessionId(String currentSessionId) {
		this.currentSessionId = currentSessionId;
	}
	
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	@Override
	public String toString() {
		return "SessionState [statusCode=" + statusCode + ", runningSessionId=" + runningSessionId
				+ ", currentSessionId=" + currentSessionId + ", timeToLive=" + timeToLive + ", message=" + message
				+ "]";
	}
	
	

	
	


	
	
	
	

}
