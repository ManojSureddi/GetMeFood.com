package com.gmf.ws.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.gmf.ws.dao.AddressManagementDAO;

@Path("/address")
public class AddressManagementResource {
	@POST
	@Path("/storeAddr")
	@Produces(MediaType.APPLICATION_JSON)
public String storeAddress(@FormParam("address") String address,@Context HttpServletRequest req){
		AddressManagementDAO dao= new AddressManagementDAO();
		HttpSession session = req.getSession();
		Object userid=session.getAttribute("userid");
	String addr=dao.storeAddress(address, userid.toString());
	if("".equals(addr)){
		session.setAttribute("addr", addr);
		return "[\"Success\"]";
	}else{
		return "[\"Failed\"]";
	}
} 
	@POST
	@Path("/retrieveAddr")
	@Produces(MediaType.APPLICATION_JSON)
public String retrieveAddress(@FormParam("address") String address,@Context HttpServletRequest req){
		AddressManagementDAO dao= new AddressManagementDAO();
		HttpSession session = req.getSession(true);
		Object userid=session.getAttribute("userid");
		System.out.println("dsfsfsfsf"+userid.toString());	
	System.out.println(dao.getAddresses(userid.toString())); 
	 
	return dao.getAddresses(userid.toString());
} 
}
