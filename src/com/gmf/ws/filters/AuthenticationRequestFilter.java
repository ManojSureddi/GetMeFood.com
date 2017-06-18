/*
 * Author: Siva_Varma
 * $Id: $
 * $Copyright: $
 */

package com.gmf.ws.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.bl.AuthenticateBL;
import com.gmf.ws.exceptions.GMFException;
import com.gmf.ws.exceptions.GMFExceptionTypes;
import com.gmf.ws.utils.HelperUtil;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

/**
 * @author siva_varma Description: The AuthenticationRequestFilter is to
 *         authenticate the request before accessing the resource.
 * 
 */
public class AuthenticationRequestFilter implements ContainerRequestFilter {
	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationRequestFilter.class.getCanonicalName());

	@Override
	public ContainerRequest filter(ContainerRequest requestContext) {

		String path = requestContext.getRequestUri().getPath();

		// options request doesn't need authentication.
		if (requestContext.getMethod().equals("OPTIONS")) {
			logger.debug("bypassing authentication due to OPTIONS");
			return requestContext;
		}

		// Bypass validation for authentication resource
		if (path.equals("/gmfws1/v1/authenticate/login") || path.equals("/gmfws1/v1/cities")) {
			logger.debug("bypassing authentication due to login");
			return requestContext;
		}

		String xAuthToken = requestContext.getHeaderValue("X-Auth-Token");
		// Now validate the return for null and empty string.
		if (HelperUtil.validateXAuthTokenFormat(xAuthToken)) {
			String token = HelperUtil.getToken(xAuthToken);
			String ldapId = HelperUtil.getLdapId(xAuthToken);
//			 If the environment is dev and token is predefined then by pass
	//		 the authentication. This bypass is for dev purpose.
//			 if ((ConfigurationParameters.ENVIRONMENT.equals("dev") && token
//			 .equals("fc9f9808d8b348de9bbfe9a8c8b7f45d"))) {
//			 logger.error("Dummy validation should be removed");
//			 return requestContext;
//			 }

			if (!new AuthenticateBL().validateToken(token, ldapId)) {
				logger.info(
						"Exception code {} token or ldapId in X-Auth-Token are Invalid",
						GMFExceptionTypes.IC);
				throw new GMFException(GMFExceptionTypes.IC,
						"token or ldapId in X-Auth-Token are Invalid");
			}

		}

		return requestContext;
	}
}