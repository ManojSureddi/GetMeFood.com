package com.gmf.ws.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("/conforder")
public class OrderManagementDAO {
	@POST
	@Path("/storeOrder")
	@Produces(MediaType.APPLICATION_JSON)
public String storeOrder(@FormParam("address") String address,@FormParam("address") String des,@Context HttpServletRequest req){
		HttpSession session = req.getSession();
		Object userid=session.getAttribute("userid");
		Object cart=session.getAttribute("cart");
		try{
			
			
			return "";
		}catch(NullPointerException e){
			return "";
		}
		

} 
}
