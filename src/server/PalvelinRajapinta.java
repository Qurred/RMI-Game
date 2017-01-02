package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import client.AsiakasRajapinta;

public interface PalvelinRajapinta extends Remote{
	public boolean liity(AsiakasRajapinta ar) throws RemoteException;
	public String kirjaudu(String nimi, String salasana, AsiakasRajapinta arp) throws RemoteException;
	public String rekisteroidu(String nimi, String salasana) throws RemoteException;
//	public User annaKayttaja() throws RemoteException;
	public void lahetaViesti(String msg) throws RemoteException;
	public void kirjauduUlos() throws RemoteException;
	public void etsiPelia();
}
