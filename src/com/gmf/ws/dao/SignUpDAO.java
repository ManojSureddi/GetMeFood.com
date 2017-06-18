package com.gmf.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gmf.ws.helper.Encryptor;
import com.gmf.ws.helper.UniqueKeyGenerator;

public class SignUpDAO {
	String j;
	private DAOUtility daoUtility = null;
	private ConnectionDAO connectionDAO = null;

	public SignUpDAO() {
		daoUtility = new DAOUtility();
		connectionDAO = ConnectionDAO.getConnectionDAO();
	}

	public String storeUser(String name, String email, String mobile,
			String password) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = connectionDAO.getConnection();
			
			ps = con.prepareStatement("SELECT * FROM `users` WHERE `Email`=? or `Contact`=?");
			ps.setString(1, email);
			ps.setString(2, mobile);
			rs=ps.executeQuery();
			if(!rs.next()){
			ps = con.prepareStatement("INSERT INTO `users`(`User_Id`, `Name`, `Password`, `Email`, `Type`, `Contact`, `Default_Address`, `Pincode`) VALUES (?,?,?,?,?,?,?,?)");
			ps.setString(1, UniqueKeyGenerator.generateKey(mobile, password));
			ps.setString(2, name);
			ps.setString(3, Encryptor.encryptData(password));
			ps.setString(4, email);
			ps.setString(5, "CUSTOMER");
			ps.setString(6, mobile);
			ps.setString(7, "");
			ps.setString(8, "");
			ps.executeUpdate();
			return "[\"Success\"]";
			}
			else{
				return "[\"Mobile/email already registered\"]";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return "[\"Registration failed\"]";
		}
	}
}
