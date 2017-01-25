package client;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Info{

	private String nimi;
	private String luokka;
	private String tarina;
	private ImageIcon kuva;

	public Info(String nimi, String luokka, String tarina, String kuva){
		this.asetaNimi(nimi);
		this.asetaLuokka(luokka);
		this.asetaTarina(tarina);
		this.asetaKuva(kuva);
	}

	public String annaNimi() {
		return nimi;
	}

	public String annaLuokka() {
		return luokka;
	}

	public String annaTarina() {
		return tarina;
	}



	public ImageIcon annaKuva(){
		return kuva;
	}

	public void asetaNimi(String nimi) {
		this.nimi = nimi;
	}

	public void asetaLuokka(String luokka) {
		this.luokka = luokka;
	}

	public void asetaTarina(String tarina) {
		this.tarina = tarina;
	}

	public void asetaKuva(String kuva){
		System.out.println(kuva);
		this.kuva = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/"+kuva)));
	}
}


