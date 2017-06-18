package com.gmf.ws.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.utils.HelperUtil;

public class SignUpBL {
	private Logger logger = LoggerFactory.getLogger(SignUpBL.class
			.getCanonicalName());
	public String signUpCustomer(String mobileNumber, String name, String emailId, String password)
	{
		logger.debug("In sigunUpCustomer");
		HelperUtil.validateEmailIdFormat(emailId);
		HelperUtil.validateMobileNumber(mobileNumber);
		HelperUtil.validatePassword(password);
		HelperUtil.validateUserName(name);
		
		// call to dao.
		return "ok";
	}
	
}
