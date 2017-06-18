package com.gmf.ws.bl;

import java.util.HashMap;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.dao.MenuItemDAO;
import com.gmf.ws.utils.GMFUtility;

public class MenuItemBL {
	private Logger logger = LoggerFactory.getLogger(MenuBL.class
			.getCanonicalName());
	GMFUtility gmfUtility = null;
	public MenuItemBL(){
		
		gmfUtility = new GMFUtility();
	}
	public String getMenuTypesInRestaurant(String restaurantId) {
		String menuJson = null;
		MenuItemDAO menuItemDAO = new MenuItemDAO();
		HashSet<String> menuList = menuItemDAO.getMenuTypeInRestaurant(restaurantId);
		HashMap<String, HashSet<String>> menu = new HashMap<>();
		menu.put("ItemTypesRestaurant", menuList);
		menuJson = gmfUtility.convertToJSON(menu);
		return menuJson;
	}
}
