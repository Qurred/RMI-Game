package server;

import java.rmi.RemoteException;
import domain.*;

public interface PalvelinRajapinta extends java.rmi.Remote{
	public boolean liity(/*Tänne tulee clientin tiedot jne*/) throws RemoteException;
	public boolean kirjaudu(String nimi, String salasana) throws RemoteException;
	public boolean rekisteroidu(String nimi, String salasana) throws RemoteException;
//	public User annaKayttaja() throws RemoteException;
	public boolean lahetaViesti(String msg) throws RemoteException;
	
}
