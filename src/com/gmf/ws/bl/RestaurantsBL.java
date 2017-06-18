package com.gmf.ws.bl;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.dao.RestaurantsDAO;
import com.gmf.ws.utils.GMFUtility;

public class RestaurantsBL {
	private Logger logger = LoggerFactory
			.getLogger(RestaurantsBL.class.getCanonicalName());
	GMFUtility gmfUtility = null;
	public RestaurantsBL()
	{
		gmfUtility=new GMFUtility();
	}
	public String getRestaurantsOfArea(String areaCode)
	{
		String restaurantsJson=null;
		RestaurantsDAO areasDao=new RestaurantsDAO();
		ArrayList<HashMap<String,String>> restaurantsList=areasDao.getRestaurantsOfArea(areaCode);
		HashMap<String,ArrayList<HashMap<String,String>>> restaurants=new HashMap<String,ArrayList<HashMap<String,String>>>();
		restaurants.put("restaurantsOfArea",restaurantsList);
		restaurantsJson=gmfUtility.convertToJSON(restaurants);
		return restaurantsJson;
	}
	
}
