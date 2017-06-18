package com.gmf.ws.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.bl.MenuBL;
import com.gmf.ws.dao.DAOException;
import com.gmf.ws.exceptions.GMFException;
import com.gmf.ws.exceptions.GMFExceptionTypes;

@Path("/menu")
public class MenuResource {
	private Logger logger = LoggerFactory
			.getLogger(MenuResource.class.getCanonicalName());
	
	@GET
	@Path("{restaurantId}/{itemtype}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMenuOfRestaurant(@PathParam("restaurantId") String restaurantId,@PathParam("itemtype")String itemtype)
	{
		
		String menuOfRestaurant=null;
		MenuBL menuBL=new MenuBL();
		try{
		menuOfRestaurant=menuBL.getMenuOfRestaurant(restaurantId,itemtype);
		} catch (GMFException gmfEx) {
			throw gmfEx;
		} catch (DAOException daoEx) {
			throw new GMFException(GMFExceptionTypes.GMF_DAOEXCE);
		} catch (Exception ex) {
			logger.error("Unexpected Exception araised.");
			throw new GMFException(GMFExceptionTypes.GMF_UNKNOWN);
		} finally {
			logger.debug("menu resource completed.");
		}
		return menuOfRestaurant;
	}

}
