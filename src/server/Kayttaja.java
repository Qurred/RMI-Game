package server;

import java.rmi.RemoteException;

import client.AsiakasRajapinta;

public class Kayttaja{

	public static final int IDLE = 0;
	public static final int ETSIMASSA = 1;
	public static final int PELISSA = 2;
	
	private int id;
	private String nimimerkki;
	private int tila;
	private AsiakasRajapinta asiakas;
	
	public Kayttaja(String nimimerkki,String id, AsiakasRajapinta arp) throws RemoteException{
		this.tila = IDLE;
		this.nimimerkki = nimimerkki;
		this.id = Integer.parseInt(id);
		this.asiakas = arp;
	}
	public void vaihdaTila(int i){
		this.tila = i;
	}
	
	public int annaID(){
		return this.id;
	}
	public String annaNimimerkki(){
		return this.nimimerkki;
	}
	public int annaTila(){
		return this.tila;
	}
	public void vastaanotaViesti(String s){
		try {
			asiakas.vastaanotaViesti(s);
		} catch (RemoteException e) {
			System.err.println("Virhe tapahtui lähetettäessä");
		}
	}
	
}
