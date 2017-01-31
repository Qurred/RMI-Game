package server;

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
		this.elama = this.maksimiElama = hp;
		this.puolustusLahi = hp;
		this.puolustusMatka = pm;
		this.nopeus = nopeus;
		this.hyokkaus = hyokkaus;
		this.moraali = 100;
	}
	
	
	public int annaElama() {
		return elama;
	}


	public void asetaElama(int elama) {
		this.elama = elama;
	}


	public int annaMaksimiElama() {
		return maksimiElama;
	}


	public void asetaMaksimiElama(int maksimiElama) {
		this.maksimiElama = maksimiElama;
	}


	public int annaPuolustusLahi() {
		return puolustusLahi;
	}


	public void asetaPuolustusLahi(int puolustusLahi) {
		this.puolustusLahi = puolustusLahi;
	}


	public int annaPuolustusMatka() {
		return puolustusMatka;
	}


	public void asetaPuolustusMatka(int puolustusMatka) {
		this.puolustusMatka = puolustusMatka;
	}


	public int annaNopeus() {
		return nopeus;
	}


	public void asetaNopeus(int nopeus) {
		this.nopeus = nopeus;
	}


	public int annaMoraali() {
		return moraali;
	}


	public void asetaMoraali(int moraali) {
		this.moraali = moraali;
	}


	public int annaHyokkaus() {
		return hyokkaus;
	}


	public void asetaHyokkaus(int hyokkaus) {
		this.hyokkaus = hyokkaus;
	}


	public String annaNimi() {
		return nimi;
	}


	public int annaTyyppi() {
		return tyyppi;
	}


	public int annaId() {
		return id;
	}

	public Hahmo kopioi(){
		return new Hahmo(id, nimi, tyyppi, elama, puolustusLahi, puolustusMatka, nopeus, hyokkaus);
	}

	@Override
	public String toString() {
		return this.nimi;
	}
	
	

}
