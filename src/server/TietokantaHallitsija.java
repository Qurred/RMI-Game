package server;

import java.sql.*;

public class TietokantaHallitsija {

	private Connection yhteys;
	private PreparedStatement  prstmt;

	public TietokantaHallitsija(){
		try {
			Class.forName("org.sqlite.JDBC");
			yhteys = DriverManager.getConnection("jdbc:sqlite:database.db");
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
		boolean onnistui = false;
		try {
			prstmt = yhteys.prepareStatement("SELECT * FROM KAYTTAJA WHERE NIMIMERKKI = ? AND SALASANA = ? ;");
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
			if(yhteys != null && !yhteys.isClosed()){
			prstmt = yhteys.prepareStatement("INSERT INTO KAYTTAJA (NIMIMERKKI, SALASANA, ESTETTY) VALUES (? , ? , ?) ;");
			prstmt.setString(1, nimi);
			prstmt.setString(2, salasana);
			prstmt.setString(3, "0");
			System.out.println("suoretataan komento...");
			prstmt.executeUpdate();
			prstmt.close();
			}else{
				System.out.println("Onko yhteys suljettu? ");
				if(yhteys == null){
					System.out.println("YHTEYS ON NULL!");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection annaYhteys(){
		return yhteys;
	}
}
