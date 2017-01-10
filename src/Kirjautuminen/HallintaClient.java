package Kirjautuminen;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.AsiakasRajapinta;
import server.PalvelinRajapinta;

@SuppressWarnings("deprecation")
public class HallintaClient extends UnicastRemoteObject implements AsiakasRajapinta{
	

	private static final long serialVersionUID = 1L;
	private String nimimerkki;
	private String salasana;
	private String salasanauusi;
	private String nimi;
	private PalvelinRajapinta prp;
	private String osoite;
	
	@SuppressWarnings("deprecation")
	public HallintaClient() throws RemoteException{
		System.setSecurityManager(new RMISecurityManager());
		
		try{
			prp = (PalvelinRajapinta) Naming.lookup("rmi://" +osoite+"/peli");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void vastaanotaViesti(String msg) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int ping() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] vastaanotaTulokset(String[] tulokset) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
