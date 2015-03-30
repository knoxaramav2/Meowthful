package drawer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class TerrainButton extends JButton implements ActionListener{
	private MapButton button;
	private int index;
	
	public TerrainButton(int label, MapButton button) throws IOException{
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		this.button = button;
		addActionListener(this);
		index = label;
		setIcon(new ImageIcon(resize(MapButton.image.getPokemonSprite(index), MainNullLayout.CELL_SIDE, MainNullLayout.CELL_SIDE)));
	}
	
	public void actionPerformed(ActionEvent e) {
		button.setBlockType(index);
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(MainNullLayout.CELL_SIDE,MainNullLayout.CELL_SIDE);
	}

	public static BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
}
