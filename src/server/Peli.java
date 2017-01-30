package server;

import java.io.File;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class Peli extends Thread {


	private Joukkue sininenTiimi;
	private Joukkue punainenTiimi;
	private String tunniste;
	private boolean sininenVoitti = false;
	private Connection yhteys;


	private ArrayList<String> tapahtumat;

	public Peli(Joukkue sininen, Joukkue punainen, Connection yhteys){
		this.sininenTiimi = sininen;
		this.punainenTiimi = punainen;
		this.yhteys = yhteys;
	}

	public Peli(Joukkue sininen, Connection yhteys){
		this.sininenTiimi = sininen;
		this.yhteys = yhteys;
	}

	public boolean onMolemmat(){
		if(this.punainenTiimi != null){
			return true;
		}
		return false;
	}

	public void liityPeliin(Joukkue punainen){
		this.punainenTiimi = punainen;
		System.out.println("Liityttiin");
	}

	public void run() {
		tapahtumat = new ArrayList<>();
		while(this.punainenTiimi == null){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		System.out.println("Aloitetaan peli!");
		tunniste = UUID.randomUUID().toString();
		System.out.println(sininenTiimi);
		System.out.println(sininenTiimi.annaNimi());
		tapahtumat.add("Pelaajien " + this.sininenTiimi.annaNimi() + " & " + this.punainenTiimi.annaNimi() + " joukot kohtasivat");
		alkuTarkastus();
		tapahtumat.add("Aloitettiin taistelun ensimmäinen vaihe");
		vaiheYksi();
		tapahtumat.add("Aloitettiin taistelun toinen vaihe");
		vaiheKaksi();
		tarkistaVoittaja();
		tallennaTapahtumat();
//		tulosTietokantaan();
		System.out.println("TAISTELU OHI");
		for (String tapahtuma : tapahtumat) {
			System.out.println(tapahtuma);
		}
	}

	public void alkuTarkastus(){
		ArrayList<Hahmo> sininen = this.sininenTiimi.annaHahmot();
		ArrayList<Hahmo> punainen = this.punainenTiimi.annaHahmot();
		for (Hahmo hahmo : sininen) {
			for (Hahmo hahmo2 : punainen) {
				if(hahmo.annaId() == hahmo2.annaId()){
					hahmo.asetaMoraali(hahmo.annaMoraali()/2);
					hahmo2.asetaMoraali(hahmo2.annaMoraali()/2);
					tapahtumat.add(hahmo.annaNimi() + " kohtasi taistelun alussa itsensä vastapuolella, hänen moraalinsa kärsi tästä");
				}
			}
		}
	}

	public void vaiheYksi(){
		//Täällä pitäisi olla ankaraa laskentaa :)
		System.out.println("VAIHE YKSI");
	}

	public void vaiheKaksi(){
		//Täällä pitäisi olla ankaraa laskentaa :)
		System.out.println("VAIHE KAKSI");

	}

	public void tarkistaVoittaja(){
		double jaljellaSininen = sininenTiimi.laskeMenetys();
		double jaljellaPunainen = punainenTiimi.laskeMenetys();
		if(jaljellaSininen > jaljellaPunainen){
			sininenVoitti = true;
			tapahtumat.add("Pelaajan " +this.sininenTiimi.annaNimi() + "joukkue voitti taistelun pelaajaan "
					+ this.punainenTiimi.annaNimi() + " joukkueen");
		}else{
			sininenVoitti = false;
			tapahtumat.add("Pelaajan " +this.punainenTiimi.annaNimi() + "joukkue voitti taistelun pelaajaan "
					+ this.sininenTiimi.annaNimi() + " joukkueen");
		}
	}

	public void tallennaTapahtumat(){
		try{
			PrintWriter kirjoitin = new PrintWriter(this.getClass().getClassLoader().getResource("").getPath()+"\\taistelut\\" + tunniste + ".txt","UTF-8" );
			for (String tapahtuma : tapahtumat) {
				System.out.println(tapahtuma);
				kirjoitin.println(tapahtuma);
			}
			kirjoitin.close();
		}catch (Exception e) {
		}
	}

	public void tulosTietokantaan(){
		try {
			String komento = "INSERT INTO PELI (PELAAJA1, PELAAJA2, TUNNISTE) VALUES "+this.sininenTiimi.annaPelaajanID()
			+ ", " + this.punainenTiimi.annaPelaajanID() + ", '" + this.tunniste + "' );" ; 
			Statement stmt =  yhteys.createStatement();
			stmt.executeUpdate(komento);
			yhteys.commit();
			stmt.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void vapautaPelaajat(){
		//Vapautetaan pelaajat, eli asetetaan tilaksi vapaa
	}

}
