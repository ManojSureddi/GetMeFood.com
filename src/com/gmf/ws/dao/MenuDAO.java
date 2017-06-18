package com.gmf.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuDAO {
	private Logger logger = LoggerFactory
			.getLogger(MenuDAO.class.getCanonicalName());
	public ArrayList<HashMap<String,String>> getMenuOfRestaurant(String restaurantId,String itemType) {
		Connection con = null;
		PreparedStatement cs = null;
		ResultSet rs = null;
		ResultSetMetaData md=null;
		int columns;
		String query = "SELECT  `Restaurant_Id` , `Item_Type` ,  `Item_Code` ,`Name` ,  `Price` ,  `Description` , `Item_State` "+ 
"FROM  `restaurant_menu` WHERE Restaurant_Id =? AND  `Item_Type` =?";
		ArrayList<HashMap<String,String>> menu = null;
		try {
			con = ConnectionDAO.getConnectionDAO().getConnection();
			cs = con.prepareStatement("SELECT  `Restaurant_Id` , `Item_Type` ,  `Item_Code` ,`Name` ,  `Price` ,  `Description` , `Item_State` FROM  `restaurant_menu` WHERE Restaurant_Id =? AND  `Item_Type` =?");
			cs.setString(1, restaurantId);
			cs.setString(2, itemType);
			rs = cs.executeQuery();			
			md = rs.getMetaData();
			columns = md.getColumnCount();
			
				menu = new ArrayList<HashMap<String, String>>();
	
				while (rs.next()) {
					System.out.println("fuck");
					HashMap<String, String>	menuItem	= new HashMap<String, String>(columns);
					for (int i = 1; i <= columns; i++) {
						String key = md.getColumnName(i);
						String value = rs.getString(key);
						System.out.println(key+" "+value);
						menuItem.put(key, value);
					}
					menu.add(menuItem);
				}
		} catch (SQLException e) {
			logger.error("{} ,{} ",e.getMessage(),(Object[])e.getStackTrace());
		} catch (Exception e) {
			logger.error("{} ,{} ",e.getMessage(),(Object[])e.getStackTrace());
		} finally {
			ConnectionDAO.getConnectionDAO().close(con);
		}
		return menu;
	}
}
