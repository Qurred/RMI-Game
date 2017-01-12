package server;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Entiteetit.Hahmo;
import peli.Peli;

import client.AsiakasRajapinta;

/**
 * @author Ruumis
 *
 */
public class PalvelinToteutus extends UnicastRemoteObject implements PalvelinRajapinta, Runnable{

	private static final long serialVersionUID = 1L;
	private TietokantaHallitsija tkh;
	private ArrayList<Kayttaja> kayttajat;
	private Scanner scan;
	private Logger loggerPalvelin;
	private Logger loggerChatti;
	private ArrayList<Peli> pelit;
	private ArrayList<Hahmo> hahmot;

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
		loggerPalvelin.log(Level.INFO, "Palvelinta k�ynnistet��n");
		tkh = new TietokantaHallitsija();
		kayttajat = new ArrayList<Kayttaja>();
		scan = new Scanner(System.in);
		pelit = new ArrayList<Peli>();
		alustaHahmot();
		new Thread(this).start();
		loggerPalvelin.log(Level.INFO, "Palvelin k�ynnistetty");
	}

	public synchronized String kirjaudu(String nimi, String salasana, AsiakasRajapinta arp) throws RemoteException {
		System.out.println("Kokeillaan kirajutua tiedoilla: "+ nimi + ":" + salasana );
		String tulos = "";
		ResultSet tulokset = tkh.kirjauduSisaan(nimi, salasana);
		if(tulokset == null){
			tulos += 0;
		}else{
			try {
				if(tulokset.isBeforeFirst()){ // Tarkistetaan onko resultsetiss� osumia
					if(tulokset.getString(4).equals(true)){ // K�ytt�j� on estetty
						tulos += 2 +tulokset.getString(5); //Lis�t��n tulokseen luku 2 ja est�misen syy
					}else{
						for (Kayttaja kayttaja : kayttajat) {
							if(kayttaja.annaID() == Integer.parseInt(tulokset.getString(1))){
								tulos += 2 + "K�ytt�j� on jo kirjautunut sis��n, yrit� hetken p��st� my�hemmin";
								tulokset.getStatement().close();
								tulokset.close();
								return tulos;
							}
						}
						String uuid = UUID.randomUUID().toString();
						kayttajat.add(new Kayttaja(tulokset.getString(2), tulokset.getString(1), arp, uuid));
						tulos += "1" + uuid;
					}
				}else{
					System.out.println("K�ytt�j�� ei l�ydy");
					tulos += 0;
				}
				tulokset.getStatement().close();
				tulokset.close();
			} catch (SQLException e) {
				loggerPalvelin.log(Level.SEVERE, "Kirjautuminen ep�onnistui tietokannan puolella, onko yhteys voimassa?" + e.toString());
				tulos = "0";
			}
		}
		return tulos;
	}

	public synchronized String rekisteroidu(String nimi, String salasana) throws RemoteException {
		tkh.rekisteroi(nimi, salasana);
		loggerPalvelin.log(Level.INFO, "Uusi kirjautuminen rekister�ityminen: " + nimi+":"+salasana);
		return "1";
	}

	public void lahetaViesti(String msg, String nimimerkki) throws RemoteException {
		if(kayttajat.size()!=0){
			loggerChatti.log(Level.INFO, nimimerkki +": " + msg);
			for (Kayttaja kayttaja : kayttajat) {
				kayttaja.vastaanotaViesti( nimimerkki +": " + msg);
			}
		}
	}

	private void otaKomento(String s) throws RemoteException{
		String[] syote = s.split(" ", 2);
		switch (syote[0]) {
		case "apua":
			System.out.println("Avustukset....");
			break;
		case "kuuluta":
			System.out.println("Kuullutetaan viesti pelaajille...");
			lahetaViesti(syote[1], "[PALVELIN]");
			break;
		case "sammuta":
			sammuta();
			break;
		case "kayttajat":
			listaaKayttajat();
			break;
		case "hahmot":
			listaaHahmot();
			break;
		default:
			System.out.println("Komentoa ei tunnisteta... K�yt� help");
			break;
		}
	}

	private void listaaKayttajat(){
		if(kayttajat.size()!=0){
			for (Kayttaja kayttaja : kayttajat) {
				System.out.println(kayttaja.annaNimimerkki()+ ":" + kayttaja.annaID() + " " + kayttaja.annaTila());
			}
		}else{
			System.out.println("Palvelimella ei ole k�ytt�ji�");
		}
	}

	private void sammuta(){
		try {
			lahetaViesti("PALVELIN SAMMUTETAAN MINUUTIN KULUTTUA. PYYD�MME TEIT� KIRJAUTUMAAN ULOS PELIST�", "[PALVELIN]");
			//Thread.sleep(60000);//Odotetaan minuutti
			System.exit(0);
		} catch (/*InterruptedException |*/ RemoteException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(true){
			try {
				otaKomento(scan.nextLine());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String annaTiedot(String identitykey) throws RemoteException {
		String tiedot = ""; //tulee olemaan formaattia: [Nimimerkki]:[muutasiat]
		for (Kayttaja kayttaja : kayttajat) {
			if(kayttaja.annaUUID().equals(identitykey)){
				tiedot += kayttaja.annaNimimerkki();
			}
		}
		return tiedot;
	}

	@Override
	public synchronized void kirjauduUlos(String identitykey) throws RemoteException {
		for (int i = 0; i < kayttajat.size(); i++){
			if(kayttajat.get(i).annaUUID().equals(identitykey)){
				kayttajat.remove(i);
			}
		}

	}

	@Override
	public void etsiPelia(String identitykey, ArrayList<Hahmo> hahmot) throws RemoteException {
		for (Kayttaja kayttaja : kayttajat) {
			if(kayttaja.annaUUID().equals(identitykey)){
				kayttaja.vaihdaTila(Kayttaja.ETSIMASSA);
				//T��ll� pit�isi tehd� paljon muutakin ._.
			}
		}
	}
	
	public void alustaHahmot(){
		hahmot = tkh.annaHahmot();
	}

	public void listaaHahmot(){
		for (Hahmo hahmo : hahmot) {
			System.out.println(hahmo.toString());
		}
	}

	@Override
	public ArrayList<Hahmo> annaHahmot() throws RemoteException {
		return (ArrayList<Hahmo>) hahmot.clone();
	}
}
