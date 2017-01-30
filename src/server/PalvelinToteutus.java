package server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import client.AsiakasRajapinta;

/**
 * @author Ruumis
 *
 */
public class PalvelinToteutus extends UnicastRemoteObject implements PalvelinRajapinta, Runnable{

	private static final long serialVersionUID = 1L;
	private TietokantaHallitsija tkh;
	private List<Kayttaja> kayttajat;
	private Scanner scan;
	private Logger loggerPalvelin;
	private ArrayList<Peli> pelit;
	private ArrayList<Hahmo> hahmot;
	private KeskusteluHallitsija kh;

	public PalvelinToteutus() throws RemoteException{
		loggerPalvelin = Logger.getLogger("Palvelin");
		try {
			FileHandler handlerPalvelin = new FileHandler("palvelinLog");
			FileHandler handlerChatti = new FileHandler("chattiLog");
			handlerChatti.setFormatter(new SimpleFormatter()); //Muuttaa login formaatin helpommin luettavaan muotoon xml:n sijasta
			loggerPalvelin.addHandler(handlerPalvelin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		loggerPalvelin.log(Level.INFO, "Palvelinta k‰ynnistet‰‰n");
		tkh = new TietokantaHallitsija();
		kayttajat = Collections.synchronizedList(new ArrayList<Kayttaja>());
		scan = new Scanner(System.in);
		pelit = new ArrayList<Peli>();
		alustaHahmot();
		try {
			kh = new KeskusteluHallitsija(kayttajat);
		} catch (IOException e) {
			loggerPalvelin.log(Level.WARNING, "Keskustelujen hallitsijaa ei kyetty kaynnistamaan tallentamisen kannalta");
		}
		kh.start();
		new Thread(this).start();
		loggerPalvelin.log(Level.INFO, "Palvelin k‰ynnistetty");
	}

	public synchronized String kirjaudu(String nimi, String salasana, AsiakasRajapinta arp) throws RemoteException {
		System.out.println("Kokeillaan kirajutua tiedoilla: "+ nimi + ":" + salasana );
		String tulos = "";
		ResultSet tulokset = tkh.kirjauduSisaan(nimi, salasana);
		if(tulokset == null){
			tulos += 0;
		}else{
			try {
				if(tulokset.isBeforeFirst()){ // Tarkistetaan onko resultsetiss‰ osumia
					if(tulokset.getString(4).equals("1")){ // K‰ytt‰j‰ on estetty
						tulos += 2 +tulokset.getString(5); //Lis‰t‰‰n tulokseen luku 2 ja est‰misen syy
					}else{
						for (int i = 0; i < kayttajat.size(); i++) {
							if(kayttajat.get(i).annaID() == Integer.parseInt(tulokset.getString(1))){
								if(kayttajat.get(i).annaTila() == Kayttaja.POISSA){
									kayttajat.remove(i);
									break;
								}else{
									tulos += 2 + "K‰ytt‰j‰ on jo kirjautunut sis‰‰n, yrit‰ hetken p‰‰st‰ myˆhemmin";
									kayttajat.get(i).vaihdaTila(Kayttaja.POISSA);
									tulokset.getStatement().close();
									tulokset.close();
									return tulos;
								}
							}
						}
						String uuid = UUID.randomUUID().toString();
						kayttajat.add(new Kayttaja(tulokset.getString(2), tulokset.getString(1), arp, uuid));
						tulos += "1" + uuid;
					}
				}else{
					System.out.println("K‰ytt‰j‰‰ ei lˆydy");
					tulos += 0;
				}
				tulokset.getStatement().close();
				tulokset.close();
			} catch (SQLException e) {
				loggerPalvelin.log(Level.SEVERE, "Kirjautuminen ep‰onnistui tietokannan puolella, onko yhteys voimassa?" + e.toString());
				tulos = "0";
			}
		}
		return tulos;
	}

	public synchronized String rekisteroidu(String nimi, String salasana) throws RemoteException {
		tkh.rekisteroi(nimi.toLowerCase(), salasana);
		loggerPalvelin.log(Level.INFO, "Uusi rekisterˆityminen: " + nimi+":"+salasana);
		return "1";
	}

	public void lahetaViesti(String msg, String nimimerkki) throws RemoteException {
		kh.lisaaJonoon(nimimerkki + ": " + msg);
		System.out.println(nimimerkki + ": " + msg);
	}

	private void otaKomento(String s) throws RemoteException{
		try{
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
				System.out.println("Komentoa ei tunnisteta... KÔøΩytÔøΩ help");
				break;
			}
		}catch (Exception e) {
			System.out.println("Ei ollut oikeaa muotoa...");
		}
	}

	private void listaaKayttajat(){
		if(kayttajat.size()!=0){
			for (Kayttaja kayttaja : kayttajat) {
				System.out.println(kayttaja.annaNimimerkki()+ ":" + kayttaja.annaID() + " " + kayttaja.annaTila());
			}
		}else{
			System.out.println("Palvelimella ei ole kÔøΩyttÔøΩjiÔøΩ");
		}
	}

	private void sammuta(){
		try {
			lahetaViesti("PALVELIN SAMMUTETAAN", "[PALVELIN]");
			tkh.annaYhteys().close();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			kh.sammuta();
			System.exit(0);
		} catch (/*InterruptedException |*/ RemoteException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void etsiPelia(String identitykey, int[] hahmotiedot) throws RemoteException {
		for (Kayttaja kayttaja : kayttajat) {
			if(kayttaja.annaUUID().equals(identitykey) && kayttaja.annaTila() != Kayttaja.PELISSA){
				kayttaja.vaihdaTila(Kayttaja.PELISSA);
				ArrayList<Hahmo> hahmot = new ArrayList<>();
				Joukkue joukkue = new Joukkue(kayttaja.annaRajapinta(), kayttaja.annaNimimerkki(), kayttaja.annaID(), hahmot);
				if(pelit.size() > 0){
					for (int i = 0; i < pelit.size(); i++) {
						if(!pelit.get(i).onMolemmat()){
							pelit.get(i).liityPeliin(joukkue);
							break;
						}
						if(i == pelit.size()-1){
							pelit.add(new Peli(joukkue, tkh.annaYhteys()));
							pelit.get(pelit.size()).run();			
						}
					}
				}
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

}
