package client;

import java.util.Date;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.KeyListener;
//import java.awt.event.KeyEvent;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.text.SimpleDateFormat;

public class Aloitusnakyma extends JPanel{
  
  private static final long serialVersionUID = 1L;
 // private static final String  = null;
  private JButton   laheta, info, hyvaksy;
  private JRadioButton hahmo1, hahmo2, hahmo3, hahmo4, hahmo5, hahmo6, hahmo7, hahmo8;
  private ButtonGroup hahmot; 
  private JTextArea chatti;
  private JTextField viesti;
  private JLabel kirjoitus, virhe, rage;
  private JScrollPane scrollpane;
  private ImageIcon kuva1, kuva2;
//  private String nimi;
  
  public Aloitusnakyma(){;
    info = new JButton("Katso tietoa hahmoista");
    hyvaksy = new JButton("Hyv‰ksy valinta");
    laheta = new JButton("L‰het‰");
    viesti = new JTextField();
    chatti = new JTextArea();
    kirjoitus = new JLabel("Sin‰:");
    virhe = new JLabel("K‰ytt‰ydy!");
    scrollpane = new JScrollPane(chatti);
    rage = new JLabel(kuva1);
    hahmot = new ButtonGroup();
    hahmo1 = new JRadioButton("Rage");
    hahmo2 = new JRadioButton("woodoo");
    hahmo3 = new JRadioButton("o");
    hahmo4 = new JRadioButton("s");
    hahmo5 = new JRadioButton("s");
    hahmo6 = new JRadioButton("s");
    hahmo7 = new JRadioButton("s");
    hahmo8 = new JRadioButton("S");
    kuva1 = new ImageIcon("C:/Users/Omistaja/Documents/GitHub/RMI-Turn-Based-Game/src/client/rage.jpg");
    kuva2 = new ImageIcon("C:/Users/Omistaja/Documents/GitHub/RMI-Turn-Based-Game/src/client/woodoo.jpg");
    

    info.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
        info.setBackground(Color.RED);
      }
    }
                          );
     hyvaksy.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
         hyvaksy.setBackground(Color.BLUE);
      }
    }
                          );
  /*   hahmo3.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
        hahmo3.setBackground(Color.BLACK);
      }
      }
   */                      // );
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

   // info.setBounds(140,40,80,80);
   // hyvaksy.setBounds(140,140,80,80);
    chatti.setBounds(400,30,200,200); 
    viesti.setBounds(400,360,200,20);
    laheta.setBounds(400,380,200,20);
    scrollpane.setBounds(400,40,200,300);
    kirjoitus.setBounds(400,340,200,20);
    virhe.setBounds(420, 340, 320, 20);
    // ButtonGroup hahmot
  //  hahmo1.setBounds(40,40,80,80);
   // hahmo2.setBounds(40,100,80,80);
  //  hahmo3.setBounds(40, 160,20,20);
   // hahmo4.setBounds(40, 190,20,20);
    //hahmo5.setBounds(40, 230,20,20);
    rage.setBounds(40,100,80,80);
    

    add(laheta);
   // add(info);
   // add(hyvaksy);
    add(viesti);
    add(scrollpane);
    add(kirjoitus);
    add(virhe);
    add(rage);
    hahmot.add(hahmo1);
    hahmot.add(hahmo2);
    hahmot.add(hahmo3);
    hahmot.add(hahmo4);
    hahmot.add(hahmo5);
    hahmot.add(hahmo6);
    hahmot.add(hahmo6);
    hahmot.add(hahmo7);
    hahmot.add(hahmo8);
    add(hahmo1);
    add(hahmo2);
    add(hahmo3);
    add(hahmo4);
    add(hahmo5);
    
    
   // add(chatti);
    
    // Virheviesti jos yritt‰‰ l‰h‰tt‰‰ tyhj‰‰ viesti‰ 
    
    virhe.setVisible(false);
    virhe.setForeground(Color.RED);
    
    setSize(1000,1000);
    setVisible(true);
    setLayout(new BorderLayout());
    
    scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
  }
}
    
  