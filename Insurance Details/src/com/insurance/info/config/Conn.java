package com.insurance.info.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {

	public Connection c;
	public Statement s;

	public Conn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurancedetails", "root", "2720");
			s = c.createStatement();
		} catch (Exception e) {
			System.out.println(e);
			
			
		}
	}

}
