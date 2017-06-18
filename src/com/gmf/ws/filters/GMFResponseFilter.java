/**
 * @author Siva_Varma
 * $Id: $
 * $Copyright: $
 */
package com.gmf.ws.filters;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * The GMFResponseFilter class responsible to append access controls to the
 * response.
 */

public class GMFResponseFilter implements ContainerResponseFilter {
	private Logger logger = LoggerFactory.getLogger(GMFResponseFilter.class
			.getCanonicalName());

	@Override
	public ContainerResponse filter(ContainerRequest request,
			ContainerResponse response) {
		logger.debug("In Response Filter");
		ResponseBuilder responseBuilder = Response.fromResponse(response
				.getResponse());
		responseBuilder
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
				.header("Content-Type", "application/json")
				.header("Access-Control-Allow-Headers",
						" Content-Type, Authorization, X-Requested-With");

		String requestHeader = request
				.getHeaderValue("Access-Control-Request-Headers");

		if (null != requestHeader && !requestHeader.equals(null)) {
			responseBuilder.header("Access-Control-Allow-Headers",
					requestHeader);
		}
		response.setResponse(responseBuilder.build());
		logger.debug("Completed Response Filter");
		return response;
	}

}