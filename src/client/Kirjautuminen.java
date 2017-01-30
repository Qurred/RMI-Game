package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Kirjautuminen extends JPanel{


	private static final long serialVersionUID = 1L;
	private JButton kirjaudu, rekisteri;
	private JTextField kayttaja;
	private JPasswordField salasana;
	private JLabel kayttajakentta, salasanakentta ;
	private CardLayout cl;
	private JPanel sailio;
	private JPanel kirjautuminen;
	private Rekisteroituminen rekisteroituminen;


	public Kirjautuminen(){
		// painikkeet
		kirjaudu = new JButton("Kirjaudu sisään");
		rekisteri = new JButton("Etkö ole rekisteröitynyt?");
		// kirjoituskentät
		kayttaja = new JTextField();
		salasana = new JPasswordField();
		// tekstit
		kayttajakentta = new JLabel("Käyttäjätunnus:");
		salasanakentta = new JLabel("Salasana:");
		cl = new CardLayout();
		sailio = new JPanel(cl);
		kirjautuminen = new JPanel();
		rekisteroituminen = new Rekisteroituminen();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		//layout null, sammutus rastista, näkyvyys, koko jne
		setSize(dim.width, dim.height);
		setLayout(null);
		setBackground(null);
		setOpaque(false);
		
		//setLocation(0,0);

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
							Data.vaihdaNakyma(Data.PERUSNAKYMA); //Pitää vaihtaa päänäkymään myöhemmin kun saadaan kaikki toimimaan
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
		// Jos käyttäjällä ei ole kirjautumisruudussa tunnuksia, hän voi
		// siirtyä rekisteröitymisnäkymään

		rekisteri.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				cl.show(sailio, "reks");
			}
		}
				);
		// sijainti
		kirjaudu.setBounds(140,55,200,20);
		rekisteri.setBounds(140,75,200,20);
		kayttaja.setBounds(140,10,200,20);
		salasana.setBounds(140,30,200,20);
		kayttajakentta.setBounds(10,10,120,20);
		salasanakentta.setBounds(10,30,120,20);

		add(sailio);
		add(kirjaudu);
		add(rekisteri);
		add(kayttaja);
		add(salasana);
		add(kayttajakentta);
		add(salasanakentta);
		sailio.add(kirjautuminen, "kirj");
		sailio.add(rekisteroituminen, "reks");


		sailio.setVisible(true);
		setVisible(true);
	}
}

