package com.gmf.ws.resources;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gmf.ws.bl.AuthenticateBL;

//import com.gmf.ws.dao.UserAuthenticationDAO;
@Path("/authenticate")
public class AuthenticationResource {

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String userLogIn(@FormParam("userName") String username,
			@FormParam("password") String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		AuthenticateBL authenticationBL = new AuthenticateBL();
		String token = authenticationBL.userLogIn(username, password);
		return token;
	}

	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public String userLogOut(@FormParam("uname") String username,
			@HeaderParam("usertoken") String token)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		AuthenticateBL authenticationBL = new AuthenticateBL();
		authenticationBL.userLogOut(username, token);
		return token;
	}
}
