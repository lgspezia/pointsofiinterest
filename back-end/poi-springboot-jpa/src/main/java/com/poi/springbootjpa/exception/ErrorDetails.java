package com.poi.springbootjpa.exception;

import java.util.Date;

public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String detail;

	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.detail = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetail() {
		return detail;
	}
}
