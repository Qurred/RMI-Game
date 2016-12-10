package server;

import java.sql.*;

public class DatabaseHandler {

	private Connection connection = null;
	
	public DatabaseHandler(String address){
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:"+address);
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			System.err.println("org.sqlite.JDBC did not found");
			return;
		} catch (SQLException e) {
			System.err.println("Could not found a requested database: " + address);
			return;
		}
	}
	
	public Connection getConnection(){
		return connection;
	}
}
