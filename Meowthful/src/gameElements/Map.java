package gameElements;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Map {
	private static int width = 720;
	private static int height = 720;
	private MapComponents mapComponents;
	private BufferedImage map = null;
	private String filename = null;
	private int[][] moveType = new int[15][15];
	private int[][] specialID = new int[15][15];
	@SuppressWarnings("unused")
	private ArrayList <String> scripts = new ArrayList <String>();

	public Map(String fileName) throws IOException {
		filename = fileName;
		mapComponents = new MapComponents(filename);
		// load .World file
		BufferedImage imgs[][] = new BufferedImage[15][15];
		GetImage getImage = new GetImage();
		
		getImage.loadMapSprites("core/world sprites.png", 16,16,61,28,0);
		
		for(int j = 0; j < 15; j++){
			for(int i = 0; i < 15; i++){
				imgs[i][j] = GetImage.getPokemonSprite(mapComponents.getMapImageIndex(i, j));
				moveType[i][j] = mapComponents.getMoveType(i, j);
				specialID[i][j] = mapComponents.getSpecialID(i, j);
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
	
	public boolean isBlocked(int x, int y){
		
		if (x<0 || y <0 || x>14 || y>14)
			return false;
		
		return moveType[y][x] == 2;
	}
	
	public int getMoveType(int x, int y){
		return moveType[y][x];
	}
	
	public int getSpecialID(int x, int y){
		return specialID[y][x];
	}
	
	public String getMapFileName(){
		return filename;
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

class GetImage {
	// basic pokemon sprites
	final static int pokeSpriteCol = 61;
	final static int pokeSpriteRow = 28;
	static BufferedImage[] sprites = new BufferedImage[pokeSpriteCol * pokeSpriteRow];

	public void loadMapSprites(String filename, int pokeSpriteWidth, int pokeSpriteHeight, int pokeSpriteCol, int pokeSpriteRow, int buffer) throws IOException {
		BufferedImage baseImage = ImageIO.read(new File(filename));
		
		for (int i = 0; i < pokeSpriteRow; i++) {
			for (int j = 0; j < pokeSpriteCol; j++) {
				sprites[(i * pokeSpriteCol) + j] = baseImage.getSubimage(j * pokeSpriteWidth + buffer, i * pokeSpriteHeight + buffer, pokeSpriteWidth - buffer, pokeSpriteHeight - buffer);
			}
		}
	}

	public static BufferedImage getPokemonSprite(int id) {
		if (sprites[(id - 1)]==null)
		{
			System.out.println("Failure at id: "+id);
		}
		return sprites[(id - 1)];
	}
}
