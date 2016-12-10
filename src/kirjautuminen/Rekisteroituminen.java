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
    
    //  actionlistener
    in.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        if(String.valueOf(pfpass1.getPassword()).equals(String.valueOf(pfpass2.getPassword()))){
          if(String.valueOf(pfpass1.getPassword()) != ""){
             in.setBackground(Color.RED);
        }else{
          error.setVisible(true);
        }
           }
}
    }
);
               
    // sijoitukset
    in.setBounds(140,110,200,20);
    tfname.setBounds(140,10,200,20);
    tfnickname.setBounds(140,30,200,20);
    pfpass1.setBounds(140,50,200,20);
    pfpass2.setBounds(140,80,200,20);
    name.setBounds(10,10,120,20);
    nickname.setBounds(10,30,120,20);
    pass1.setBounds(10,50,120,20);
    pass2.setBounds(10,80,120,20);
    error.setBounds (140,130,200,20);
    
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
  