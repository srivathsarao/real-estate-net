package com.vertexnet.realestate.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {
	private static String dbUrl = "jdbc:mysql://localhost/real_estate_net";
	private static String dbClass = "com.mysql.jdbc.Driver";
	private static Connection connection;

	public static void createConnection() throws Exception {
		Class.forName(dbClass);
		connection = DriverManager.getConnection(dbUrl, "root", "root");
	}

	public static Connection getConnection() {
		return connection;
	}
}
