package client;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import nakumat.OsoiteNakyma;
import server.PalvelinRajapinta;

public class Data {

	public static final int IP = 0;
	public static final int KIRJAUTUMINEN = 1;
	public static final int PERUSNAKYMA = 2;
	public static final int HAHMONAKYMA = 3;
	
	public static String ip;
	public static String nimi;
	public static String uuid;
	public static ArrayList<Info> hahmot;
	public static PalvelinRajapinta prp;
	public static ArrayList<JPanel> nakymat;
	public static ArrayList<Info> tiedot;
	public static JPanel nykyinen;
	

	public static void Alusta(){
		hahmot = new ArrayList<Info>();
		nakymat = new ArrayList<JPanel>();
	}
	
	public static void lisaaNakymat(Dimension dim){
		//Lisättäisiin kaikki kivat nakuilukuvat
		nakymat.add(new OsoiteNakyma(dim));
		for (JPanel nakyma : nakymat) {
			nakyma.setVisible(false);
		}
		
		//Laitetaan yhdistämis näkymä alustavaksi näkymäksi
		nykyinen = nakymat.get(0);
		nykyinen.setVisible(true);
	}
	
	public static ArrayList<JPanel> annaNakymat(){
		return nakymat;
	}
	
	public static void vaihdaNakyma(int i){
		nykyinen.setVisible(false);
		nykyinen = nakymat.get(i);
		nykyinen.setVisible(true);
	}
	
}
