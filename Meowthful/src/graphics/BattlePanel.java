package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BattlePanel extends JPanel{
	public static final int WIDTH = 720;
	public static final int HEIGHT = 720;
	
	private BufferedImage bg;
	
	public BattlePanel(int index) throws IOException{
		ArenaParser ap = new ArenaParser();
		
		ap.loadArenaSprites("core/battle arenas.png", 240, 112, 4, 4, 0);

		bg = resize(ap.getArenaSprite(index), WIDTH, HEIGHT);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, null);
	}

	
	private BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
}

class ArenaParser{
	final int arenaSpriteCol = 4;
	final int arenaSpriteRow = 4;
	BufferedImage[] sprites = new BufferedImage[arenaSpriteCol * arenaSpriteRow];

	public void loadArenaSprites(String filename, int pokeSpriteWidth, int pokeSpriteHeight, int pokeSpriteCol, int pokeSpriteRow, int buffer) throws IOException {
		BufferedImage baseImage = ImageIO.read(new File(filename));
		
		for (int i = 0; i < pokeSpriteRow; i++) {
			for (int j = 0; j < pokeSpriteCol; j++) {
				sprites[(i * pokeSpriteCol) + j] = baseImage.getSubimage(j * pokeSpriteWidth + buffer, i * pokeSpriteHeight + buffer, pokeSpriteWidth - buffer, pokeSpriteHeight - buffer);
			}
		}
	}

	public BufferedImage getArenaSprite(int id) {
		return sprites[(id - 1)];
	}

}