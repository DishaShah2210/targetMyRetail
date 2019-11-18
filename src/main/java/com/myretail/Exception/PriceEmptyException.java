package com.myretail.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class PriceEmptyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PriceEmptyException() {
		super();
	}

	public PriceEmptyException(String message) {
		super(message);
	}
	
	public PriceEmptyException(String message, Throwable cause) {
		super(message, cause);
	}
}
