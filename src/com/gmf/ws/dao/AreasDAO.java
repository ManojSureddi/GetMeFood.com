package com.gmf.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AreasDAO {
	private Logger logger = LoggerFactory.getLogger(AreasDAO.class
			.getCanonicalName());
	private DAOUtility daoUtility = null;
	private ConnectionDAO connectionDAO = null;

	public AreasDAO() {
		daoUtility = new DAOUtility();
		connectionDAO = ConnectionDAO.getConnectionDAO();
	}

	public ArrayList<HashMap<String, String>> getAreasOfCity(String cityName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = DBQueries.GET_AREAS_OF_CITY;
		ArrayList<HashMap<String, String>> areasList = null;
		try {
			con = connectionDAO.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, cityName);
			rs = ps.executeQuery();
			areasList = new ArrayList<HashMap<String, String>>();
			while (rs.next()) {
				HashMap<String, String> areaMap = new HashMap<String, String>();
				String areaCode = rs.getString("AREA_CODE");
				String areaName = rs.getString("AREA_NAME");
				areaMap.put("areaCode", areaCode);
				areaMap.put("areaName", areaName);
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
			logger.debug("Finished getCities in CitiesDAO");
		}
		return areasList;
	}
}
