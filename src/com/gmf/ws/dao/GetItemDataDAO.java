package com.gmf.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetItemDataDAO {
	private Logger logger = LoggerFactory
			.getLogger(CitiesDAO.class.getCanonicalName());
	public HashMap<String,String> getItemData(String itemCode,String restaurantCode,String qty) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "Select * from RESTAURANT_MENU where RESTAURANT_ID = ? and ITEM_CODE = ? ";
		HashMap<String,String> itemsList = null;
		try {
			con = ConnectionDAO.getConnectionDAO().getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,restaurantCode);
			ps.setString(2,itemCode);
          	rs = ps.executeQuery();
          	itemsList =new HashMap<String,String>();
			while (rs.next()) {
              	String itemName=rs.getString("Name");
				String price=rs.getString("Price");
				itemsList.put("itemCode", itemCode);
				itemsList.put("itemName", itemName);
				itemsList.put("itemPrice", price);
				itemsList.put("itemQty", qty);
				logger.debug("item resource completed");
				System.out.println("fuck ");
		}
			return itemsList;
		} catch (SQLException e) {
			System.out.println("fuck "+e.getMessage());
			logger.error("{} ,{} ",e.getMessage(),(Object[])e.getStackTrace());
		} catch (Exception e) {
			logger.error("{} ,{} ",e.getMessage(),(Object[])e.getStackTrace());
		} finally {
			ConnectionDAO.getConnectionDAO().close(con);
		}
		return itemsList;
	}
	
}
