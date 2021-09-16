package com.epam.solution.ecommerce.model;

import java.util.Date;

public class ErrorMessage {
	
	private int statusCode;
	
	private Date timestamp;
	
	private String message;
	
	private String requestedResource;

	public ErrorMessage(final int statusCode, final Date timestamp, final String message, final String requestedResource) {
		
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
		this.requestedResource = requestedResource;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getRequestedResource() {
		return requestedResource;
	}

	public void setRequestedResource(String requestedResource) {
		this.requestedResource = requestedResource;
	}
}
