package server;

import java.sql.*;

public class TietokantaHallitsija {

	private Connection yhteys = null;
	
	public TietokantaHallitsija(String osoite){
		try {
			Class.forName("org.sqlite.JDBC");
			yhteys = DriverManager.getConnection("jdbc:sqlite:"+osoite);
			yhteys.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			System.err.println("org.sqlite.JDBC did not found");
			return;
		} catch (SQLException e) {
			System.err.println("Could not found a requested database: " + osoite);
			return;
		}
	}
	
	public Connection annaYhteys(){
		return yhteys;
	}
}
