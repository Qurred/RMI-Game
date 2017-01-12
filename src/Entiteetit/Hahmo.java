package Entiteetit;

public class Hahmo {
	
	private String nimi;
	private int elama;
	private int maksimiElama;
	private int puolustusLahi;
	private int puolustusMatka;
	private int nopeus;
	private int moraali;
	private int tyyppi;
	private int id;
	private int hyokkaus;
	
	public Hahmo(int id, String nimi, int tyyppi, int hp, int pl, int pm, int nopeus, int hyokkaus){
		this.id = id;
		this.nimi = nimi;
		this.tyyppi = tyyppi;
		this.elama = hp;
		this.puolustusLahi = hp;
		this.puolustusMatka = pm;
		this.nopeus = nopeus;
		this.hyokkaus = hyokkaus;
	}
	
	@Override
	public String toString() {
		return this.nimi;
	}
	
	

}
