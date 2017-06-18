package com.gmf.ws.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionDAO {
	private Logger logger = LoggerFactory.getLogger(ConnectionDAO.class
			.getCanonicalName());
	public static ConnectionDAO connectionDAO = null;
	private Context initContext = null;
	private Context envContext = null;
	private DataSource ds = null;

	public ConnectionDAO() {
		try {
			initContext = new InitialContext();
			envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/gmfdb");
		} catch (NamingException e) {
			logger.error("{} ,{} ", e.getMessage(),
					(Object[]) e.getStackTrace());
		} catch (Exception e) {
			logger.error("{} ,{} ", e.getMessage(),
					(Object[]) e.getStackTrace());
		}
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			logger.error("{} ,{} ", e.getMessage(),
					(Object[]) e.getStackTrace());
		}
		return con;
	}

	public boolean close(Connection con) {
		if (con != null) {
			try {
				con.close();
				return true;
			} catch (SQLException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public static ConnectionDAO getConnectionDAO() {
		if (connectionDAO == null) {
			connectionDAO = new ConnectionDAO();
			return connectionDAO;
		} else {
			return connectionDAO;
		}
	}
}
