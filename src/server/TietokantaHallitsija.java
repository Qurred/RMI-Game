package server;

import java.sql.*;
import java.util.ArrayList;

import Entiteetit.Hahmo;

public class TietokantaHallitsija {

	private final String tietokannanNimi = "database.db";
	private Connection yhteys;
	private PreparedStatement  prstmt;

	public TietokantaHallitsija(){
		try {
			Class.forName("org.sqlite.JDBC");
			yhteys = DriverManager.getConnection("jdbc:sqlite:"+tietokannanNimi);
			if(yhteys != null){
				System.out.println("Yhteys tietokantaan muodostettu");
			}else{
				System.out.println("Tietokantaan ei saatu yhteyttä");
			}
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
		try {
			prstmt = yhteys.prepareStatement("SELECT * FROM KAYTTAJA WHERE NIMIMERKKI = ? AND SALASANA = ? ;");
			prstmt.setString(1, nimi);
			prstmt.setString(2, salasana);
			ResultSet tulokset = prstmt.executeQuery();
			return tulokset;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public synchronized int rekisteroi(String nimi, String salasana){
		int tulos = 0;
		try {
			if(yhteys != null && !yhteys.isClosed()){
				prstmt = yhteys.prepareStatement("SELECT COUNT(*) FROM KAYTTAJA WHERE NIMIMERKKI = ? ;");
				prstmt.setString(1, nimi);
				ResultSet maara = prstmt.executeQuery();
				maara.next();
				if(Integer.parseInt(maara.getString(1)) == 0){
					prstmt.close();
					prstmt = yhteys.prepareStatement("INSERT INTO KAYTTAJA (NIMIMERKKI, SALASANA, ESTETTY) VALUES (? , ? , ?) ;");
					prstmt.setString(1, nimi);
					prstmt.setString(2, salasana);
					prstmt.setString(3, "0");
					System.out.println("suoretataan komento...");
					prstmt.executeUpdate();
					prstmt.close();
				}else{
					prstmt.close();
					tulos = 1;
				}
			}else{
				System.out.println("Onko yhteys suljettu? ");
				if(yhteys == null){
					System.out.println("YHTEYS ON NULL!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tulos;
	}

	public Connection annaYhteys(){
		return yhteys;
	}

	public void sammuta(){
		try {
			if(!prstmt.isClosed()){
				prstmt.close();
			}
			if(!yhteys.isClosed()){
				yhteys.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Hahmo> annaHahmot(){
		ArrayList<Hahmo> hahmot = new ArrayList<Hahmo>();
		try {
			prstmt = yhteys.prepareStatement("SELECT * FROM HAHMOT ;");
			ResultSet maara = prstmt.executeQuery();
			maara.beforeFirst();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hahmot;
	}
	
	public void alustaTietokanta(){
		
	}
}
