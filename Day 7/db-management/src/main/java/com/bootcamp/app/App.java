package com.bootcamp.app;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		CallableStatement call = null;

		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/bootcamp_suppliers?" + "user=matias&password=matias");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select * from bootcamp_suppliers.suppliers");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while(resultSet.next()) {
				String name = resultSet.getString("sname");
				String address = resultSet.getString("address");
				System.out.println(name + ", " + address);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			call = connect.prepareCall("{call greenPartsSuppliersQuantities()}");
			resultSet = call.executeQuery();
			while(resultSet.next()) {
				String name = resultSet.getString("supname");
				int greenParts = resultSet.getInt("verdes");
				System.out.println(name + ", greens: " + greenParts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		try {
			resultSet.close();
			statement.close();
			call.close();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePrice(int catalogId, Connection connect) throws SQLException {
		Statement statement = null;
		statement = connect.createStatement();
		statement.executeQuery("");
				
	}
}
