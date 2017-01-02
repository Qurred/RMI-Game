package peli;

import java.util.ArrayList;

import Entiteetit.Hahmo;

public class Peli implements Runnable {

	
	private Joukkue sininenTiimi;
	private Joukkue punainenTiimi;
	
	private ArrayList<String> tapahtumat;
	
	public Peli(Joukkue sininen, Joukkue punainen){
		this.sininenTiimi = sininen;
		this.punainenTiimi = punainen;
		
	}
	
	public void run() {
		tapahtumat.add("Aloitetaan peli pelaajien " + sininenTiimi.annaNimi() + " & " + punainenTiimi.annaNimi()+ " kesken");
		
	}
	
	public void kysyAsetelmat(){
		//TODO t‰m‰ pit‰isi siirt‰‰ kokonaan clientin puolelle
		sininenTiimi.kysyAsetelmaa();
		punainenTiimi.kysyAsetelmaa();
	}
	
	public void tarkistaVoittaja(){}
	
	public void tallennaTapahtumat(){}
	
	public void tulosTietokantaan(){}
	

}
