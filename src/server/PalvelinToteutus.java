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

	public boolean kirjaudu(String nimi, String salasana) throws RemoteException {
		
		return false;
	}

	public boolean rekisteroidu(String nimi, String salasana) throws RemoteException {
		return false;
	}

	public boolean lahetaViesti(String msg) throws RemoteException {
		//Käydään käyttäjät läpi ja lähetään niille viestit
		//Esim metodilla 'vastaanOta' tai vastaavalla asiakaspuolenrajapinnassa
		return false;
	}

	private void otaKomento(String s){
		String[] syote = s.split(" ", 2);
		switch (syote[0]) {
		case "help":
			System.out.println("Avustukset....");
			break;
		case "msg":
			System.out.println("Kuullutetaan viesti pelaajille...");
			break;
		case "shutdown":
			System.out.println("Sammutetaan palvelin");
			System.exit(0);
		default:
			System.out.println("Komentoa ei tunnisteta... Käytä help");
			break;
		}
	}
	
	public void run() {
		while(true){
			
			otaKomento(scan.nextLine());
		}
	}

}
