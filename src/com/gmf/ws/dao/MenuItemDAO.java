package com.gmf.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuItemDAO {
	private Logger logger = LoggerFactory.getLogger(MenuDAO.class
			.getCanonicalName());
	private DAOUtility daoUtility = null;
	private ConnectionDAO connectionDAO = null;

	public MenuItemDAO() {
		daoUtility = new DAOUtility();
		connectionDAO = ConnectionDAO.getConnectionDAO();
	}

	public HashSet<String> getMenuTypeInRestaurant(String restaurantId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HashSet<String> itemTypes=new HashSet<>();
		 try{
			con = connectionDAO.getConnection();
			 ps=con.prepareStatement(DBQueries.GET_ITEM_TYPES_IN_RESTAURANT);
				ps.setString(1, restaurantId);
				rs = ps.executeQuery();
				while(rs.next()){
					System.out.println(rs.getString(1));
					itemTypes.add(rs.getString(1));
				}
				
			 
		 }catch(SQLException e){
			 
		 }
		return itemTypes;
	
	}
}
