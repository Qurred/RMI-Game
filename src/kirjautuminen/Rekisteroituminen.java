package kirjautuminen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// t�ss� on nyt importoitu paljon kaikkea paskaa...

public class Rekisteroituminen extends JPanel{
  
  // buttonit
  private JButton rekisteroidy;
  // labelit
  private JLabel nimi, nimimerkki, salasana1, salasana2, virhe;
  // kent�t
  private JTextField nimikentta, nimimekentta;
  private JPasswordField salakentta1, salakentta2;
  
  public Rekisteroituminen(){
    
    // Buttonit
    rekisteroidy = new JButton("Rekister�idy.");
    //labelit
    nimi = new JLabel("Etu- ja sukunimi:");
    nimimerkki = new JLabel("K�ytt�j�tunnus:");
    salasana1 = new JLabel("Anna salasana:");
    salasana2 = new JLabel("Anna salasana uudelleen:");
    virhe = new JLabel("Salasana puuttuu tai ne eiv�t t�sm��");
    //kent�t
    nimikentta = new JTextField();
    nimimekentta = new JTextField();
    salakentta1 = new JPasswordField();
    salakentta2 = new JPasswordField();
    
   
    setLayout(null);
    virhe.setVisible(false);
    setVisible(false);
    virhe.setForeground (Color.red);
    //  actionlistener
    rekisteroidy.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        if(String.valueOf(salakentta1.getPassword()).equals(String.valueOf(salakentta2.getPassword()))){
          // pit�is vissii kans tarkistaa ettei eka oo tyhj�
             rekisteroidy.setBackground(Color.RED);
        }else{
          virhe.setVisible(true);
        
           }
}
}
);
               
    // sijoitukset
    rekisteroidy.setBounds(160,130,200,20);
    nimikentta.setBounds(160,10,200,20);
    nimimekentta.setBounds(160,40,200,20);
    salakentta1.setBounds(160,70,200,20);
    salakentta2.setBounds(160,100,200,20);
    nimi.setBounds(10,10,120,20);
    nimimerkki.setBounds(10,40,120,20);
    salasana1.setBounds(10,70,120,20);
    salasana2.setBounds(10,100,120,20);
    virhe.setBounds (160,160,300,20);
    
    add(rekisteroidy);
    add(nimikentta);
    add(nimimekentta);
    add(salakentta1);
    add(salakentta2);
    add(nimi);
    add(nimimerkki);
    add(salasana1);
    add(salasana2);
    add(virhe);
    
    }
}
  