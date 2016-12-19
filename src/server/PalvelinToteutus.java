package server;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Scanner;

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
		System.out.println("Kommennot otettu käyttöön...");
	}

	public boolean liity() throws RemoteException {
		return false;
	}

	public synchronized boolean kirjaudu(String nimi, String salasana) throws RemoteException {
		
		return false;
	}

	public synchronized boolean rekisteroidu(String nimi, String salasana) throws RemoteException {
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
		kayttajat.remove(0); //<- parametri tulee olemaan jotain kunnollista, joka lähetetään kirjauduUlos metodin kutsussa parametrina
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
			System.out.println("Komentoa ei tunnisteta... Käytä help");
			break;
		}
	}
	
	private void listaaKayttajat(){
		if(kayttajat.size()!=0){
			for (Kayttaja kayttaja : kayttajat) {
				System.out.println(kayttaja.annaNimimerkki()+ ":" + kayttaja.annaID() + " " + kayttaja.annaTila());
			}
		}else{
			System.out.println("Palvelimella ei ole käyttäjiä");
		}
	}
	
	private void sammuta(){
		try {
			lahetaViesti("PALVELIN: PALVELIN SAMMUTETAAN MINUUTIN KULUTTUA. PYYDÄMME TEITÄ KIRJAUTUMAAN ULOS PELISTÄ");
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
