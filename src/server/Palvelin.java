package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Palvelin extends UnicastRemoteObject implements PalvelinRajapinta, Runnable{
	
	TietokantaHallitsija tkh;
//	ArrayList<AsiakasRajapinta> kayttajat;
	
	public Palvelin()throws RemoteException{
		tkh = new TietokantaHallitsija();
//		kayttajat = new ArrayList<AsiakasRajapinta>();
//		new Thread(this).start();
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
