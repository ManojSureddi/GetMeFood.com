package com.gmf.ws.bl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.dao.UserAuthenticationDAO;
import com.gmf.ws.exceptions.GMFException;
import com.gmf.ws.exceptions.GMFExceptionTypes;
import com.gmf.ws.utils.TokenStore;

public class AuthenticateBL {
	private Logger logger = LoggerFactory.getLogger(AuthenticateBL.class
			.getCanonicalName());

	public String userLogIn(String userName, String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		logger.debug("in user login");
		TokenStore tokenStore = TokenStore.getInstance();
		UserAuthenticationDAO auth = new UserAuthenticationDAO();
		boolean isValidUser = auth.checkUserData(userName, password);

		if(!isValidUser)
		{
			logger.info(
					"Exception code {} Invalid username or pasword",
					GMFExceptionTypes.IC);
			throw new GMFException(GMFExceptionTypes.IC,
					"Invalid username or pasword");
			
		}
		
		String token = tokenStore.getToken(userName);
		tokenStore.cart.put(token,
				new HashMap<String, HashMap<String, String>>());
		tokenStore.userTokens.put(userName, token);
		return token;
	}

	public String userLogOut(String userName, String token)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		logger.debug("preparing to log out");
		TokenStore tokenStore = TokenStore.getInstance();
		tokenStore.cart.remove(token);
		tokenStore.userTokens.remove(userName);
		return token;
	}

	public boolean validateToken(String token, String userName) {
		logger.debug("In validating the token");
		TokenStore tokenStore = TokenStore.getInstance();
		if (tokenStore.userTokens.containsKey(userName)) {
			logger.debug("User is present");
			if (tokenStore.userTokens.get(userName).equals(token)) {
				logger.debug("User is present and token matched");
				return true;
			} else {
				logger.debug("User {} present and token {} not matched",
						userName, token);
				return false;

			}
		} else {
			logger.debug("User {} is not  present", userName);
		}

		return false;
	}

}
