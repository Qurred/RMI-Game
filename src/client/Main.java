package client;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import nakymat.YleisNakyma;

public class Main extends UnicastRemoteObject implements AsiakasRajapinta {
	private JFrame ikkuna;
	private Dimension dim = new Dimension(800,450);
	private YleisNakyma yleis;

	public static void main(String[] args) throws RemoteException{
		Main main = new Main();
	}

	public Main() throws RemoteException{
		Data.Alusta();
		Data.lisaaNakymat(dim);
		yleis = (YleisNakyma) Data.nakymat.get(Data.PERUSNAKYMA);
		ikkuna = new JFrame("RMI-Game"); 
		ikkuna.setSize(dim);
		ikkuna.setLayout(null);
		ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ikkuna.setVisible(true);
		ikkuna.setLocationRelativeTo(null);
		ikkuna.setResizable(false);
		for(int i = 0; i < Data.nakymat.size(); i++){
			ikkuna.add(Data.nakymat.get(i));
		}
		JLabel tausta = new JLabel();
		tausta.setBounds(0, 0, dim.width, dim.height);
		tausta.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/tausta.png"))));
		tausta.setOpaque(false);
		tausta.setVisible(true);
		ikkuna.add(tausta);
		Data.arp = this;
	}

	@Override
	public void vastaanotaViesti(String msg) throws RemoteException {
		yleis.vastaanotaViesti(msg);
		
	}

	@Override
	public int ping() throws RemoteException {
		return 0;
	}

	@Override
	public void vastaanotaTulokset(String[] tulokset) throws RemoteException {
		for(int i = 0; i < tulokset.length; i++){
			System.out.println(tulokset[i]);
		}
	}
}



