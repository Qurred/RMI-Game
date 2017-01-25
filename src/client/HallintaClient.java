package client;

import java.rmi.Naming;
//import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
		//System.setSecurityManager(new RMISecurityManager());

		try{
			Data.prp = (PalvelinRajapinta) Naming.lookup("rmi://" +"192.168.0.195"+"/peli");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void vastaanotaViesti(String msg) throws RemoteException {
		System.out.println(msg);		
	}

	@Override
	public int ping() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void vastaanotaTulokset(String[] tulokset) throws RemoteException {
		// TODO Auto-generated method stub
	}

}
