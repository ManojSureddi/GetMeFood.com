package com.gmf.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAuthenticationDAO {
	private Logger logger = LoggerFactory.getLogger(UserAuthenticationDAO.class
			.getCanonicalName());

	public boolean checkUserData(String username, String password) {
		logger.debug("validating user data");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String countquery = DBQueries.VALIDATE_USER;
		try {
			con = ConnectionDAO.getConnectionDAO().getConnection();
			ps = con.prepareStatement(countquery);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				logger.debug("count {}",rs.getString(1));
				if (rs.getString(1).equals("1")) {
					logger.debug("valid user");
					return true;
				} else {
					logger.debug("invalid user");
					return false;
				}
			}
			
		} catch (SQLException e) {
			logger.error("{} ,{} ", e.getMessage(),
					(Object[]) e.getStackTrace());
		} catch (Exception e) {
			logger.error("{} ,{} ", e.getMessage(),
					(Object[]) e.getStackTrace());
		} finally {
			ConnectionDAO.getConnectionDAO().close(con);
		}
		return false;
	}

}
