package client;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	private static JFrame ikkuna;
	private static Dimension dim = new Dimension(800,450);

	public static void main(String[] args){
		Data.Alusta();
		Data.lisaaNakymat(dim);
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



