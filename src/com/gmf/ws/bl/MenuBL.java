package com.gmf.ws.bl;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.dao.MenuDAO;
import com.gmf.ws.utils.GMFUtility;

public class MenuBL {
	private Logger logger = LoggerFactory.getLogger(MenuBL.class
			.getCanonicalName());
	GMFUtility gmfUtility = null;

	public MenuBL() {
		gmfUtility = new GMFUtility();
	}

	public String getMenuOfRestaurant(String restaurantId,String itemType) {
		String menuJson = null;
		MenuDAO menuDAO = new MenuDAO();
		ArrayList<HashMap<String, String>> menuList = menuDAO
				.getMenuOfRestaurant(restaurantId,itemType);
		HashMap<String, ArrayList<HashMap<String, String>>> menu = new HashMap<String, ArrayList<HashMap<String, String>>>();
		menu.put("menuOfRestaurant", menuList);
		menuJson = gmfUtility.convertToJSON(menu);
		return menuJson;
	}

}
