/**
 * @author Siva_Varma
 * $Id: $
 * $Copyright: $
 */
package com.gmf.ws.utils;

/**
 * The ResponseMessage is a bean which carries status, errorCode, message.
 * 
 */
public class ResponseMessage {
	private String status;
	private String errorCode;
	private String message;

	public ResponseMessage(String status, String errorCode, String message) {
		super();
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return new GMFUtility().convertToJSON(this);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
