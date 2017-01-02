package kirjautuminen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.JTextComponent;

// tässä on nyt importoitu paljon kaikkea paskaa...

public class Peli extends JPanel{
  private JButton painike1, painike2, painike3, laheta;
  private JTextArea chatti;
  private JTextField viesti;
  private JLabel kirjoitus;
  
  public Peli(){
    painike1 = new JButton("Painike1");
    painike2 = new JButton("Painike2");
    painike3 = new JButton("Painike3");
    laheta = new JButton("Lähetä");
    viesti = new JTextField();
    chatti = new JTextArea();
    kirjoitus = new JLabel("Sinä:");
    JScrollPane scrollPane = new JScrollPane(chatti);
    
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
     laheta.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
        chatti.append("sinä:     " + viesti.getText() + "\n");
        viesti.setText("");
        
      }
    }
                          );
       
    painike1.setBounds(40,90,220,20);
    painike2.setBounds(40,130,220,20);
    painike3.setBounds(40,170,220,20);
    chatti.setBounds(300,30,300,500);
    viesti.setBounds(300,560,300,20);
    laheta.setBounds(300,580,300,20);
    scrollPane.setBounds(300,30,320,500);
    kirjoitus.setBounds(300,530,300,20);

    
    add(painike1);
    add(painike2);
    add(painike3);
    add(laheta);
    add(viesti);
    add(scrollPane);
    add(kirjoitus);
    
    setSize(1000,1000);
    setVisible(true);
    setLayout(null);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
  }
}
    
  