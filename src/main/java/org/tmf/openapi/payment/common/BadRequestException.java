package org.tmf.openapi.payment.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -3160905170466646936L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(String message) {
		super(message);
	}

}
