package client;

import java.util.Date;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.text.SimpleDateFormat;

public class Aloitusnakyma extends JFrame{
  
  private static final long serialVersionUID = 1L;
  private JButton   laheta, info, hyvaksy;
  private JRadioButton hahmo1, hahmo2, hahmo3, hahmo4, hahmo5, hahmo6, hahmo7;// hahmo8;
  private ButtonGroup hahmot; 
  private JTextArea chatti;
  private JTextField viesti;
  private JLabel kirjoitus, virhe, rage, woodoo, pirate, amatson, khan, blue;
  private JScrollPane scrollpane;
  private ImageIcon kuva1, kuva2, kuva3, kuva4, kuva5, kuva6;
  private JPanel aloitusnakyma;
  private CardLayout cl;
  
  public Aloitusnakyma(){;
    info = new JButton("Katso tietoa hahmoista");
    hyvaksy = new JButton("Hyv‰ksy valinta");
    laheta = new JButton("L‰het‰");
    viesti = new JTextField();
    chatti = new JTextArea();
    kirjoitus = new JLabel("Sin‰:");
    virhe = new JLabel("K‰ytt‰ydy!");
    scrollpane = new JScrollPane(chatti);
    
    //Ensiksi ladataan kuva joka halutaan asettaa JLabeliin t‰ss‰ tapauksessa. Kuvat + labelit.
    
    kuva1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/rage.jpg"))); //Ladataan suorituksen sijainnista
    rage = new JLabel(kuva1);
 
    kuva2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/woodoo.jpg"))); //Ladataan suorituksen sijainnista
    woodoo = new JLabel(kuva2);
    
    kuva3 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/pirate.jpg"))); //Ladataan suorituksen sijainnista
    pirate = new JLabel(kuva3);

    kuva4 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/amatson.jpg"))); //Ladataan suorituksen sijainnista
    amatson = new JLabel(kuva4);
    
    kuva5 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/khan.jpg"))); //Ladataan suorituksen sijainnista
    khan = new JLabel(kuva5);

    kuva6 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/blue.jpg"))); //Ladataan suorituksen sijainnista
    blue = new JLabel(kuva6);

    
    hahmot = new ButtonGroup();
    hahmo1 = new JRadioButton("Rage");
    hahmo1.setSelected(true);
    hahmo2 = new JRadioButton("woodoo");
    hahmo3 = new JRadioButton("pirate");
    hahmo4 = new JRadioButton("amatsoni");
    hahmo5 = new JRadioButton("khan");
    hahmo6 = new JRadioButton("blue");
    hahmo7 = new JRadioButton("juho");
    //hahmo8 = new JRadioButton("rogue");   
    aloitusnakyma = new JPanel();
    cl = new CardLayout();
    
    // JFrame Koko, skaalaus
    setSize(640, 480);
    setResizable(false);
    aloitusnakyma.setLayout(null);
    setVisible(true);

   
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //  setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    
    

    info.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
        info.setBackground(Color.RED);
      }
    }
                          );
     hyvaksy.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){ 
    	  if (valittujenMaara()){
    		  
    			 hyvaksy.setBackground(Color.BLUE);
    			 // valittuJoukko();
    	  }else{
    		     hyvaksy.setBackground(Color.RED);
    	  }
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
     

    info.setBounds(20,20,100,20);
    //hyvaksy.setBounds(120,20,100,20);
    //chatti.setBounds(400,30,200,200); 
    viesti.setBounds(400,360,200,20);
    laheta.setBounds(400,380,200,20);
    scrollpane.setBounds(400,40,200,300);
    kirjoitus.setBounds(400,340,200,20);
    virhe.setBounds(420,340,320,20);
    // ButtonGroup hahmojen valinta
    hahmo1.setBounds(30,50,20,20);
    hahmo2.setBounds(30,100,20,20);
    hahmo3.setBounds(30,150,20,20);
    hahmo4.setBounds(30,200,20,20);
    hahmo5.setBounds(30,250,20,20);
    hahmo6.setBounds(30,300,20,20);
    hahmo7.setBounds(30,350,20,20);
    //hahmo8.setBounds(30,400,20,20);
    // hamojen kuvat
    rage.setBounds(150,40,20,20);
    woodoo.setBounds(150,90,20,20);
    pirate.setBounds(150,140,20,20);
    amatson.setBounds(150,190,20,20);
    khan.setBounds(150,240,20,20);
    blue.setBounds(150,290,20,20);

    add(laheta);
    add(info);
    add(hyvaksy);
    add(viesti);
    add(scrollpane);
    add(kirjoitus);
    add(virhe);
    add(rage);
    add(woodoo);
    add(amatson);
    add(pirate);
    add(blue);
    add(khan);
    hahmot.add(hahmo1);
    hahmot.add(hahmo2);
    hahmot.add(hahmo3);
    hahmot.add(hahmo4);
    hahmot.add(hahmo5);
    hahmot.add(hahmo6);
    hahmot.add(hahmo7);
    //hahmot.add(hahmo8);
    add(hahmo1);
    add(hahmo2);
    add(hahmo3);
    add(hahmo4);
    add(hahmo5);
    add(hahmo6);
    add(hahmo7);

   // add(hahmo8);
    
    // Virheviesti jos yritt‰‰ l‰h‰tt‰‰ tyhj‰‰ viesti‰   
    virhe.setVisible(false);
    virhe.setForeground(Color.RED);
    scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
  }

 /**
	 * Selvitt‰‰ mink‰ joukon pelaaja valitsee ButtonGroupista
	 * @param valittu
	 * @return joukko
	 */

 // Palauttaa valittujen joukon
  
public AbstractButton valittuButton(ButtonGroup valittu){
	for (Enumeration<AbstractButton> joukkue = valittu.getElements();
			joukkue.hasMoreElements();){
		AbstractButton joukko = joukkue.nextElement();
		
		if (joukko.isSelected()){
			return joukko;
		}
	}
	return null;
}

// Selvitt‰‰ kuinka monta buttonia on valittu,
// Jos on valittu 4, palauttaa true
// muuten false 

public boolean valittujenMaara(){
	int laskuri = 0;
	if(hahmo1.isSelected()){
		laskuri++;
	}
    if(hahmo2.isSelected()){
        laskuri++;
    }
    if(hahmo3.isSelected()){
    		laskuri++;
    }
    if(hahmo4.isSelected()){
            laskuri++;    
    }
    if(hahmo5.isSelected()){
		laskuri++;
	}
    if(hahmo6.isSelected()){
        laskuri++;
    }
    if(hahmo7.isSelected()){
    		laskuri++;
    }
 /*   if(hahmo8.isSelected()){
            laskuri++;    
    } */
    if(laskuri == 4){
    	return true;
    }else{
    	return false;
    }
}
}

