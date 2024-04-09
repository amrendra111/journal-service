package com.apica.journal.service.exception;

import org.springframework.http.HttpStatus;

public class JournalServiceApiException extends RuntimeException {

	private static final long serialVersionUID = -2667164554068478071L;
	private HttpStatus status;

	public JournalServiceApiException(String message, HttpStatus status) {
		super(message);
		this.setStatus(status);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
