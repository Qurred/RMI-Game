package server;

import java.rmi.RemoteException;

public class Kayttaja{

	public static final int IDLE = 0;
	public static final int ETSIMASSA = 1;
	public static final int PELISSA = 2;
	
	private int id;
	private String nimimerkki;
	private int tila;
	
	public Kayttaja() throws RemoteException{
		this.tila = IDLE;
		this.nimimerkki = "";
		this.id = 0;
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
	
}
