package server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import client.AsiakasRajapinta;

public class PalvelinToteutus extends UnicastRemoteObject implements PalvelinRajapinta, Runnable{

	private static final long serialVersionUID = 1L;
	private TietokantaHallitsija tkh;
	private ArrayList<Kayttaja> kayttajat;
	private Scanner scan;
	private Logger loggerPalvelin;
	private Logger loggerChatti;

	public PalvelinToteutus() throws RemoteException{
		loggerPalvelin = Logger.getLogger("Palvelin");
		loggerChatti = Logger.getLogger("Chatti");
		try {
			FileHandler handlerPalvelin = new FileHandler("palvelinLog");
			FileHandler handlerChatti = new FileHandler("chattiLog");
			handlerChatti.setFormatter(new SimpleFormatter()); //Muuttaa login formaatin helpommin luettavaan muotoon xml:n sijasta
			loggerPalvelin.addHandler(handlerPalvelin);
			loggerChatti.addHandler(handlerChatti);
		} catch (Exception e) {
			e.printStackTrace();
		}
		loggerPalvelin.log(Level.INFO, "Palvelinta käynnistetään");
		tkh = new TietokantaHallitsija();
		kayttajat = new ArrayList<Kayttaja>();
		scan = new Scanner(System.in);
		new Thread(this).start();
		System.out.println("Kommennot otettu kï¿½yttï¿½ï¿½n...");
		loggerPalvelin.log(Level.INFO, "Palvelin käynnistetty");
	}

	public boolean liity(AsiakasRajapinta art) throws RemoteException {
		return false;
	}

	public synchronized String kirjaudu(String nimi, String salasana, AsiakasRajapinta arp) throws RemoteException {
		System.out.println("Kokeillaan kirajutua tiedoilla: "+ nimi + ":" + salasana );
		String tulos = "";
		ResultSet tulokset = tkh.kirjauduSisaan(nimi, salasana);
		System.out.println("resultset saatu");
		try {
			if(tulokset.isBeforeFirst()){
				System.out.println("Saatiin tulos");
				if(tulokset.getString(4).equals(true)){
					
					tulos += 2 +tulokset.getString(5);
				}else{
					kayttajat.add(new Kayttaja(tulokset.getString(2), tulokset.getString(1), arp));
					tulos += 1;
				}
			}else{
				System.out.println("Käyttäjää ei löydy");
				arp.vastaanotaViesti("pepperoni"); //vain debuggaukseen kehityksen aikana
				tulos += 0;
			}
		} catch (SQLException e) {
			loggerPalvelin.log(Level.SEVERE, "Kirjautuminen epäonnistui tietokannan puolella, onko yhteys voimassa?" + e.toString());
			tulos = "0";
		}
		return tulos;
	}

	public synchronized String rekisteroidu(String nimi, String salasana) throws RemoteException {
		tkh.rekisteroi(nimi, salasana);
		loggerPalvelin.log(Level.INFO, "Uusi kirjautuminen rekisteröityminen: " + nimi+":"+salasana);
		return "1";
	}

	public void lahetaViesti(String msg) throws RemoteException {
		if(kayttajat.size()!=0){
			loggerChatti.log(Level.INFO, msg);
			for (Kayttaja kayttaja : kayttajat) {
				kayttaja.vastaanotaViesti(msg);
			}
		}
	}

	public synchronized void kirjauduUlos() throws RemoteException {
		kayttajat.remove(0); //<- parametri tulee olemaan jotain kunnollista, joka lï¿½hetetï¿½ï¿½n kirjauduUlos metodin kutsussa parametrina
	}

	private void otaKomento(String s){
		String[] syote = s.split(" ", 2);
		switch (syote[0]) {
		case "apua":
			System.out.println("Avustukset....");
			break;
		case "kuuluta":
			System.out.println("Kuullutetaan viesti pelaajille...");
			break;
		case "sammuta":
			sammuta();
			break;
		case "kayttajat":
			listaaKayttajat();
			break;
		default:
			System.out.println("Komentoa ei tunnisteta... Kï¿½ytï¿½ help");
			break;
		}
	}

	private void listaaKayttajat(){
		if(kayttajat.size()!=0){
			for (Kayttaja kayttaja : kayttajat) {
				System.out.println(kayttaja.annaNimimerkki()+ ":" + kayttaja.annaID() + " " + kayttaja.annaTila());
			}
		}else{
			System.out.println("Palvelimella ei ole kï¿½yttï¿½jiï¿½");
		}
	}

	private void sammuta(){
		try {
			lahetaViesti("PALVELIN: PALVELIN SAMMUTETAAN MINUUTIN KULUTTUA. PYYDï¿½MME TEITï¿½ KIRJAUTUMAAN ULOS PELISTï¿½");
			Thread.sleep(60000);//Odotetaan minuutti
			System.exit(0);
		} catch (InterruptedException | RemoteException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(true){
			otaKomento(scan.nextLine());
		}
	}

	@Override
	public void etsiPelia() {
		
	}

}
