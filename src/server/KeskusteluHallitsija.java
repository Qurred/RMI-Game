package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class KeskusteluHallitsija extends Thread {

	private static ArrayList<String> viestit;
	private static List<Kayttaja> kayttajat;
	private PrintWriter kirjoittaja;
	private File logi;
	private FileWriter tiedosto;
	private BufferedWriter lukija;
	private boolean paalla = true ;
	
	public KeskusteluHallitsija( List<Kayttaja> kayttajat) throws IOException {
		KeskusteluHallitsija.viestit = new ArrayList<String>();
		KeskusteluHallitsija.kayttajat = kayttajat;
		GregorianCalendar kalenteri = new GregorianCalendar();
		String paivaus = ""+kalenteri.get(Calendar.DATE)+"-" +(kalenteri.get(Calendar.MONTH)+1) + "-" + kalenteri.get(Calendar.YEAR);
		logi = new File("keskusteluLogi"+paivaus+".txt");
		if(!logi.exists()){
			logi.createNewFile();
		}
		tiedosto  = new FileWriter(logi, true);
		lukija = new BufferedWriter(tiedosto);
		kirjoittaja = new PrintWriter(lukija);
		kirjoittaja.println("Keskustelu logi " + paivaus);
	}
	
	public void sammuta(){
		this.paalla = false;
	}
	
	public void lisaaJonoon(String viesti){
		KeskusteluHallitsija.viestit.add(viesti);
	}
	
	@Override
	public void run() {
		kirjoittaja.println("KeskusteluHallitsija P‰‰ll‰");
		while(paalla){
			while(!viestit.isEmpty()){
				kuuluta(viestit.get(0));
				viestit.remove(0);
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {}
		}
		try{
		kirjoittaja.close();
		lukija.close();
		tiedosto.close();
		}catch (Exception e) {
		}
	}

	private void kuuluta(String viesti){
		if(kayttajat.size()!=0){
		//	loggerChatti.log(Level.INFO, nimimerkki +": " + msg);
			for (int i = 0; i < kayttajat.size(); i++) {
				if(!kayttajat.get(i).annaPoissa()){
					System.out.println(kayttajat.get(i).annaNimimerkki());
					kayttajat.get(i).vastaanotaViesti(viesti);
				}else{
					kayttajat.remove(i--);
				}
			}
		}
	}
	
}
