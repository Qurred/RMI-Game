package server;

import java.sql.*;

public class TietokantaHallitsija {

	private Connection yhteys = null;
	private PreparedStatement  prstmt;

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

	public synchronized ResultSet kirjauduSisaan(String nimi, String salasana){
		boolean onnistui = false;
		try {
			prstmt = yhteys.prepareStatement("SELECT * FROM KAYTTAJA WHERE NIMIMERKKI = ? AND SALANSANA = ? );");
			prstmt.setString(1, nimi);
			prstmt.setString(2, salasana);
			ResultSet tulokset = prstmt.executeQuery();
//			tulokset.next();
//			if(tulokset.getString(0).equals("1")){
//				onnistui = true;
//			}
//			prstmt.close();
			return tulokset;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public synchronized void rekisteroi(String nimi, String salasana){
		try {
			prstmt = yhteys.prepareStatement("INSERT INTO KAYTTAJA VALUES(NIMIMERKKI, SALASANA) VALUES (? , ?) );");
			prstmt.setString(1, nimi);
			prstmt.setString(2, salasana);
			prstmt.executeUpdate();
			prstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection annaYhteys(){
		return yhteys;
	}
}
