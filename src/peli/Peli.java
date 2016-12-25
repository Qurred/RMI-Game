package peli;

import java.util.ArrayList;

import Entiteetit.Hahmo;

public class Peli implements Runnable {

	
	//Pitäisikö tiimit toteuttaa Tiimiluokkana? voisi helpoittaa datan käsittelyä
	private ArrayList<Hahmo> sininenTiimi;
	private ArrayList<Hahmo> punainenTiimi;
	
	private ArrayList<String> tapahtumat;
	
	public Peli(ArrayList<Hahmo> sininen, ArrayList<Hahmo> punainen /*, pelaajien yhteydet*/){
		this.sininenTiimi = sininen;
		this.punainenTiimi = punainen;
		
	}
	
	public void run() {}
	
	public void kysyAsetelmat(){}
	
	public void tarkistaVoittaja(){}
	
	public void tallennaTapahtumat(){}
	
	public void tulosTietokantaan(){}
	

}
