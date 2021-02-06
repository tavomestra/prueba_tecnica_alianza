package com.alianza.api.model.exceptions;


public class BadRequestException extends ApiException {
	private static final long serialVersionUID = 1L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(ErrorRq errorDto) {
		super(errorDto);
	}

	public BadRequestException(String msg) {
		super(msg);
	}
}
