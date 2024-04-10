package com.mycompany.dao.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * A simple data source for getting database connections.
 */
public class JdbcDataSource {
	private static String url;
	private static String username;
	private static String password;

	public JdbcDataSource(String fileName) throws ClassNotFoundException, IOException, SQLException {
		init(fileName);
	}

	/**
	 * Initialize the data source
	 * 
	 * @param fileName the name of the property file that contains the database
	 *                 driver, URL, username, and password
	 */
	private void init(String fileName) throws IOException, ClassNotFoundException {
		Properties props = new Properties();
		FileInputStream in = new FileInputStream(fileName);
		props.load(in);

		String driver = props.getProperty("jdbc.driver");
		url = props.getProperty("jdbc.url");
		username = props.getProperty("jdbc.username");
		password = props.getProperty("jdbc.password");

		System.out.println("username: " + username);
		System.out.println("password: " + password);
		System.out.println("url: " + url);
		System.out.println("driver:'" + driver + "'");

		Class.forName(driver);
	}

	/**
	 * Gets a connection to the database
	 * 
	 * @return the database connection
	 */
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}
}