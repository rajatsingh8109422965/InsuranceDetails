package com.insurance.info.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.insurance.info.constants.Constants;

public class DataSourceConfig {

	private static Connection connection;
	
	public static Connection getConnection() {
		if(connection==null) {
			try {
				connection= DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}
		else {
			return connection;
		}
	}

}
