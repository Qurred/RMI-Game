package server;

import java.rmi.RemoteException;
import domain.*;

public interface ServerInterface extends java.rmi.Remote{
	
	public boolean login(String username, String password) throws RemoteException;
	public User getUser() throws RemoteException;
	public boolean sendMessage(Message msg) throws RemoteException;
	
}
