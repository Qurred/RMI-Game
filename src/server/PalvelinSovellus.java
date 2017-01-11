package server;


import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class PalvelinSovellus {

	public static final int PORTTI = 1099;

	public static void main(String[] args) {
		try {
			//System.setSecurityManager(new RMISecurityManager());
			LocateRegistry.createRegistry(PORTTI);
			InetAddress i = InetAddress.getLocalHost();
			PalvelinToteutus palvelin = new PalvelinToteutus();
			Naming.rebind("rmi://"+i.getHostAddress()+"/peli", palvelin);
			System.out.println("Palvelin käynnistetty onnistuneesti sisäverkon osoitteella: " + i.getHostAddress()+"/"+PORTTI+
					"\nRebindattu osoitteeseen: " + "rmi://"+i.getHostAddress()+"/peli");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
