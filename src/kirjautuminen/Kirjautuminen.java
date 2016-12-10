import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Kirjautuminen extends JFrame implements ActionListener{
  
  JButton login;
  JTextField user;
  JPasswordField pass;
  JLabel lauser, lapass;
  
  public Kirjautuminen(){
    // painikkeet
    login = new JButton("Kirjaudu sis‰‰n");
    // kirjoituskent‰t
    user = new JTextField();
    pass = new JPasswordField();
    // tekstit
    lauser = new JLabel("K‰ytt‰j‰tunnus:");
    lapass = new JLabel("Salasana:");
    
    //layout null, sammutus rastista, n‰kyvyys, koko jne
    this.setLayout(null); 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setSize(500,300);
    
    // lis‰t‰‰n actionlistener loginiin
    login.addActionListener(this);
    
    // sijainti
    login.setBounds(140,55,200,20);
    user.setBounds(140,10,200,20);
    pass.setBounds(140,30,200,20);
    lauser.setBounds(10,10,120,20);
    lapass.setBounds(10,30,120,20);
    
    add(login);
    add(user);
    add(pass);
    add(lauser);
    add(lapass);
    
  }
  @Override
  public void actionPerformed(ActionEvent arg0) {
  }
  public static void main(String args[]){
    Kirjautuminen kirjautuminen = new Kirjautuminen();
  }
}
  
  