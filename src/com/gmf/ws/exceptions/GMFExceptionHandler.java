/**
 * @author Siva_Varma
 * $Id: $
 * $Copyright: $
 */
package com.gmf.ws.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.utils.ResponseMessage;

/**
 * The GMFExceptionHandler Class is to handle all DalExceptions.
 */

@Provider
public class GMFExceptionHandler implements ExceptionMapper<GMFException> {
	private Logger logger = LoggerFactory.getLogger(GMFExceptionHandler.class
			.getCanonicalName());
	@Override
	public Response toResponse(GMFException ex) {
logger.debug("In GMFExceptionHandler Response");
		Status responseStatus = Status.SERVICE_UNAVAILABLE;
		String message = ex.getMessage();
		GMFExceptionTypes type = null;
		type = ex.getType();
		ResponseMessage rm = null;
		String errorCode = type.getCode();
		switch (errorCode) {
		case "GMF-004":
			responseStatus = Status.UNAUTHORIZED;
			break;

		case "GMF-017":
			responseStatus = Status.BAD_REQUEST;
			break;

		case "GMF-013":
			responseStatus = Status.FORBIDDEN;
			break;

		default:
			responseStatus = Status.SERVICE_UNAVAILABLE;
			message = "Service Unavilable, please try again later";

		}

		rm = new ResponseMessage(new String(responseStatus.getStatusCode()
				+ " " + responseStatus.name()), errorCode, message);

		logger.debug("Completed GMFExceptionHandler Response");
		return Response.status(responseStatus).entity(rm.toString()).build();
	}
}
