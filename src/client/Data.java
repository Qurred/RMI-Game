package client;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import hahmonakymakokeilu.HahmoNakyma;
import nakymat.Hahmojenvalinta;
import nakymat.OsoiteNakyma;
import server.PalvelinRajapinta;

public class Data {

	public static final int IP = 0;
	public static final int KIRJAUTUMINEN = 1;
	public static final int HAHMONAKYMA = 2;
	public static final int PERUSNAKYMA = 3;
	
	public static String ip;
	public static String nimi;
	public static String uuid;
	public static ArrayList<Info> hahmot;
	public static PalvelinRajapinta prp;
	public static ArrayList<JPanel> nakymat;
	public static ArrayList<Info> tiedot;
	public static JPanel nykyinen;
	public static AsiakasRajapinta arp;
	
	public static void Alusta(){
		hahmot = new ArrayList<Info>();
		nakymat = new ArrayList<JPanel>();
	}
	
	public static void lisaaNakymat(Dimension dim){
		//Lis‰tt‰isiin kaikki kivat nakuilukuvat
		nakymat.add(new OsoiteNakyma(dim));
		nakymat.add(new Kirjautuminen());
		nakymat.add(new HahmoNakyma(dim));
		nakymat.add(new Hahmojenvalinta(dim)); //Lis‰t‰‰n varmaankin yleiseen JPaneeliin
		for (JPanel nakyma : nakymat) {
			nakyma.setVisible(false);
		}
		
		//Laitetaan yhdist‰mis n‰kym‰ alustavaksi n‰kym‰ksi
		nykyinen = nakymat.get(0);
		nykyinen.setVisible(true);
	}
	
	public static void vaihdaNakyma(int i){
		nykyinen.setVisible(false);
		nykyinen = nakymat.get(i);
		nykyinen.setVisible(true);
	}
	
}
