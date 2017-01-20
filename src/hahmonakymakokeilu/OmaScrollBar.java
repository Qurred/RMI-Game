package hahmonakymakokeilu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class OmaScrollBar extends BasicScrollBarUI {

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle alue) {
		c.setOpaque(false);
	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle alue) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Color color = null;
		JScrollBar sb = (JScrollBar) c;
		if (!sb.isEnabled() || alue.width > alue.height) {
			return;
		} else if (isDragging) {
			color = new Color(0, 0, 0, 100);
		}else{
			color = new Color(0,0,0,60);
		}
		g2.setPaint(color);
		g2.fillRoundRect(alue.x + alue.width/2,alue.y, alue.width/2, alue.height, 7, 3);
		g2.dispose();
	}


	@Override
	protected JButton createDecreaseButton(int orientation) {
		return eiNappeja();
	}

	@Override    
	protected JButton createIncreaseButton(int orientation) {
		return eiNappeja();
	}

	private JButton eiNappeja() {
		JButton jbutton = new JButton();
		jbutton.setPreferredSize(new Dimension(0, 0));
		jbutton.setMinimumSize(new Dimension(0, 0));
		jbutton.setMaximumSize(new Dimension(0, 0));
		return jbutton;
	}
}
