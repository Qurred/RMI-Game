package server;

import java.io.File;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class Peli extends Thread {


	private Joukkue sininenTiimi;
	private Joukkue punainenTiimi;
	private String tunniste;
	private boolean sininenVoitti = false;
	private Connection yhteys;
	private Random random;


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
		this.random = new Random();
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
		vapautaPelaajat();
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
		ArrayList<Hahmo> sininenMatka = new ArrayList<>();
		ArrayList<Hahmo> punainenMatka = new ArrayList<>();
		for(int i = 0; i < sininenTiimi.annaHahmot().size(); i++){
			if(sininenTiimi.annaHahmot().get(i).annaTyyppi() == 1){
				sininenMatka.add(sininenTiimi.annaHahmot().get(i));
			}
			if(punainenTiimi.annaHahmot().get(i).annaTyyppi() == 1){
				punainenMatka.add(punainenTiimi.annaHahmot().get(i));
			}
		}

		int s = 0,p = 0;
		while(s < sininenMatka.size() || p < punainenMatka.size()){
			if(s < sininenMatka.size()){
				Hahmo hyokkaava = sininenMatka.get(s);
				while(hyokkaava.annaElama() < 1){
					hyokkaava = sininenMatka.get(random.nextInt(sininenMatka.size()));
				}
				Hahmo puolustava = punainenTiimi.annaHahmot().get(random.nextInt(punainenTiimi.annaHahmot().size()));
				while(puolustava.annaElama() < 1){
					puolustava = punainenTiimi.annaHahmot().get(random.nextInt(punainenTiimi.annaHahmot().size()));
				}
				int vahinko = hyokkaava.annaHyokkaus()- puolustava.annaPuolustusMatka();
				if(vahinko < 0) vahinko = 0;
				puolustava.asetaElama(puolustava.annaElama() - vahinko);
				tapahtumat.add(punainenTiimi.annaNimi()+ ": "+puolustava.annaNimi() + " otti " + hyokkaava.annaNimi() + " hyökkäyksestä vahinkoa " + vahinko + " ja hänelle on nyt " + puolustava.annaElama()+"/" + puolustava.annaMaksimiElama());
				if(puolustava.annaElama()<=0){
					tapahtumat.add(punainenTiimi.annaNimi()+ ": "+puolustava.annaNimi()+ " kuoli");
				}
				s++;
			}
			if(p < punainenMatka.size()){
				Hahmo hyokkaava = punainenMatka.get(p);
				while(hyokkaava.annaElama() < 1){
					hyokkaava = punainenMatka.get(random.nextInt(punainenMatka.size()));
				}
				Hahmo puolustava = sininenTiimi.annaHahmot().get(random.nextInt(sininenTiimi.annaHahmot().size()));
				while(puolustava.annaElama() < 1){
					puolustava = sininenTiimi.annaHahmot().get(random.nextInt(sininenTiimi.annaHahmot().size()));
				}
				int vahinko =hyokkaava.annaHyokkaus() - puolustava.annaPuolustusMatka();
				if(vahinko < 0) vahinko = 0;
				puolustava.asetaElama(puolustava.annaElama() - vahinko);
				tapahtumat.add(sininenTiimi.annaNimi()+ ": "+puolustava.annaNimi() + " otti " + hyokkaava.annaNimi() + " hyökkäyksestä vahinkoa " + vahinko + " ja hänelle on nyt " + puolustava.annaElama()+"/" + puolustava.annaMaksimiElama());
				if(puolustava.annaElama()<=0){
					tapahtumat.add(sininenTiimi.annaNimi()+ ": "+puolustava.annaNimi()+ " kuoli");
				}
				p++;
			}
		}
	}

	public void vaiheKaksi(){
		ArrayList<Hahmo> sininenLahi = new ArrayList<>();
		ArrayList<Hahmo> punainenLahi = new ArrayList<>();
		for(int i = 0; i < sininenTiimi.annaHahmot().size(); i++){
			if(sininenTiimi.annaHahmot().get(i).annaTyyppi() == 0){
				sininenLahi.add(sininenTiimi.annaHahmot().get(i));
			}
			if(punainenTiimi.annaHahmot().get(i).annaTyyppi() == 0){
				punainenLahi.add(punainenTiimi.annaHahmot().get(i));
			}
		}
		
		int s = 0,p = 0;
		while(s < sininenLahi.size() || p < punainenLahi.size()){
			if(s < sininenLahi.size()){
				Hahmo hyokkaava = sininenLahi.get(s);
				while(hyokkaava.annaElama() < 1){
					hyokkaava = sininenLahi.get(random.nextInt(sininenLahi.size()));
				}
				Hahmo puolustava = punainenTiimi.annaHahmot().get(random.nextInt(punainenTiimi.annaHahmot().size()));
				while(puolustava.annaElama() < 1){
					puolustava = punainenTiimi.annaHahmot().get(random.nextInt(punainenTiimi.annaHahmot().size()));
				}
				int vahinko = hyokkaava.annaHyokkaus()  - puolustava.annaPuolustusMatka();
				if(vahinko < 0) vahinko = 0;
				puolustava.asetaElama(puolustava.annaElama() - vahinko);
				tapahtumat.add(punainenTiimi.annaNimi()+ ": "+puolustava.annaNimi() + " otti " + hyokkaava.annaNimi() + " hyökkäyksestä vahinkoa " + vahinko + " ja hänelle on nyt " + puolustava.annaElama()+"/" + puolustava.annaMaksimiElama());
				if(puolustava.annaElama()<=0){
					tapahtumat.add(punainenTiimi.annaNimi()+ ": "+puolustava.annaNimi()+ " kuoli");
				}
				s++;
			}
			if(p < punainenLahi.size()){
				Hahmo hyokkaava = punainenLahi.get(p);
				while(hyokkaava.annaElama() < 1){
					hyokkaava = punainenLahi.get(random.nextInt(punainenLahi.size()));
				}
				Hahmo puolustava = sininenTiimi.annaHahmot().get(random.nextInt(sininenTiimi.annaHahmot().size()));
				while(puolustava.annaElama() < 1){
					puolustava = sininenTiimi.annaHahmot().get(random.nextInt(sininenTiimi.annaHahmot().size()));
				}
				int vahinko = hyokkaava.annaHyokkaus() - puolustava.annaPuolustusMatka();
				if(vahinko < 0) vahinko = 0;
				puolustava.asetaElama(puolustava.annaElama() - vahinko);
				tapahtumat.add(sininenTiimi.annaNimi()+ ": "+puolustava.annaNimi() + " otti " + hyokkaava.annaNimi() + " hyökkäyksestä vahinkoa " + vahinko + " ja hänelle on nyt " + puolustava.annaElama()+"/" + puolustava.annaMaksimiElama());
				if(puolustava.annaElama()<=0){
					tapahtumat.add(sininenTiimi.annaNimi()+ ": "+puolustava.annaNimi()+ " kuoli");
				}
				p++;
			}
		}

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
		tapahtumat.add("-----------------------");
		tapahtumat.add(sininenTiimi.annaNimi());
		for (Hahmo hahmo : sininenTiimi.annaHahmot()) {
			tapahtumat.add(hahmo.annaNimi()+ ": " + hahmo.annaElama()+"/"+hahmo.annaMaksimiElama());
		}
		tapahtumat.add("-----------------------");
		tapahtumat.add(punainenTiimi.annaNimi());
		for (Hahmo hahmo : punainenTiimi.annaHahmot()) {
			tapahtumat.add(hahmo.annaNimi()+ ": " + hahmo.annaElama()+"/"+hahmo.annaMaksimiElama());
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
		String[] tapahtumaTaulu = new String[tapahtumat.size()];
		tapahtumaTaulu =tapahtumat.toArray(tapahtumaTaulu);
		sininenTiimi.annaTiedot(tapahtumaTaulu);
		punainenTiimi.annaTiedot(tapahtumaTaulu);
		sininenTiimi.asetaTila(Kayttaja.IDLE);
		punainenTiimi.asetaTila(Kayttaja.IDLE);
	}
}
