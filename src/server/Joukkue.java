package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import client.AsiakasRajapinta;

public class Joukkue extends UnicastRemoteObject{
	
	private String nimi;
	private int pelaajanID;
	private AsiakasRajapinta arp;
	private ArrayList<Hahmo> hahmot;
	private int asetelma;
	private int yhteisElama;
	
	public Joukkue(AsiakasRajapinta arp, String nimi, int pelaajanID, ArrayList<Hahmo> hahmot) throws RemoteException{
		this.hahmot = (ArrayList<Hahmo>) hahmot.clone();
		this.arp = arp;
		this.nimi = nimi;
		this.asetaPelaajanID(pelaajanID);
	}
	
	
	public String annaNimi() {
		return nimi;
	}


	public void asetaNimi(String nimi) {
		this.nimi = nimi;
	}


	public ArrayList<Hahmo> annaHahmot() {
		return hahmot;
	}


	public void asetaHahmot(ArrayList<Hahmo> hahmot) {
		this.hahmot = hahmot;
	}


	public int annaAsetelma() {
		return asetelma;
	}

	public void annaTiedot(ArrayList<String> tapahtumat){
		for (String tapahtuma : tapahtumat) {
			//arp.tulostaTiedot(tapahtuma);
		}	
	}
	
	public int annaPelaajanID() {
		return pelaajanID;
	}


	public void asetaPelaajanID(int pelaajanID) {
		this.pelaajanID = pelaajanID;
	}


	public double laskeMenetys(){
		double maksimi = 0;
		double nykyinen = 0;
		for (Hahmo hahmo : hahmot) {
			maksimi += hahmo.annaMaksimiElama();
			nykyinen += hahmo.annaElama();
		}
		return nykyinen / maksimi * 100;
	}

}
