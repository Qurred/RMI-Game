package koodiesimerkki;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.Data;

public class Hahmojenvalinta extends JPanel {
	
	private ArrayList<Integer> valitut;
	private JPanel hahmot;
	private JPanel valitutHahmot;
	private ArrayList<JLabel> listahahmot;
	
	private int klikattulukumaara = 0;
	
	public Hahmojenvalinta(Dimension dim){
		super();
		valitut = new ArrayList<Integer>();
		this.setLayout(null);
		this.setBounds(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
		this.setBackground(Color.DARK_GRAY);
		this.setVisible(true);
		alusta();
	}
	
	private void alusta(){	
		hahmot = new JPanel();
		hahmot.setLayout(new BoxLayout(hahmot, BoxLayout.Y_AXIS));
		hahmot.setBounds(10, 10, 160, 384);
		hahmot.setBackground(null);
		hahmot.setVisible(true);
		
		Dimension empty = new Dimension(0, 20);

		valitutHahmot = new JPanel();
		valitutHahmot.setLayout(new BoxLayout(valitutHahmot, BoxLayout.Y_AXIS));
		valitutHahmot.setBounds(170, 10, 160, 384);
		valitutHahmot.setBackground(null);
		valitutHahmot.setVisible(true);
		listahahmot = new ArrayList<>();
		for(int i = 0; i < 4; i++){
			JLabel tmp = new JLabel();
			tmp.setAlignmentX(Component.CENTER_ALIGNMENT);
			tmp.setOpaque(true);
			tmp.setVisible(false);
			tmp.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					//tarkistaPainallus(tmp);
					System.out.println("RIKKI ON OOTKO TYYTYVÄINE HÄH!");
				}
			});
			listahahmot.add(tmp);
			valitutHahmot.add(new Box.Filler(empty, empty, empty));
			valitutHahmot.add(tmp);
		}
		
		for(int i = 0; i < 8; i++){
			JLabel nappi = new JLabel(Data.tiedot.get(i).annaNimi());
			nappi.setName(Integer.toString(i));		
			nappi.setVisible(true);
			nappi.setAlignmentX(Component.CENTER_ALIGNMENT);
			nappi.setOpaque(true);
			nappi.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					boolean sama = false;
					int painettu = Integer.parseInt(nappi.getName());		
					for (Integer id : valitut) {
						if(painettu == id){
							sama = true;
						}
					}
					if(!sama && valitut.size()==4){
						for(int i = 0; i < 3; i++){
							listahahmot.get(i).setText(listahahmot.get(i+1).getText());
							listahahmot.get(i).setName(listahahmot.get(i+1).getName());
						}
						valitut.remove(0);
						valitut.add(Integer.parseInt(nappi.getName()));
						listahahmot.get(3).setText(nappi.getText());
						listahahmot.get(3).setName(nappi.getName());
					}else if(!sama){
						if(klikattulukumaara < 4 && !listahahmot.get(klikattulukumaara).isVisible()){
							valitut.add(Integer.parseInt(nappi.getName()));
							listahahmot.get(klikattulukumaara).setVisible(true);
							listahahmot.get(klikattulukumaara).setText(nappi.getText());
							listahahmot.get(klikattulukumaara).setName(nappi.getName());
							klikattulukumaara++;
						}
					}
				}
			});
			
			hahmot.add(new Box.Filler(empty, empty, empty));
			hahmot.add(nappi);
		}
		this.add(valitutHahmot);
		this.add(hahmot);
		
	}


	
}
