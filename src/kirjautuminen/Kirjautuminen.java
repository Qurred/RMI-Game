import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// tässä on nyt importoitu paljon kaikkea paskaa...

public class Kirjautuminen extends JFrame{
  
  private JButton login, rek;
  private JTextField user;
  private JPasswordField pass;
  private JLabel lauser, lapass;
  private CardLayout cl;
  private JPanel sailio;
  private JPanel kirjautuminen;
  private Rekisteroituminen rekisteroituminen;
  
  public Kirjautuminen(){
    // painikkeet
    login = new JButton("Kirjaudu sisään");
    rek = new JButton("Etkö ole rekisteröitynyt?");
    // kirjoituskentät
    user = new JTextField();
    pass = new JPasswordField();
    // tekstit
    lauser = new JLabel("Käyttäjätunnus:");
    lapass = new JLabel("Salasana:");
    cl = new CardLayout();
    sailio = new JPanel(cl);
    kirjautuminen = new JPanel();
    rekisteroituminen = new Rekisteroituminen();
    
    //layout null, sammutus rastista, näkyvyys, koko jne
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(500,300);
    kirjautuminen.setLayout(null);
   
    // Sisäänkirjautumisen actionlistener
    
    login.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
      login.setBackground(Color.RED);
     }
      }
                           );
    // Jos käyttäjällä ei ole kirjautumisruudussa tunnuksia, hän voi
    // siirtyä rekisteröitymisnäkymään
     
    rek.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
        cl.show(sailio, "reks");
      }
    }
                          );
    // sijainti
    login.setBounds(140,55,200,20);
    rek.setBounds(140,75,200,20);
    user.setBounds(140,10,200,20);
    pass.setBounds(140,30,200,20);
    lauser.setBounds(10,10,120,20);
    lapass.setBounds(10,30,120,20);
    
    add(sailio);
    kirjautuminen.add(login);
    kirjautuminen.add(rek);
    kirjautuminen.add(user);
    kirjautuminen.add(pass);
    kirjautuminen.add(lauser);
    kirjautuminen.add(lapass);
    sailio.add(kirjautuminen, "kirj");
    sailio.add(rekisteroituminen, "reks");
    
     sailio.setVisible(true);
     setVisible(true);
    }
  
  public static void main(String args[]){
    Kirjautuminen kirjautuminen = new Kirjautuminen();
  }
}
  
  