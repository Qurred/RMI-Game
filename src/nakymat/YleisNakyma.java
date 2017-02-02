package nakymat;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.Data;
import hahmonakymakokeilu.HahmoLabel;
import hahmonakymakokeilu.OmaScrollBar;

public class YleisNakyma extends JPanel {

	//Chatti
	private JLabel keskusteluTausta;
	private JScrollPane chattiPaneeli;
	private JTextField viesti;
	private JButton lahetaViesti;
	private JTextArea chatti;
	private ImageIcon chattiTausta;
	//Fontit
	private Font chattiFontti, viestiFontti, yleisFontti;
	//Hahmovalinta
	private HahmoValinta hahmovalinta;

	public YleisNakyma(Dimension dim){
		super();
		this.setLayout(null);
		this.setBounds(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
		this.setBackground(null);
		this.setOpaque(false);
		this.setVisible(true);
		alusta();
	}

	private void alusta(){
		//Fonttien alustus
		Font chattiFontti = new Font("Verdana", Font.HANGING_BASELINE, 13);
		Font viestiFontti = new Font("Verdana", Font.PLAIN, 12);
		Font yleisFontti = new Font("Verdana", Font.ROMAN_BASELINE, 16);

		//Chatin alustus

		chatti = new JTextArea();
		chatti.setVisible(true);
		chatti.setBounds(524,17,261,345);
		chatti.setBackground(null);
		chatti.setOpaque(false);
		chatti.setEditable(false);
		chatti.setWrapStyleWord(true);
		chatti.setLineWrap(true);
		chatti.setFont(chattiFontti);
		chatti.setForeground(Color.black);
		chattiPaneeli = new JScrollPane(chatti, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chattiPaneeli.setBounds(524,17,261,345);
		chattiPaneeli.getVerticalScrollBar().setUI(new OmaScrollBar());
		chattiPaneeli.setOpaque(false);
		chattiPaneeli.getViewport().setOpaque(false);
		chattiPaneeli.getViewport().setBackground(null);
		chattiPaneeli.setVisible(true);
		chattiPaneeli.setBorder(null);
		keskusteluTausta = new JLabel();
		chattiTausta = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/chattitausta.png")));
		keskusteluTausta.setIcon(chattiTausta);
		keskusteluTausta.setBounds(505,-2,280, 430);
		keskusteluTausta.setVisible(true);
		
		//Viestiosion alustus
		viesti = new JTextField();
		viesti.setVisible(true);
		viesti.setBounds(520,373,250,16);
		viesti.setBackground(null);
		viesti.setFont(viestiFontti);
		viesti.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {laheta();}		
		});

		//JButton
		lahetaViesti = new JButton("Laheta viesti");
		lahetaViesti.setBounds(520,402, 250,16);
		lahetaViesti.setFont(yleisFontti);
		lahetaViesti.setVisible(true);
		lahetaViesti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {laheta();}
		});

		hahmovalinta = new HahmoValinta();

		this.add(hahmovalinta);
		this.add(chattiPaneeli);
		this.add(viesti);
		this.add(lahetaViesti);
		this.add(keskusteluTausta);
	}

	public void vastaanotaViesti(String viesti){
		chatti.append(viesti + "\n");
	}

	private void laheta(){
		if(!viesti.getText().trim().equals("")){
			try {
				Data.prp.lahetaViesti(viesti.getText(), Data.nimi);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			viesti.setText("");
		}
	}
	
	public void vastaanotaTiedot(String[] tiedot){
		for (String tapahtuma : tiedot) {
			hahmovalinta.tapahtumat.append(tapahtuma + "\n");
		}
	}

	class HahmoValinta extends JPanel{
		private ImageIcon tausta, taustaValittu, taustaHover, chattiTausta;
		private ArrayList<HahmoLabel> hahmot;
		private JButton taistele, tiedot;
		private JPanel hahmoPaneeli;
		private int[] valitut = {-1,-1,-1,-1};
		private JScrollPane tapahtumaPaneeli;
		public JTextArea tapahtumat;
		
		
		public HahmoValinta(){
			super();
			setLayout(null);
			setBounds(0, 0, 400, 450);
			setVisible(true);
			hahmot = new ArrayList<>();
			alusta();
		}
		private void alusta(){
			tausta = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/hahmoNappi.png")));
			taustaValittu = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/hahmoNappiValittu.png")));
			taustaHover = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/hahmoNappiHover.png")));
			hahmoPaneeli = new JPanel();
			for(int i = 0; i < Data.tiedot.size(); i++){
				HahmoLabel tmp = new HahmoLabel(tausta, taustaValittu, taustaHover, Data.tiedot.get(i).annaNimi(), i); 
				tmp.setAlignmentX(Component.CENTER_ALIGNMENT);
				tmp.setVisible(true);
				tmp.setSize(130,40);
				tmp.setLocation(25, 5+(40*i));
				tmp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int id = tmp.annaId();
						boolean done = false;
						for(int i = 0; i < valitut.length; i++){
							if(valitut[i] == id){
								valitut[i] = -1;
								hahmot.get(id).asetaNormaaliksi();	
								done = true;
							}
						}
						if(!done){
							for(int i = 0; i < valitut.length; i++){
								if(valitut[i] == -1){
									valitut[i] = id;
									hahmot.get(id).asetaValituksi();	
									break;
								}
							}
						}
						int maara = 0;
						for(int i = 0; i < valitut.length; i++){					
							if(valitut[i] != -1){
								maara++;
							}
						}
						if(maara == 4){
							taistele.setEnabled(true);
						}else{
							taistele.setEnabled(false);
						}
					}
				});
				hahmot.add(tmp);
				hahmoPaneeli.add(tmp);
			}
			hahmoPaneeli.setBounds(5, 5, 300, 200);
			hahmoPaneeli.setVisible(true);
			taistele = new JButton("TAISTELUUN!");
			taistele.setEnabled(false);
			taistele.setVisible(true);
			taistele.setBounds(20,200,200,40);
			taistele.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						System.out.println("TAISTELUUN");
						tapahtumat.setText("");
						Data.prp.etsiPelia(Data.uuid, valitut);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			});
			tapahtumat = new JTextArea();
			tapahtumat.setVisible(true);
			tapahtumat.setBounds(5, 250,400, 160);
			tapahtumat.setEditable(false);
			tapahtumat.setWrapStyleWord(true);
			tapahtumat.setLineWrap(true);
			tapahtumat.setFont(chattiFontti);
			tapahtumaPaneeli = new JScrollPane(tapahtumat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			tapahtumaPaneeli.setBounds(5, 250,400, 160);
			tapahtumaPaneeli.getVerticalScrollBar().setUI(new OmaScrollBar());
			tapahtumaPaneeli.setOpaque(false);
			tapahtumaPaneeli.getViewport().setOpaque(false);
			tapahtumaPaneeli.getViewport().setBackground(null);
			tapahtumaPaneeli.setVisible(true);
			tapahtumaPaneeli.setBorder(null);
			tiedot = new JButton("Hahmojen tiedot");
			tiedot.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
						Data.vaihdaNakyma(Data.HAHMONAKYMA);
				}
			});
			tiedot.setVisible(true);
			tiedot.setBounds(225,200,200,40);
			add(tapahtumaPaneeli);
			add(hahmoPaneeli);
			add(tiedot);
			add(taistele);
		}
	}
}
