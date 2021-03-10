package com.revature.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionUtil {
	private static ConnectionUtil cu = null;
	private static Properties prop;


	private ConnectionUtil() {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream is = classLoader.getResourceAsStream("database.properties");
		prop = new Properties();
		try {
			prop.load(is);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static synchronized ConnectionUtil getConnectionUtil() {
		if(cu == null)
			cu = new ConnectionUtil();
		return cu;
	}


	public Connection getConnection() {
		try {		
			Connection conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("usr"),
					prop.getProperty("pswd"));
			return conn;
		} catch (SQLException e) {

			e.printStackTrace();

			return null;
		}
	}
}
