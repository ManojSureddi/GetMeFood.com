package com.gmf.ws.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.bl.SignUpBL;
import com.gmf.ws.dao.DAOException;
import com.gmf.ws.exceptions.GMFException;
import com.gmf.ws.exceptions.GMFExceptionTypes;

@Path("/sign-up")
public class SignUpResource {
	private Logger logger = LoggerFactory.getLogger(SignUpResource.class
			.getCanonicalName());

	@POST
	public String signUpCustomer(
			@FormParam("mobileNumber") String mobileNumber,
			@FormParam("name") String name,
			@FormParam("emailId") String emailId,
			@FormParam("password") String password) {
		logger.debug("In signUpCustomer Resource");
		
		String status = null;
		SignUpBL signUpBL = null;
		signUpBL = new SignUpBL();
		try {
			status = signUpBL.signUpCustomer(mobileNumber, name, emailId,
					password);
		} catch (GMFException gmfEx) {
			throw gmfEx;
		} catch (DAOException daoEx) {
			throw new GMFException(GMFExceptionTypes.GMF_DAOEXCE);
		} catch (Exception ex) {
			logger.error("Unexpected Exception araised.");
			throw new GMFException(GMFExceptionTypes.GMF_UNKNOWN);
		} finally {
			logger.debug("SignUpCustomer resource completed.");
		}
		return status;

	}

}
