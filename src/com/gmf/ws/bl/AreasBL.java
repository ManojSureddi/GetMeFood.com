package com.gmf.ws.bl;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.dao.AreasDAO;
import com.gmf.ws.utils.GMFUtility;

public class AreasBL {
	private Logger logger = LoggerFactory.getLogger(AreasBL.class
			.getCanonicalName());
	GMFUtility gmfUtility = null;

	public AreasBL() {
		gmfUtility = new GMFUtility();
	}

	public String getAreasOfCity(String cityName) {
		logger.debug("In get Areas of City BL");
		String areasJson = null;
		AreasDAO areasDAO = new AreasDAO();
		ArrayList<HashMap<String, String>> areasList = areasDAO
				.getAreasOfCity(cityName);
		HashMap<String, Object> areas = new HashMap<String, Object>();
		areas.put("cityName", cityName);
		areas.put("areasOfCity", areasList);
		areasJson = gmfUtility.convertToJSON(areas);
		logger.debug("Finished get Areas of City BL");
		return areasJson;
	}

}
