package com.gmf.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.helper.OrdersBean;
import com.gmf.ws.helper.UniqueKeyGenerator;

public class OrderConfirmationDAO {
	private Logger logger = LoggerFactory.getLogger(AreasDAO.class
			.getCanonicalName());
	private DAOUtility daoUtility = null;

	private ConnectionDAO connectionDAO = null;

	public OrderConfirmationDAO() {
		daoUtility = new DAOUtility();
		connectionDAO = ConnectionDAO.getConnectionDAO();
	}

	public String storeOrder(OrdersBean bean,
			HashMap<String, HashMap<String, String>> cart, String des) {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		String query = "INSERT INTO `orders`(`Order_Id`, `Order_Time`, `Order_Status`, `City_Code`, `Area_Code`, `Area_Name`, `Restaurant_Id`, `Restaurant_Name`, `Customer_Id`, `Delivery_Address`, `Assigned_Executive`, `Assigned_Delivery_Boy`, `Log`, `Delivery_Time`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int k = 0;
		String ordercontent="INSERT INTO `order_contents`(`Order_Id`, `Item_Code`, `Item_Name`, `Iteam_Cost`, `Item_Quantity`, `Total_Item_Cost`) VALUES (?,?,?,?,?,?)";
		
		try {
			con = connectionDAO.getConnection();
			ps = con.prepareStatement(query);
			ps1 = con.prepareStatement(ordercontent);
			ps.setString(1, bean.getOrderId());
			ps.setString(2, bean.getOrderTime());
			ps.setString(3, bean.getStatus());
			ps.setString(4, bean.getCityCode());
			ps.setString(5, bean.getAreaCode());
			ps.setString(6, bean.getAreaName());
			ps.setString(7, bean.getRestId());
			ps.setString(8, bean.getRestName());
			ps.setString(9, bean.getCustId());
			ps.setString(10, bean.getDeliveryAddr());
			ps.setInt(11, 0);
			ps.setInt(12, 0);
			ps.setString(13, "");
			ps.setString(14, "");
			k = ps.executeUpdate();
			HashMap<String, String> tempmap=null;
			for(String tempkey:cart.keySet()){
				tempmap=cart.get(tempkey);
				ps1.setString(1, bean.getOrderId());
				ps1.setString(2, tempmap.get("itemCode"));
				ps1.setString(3, tempmap.get("itemName"));
				ps1.setString(4, tempmap.get("itemPrice"));
				ps1.setString(5, tempmap.get("itemQty"));
				ps1.setString(6, (Integer.parseInt(tempmap.get("itemQty"))*Double.parseDouble( tempmap.get("itemPrice")))+"");
			ps1.executeUpdate();
			}
			k=1;
			

		} catch (SQLException e) {
			logger.error("SQL Exception {} ,{} ", e.getMessage(),
					(Object[]) e.getStackTrace());
			throw new DAOException(DAOExceptionTypes.DAO_SQLEX);
			
		} catch (Exception ex) {
			logger.error("SQL Exception {} ,{} ", ex.getMessage(),
					(Object[]) ex.getStackTrace());
			throw new DAOException(DAOExceptionTypes.DAO_SQLEX);
			
		} finally {
			daoUtility.closeResultSet(rs);
			daoUtility.closeStatement(ps);
			daoUtility.closeConnection(con);
			logger.debug("Finished storeing address in storeDAO");
		}

		if(k>0){
			return bean.getOrderId();
		}
		else{
			return "";
		}
	}
}
