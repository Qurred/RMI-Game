package server;

import java.rmi.RemoteException;

import client.AsiakasRajapinta;

public class Kayttaja{

	public static final int IDLE = 0;
	public static final int POISSA = 1;
	public static final int PELISSA = 2;

	private int id;
	private String nimimerkki;
	private int tila;
	private AsiakasRajapinta asiakas;
	private String uuid;
	private boolean poissa = false;

	public Kayttaja(String nimimerkki,String id, AsiakasRajapinta arp, String uuid) throws RemoteException{
		this.tila = IDLE;
		this.nimimerkki = nimimerkki;
		this.id = Integer.parseInt(id);
		this.asiakas = arp;
		this.uuid = uuid;
	}
	public void vaihdaTila(int i){
		this.tila = i;
	}
	
	public AsiakasRajapinta annaRajapinta(){
		return this.asiakas;
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

	public String annaUUID(){
		return this.uuid;
	}
	
	public boolean annaPoissa(){
		return this.poissa;
	}
	
	public void vastaanotaViesti(String s){
		try {
			asiakas.vastaanotaViesti(s);
		} catch (RemoteException e) {
			System.out.println(this.nimimerkki + " ei vastaa");
			this.poissa = true;
		}
	}

}
