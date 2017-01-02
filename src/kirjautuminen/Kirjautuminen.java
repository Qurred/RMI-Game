package Kirjautuminen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// tässä on nyt importoitu paljon kaikkea paskaa...

public class Kirjautuminen extends JFrame{
  
  private JButton kirjaudu, rekisteri;
  private JTextField kayttaja;
  private JPasswordField salasana;
  private JLabel kayttajakentta, salasanakentta ;
  private CardLayout cl;
  private JPanel sailio;
  private JPanel kirjautuminen;
  private Rekisteroituminen rekisteroituminen;
  private Peli peli;
  
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
    peli = new Peli();
    
    //layout null, sammutus rastista, näkyvyys, koko jne
   // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(500,300);
    kirjautuminen.setLayout(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    // Sisäänkirjautumisen actionlistener
    
    kirjaudu.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
      // tässä pitäis tarkistaa tietokannasta täsmääkö salasana ja käyttäjänimi
      // "tyhjennetään" kirjoitetut kentät
      // user.setText("");
      // pass.setText("");
          cl.show(sailio, "peli");
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
    kirjautuminen.add(kirjaudu);
    kirjautuminen.add(rekisteri);
    kirjautuminen.add(kayttaja);
    kirjautuminen.add(salasana);
    kirjautuminen.add(kayttajakentta);
    kirjautuminen.add(salasanakentta);
    sailio.add(kirjautuminen, "kirj");
    sailio.add(rekisteroituminen, "reks");
    sailio.add(peli, "peli");
    
     sailio.setVisible(true);
     setVisible(true);
    }
  
  public static void main(String args[]){
    Kirjautuminen kirjautuminen = new Kirjautuminen();
  }
}
  
  