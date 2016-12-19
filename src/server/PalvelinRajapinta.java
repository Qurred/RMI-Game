package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PalvelinRajapinta extends Remote{
	public boolean liity(/*Tänne tulee clientin tiedot jne*/) throws RemoteException;
	public boolean kirjaudu(String nimi, String salasana) throws RemoteException;
	public boolean rekisteroidu(String nimi, String salasana) throws RemoteException;
//	public User annaKayttaja() throws RemoteException;
	public void lahetaViesti(String msg) throws RemoteException;
	public void kirjauduUlos() throws RemoteException;
	
}
