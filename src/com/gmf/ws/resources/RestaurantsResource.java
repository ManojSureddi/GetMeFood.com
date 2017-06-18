package com.gmf.ws.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.bl.RestaurantsBL;

@Path("/restaurants")
public class RestaurantsResource {
	private Logger logger = LoggerFactory
			.getLogger(RestaurantsResource.class.getCanonicalName());
	
	@GET
	@Path("{areaCode}")
	public String getRestaurantsOfArea(@PathParam("areaCode") String areaCode)
	{
		String restaurantsOfArea=null;
		RestaurantsBL restaurantsBL=new RestaurantsBL();
		restaurantsOfArea=restaurantsBL.getRestaurantsOfArea(areaCode);
		return restaurantsOfArea;
	}

}
