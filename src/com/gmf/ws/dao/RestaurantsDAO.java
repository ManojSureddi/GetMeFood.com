package com.gmf.ws.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestaurantsDAO {
	private Logger logger = LoggerFactory
			.getLogger(RestaurantsDAO.class.getCanonicalName());
	public ArrayList<HashMap<String,String>> getRestaurantsOfArea(String areaCode) {
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ResultSetMetaData md=null;
		int columns;
		String query = "call getRestaurantsByAreaCode(?)";
		ArrayList<HashMap<String,String>> restaurants = null;
		try {
			con = ConnectionDAO.getConnectionDAO().getConnection();
			cs = con.prepareCall(query);
			cs.setString(1, areaCode);
			
			rs = cs.executeQuery();
			
			md = rs.getMetaData();
			columns = md.getColumnCount();
			
				restaurants = new ArrayList<HashMap<String, String>>();
			
				while (rs.next()) {
					HashMap<String, String>	restaurant = new HashMap<String, String>(columns);
					for (int i = 1; i <= columns; i++) {
						String key = md.getColumnName(i);
						String value = rs.getString(key);
						restaurant.put(key, value);
					}
					restaurants.add(restaurant);
				}
		} catch (SQLException e) {
			System.out.println("SQLException "+e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception "+e.getMessage());
		} finally {
			ConnectionDAO.getConnectionDAO().close(con);
		}
		return restaurants;
	}

}
