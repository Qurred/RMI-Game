package server;

import java.rmi.RemoteException;
import java.util.ArrayList;

import domain.Message;
import domain.User;

public class Palvelin implements PalvelinRajapinta, Runnable{
	
	TietokantaHallitsija tkh;
//	ArrayList<AsiakasRajapinta> kayttajat;
	
	public Palvelin(String osoite){
		tkh = new TietokantaHallitsija(osoite);
//		kayttajat = new ArrayList<AsiakasRajapinta>();
	}

	public boolean liity() throws RemoteException {
		return false;
	}

	public boolean kirjaudu(String nimi, String salasana) throws RemoteException {
		
		return false;
	}

	public boolean rekisteroidu(String nimi, String salasana) throws RemoteException {
		return false;
	}

	public boolean lahetaViesti(String msg) throws RemoteException {
		//K‰yd‰‰n k‰ytt‰j‰t l‰pi ja l‰het‰‰n niille viestit
		//Esim metodilla 'vastaanOta' tai vastaavalla asiakaspuolenrajapinnassa
		return false;
	}

	public void run() {
		/*
		 * K‰yd‰‰n syˆtteit‰ konsoliin l‰pi
		 */
	}

}
