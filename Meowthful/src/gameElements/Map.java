package gameElements;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Map {
	private static int width = 1280;
	private static int height = 720;
	private BufferedImage map = null;
	private int attributeMatrix[] = null;
	private boolean[][] blocked = new boolean[15][15];
	private Point loc = null;
	private ArrayList <String> scripts = new ArrayList <String>();

	public Map(String fileName) throws IOException {
		attributeMatrix = new int[1280 * 720];
		// load .World file
		BufferedImage imgs[][] = new BufferedImage[15][15];
		Scanner s;
		GetImage getImage = new GetImage();
		
		getImage.loadPokeSprites("src/gameFiles/world_spritesheet.png", 17,17,43,13,1);
		s = new Scanner(new File(fileName));
		
		for(int j = 0; j < 15; j++){
			for(int i = 0; i < 15; i++){
				imgs[i][j] = resize(getImage.getPokemonSprite(parseMap(s)), 30, 30);
			}
		}

		int type = imgs[0][0].getType();
		int cellWidth = imgs[0][0].getWidth();
		int cellHeight = imgs[0][0].getHeight();

		map = new BufferedImage(cellWidth*15, cellHeight*15, type);

		for(int j = 0; j < 15; j++){
			for(int i = 0; i < 15; i++){
				map.createGraphics().drawImage(imgs[i][j], cellWidth*j, cellHeight*i, null);
			}
		}
		
		map = resize(map, width, height);
	}
	
	public BufferedImage getMap(){
		return map;
	}
	
	private BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
	
	private static int parseMap(Scanner s) throws FileNotFoundException {
		s.useDelimiter(",");
		if(s.hasNextInt()){
			int next = s.nextInt();
			next /= 10;
			return next;
		} else return 160;
	}
}

class GetImage {
	// basic pokemon sprites
	final int pokeSpriteCol = 43;
	final int pokeSpriteRow = 13;
	BufferedImage[] sprites = new BufferedImage[pokeSpriteCol * pokeSpriteRow];

	public void loadPokeSprites(String filename, int pokeSpriteWidth, int pokeSpriteHeight, int pokeSpriteCol, int pokeSpriteRow, int buffer) throws IOException {
		BufferedImage baseImage = ImageIO.read(new File(filename));
		
		for (int i = 0; i < pokeSpriteRow; i++) {
			for (int j = 0; j < pokeSpriteCol; j++) {
				sprites[(i * pokeSpriteCol) + j] = baseImage.getSubimage(j * pokeSpriteWidth + buffer, i * pokeSpriteHeight + buffer, pokeSpriteWidth - buffer, pokeSpriteHeight - buffer);
			}
		}
	}

	public BufferedImage getPokemonSprite(int id) {
		return sprites[(id - 1)];
	}
}
