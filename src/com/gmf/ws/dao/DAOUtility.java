package com.gmf.ws.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOUtility {
	private Logger logger = LoggerFactory.getLogger(DAOUtility.class
			.getCanonicalName());

	public void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			logger.error("Exception in closing the Connection : {} {}",
					e.getMessage(), e.getStackTrace());
		}
	}

	public void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			logger.error("Exception in closing the ResultSet : {} {}",
					e.getMessage(), e.getStackTrace());
		}
	}

	public void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			logger.error("Exception in closing the ResultSet : {} {}",
					e.getMessage(), e.getStackTrace());
		}

	}

}
