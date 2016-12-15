package kirjautuminen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// tässä on nyt importoitu paljon kaikkea paskaa...

public class Peli extends JPanel{
  private JButton painike1, painike2, painike3;
  
  public Peli(){
    painike1 = new JButton("Painike1");
    painike2 = new JButton("Painike2");
    painike3 = new JButton("Painike3");
    
     painike1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
        painike1.setBackground(Color.RED);
      }
    }
                          );
      painike2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
         painike2.setBackground(Color.BLUE);
      }
    }
                          );
       painike3.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
        painike3.setBackground(Color.BLACK);
      }
    }
                          );
    
    painike1.setBounds(140,30,220,20);
    painike2.setBounds(140,60,220,20);
    painike3.setBounds(140,90,220,20);
    
    add(painike1);
    add(painike2);
    add(painike3);
    
  }
}
    
  