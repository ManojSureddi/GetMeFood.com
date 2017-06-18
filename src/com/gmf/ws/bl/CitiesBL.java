package com.gmf.ws.bl;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.dao.CitiesDAO;
import com.gmf.ws.utils.GMFUtility;

public class CitiesBL {
	private Logger logger = LoggerFactory.getLogger(CitiesBL.class
			.getCanonicalName());
	GMFUtility gmfUtility = null;

	public CitiesBL() {
		gmfUtility = new GMFUtility();
	}

	public String getCities() {
		logger.debug("In get Cities BL");
		String citiesJson = null;
		CitiesDAO citiesDao = null;
		HashMap<String, ArrayList<String>> cities = null;
		citiesDao = new CitiesDAO();
		ArrayList<String> citiesList = citiesDao.getCities();
		cities = new HashMap<String, ArrayList<String>>();
		cities.put("cities", citiesList);
		citiesJson = gmfUtility.convertToJSON(cities);
		logger.debug("Finished get Cities BL");
		return citiesJson;
	}
}
