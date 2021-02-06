package com.alianza.api.model.exceptions;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Class<?> exceptionClass;
	private final Class<?> notFoundClass;
	
	public DataNotFoundException(Class<?> exceptionClass, Class<?> notFoundClass) {
		super();
		this.exceptionClass = exceptionClass;
		this.notFoundClass = notFoundClass;
	}

	public Class<?> getExceptionClass() {
		return exceptionClass;
	}
	
	public Class<?> getNotFoundClass() {
		return notFoundClass;
    }

}
