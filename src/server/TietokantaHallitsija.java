package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
			File tietokanta = new File("database.db");
			if(tietokanta.exists()){
				yhteys = DriverManager.getConnection("jdbc:sqlite:"+tietokannanNimi);
				yhteys.setAutoCommit(false);
			}else{
				alustaTietokanta();

			}
		} catch (ClassNotFoundException e) {
			System.out.println("org.sqlite.JDBC ei löytynyt, tarkista luokkapolku");
			System.exit(0);
		} catch (SQLException e) {
			
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
		System.out.println("Tietokantaa ei löytynyt, luodaan uusi tietokanta ja alustetaan");
		try {
			yhteys = DriverManager.getConnection("jdbc:sqlite:"+tietokannanNimi);
			yhteys.setAutoCommit(false);
			Statement tmp = yhteys.createStatement();
			String komento =		("CREATE TABLE KAYTTAJA("
					+ "	ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "	NIMIMERKKI TEXT NOT NULL,"
					+ "	SALASANA TEXT NOT NULL,"
					+ "	ESTETTY BOOLEAN NOT NULL,"
					+ "	ESTAMISENSYY TEXT"
					+ ");"
					);
			tmp.executeUpdate(komento);
			yhteys.commit();
			komento  = ("CREATE TABLE PELI("
					+ "	PELAAJA1 INTEGER REFERENCES KAYTTAJA(ID),"
					+ "	PELAAJA2 INTEGER REFERENCES KAYTTAJA(ID),"
					+ "	TUNNISTE INT PRIMARY KEY NOT NULL"
					+ ");"
					);
			tmp.executeUpdate(komento);
			yhteys.commit();
			komento = ("CREATE TABLE HAHMO("
					+ "	ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "	NIMIMERKKI TEXT NOT NULL,"
					+ "	TYYPPI INTEGER NOT NULL,"
					+ "	HP INTEGER NOT NULL,"
					+ "	PUOLUSTUSLAHI INTEGER NOT NULL,"
					+ "	PUOLUSTUSMATKA INTEGER NOT NULL,"
					+ "	NOPEUS INTEGER NOT NULL,"
					+ " HYOKKAUS INTEGER NOT NULL"
					+ ");"
					);
			tmp.executeUpdate(komento);
			tmp.close();
			yhteys.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		luoHahmot();
	}
	
	public void luoHahmot(){
		File hahmot = new File("hahmot.data");
		try {
			BufferedReader lukija = new BufferedReader(new FileReader(hahmot));
			String rivi = "";
			while((rivi = lukija.readLine()) != null){
				String[] tmp = rivi.split(":");
				prstmt = yhteys.prepareStatement("INSERT INTO HAHMO (NIMIMERKKI, TYYPPI, HP, PUOLUSTUSLAHI, PUOLUSTUSMATKA, NOPEUS, HYOKKAUS)  VALUES( '" + tmp[0]
						+ "'," + tmp[1] + "," + tmp[2] + "," + tmp[3] + "," + tmp[4] + "," + tmp[5] +  "," + tmp[6] +");");
							
				prstmt.executeUpdate();
				prstmt.close();
				yhteys.commit();	
			}
			lukija.close();
		} catch (FileNotFoundException e) {
			System.out.println("Tiedostoa " + hahmot.getName() + " ei löytynyt hahmojen alustamista varten");
		} catch (IOException e) {
			System.out.println("Tiedoston luku epäonnistui");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
