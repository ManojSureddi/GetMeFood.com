package com.gmf.ws.dao;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	String message = null;
	DAOExceptionTypes exceptionType = null;

	public DAOException(DAOExceptionTypes exceptionType,String message) {
		this.exceptionType = exceptionType;
		this.message = message;
	}

	public DAOException(DAOExceptionTypes exceptionType) {
		this.exceptionType = exceptionType;
	}

}
