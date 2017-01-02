package peli;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Entiteetit.Hahmo;
import client.AsiakasRajapinta;

public class Joukkue extends UnicastRemoteObject{
	
	private String nimi;
	private AsiakasRajapinta arp;
	private ArrayList<Hahmo> hahmot;
	private int asetelma;
	
	public Joukkue(AsiakasRajapinta arp, String nimi) throws RemoteException{
		this.hahmot = new ArrayList<Hahmo>();
		this.arp = arp;
		this.nimi = nimi;
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


	public void asetaAsetelma(int asetelma) {
		this.asetelma = asetelma;
	}


	public void kysyAsetelmaa(){
		this.asetelma = 0; //arp.kysyAsetaelmaa();
	}
	
	public void tiedotaVuoro(){
		
	}

}
