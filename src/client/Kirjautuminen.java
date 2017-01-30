package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JPasswordField;

public class Kirjautuminen extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton kirjaudu, rekisteri;
	private JPasswordField salasana;
	private JLabel kayttajakentta, salasanakentta,nimimerkki, salasana1, salasana2, virhe, viesti, viesti2, kirjautumisohje, rekisteroitumisohje;
	private JPanel kirjautuminen;
	private JTextField nimimerkkikentta, kayttaja;
	private JPasswordField salasanakentta1, salasanakentta2;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();


	public Kirjautuminen(){

		kirjaudu = new JButton("Kirjaudu sisään");
		rekisteri = new JButton("Rekisteröidy");
		kayttaja = new JTextField();
		salasana = new JPasswordField();
		salasanakentta1 = new JPasswordField();
		salasanakentta2 = new JPasswordField();
		kayttajakentta = new JLabel("Käyttäjänimi:");
		salasanakentta = new JLabel("Salasana:");
		salasana1 = new JLabel("Anna salasana:");
		salasana2 = new JLabel("Anna uudestaan:");
		nimimerkkikentta = new JTextField();
		nimimerkki = new JLabel("Anna käyttäjänimesi:");
		kirjautuminen = new JPanel();
		viesti = new JLabel("Rekisteröityminen onnistui. Ole hyvä ja kirjaudu!");
		kirjautumisohje = new JLabel("Kirjaudu sisään jos olet jo käyttäjä");
		rekisteroitumisohje = new JLabel ("...tai luo ensin tunnus.");
		viesti.setVisible(false);

		// fontit
		Font fontti = new Font("Kumar One", Font.BOLD, 11);
		Font otsikko = new Font("Kumar One", Font.BOLD, 15);


		setSize(dim.width, dim.height);
		setLayout(null);
		setBackground(null);
		setOpaque(false);
		setVisible(true);


		// Sisäänkirjautumisen actionlistener

		kirjaudu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{ 
					String tulos = Data.prp.kirjaudu(kayttaja.getText(), String.valueOf(salasana.getPassword()),Data.arp);
					System.out.println(tulos);
					int arvo = Integer.parseInt(tulos.substring(0, 1));
					switch (arvo) {
					case 0: //Kyseisellä kyselyllä ei ollut tulosta
						System.out.println("Annettua yhdistelmää ei löydetty");
						salasana.setText("");
						break;
					case 1:
						System.out.println("Löydettiin");
						Data.nimi = kayttaja.getText();
						Data.vaihdaNakyma(Data.HAHMONAKYMA); //Pitää vaihtaa päänäkymään myöhemmin kun saadaan kaikki toimimaan
						break;			
					default:
						System.out.println(tulos);
						break;
					}
					//System.out.print("oot");
					//Aloitusnakyma aloitusnakyma = new Aloitusnakyma(kayttaja.getText());
				}catch (RemoteException e2){
					e2.printStackTrace();
				}

			}
		}
				);

		// Jos käyttäjällä ei ole kirjautumisruudussa tunnuksia, hän voi rekisteröityä

		rekisteri.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				if(String.valueOf(salasanakentta1.getPassword()).equals(String.valueOf(salasanakentta2.getPassword())) && String.valueOf(salasanakentta1.getPassword()) != null){
					try {
						Data.prp.rekisteroidu(nimimerkkikentta.getText(), String.valueOf(salasanakentta1.getPassword()));
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					viesti.setVisible(true);
					salasanakentta1.setText("");
					salasanakentta2.setText("");
					nimimerkkikentta.setText("");
				}else{
					virhe.setVisible(true);

				}
			}
		}
				);

		kirjaudu.setBounds(150,310,170,20);
		rekisteri.setBounds(590,310,170,20);
		kayttaja.setBounds(160,70,170,20);
		salasana.setBounds(160,150,170,20);
		kayttajakentta.setBounds(10,70,170,20);
		salasanakentta.setBounds(10,150,120,20);
		salasanakentta1.setBounds(590,150,170,20);
		salasanakentta2.setBounds(590,230,170,20);
		salasana1.setBounds(390,150,150,20);
		salasana2.setBounds(390,230,150,20);
		nimimerkkikentta.setBounds(590,70,170,20);
		nimimerkki.setBounds(390,70,170,20);
		viesti.setBounds(10,340,600,20);
		kirjautumisohje.setBounds(10,15,400,20);
		rekisteroitumisohje.setBounds(390,15,300,20);

		add(kirjaudu);
		add(rekisteri);
		add(kayttaja);
		add(salasana);
		add(kayttajakentta);
		add(nimimerkkikentta);
		add(nimimerkki);
		add(salasanakentta);
		add(salasanakentta1);
		add(salasanakentta2);
		add(salasana1);
		add(salasana2);
		add(viesti);
		add(kirjautumisohje);
		add(rekisteroitumisohje);

		nimimerkki.setFont(fontti);
		salasana.setFont(fontti);
		salasana1.setFont(fontti);
		salasana2.setFont(fontti);
		salasana.setFont(fontti);
		viesti.setFont(fontti);
		kayttaja.setFont(fontti);
		salasanakentta.setFont(fontti);
		kayttajakentta.setFont(fontti);
		rekisteri.setFont(fontti);
		kirjaudu.setFont(fontti);
		nimimerkkikentta.setFont(fontti);
		kirjautumisohje.setFont(otsikko);
		rekisteroitumisohje.setFont(otsikko);


		nimimerkki.setForeground(Color.PINK);
		salasana.setForeground(Color.DARK_GRAY);
		salasana1.setForeground(Color.PINK);
		salasana2.setForeground(Color.PINK);
		salasanakentta1.setForeground(Color.DARK_GRAY);
		salasanakentta2.setForeground(Color.DARK_GRAY);
		kayttajakentta.setForeground(Color.PINK);
		kayttaja.setForeground(Color.DARK_GRAY);
		nimimerkkikentta.setForeground(Color.DARK_GRAY);
		salasanakentta.setForeground(Color.PINK);
		kirjautumisohje.setForeground(Color.MAGENTA);
		rekisteroitumisohje.setForeground(Color.MAGENTA);
		viesti.setForeground(Color.MAGENTA);

	}
}

