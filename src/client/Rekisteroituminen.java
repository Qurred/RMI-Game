package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

// tässä on nyt importoitu paljon kaikkea paskaa...

public class Rekisteroituminen extends JPanel{
  

	private static final long serialVersionUID = 1L;
// buttonit
  private JButton rekisteroidy;
  // labelit
  private JLabel nimimerkki, salasana1, salasana2, virhe;
  // kentät
  private JTextField nimimekentta;
  private JPasswordField salakentta1, salakentta2;
  
  
  
  public Rekisteroituminen(){
    
    // Buttonit
    rekisteroidy = new JButton("Rekisteröidy.");
    //labelit
    nimimerkki = new JLabel("Käyttäjätunnus:");
    salasana1 = new JLabel("Anna salasana:");
    salasana2 = new JLabel("Anna salasana uudelleen:");
    virhe = new JLabel("Salasana puuttuu tai ne eivät täsmää");
    //kentät
    nimimekentta = new JTextField();
    salakentta1 = new JPasswordField();
    salakentta2 = new JPasswordField();
    
   
    setLayout(new BorderLayout());
    virhe.setVisible(false);
    setVisible(false);
    //virhe.setForeGround(Colour.RED);
    //  actionlistener
    
    rekisteroidy.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        if(String.valueOf(salakentta1.getPassword()).equals(String.valueOf(salakentta2.getPassword())) && String.valueOf(salakentta1.getPassword()) != null){
        	System.out.print("tyhmä");
             try {
				Data.prp.rekisteroidu(nimimerkki.getText(), String.valueOf(salakentta1.getPassword()));
				Kirjautuminen kirjautuminen = new Kirjautuminen();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
        }else{
          virhe.setVisible(true);
        
           }
}
}
);
               
    // sijoitukset
    rekisteroidy.setBounds(160,130,200,20);
    nimimekentta.setBounds(160,40,200,20);
    salakentta1.setBounds(160,70,200,20);
    salakentta2.setBounds(160,100,200,20);
    nimimerkki.setBounds(10,40,120,20);
    salasana1.setBounds(10,70,120,20);
    salasana2.setBounds(10,100,120,20);
    virhe.setBounds (160,160,300,20);
    
    add(rekisteroidy);
    add(nimimekentta);
    add(salakentta1);
    add(salakentta2);
    add(nimimerkki);
    add(salasana1);
    add(salasana2);
    add(virhe);
    
    }
}
  