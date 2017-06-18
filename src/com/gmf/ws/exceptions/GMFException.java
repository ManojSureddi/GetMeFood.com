/**
 * @author Siva_Varma
 * $Id: $
 * $Copyright: $
 */
package com.gmf.ws.exceptions;

/**
 * The RestException Class is a custom Exception class which inherits
 * RuntimeException.
 */
public class GMFException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message=null;
    private GMFExceptionTypes type=null;
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public GMFException(GMFExceptionTypes type, String message) {
		super();
		this.message = message;
		this.type = type;
	}
	
	public GMFException(GMFExceptionTypes type)
	{
		super();
		this.type = type;
	}
	public GMFExceptionTypes getType()
	{
		return type;
	}

}
