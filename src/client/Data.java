package client;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import nakumat.OsoiteNakyma;
import server.PalvelinRajapinta;

public class Data {

	public static String ip;
	public static String nimi;
	public static String uuid;
	public static ArrayList<Info> hahmot;
	public static PalvelinRajapinta prp;
	public static ArrayList<JPanel> nakymat;
	public static JPanel nykyinen;

	public static void Alusta(){
		hahmot = new ArrayList<Info>();
		nakymat = new ArrayList<JPanel>();
	}
	
	public static void lisaaNakymat(Dimension dim){
		//Lisättäisiin kaikki kivat nakuilukuvat
		nakymat.add(new OsoiteNakyma(dim));
		nykyinen = nakymat.get(0);
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
