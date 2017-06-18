package com.gmf.ws.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CitiesDAO {
	private Logger logger = LoggerFactory.getLogger(CitiesDAO.class
			.getCanonicalName());

	private DAOUtility daoUtility = null;
	private ConnectionDAO connectionDAO = null;

	public CitiesDAO() {
		daoUtility = new DAOUtility();
		connectionDAO = ConnectionDAO.getConnectionDAO();
	}

	public ArrayList<String> getCities() {
	logger.debug("In getCities in CitiesDAO");
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String query = DBQueries.GET_CITIES;
		ArrayList<String> citiesList = null;
		try {
			con = connectionDAO.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(query);
			citiesList = new ArrayList<String>();
			while (rs.next()) {
				String value = rs.getString("CITY_NAME");
				citiesList.add(value);
			}

		} catch (SQLException e) {
			logger.error("SQL Exception {} ,{} ", e.getMessage(),
					(Object[]) e.getStackTrace());
			throw new DAOException(DAOExceptionTypes.DAO_SQLEX);
		} catch (Exception ex) {
			System.out.println("fuck1");
			logger.error("SQL Exception {} ,{} ", ex.getMessage(),
					(Object[]) ex.getStackTrace());
			throw new DAOException(DAOExceptionTypes.DAO_SQLEX);
		} finally {
			daoUtility.closeResultSet(rs);
			daoUtility.closeStatement(stmt);
			daoUtility.closeConnection(con);
			logger.debug("Finished getCities in CitiesDAO");
		}
		return citiesList;
	}
}
