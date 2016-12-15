package server;

import java.sql.*;

public class TietokantaHallitsija {

	private Connection yhteys = null;
	private final char[] KIELLETYTMERKIT = {
	//Tulee sisältämään listan kielletyistä merkeistä jotka voivat olla hyväksikäytettyinä SQL-injektointia tehdessä
	};
	
	public TietokantaHallitsija(){
		try {
			Class.forName("org.sqlite.JDBC");
			yhteys = DriverManager.getConnection("jdbc:sqlite:database.db");
			yhteys.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			System.err.println("org.sqlite.JDBC did not found");
			return;
		} catch (SQLException e) {
			System.err.println("Could not found a database" );
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
	
	public synchronized boolean kirjauduSisaan(String nimi, String salasana){
		boolean onnistui = false;
		
		if(!tarkistaSyote(nimi) || !tarkistaSyote(salasana)) return onnistui;
			
		try {
			Statement stmt = yhteys.createStatement();
			String komento = "SELECT COUNT(*) FROM KAYTTAJA WHERE NIMIMERKKI = "+nimi+" AND SALANSANA = " + salasana +");"; 
			ResultSet tulokset = stmt.executeQuery(komento);
			tulokset.next();
			
			if(tulokset.getString(0).equals("1")){
				onnistui = true;
			}
			yhteys.commit();
			stmt.close();
			return onnistui;
		} catch (SQLException e) {
			e.printStackTrace();
			return onnistui;
		}
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
