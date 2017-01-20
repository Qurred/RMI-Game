package hahmonakymakokeilu;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class HahmoLabel extends JLabel {

	private ImageIcon tausta;
	private ImageIcon taustaValittu;
	private ImageIcon taustahover;
	private String nimi;
	private int id;

	public HahmoLabel(ImageIcon tausta, ImageIcon taustaValittu, ImageIcon taustaHover, String nimi, int id){
		super();
		this.tausta = tausta;
		this.taustaValittu = taustaValittu;
		this.taustahover = taustaHover;
		this.nimi = nimi;
		this.id = id;
		//Alustetaan nämä
		this.setText(this.nimi);
		this.setHorizontalTextPosition(JLabel.CENTER);
		this.setForeground(Color.WHITE);
		Dimension dim = new Dimension(130,40);
		this.setPreferredSize(dim);
		this.setMaximumSize(dim);
		this.setMinimumSize(dim);
		this.setIcon(tausta);
		this.setOpaque(false);
		this.setVisible(true);
	}

	public int annaId(){
		return this.id;
	}
	public void asetaValituksi(){
		this.setIcon(taustaValittu);
	}
	public void asetaHover(){
		this.setIcon(taustahover);
	}
	public void asetaNormaaliksi(){
		this.setIcon(tausta);
	}

}
