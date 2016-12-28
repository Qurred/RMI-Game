package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AsiakasRajapinta extends Remote{
 public void vastaanotaViesti(String msg) throws RemoteException;

 
}