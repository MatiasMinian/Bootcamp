package com.bootcamp.app.model.responses;

public class MessageResponse {
	
	private String status;
	private String message;
	
	/* *** CONSTRUCTORS *** */
	
	public MessageResponse() {
	}
	
	public MessageResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}
	
	/* *** GETTERS & SETTERS *** */
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}