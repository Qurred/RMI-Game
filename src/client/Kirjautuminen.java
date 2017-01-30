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
		kirjaudu = new JButton("Kirjaudu sis��n");
		rekisteri = new JButton("Etk� ole rekister�itynyt?");
		// kirjoituskent�t
		kayttaja = new JTextField();
		salasana = new JPasswordField();
		// tekstit
		kayttajakentta = new JLabel("K�ytt�j�tunnus:");
		salasanakentta = new JLabel("Salasana:");
		cl = new CardLayout();
		sailio = new JPanel(cl);
		kirjautuminen = new JPanel();
		rekisteroituminen = new Rekisteroituminen();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		//layout null, sammutus rastista, n�kyvyys, koko jne
		setSize(dim.width, dim.height);
		setLayout(null);
		setBackground(null);
		setOpaque(false);
		
		//setLocation(0,0);

		// Sis��nkirjautumisen actionlistener

		kirjaudu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{ 
					String tulos = Data.prp.kirjaudu(kayttaja.getText(), String.valueOf(salasana.getPassword()),Data.arp);
					System.out.println(tulos);
					int arvo = Integer.parseInt(tulos.substring(0, 1));
					switch (arvo) {
					case 0: //Kyseisell� kyselyll� ei ollut tulosta
							System.out.println("Annettua yhdistelm�� ei l�ydetty");
							salasana.setText("");
						break;
					case 1:
							System.out.println("L�ydettiin");
							Data.nimi = kayttaja.getText();
							Data.vaihdaNakyma(Data.PERUSNAKYMA); //Pit�� vaihtaa p��n�kym��n my�hemmin kun saadaan kaikki toimimaan
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
		// Jos k�ytt�j�ll� ei ole kirjautumisruudussa tunnuksia, h�n voi
		// siirty� rekister�itymisn�kym��n

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

