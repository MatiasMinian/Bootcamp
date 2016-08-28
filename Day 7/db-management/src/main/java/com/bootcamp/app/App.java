package com.bootcamp.app;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
		
		Scanner scanner = new Scanner(System.in);
		loop: while (true) {
			System.out.println("1: Query");
			System.out.println("2: Update Supplier");
			System.out.println("3: Update Part");
			System.out.println("4: Exit");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				System.out.println("Write query");
				String query = scanner.nextLine();
				try {
					resultSet = statement.executeQuery(query);
					ResultSetMetaData rsmd = resultSet.getMetaData();
				    int columnsNumber = rsmd.getColumnCount();
				    while (resultSet.next()) {
				        for (int i = 1; i <= columnsNumber; i++) {
				            if (i > 1) System.out.print(",  ");
				            String columnValue = resultSet.getString(i);
				            System.out.print(columnValue + " " + rsmd.getColumnName(i));
				        }
				        System.out.println("");
				    }
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Supplier Id: ");
				int supId = scanner.nextInt();
				System.out.println("Supplier Name: ");
				String supName = scanner.next();
				System.out.println("Supplier Address: ");
				String supAddress = scanner.next();
				try {
					statement.executeUpdate("update bootcamp_suppliers.suppliers set sname = " + supName + ", address = " + supAddress + " where sid = " + supId);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				System.out.println("Part Id: ");
				int partId = scanner.nextInt();
				System.out.println("Part Name: ");
				String partName = scanner.next();
				System.out.println("Part color: ");
				String partColor = scanner.next();
				try {
					statement.executeUpdate("update bootcamp_suppliers.parts set pname = " + partName + ", color = " + partColor + " where pid = " + partId);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			default:
				break loop;
			};
		}
		scanner.close();	
		
		try {
			resultSet.close();
			statement.close();
			call.close();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updatePriceBy5(int catalogId, Connection connect) {
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select cost from bootcamp_suppliers.catalog where id = " + catalogId);
			resultSet.next();
			int newCost = resultSet.getInt("cost") + 5;
			statement.executeUpdate("update bootcamp_suppliers.catalog set cost = " + newCost + " where id = " + catalogId);
			System.out.println("New cost: " + newCost);	
			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public static void removeSupplier(int supplierId, Connection connect) {
		Statement statement = null;
		try {
			statement = connect.createStatement();
			statement.executeUpdate("delete from bootcamp_suppliers.catalog where sid = " + supplierId);
			statement.executeUpdate("delete from bootcamp_suppliers.suppliers where sid = " + supplierId);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
