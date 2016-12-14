import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// tässä on nyt importoitu paljon kaikkea paskaa...

public class Peli extends JPanel{
  private CardLayout cl;
  private JPanel sailio;
  private JButton juho, juhio, pelaa;
  
  public Peli(){
    juho = new JButton("Juho");
    juhio = new JButton("Juhio");
    pelaa = new JButton("Pelaa");
    
    pelaa.setBounds(140,30,220,10);
    juho.setBounds(140,60,220,10);
    juhio.setBounds(140,90,220,10);
    
    add(juho);
    add(juhio);
    add(pelaa);
    add(sailio);
    
  }
}
    
  