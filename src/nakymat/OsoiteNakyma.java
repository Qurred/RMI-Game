package nakymat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import client.Data;
import server.PalvelinRajapinta;

public class OsoiteNakyma extends JPanel {

	private JLabel otsikko;
	private JTextField osoite;
	private JButton yhdista;
	private JLabel virhe;

	public OsoiteNakyma(Dimension dim){
		super();
		alusta();
		this.setLayout(null);
		this.setBounds(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
		this.setBackground(null);
		this.setOpaque(false);
		this.setVisible(true);

		//Luodaan näkymän fontit
		Font otsikkoFontti = new Font("Kumar One", Font.PLAIN, 40);
		Font osoiteFontti = new Font("Kumar One", Font.PLAIN, 20);
		Font yhdistaFontti = new Font("Kumar One", Font.BOLD, 25);

		//Alustetaan virheilmoitus
		virhe = new JLabel("Yhdistäminen ei onnistu annettuun osoitteeseen");
		virhe.setBounds(0, 250, dim.width, 20);
		virhe.setFont(osoiteFontti);
		virhe.setForeground(new Color(200, 15, 15));
		virhe.setVisible(false);
		virhe.setHorizontalAlignment(SwingConstants.CENTER);

		//Alustetaan otsikko
		otsikko = new JLabel("Syötä IP-osoite");
		otsikko.setForeground(new Color(200, 200, 200));
		otsikko.setBounds(168, 58, 464, 60);
		otsikko.setFont(otsikkoFontti);
		otsikko.setVisible(true);
		otsikko.setHorizontalAlignment(SwingConstants.CENTER);

		//Alustetaan osoite
		osoite = new JTextField();
		osoite.setBounds(230, 208, 340, 35);
		osoite.setFont(osoiteFontti);
		osoite.setVisible(true);
		osoite.setHorizontalAlignment(SwingConstants.CENTER);
		osoite.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {yhdista();}		
		});
		//Alustetaan yhdista
		yhdista = new JButton("Yhdista");
		yhdista.setBounds(296,300,211,30);
		yhdista.setFont(yhdistaFontti);
		yhdista.setVisible(true);
		yhdista.setHorizontalAlignment(SwingConstants.CENTER);
		yhdista.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){yhdista();}
		});
		//Lisätään komponentit
		this.add(virhe);
		this.add(otsikko);
		this.add(osoite);
		this.add(yhdista);
	}

	private void alusta(){
		GraphicsEnvironment ge = null;
		try{
			ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/res/KumarOne-Regular.ttf")));
		} catch(FontFormatException e){} catch (IOException e){System.out.println("Ei lataa");}
	}

	/**
	 * @deprecated
	 * @param t
	 */
	public void asetaNakyvaksi(boolean t){
		this.setVisible(t);
		if(t){
			virhe.setVisible(false);
		}
	}
	
	private void yhdista(){
		if(!osoite.getText().trim().equals("")){
			try {
				Data.prp = (PalvelinRajapinta) Naming.lookup("rmi://" + osoite.getText() + "/peli");
				osoite.setText("");
				virhe.setVisible(false);
				//Vaihdetaan näkymä
				Data.vaihdaNakyma(Data.KIRJAUTUMINEN);
			} catch (MalformedURLException | RemoteException | NotBoundException v) {
				osoite.setText("");
				virhe.setVisible(true);
			}
		}
	
	}
}

