package server;


import java.net.InetAddress;
import java.rmi.Naming;

public class PalvelinSovellus {

	public static void main(String[] args) {
		try {
			InetAddress i = InetAddress.getLocalHost();
			PalvelinToteutus palvelin = new PalvelinToteutus();
			Naming.rebind("rmi://"+i.getHostAddress()+"/peli", palvelin);
			System.out.println("Palvelin k�ynnistetty onnistuneesti sis�verkon osoitteella: " + i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
