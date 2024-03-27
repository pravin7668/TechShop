package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static Connection conn;

	public static Connection getDBConn() {
		String userNameDb = "root";
		String passwordDb = "pravin766899";
		String urlDb = "jdbc:mysql://localhost:3306/techshop";
		String driverName = "com.mysql.cj.jdbc.Driver";
		try {
			Class.forName(driverName);
			 //System.out.println("Driver Loaded..");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver could not be Loaded");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(urlDb, userNameDb, passwordDb);
			//System.out.println("Connection established");
		} catch (SQLException e) {
			System.out.println("Connection could not be established");
			e.printStackTrace();
		}

		return conn;

	}

	public static void dbClose() {
		try {
			conn.close();
			//System.out.println("Connection Closed");
		} catch (SQLException e) {
			System.out.println("Connection could not be closed");
			e.printStackTrace();
		}
	}
}
