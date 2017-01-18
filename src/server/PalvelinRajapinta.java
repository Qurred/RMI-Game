package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import client.AsiakasRajapinta;

public interface PalvelinRajapinta extends Remote{
	//	public boolean liity(AsiakasRajapinta ar) throws RemoteException; //Varmaan poistetaan hyödyttömyyden takia
	/**
	 * Metodi joka palauttaa String tyyppisen arvon koskien kirjautumista
	 * @param nimi
	 * @param salasana
	 * @param arp
	 * @return 
	 * @throws RemoteException
	 */
	public String kirjaudu(String nimi, String salasana, AsiakasRajapinta arp) throws RemoteException;
	/**
	 * 
	 * @param nimi
	 * @param salasana
	 * @return
	 * @throws RemoteException
	 */
	public String rekisteroidu(String nimi, String salasana) throws RemoteException;
	/**
	 * 
	 * @param identitykey
	 * @return
	 * @throws RemoteException
	 */
	public String annaTiedot(String identitykey) throws RemoteException;
	/**
	 * 
	 * @param msg
	 * @param nimimerkki
	 * @throws RemoteException
	 */
	public void lahetaViesti(String msg, String nimimerkki) throws RemoteException;
	/**
	 * 
	 * @param identitykey
	 * @throws RemoteException
	 */
	public void kirjauduUlos(String identitykey) throws RemoteException;
	/**
	 * 
	 * @param identitykey
	 * @param hahmot
	 */
	public void etsiPelia(String identitykey, ArrayList<Hahmo> hahmot) throws RemoteException;
	
	public ArrayList<Hahmo> annaHahmot() throws RemoteException;
}
