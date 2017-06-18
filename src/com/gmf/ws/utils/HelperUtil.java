/**
 * @author Siva_Varma
 * $Id: $
 * $Copyright: $
 */
package com.gmf.ws.utils;

import java.util.StringTokenizer;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.exceptions.GMFException;
import com.gmf.ws.exceptions.GMFExceptionTypes;

/**
 * The HelperUtil class serves as a Utility for all the Helper classes. It has
 * the methods which are commonly used in the Helper classes.
 * 
 */
public class HelperUtil {
	private static Logger logger = LoggerFactory.getLogger(HelperUtil.class
			.getCanonicalName());

	// private Cunstructor to block other objects creating instance of it.
	private HelperUtil() {

	}

	/**
	 * Validates the xAuthToken by checking its contents and verifies whether it
	 * has valid data.
	 * 
	 * @param xAuthToken
	 *            It is the combination of token and ldapId separated by colon.
	 * @return true if the xAuthToken format is valid.
	 * @throws GMFException
	 *             If the xAuthToken contents are invalid.
	 */
	public static boolean validateXAuthTokenFormat(String xAuthToken) {
		if (!(xAuthToken != null && !xAuthToken.trim().equals(""))) {
			logger.info("Exception Code {} X-Auth-Token not provided",
					GMFExceptionTypes.IC);

			throw new GMFException(GMFExceptionTypes.IC,
					"X-Auth-Token not provided");
		} else {
			StringTokenizer st = new StringTokenizer(xAuthToken, ":");
			if (st.countTokens() != 2) {
				logger.info("Exception code {} Invalid X-Auth-Token",
						GMFExceptionTypes.IP);

				throw new GMFException(GMFExceptionTypes.IP,
						"Invalid form of X-Auth-Token");
			}
		}
		return true;
	}

	/**
	 * Fetches the token value from xAuthToken.
	 * 
	 * @param xAuthToken
	 *            It is the combination of token and ldapId separated by colon.
	 * @return token.
	 */
	public static String getToken(String xAuthToken) {
		String token;
		StringTokenizer st = new StringTokenizer(xAuthToken, ":");
		token = st.nextToken();
		return token;
	}

	/**
	 * Fetches the ldapId value from xAuthToken.
	 * 
	 * @param xAuthToken
	 *            It is the combination of token and ldapId separated by colon.
	 * @return ldapId.
	 */
	public static String getLdapId(String xAuthToken) {
		String ldapId;
		StringTokenizer st = new StringTokenizer(xAuthToken, ":");
		st.nextToken();
		ldapId = st.nextToken();
		return ldapId;
	}

	/**
	 * Validates the emailId.
	 * 
	 * @param emailId
	 *            The emailId to validate.
	 * @return true if the emailId format is valid.
	 * @throws GMFException
	 *             If the emailId format is invalid.
	 */
	public static boolean validateEmailIdFormat(String emailId) {
		if (emailId == null || !EmailValidator.getInstance().isValid(emailId)) {
			logger.info("Exception code {} Invalid emailId format",
					GMFExceptionTypes.IP);

			throw new GMFException(GMFExceptionTypes.IP,
					"Invalid emailId format");
		}
		return true;
	}

	/**
	 * Validates the userName.
	 * 
	 * @param userName
	 *            The userName to validate.
	 * @return true if the userName format is valid.
	 * @throws GMFException
	 *             If the userName format is invalid.
	 */
	public static boolean validateUserName(String userName) {
		if (userName == null || userName.trim().equals("")) {
			logger.info("Exception code {} Invalid userName",
					GMFExceptionTypes.IP);

			throw new GMFException(GMFExceptionTypes.IP, "Invalid userName");
		}
		return true;
	}

	/**
	 * Validates the validatePassword.
	 * 
	 * @param validatePassword
	 *            The validatePassword to validate.
	 * @return true if the validatePassword format is valid.
	 * @throws GMFException
	 *             If the validatePassword format is invalid.
	 */
	public static boolean validatePassword(String password) {
		if (password == null || password.trim().equals("")) {
			logger.info("Exception code {} Invalid password",
					GMFExceptionTypes.IP);

			throw new GMFException(GMFExceptionTypes.IP, "Invalid password");
		}
		return true;
	}

	/**
	 * Validates the validatePassword.
	 * 
	 * @param validatePassword
	 *            The validatePassword to validate.
	 * @return true if the validatePassword format is valid.
	 * @throws GMFException
	 *             If the validatePassword format is invalid.
	 */
	public static boolean validateMobileNumber(String mobileNumber) {
		if (mobileNumber == null || mobileNumber.trim().equals("") || !mobileNumber.matches("\\d{10}")) {
			logger.info("Exception code {} Invalid mobile number",
					GMFExceptionTypes.IP);

			throw new GMFException(GMFExceptionTypes.IP, "Invalid mobile number");
		}
		return true;
	}
	
	
	
}
