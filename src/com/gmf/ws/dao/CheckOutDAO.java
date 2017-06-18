package com.gmf.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CheckOutDAO {
	private DAOUtility daoUtility = null;
	private ConnectionDAO connectionDAO = null;
	
	
public CheckOutDAO() {
	daoUtility = new DAOUtility();
	connectionDAO = ConnectionDAO.getConnectionDAO();
	}

public HashMap<String,String> getFullDetails(String restId){
	Connection con = null;
	PreparedStatement ps = null;
	PreparedStatement ps2 = null;
	ResultSet rs = null;
	HashMap<String,String>details=new HashMap<String, String>();
	try {
		con = connectionDAO.getConnection();
		ps = con.prepareStatement("SELECT * FROM `restaurants` WHERE `Restaurant_Id`=?");
		ps.setString(1, restId);
		rs=ps.executeQuery();
		while(rs.next()){
			details.put("restname",rs.getString("Name"));
		}
		
			ps=con.prepareStatement("SELECT * FROM `area_restaurant` WHERE `Restaurant_Id`=?");
			ps.setString(1, restId);
			rs=ps.executeQuery();
			while(rs.next()){
				details.put("areacode",rs.getString("Area_Code"));
				
			}
			System.out.println(details.get("areacode"));
			ps=con.prepareStatement("SELECT * FROM `areas` WHERE `Area_Code`=?");
		
			ps.setString(1, details.get("areacode"));
			rs=ps.executeQuery();
			while(rs.next()){
				details.put("cityname",rs.getString("City_Name"));
				details.put("areaname",rs.getString("Area_Name"));
				System.out.println("asdsadasdsd"+rs.getString("Area_Name"));
			}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
	
	
	
	
	return details;
}
}
