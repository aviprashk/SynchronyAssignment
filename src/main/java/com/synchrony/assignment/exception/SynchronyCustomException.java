package com.synchrony.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SynchronyCustomException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SynchronyCustomException() {
		super();
	}

	public SynchronyCustomException(String message) {
		super(message);
	}

	public SynchronyCustomException(String message, Throwable cause) {
		super(message, cause);
	}
}
