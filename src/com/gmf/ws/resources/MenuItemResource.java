package com.gmf.ws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.bl.MenuItemBL;
import com.gmf.ws.dao.DAOException;
import com.gmf.ws.exceptions.GMFException;
import com.gmf.ws.exceptions.GMFExceptionTypes;
@Path("/menutype")
public class MenuItemResource {
	private Logger logger = LoggerFactory
			.getLogger(MenuItemResource.class.getCanonicalName());
	@GET
	@Path("{restaurantId}")
	public String getMenuOfRestaurant(@PathParam("restaurantId") String restaurantId)
	{
		String menuOfRestaurant=null;
		MenuItemBL menuBL=new MenuItemBL();
		try
		{
			menuOfRestaurant=menuBL.getMenuTypesInRestaurant(restaurantId);
		} catch (GMFException gmfEx) {
			throw gmfEx;
		} catch (DAOException daoEx) {
			throw new GMFException(GMFExceptionTypes.GMF_DAOEXCE);
		} catch (Exception ex) {
			logger.error("Unexpected Exception araised.");
			throw new GMFException(GMFExceptionTypes.GMF_UNKNOWN);
		} finally {
			logger.debug("Menu Items resource completed.");
		}
		return menuOfRestaurant;
	}
}
