package server;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerManager extends JFrame implements Runnable{
	
	private JTextArea chatArea;
	private JTextField chatTextField;
	private JTable users;
	
	public ServerManager(){
		super("Server");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(8, 6));
		this.setSize(800,600);
		this.setResizable(true);
		this.setVisible(true);
	}
	
	public void run() {
		
	}

}
