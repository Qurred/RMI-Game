package server;

import java.rmi.RemoteException;
import domain.*;

public interface PalvelinRajapinta extends java.rmi.Remote{
	
	public boolean kirjaudu(String nimi, String salasana) throws RemoteException;
	public User annaKayttaja() throws RemoteException;
	public boolean lahetaViesti(Message msg) throws RemoteException;
	
}
