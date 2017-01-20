package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Kirjautuminen extends JFrame{
  

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
    setSize(640, 480);
    setResizable(false);
    kirjautuminen.setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
   
    // Sis��nkirjautumisen actionlistener
    
    kirjaudu.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
      // t�ss� pit�is tarkistaa tietokannasta t�sm��k� salasana ja k�ytt�j�nimi
      // "tyhjennet��n" kirjoitetut kent�t
      // user.setText("");
      // pass.setText("");
          Aloitusnakyma aloitusnakyma = new Aloitusnakyma();
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
    kirjautuminen.add(kirjaudu);
    kirjautuminen.add(rekisteri);
    kirjautuminen.add(kayttaja);
    kirjautuminen.add(salasana);
    kirjautuminen.add(kayttajakentta);
    kirjautuminen.add(salasanakentta);
    sailio.add(kirjautuminen, "kirj");
    sailio.add(rekisteroituminen, "reks");

    
     sailio.setVisible(true);
     setVisible(true);
    }
  
  public static void main(String args[]){
    Kirjautuminen kirjautuminen = new Kirjautuminen();
  }
}
  
  