package server;

import java.sql.*;

public class TietokantaHallitsija {

	private Connection yhteys = null;
	private final char[] KIELLETYTMERKIT = {
			
	};
	
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
	
	private boolean tarkistaSyote(String s){
		for (char c : KIELLETYTMERKIT) {
			if(s.contains(Character.toString(c))){
				return false;
			}
		}
		return true;
	}
	
	public synchronized void rekisteroi(String nimi, String salasana){
		if(!tarkistaSyote(nimi) || !tarkistaSyote(salasana)){
			System.err.println("Viallinen syöte!"); //Korvaa lopulta metodilla joka kirjoittaa esim lokiin
			return;
		}
		try {
			Statement stmt = yhteys.createStatement();
			String komento = "INSERT INTO KAYTTAJA VALUES(NIMIMERKKI, SALASANA) VALUES " +nimi + "," +salasana+");"; 
			stmt.executeUpdate(komento);
			yhteys.commit();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection annaYhteys(){
		return yhteys;
	}
}
