package drawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MapButton extends JButton implements ActionListener{
//	private JLabel terrain;
//	private JLabel move;
	
//	private JLabel moveType;
	
	private int terrainInt;
	private int moveInt;
	
	private static int blockType = 61;
	
	public static GetImage image = new GetImage();
		
	public MapButton() throws IOException{
		setBackground(Color.RED);
		addActionListener(this);
		
		terrainInt = 559;
		moveInt = 0;
	
//		moveType = new JLabel("Move: 0", JLabel.LEFT);
		
//		add(moveType);
		
		image.loadPokeSprites("src/gameFiles/world_spritesheet.png",17,17,43,13,1);
		setIcon(new ImageIcon(resize(image.getPokemonSprite(559), MainNullLayout.CELL_SIDE, MainNullLayout.CELL_SIDE)));
	}
	
	public void actionPerformed(ActionEvent e) {
		if(blockType <= 559){
			terrainInt = blockType;
			setIcon(new ImageIcon(resize(image.getPokemonSprite(blockType), MainNullLayout.CELL_SIDE, MainNullLayout.CELL_SIDE)));
			System.out.println("Terrain Type Set: " + terrainInt);
		}else{
			moveInt = blockType % 559;
			System.out.println("Movement Type Set: " + moveInt);
		}		
//		moveType.setText("Move: " + moveInt);
		repaint();
	}

	public void setBlockType(int index){
		blockType = index;
	}
	
	public int getTerrainIndex(){
		return terrainInt;
	}
	
	public int getMoveIndex(){
		return moveInt;
	}
	
	public Dimension getPreferedSize(){
		return new Dimension(MainNullLayout.CELL_SIDE,MainNullLayout.CELL_SIDE);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		setForeground(new Color(188,19,254));
		
		switch(moveInt){
		case 1:
			
			break;
			
		case 2:
			((Graphics2D) g).setStroke(new BasicStroke(5));
			g.drawLine(8, 8, getWidth()-8, getHeight()-8);
			g.drawLine(8, getHeight()-8, getWidth()-8, 8);
			break;
			
		case 3:
			g.fillRect(4,4,getWidth()-8,5);
			g.fillRect((getWidth()/2)-2,4,5,getHeight()-8);			
			break;
			
		case 4:
			g.fillRect(4,4,getWidth()-8,5);
			g.fillRect(4,4,5,getHeight()-8);			
			g.fillRect(4,getHeight()/2-2,getWidth()/2,5);
			g.fillRect(4,getHeight()-8,getWidth()-8,5);
			break;
		}
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
