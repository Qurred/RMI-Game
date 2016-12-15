package server;

import java.rmi.Naming;

public class PalvelinSovellus {

	public static void main(String[] args) {
		try {
			Palvelin palvelin = new Palvelin();
			Naming.rebind("peli", palvelin);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Se ainut asia fales, ggnore");
		}
	}

}
