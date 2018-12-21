package com.assignment.libraryJdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;


public class ConnectionFactory {
	
	
	private static final String URL = "jdbc:mysql://localhost:3306/assignment";
	private static final String USER = "root";
	private static final String PASS = "pass@word1";

	public static Connection getConnection() {
		
		try {
			DriverManager.registerDriver(new Driver());
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			System.out.println("Error connecting to database");
			e.printStackTrace();
		}
		return null;
		
	}
	
	/*public static void main(String[] args) {
		Connection connection = ConnectionFactory.getConnection();
	}*/

}
