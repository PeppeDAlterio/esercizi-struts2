package it.store.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/online_store";

	private Connection conn = null;
	
	public DataBase() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver"); //carico il driver JDBC
	}
	
	public Connection connect() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, "connector", "pass123");
		
		return conn;
	}
	
	public void close() throws SQLException {
		conn.close();
		conn = null;
	}

}
