package server;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Scanner;
import client.AsiakasRajapinta;

public class PalvelinToteutus extends UnicastRemoteObject implements PalvelinRajapinta, Runnable{

	private static final long serialVersionUID = 1L;
	TietokantaHallitsija tkh;
	ArrayList<Kayttaja> kayttajat;
	Scanner scan;

	public PalvelinToteutus() throws RemoteException{
		tkh = new TietokantaHallitsija();
		kayttajat = new ArrayList<Kayttaja>();
		scan = new Scanner(System.in);
		new Thread(this).start();
		System.out.println("Kommennot otettu k�ytt��n...");
	}

	public boolean liity(AsiakasRajapinta) throws RemoteException {
		return false;
	}

	public synchronized String kirjaudu(String nimi, String salasana) throws RemoteException {
		String tulos = "";
		ResultSet tulokset = tkh.kirjauduSisaan(nimi, salasana);
		if(tulokset.next()){
			if(tulokset.getString(3) == true){
				tulos += 2 +tulokset.get(4);
			}else{
				Kayttajat.add(new Kayttaja(tulokset.getString(1), tulokset.getString(0)))
				tulos += 1;
			}
		}else{
			tulos += 1;
		}

		return tulos;
	}

	public synchronized String rekisteroidu(String nimi, String salasana) throws RemoteException {
		return false;
	}

	public void lahetaViesti(String msg) throws RemoteException {
		if(kayttajat.size()!=0){
			for (Kayttaja kayttaja : kayttajat) {
				kayttaja.vastaanotaViesti(msg);
			}
		}
	}

	public synchronized void kirjauduUlos() throws RemoteException {
		kayttajat.remove(0); //<- parametri tulee olemaan jotain kunnollista, joka l�hetet��n kirjauduUlos metodin kutsussa parametrina
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
			lahetaViesti("PALVELIN: PALVELIN SAMMUTETAAN MINUUTIN KULUTTUA. PYYD�MME TEIT� KIRJAUTUMAAN ULOS PELIST�");
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

}
