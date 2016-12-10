import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// tässä on nyt importoitu paljon kaikkea paskaa...
public class Rekisteroituminen extends JPanel{
  
  // buttonit
  private JButton in;
  // labelit
  private JLabel name, nickname, pass1, pass2, error;
  // kentät
  private JTextField tfname, tfnickname;
  private JPasswordField pfpass1, pfpass2;
  
  public Rekisteroituminen(){
    
    // Buttonit
    in = new JButton("Rekisteröidy.");
    //labelit
    name = new JLabel("Etu- ja sukunimi:");
    nickname = new JLabel("Käyttäjätunnus:");
    pass1 = new JLabel("Anna salasana:");
    pass2 = new JLabel("Anna salasana uudelleen:");
    error = new JLabel("Salasana puuttuu tai ne eivät täsmää");
    //kentät
    tfname = new JTextField();
    tfnickname = new JTextField();
    pfpass1 = new JPasswordField();
    pfpass2 = new JPasswordField();
    
   
    setLayout(null);
    error.setVisible(false);
    setVisible(false);
    error.setForeground (Color.red);
    //  actionlistener
    in.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        if(String.valueOf(pfpass1.getPassword()).equals(String.valueOf(pfpass2.getPassword()))){
          // pitäis vissii kans tarkistaa ettei eka oo tyhjä
             in.setBackground(Color.RED);
        }else{
          error.setVisible(true);
        
           }
}
    }
);
               
    // sijoitukset
    in.setBounds(160,130,200,20);
    tfname.setBounds(160,10,200,20);
    tfnickname.setBounds(160,40,200,20);
    pfpass1.setBounds(160,70,200,20);
    pfpass2.setBounds(160,100,200,20);
    name.setBounds(10,10,120,20);
    nickname.setBounds(10,40,120,20);
    pass1.setBounds(10,70,120,20);
    pass2.setBounds(10,100,120,20);
    error.setBounds (160,160,300,20);
    
    add(in);
    add(tfname);
    add(tfnickname);
    add(pfpass1);
    add(pfpass2);
    add(name);
    add(nickname);
    add(pass1);
    add(pass2);
    add(error);
    
    }
}
  