package com.gmf.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gmf.ws.helper.UniqueKeyGenerator;
import com.gmf.ws.utils.GMFUtility;

public class AddressManagementDAO {
	private Logger logger = LoggerFactory.getLogger(AreasDAO.class
			.getCanonicalName());
	private DAOUtility daoUtility = null;

	private ConnectionDAO connectionDAO = null;

	public AddressManagementDAO() {
		daoUtility = new DAOUtility();
		connectionDAO = ConnectionDAO.getConnectionDAO();
	}

	public String storeAddress(String address, String userid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "INSERT INTO `addresses`(`id`, `name`, `userid`) VALUES (?,?,?)";
		int k = 0;
		String addr=UniqueKeyGenerator.generateKey(address, con.toString());
		try {
			con = connectionDAO.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,addr);
			ps.setString(2, userid);
			ps.setString(3, address);
			k = ps.executeUpdate();

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
		if (k > 0)
			return addr;
		else
			return "";
	}

	public String getAddresses(String userid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM `addresses` WHERE `name`=?";
		ArrayList<HashMap<String, String>> areasList = null;
		GMFUtility utils = new GMFUtility();

		try {
			con = connectionDAO.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			areasList = new ArrayList<HashMap<String, String>>();
			while (rs.next()) {
				HashMap<String, String> areaMap = new HashMap<String, String>();
				String areaCode = rs.getString("id");
				String areaName = rs.getString("userid");
				areaMap.put("id", areaCode);
				areaMap.put("name", areaName);
				areasList.add(areaMap);
			}

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
			logger.debug("Finished retrieving address in retrieveDAO");
		}
		System.out.println(utils.convertToJSON(areasList));
		return utils.convertToJSON(areasList);
	}
}
