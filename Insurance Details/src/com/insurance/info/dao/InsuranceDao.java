package com.insurance.info.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.insurance.info.config.DataSourceConfig;

public class InsuranceDao {
	
	
	public static boolean userLogin(String username,String password) {
		try {
		Connection connection = DataSourceConfig.getConnection();
		String query = "select * from account where user_name = ? and password = ?";
		PreparedStatement ps;
			ps = connection.prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
		} catch (SQLException e) {
			System.out.println("WRONG USERNAME OR PASSWORD. PLEASE TRY AGAIN");
		}
		return false;
		
	}

}
