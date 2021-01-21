package com.insurance.info.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.insurance.info.config.DataSourceConfig;
import com.insurance.info.models.CustomerDetails;

public class InsuranceDao {

	public static boolean userLogin(String username, String password) {
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

	public static int insertCustomerDetails(CustomerDetails customer) {
		Connection connection = DataSourceConfig.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"insert into customer_details (name,mobileno,address,email,date_of_issue,period_of_insurance,date_of_expiry,type_of_insurance,net_premium, gst, total_premium, agent_name) values (?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getMobileNo());
			ps.setString(3, customer.getAddress());
			ps.setString(4, customer.getEmail());
			ps.setDate(5, customer.getDateofIssue());
			ps.setString(6, customer.getPeriodOfInsurance());
			ps.setDate(7, customer.getDateOfExpiryDate());
			ps.setString(8, customer.getTypeOfInsurance());
			ps.setDouble(9, customer.getNetPremium());
			ps.setString(10, customer.getGst());
			ps.setDouble(11, customer.getTotalPremium());
			ps.setString(12, customer.getAgentName());

			ps.execute();
			return ps.getUpdateCount();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
