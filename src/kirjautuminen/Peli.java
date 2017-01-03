package Kirjautuminen;

import java.util.Date;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.KeyListener;
//import java.awt.event.KeyEvent;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.text.SimpleDateFormat;

public class Peli extends JPanel{
  private JButton painike1, painike2, painike3, laheta;
  private JTextArea chatti;
  private JTextField viesti;
  private JLabel kirjoitus;
  private JScrollPane scrollpane;
  
  public Peli(){
    painike1 = new JButton("Painike1");
    painike2 = new JButton("Painike2");
    painike3 = new JButton("Painike3");
    laheta = new JButton("Lähetä");
    viesti = new JTextField();
    chatti = new JTextArea();
    kirjoitus = new JLabel("Sinä:");
    scrollpane = new JScrollPane(chatti);
    
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
        SimpleDateFormat aika = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date aikanyt = new Date( );
        chatti.append("sinä  " +  aika.format(aikanyt) + ":  " + viesti.getText() + "\n");
        viesti.setText("");
        
      }
    }
                          );
     
   /*  laheta.addKeyListener(ne {
      public void keyPressed(KeyEvent e) {
      }
   }
     );*/                     
    painike1.setBounds(40,90,220,20);
    painike2.setBounds(40,130,220,20);
    painike3.setBounds(40,170,220,20);
    chatti.setBounds(300,30,300,500);
    viesti.setBounds(300,560,300,20);
    laheta.setBounds(300,580,300,20);
    scrollpane.setBounds(300,30,320,500);
    kirjoitus.setBounds(300,530,300,20);

    
    add(painike1);
    add(painike2);
    add(painike3);
    add(laheta);
    add(viesti);
    add(scrollpane);
    add(kirjoitus);
    
    setSize(1000,1000);
    setVisible(true);
    setLayout(new BorderLayout());
    scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
  }
}
    
  