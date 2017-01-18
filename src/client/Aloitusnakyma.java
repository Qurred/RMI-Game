package client;

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

public class Aloitusnakyma extends JPanel{
  
  private static final long serialVersionUID = 1L;
  private JButton  hahmo1, hahmo2, hahmo3, hahmo4, hahmo5, hahmo6, hahmo7, hahmo8, laheta;
  private JTextArea chatti;
  private JTextField viesti;
  private JLabel kirjoitus, virhe;
  private JScrollPane scrollpane;
//  private String nimi;
  
  public Aloitusnakyma(){
    hahmo1 = new JButton("Asehullu");
    hahmo2 = new JButton("Chthonia");
    hahmo3 = new JButton("Ragepoju");
    hahmo4 = new JButton("Tissit");
    hahmo5 = new JButton("Kulttikaveri");
    hahmo6 = new JButton("Amatsoni");
    hahmo7 = new JButton("Rakastajapoika");
    hahmo8 = new JButton("Rohmu");
    laheta = new JButton("L‰het‰");
    viesti = new JTextField();
    chatti = new JTextArea();
    kirjoitus = new JLabel("Sin‰:");
    virhe = new JLabel("K‰ytt‰ydy!");
    scrollpane = new JScrollPane(chatti);

     hahmo1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
        hahmo1.setBackground(Color.RED);
      }
    }
                          );
     hahmo2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
         hahmo2.setBackground(Color.BLUE);
      }
    }
                          );
     hahmo3.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
        hahmo3.setBackground(Color.BLACK);
      }
    }
                          );
     laheta.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
        
        SimpleDateFormat aika = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date aikanyt = new Date( );
        
        // Jos viesti on tyhj‰ tai siin‰ on pelk‰st‰‰n v‰limerkkej‰, laittaa n‰kymiin virheviestin
        
        if (viesti.getText().trim().equals("")){
        	virhe.setVisible(true);
            }else{
        	   chatti.append("sin‰  " + aika.format(aikanyt) + "  " + viesti.getText() + "\n");
        	   virhe.setVisible(false);
            }
        viesti.setText("");  
       }
     }
                          );
     
   /*  laheta.addKeyListener(ne {
      public void keyPressed(KeyEvent e) {
      }
   }
     );*/                     
    hahmo1.setBounds(40,90,220,20);
    hahmo2.setBounds(40,130,220,20);
    hahmo3.setBounds(40,170,220,20);
    hahmo4.setBounds(40,210,220,20);
    hahmo5.setBounds(40,250,220,20);
    hahmo6.setBounds(40,290,220,20);
    hahmo7.setBounds(40,330,220,20);
    hahmo8.setBounds(40,380,220,20);
    chatti.setBounds(400,30,200,200); 
    viesti.setBounds(400,560,300,20);
    laheta.setBounds(300,580,300,20);
    scrollpane.setBounds(400,30,320,200);
    kirjoitus.setBounds(300,530,100,20);
    virhe.setBounds(420, 530, 320, 20);
    
    add(hahmo1);
    add(hahmo2);
    add(hahmo3);
    add(hahmo4);
    add(hahmo5);
    add(hahmo6);
    add(hahmo7);
    add(hahmo8);
    add(laheta);
    add(viesti);
    add(scrollpane);
    add(kirjoitus);
    add(virhe);
    add(chatti);
    
    // Virheviesti jos yritt‰‰ l‰h‰tt‰‰ tyhj‰‰ viesti‰ 
    
    virhe.setVisible(false);
    virhe.setForeground(Color.RED);
    
    setSize(1000,1000);
    setVisible(true);
    setLayout(new BorderLayout());
    
    scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
  }
}
    
  