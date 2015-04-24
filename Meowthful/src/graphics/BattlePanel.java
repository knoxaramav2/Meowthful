package graphics;

import gameElements.Pokemon;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import scriptEngine.unownInterpreter;

@SuppressWarnings("serial")
public class BattlePanel extends JPanel{
	public static final int WIDTH = 720;
	public static final int HEIGHT = 720;
	
	private Pokemon userPoke;
	private Pokemon otherPoke;
	private BufferedImage bg;
	private BufferedImage poke1;
	private BufferedImage poke2;
	private BufferedImage healthBar;
	private unownInterpreter ui;
	private ArenaParser ap;
	private PokeParser pp;
	private BattleChoicePanel bcp;
	private double poke1HealthFactor;
	private double poke2HealthFactor;
	private ArrayList<Pokemon> party;
	
	public BattlePanel(unownInterpreter ui) throws IOException{
		healthBar = ImageIO.read(new File("core/HealthBar.png"));
		healthBar = resize(healthBar, 3*healthBar.getWidth(), (int)(45*healthBar.getHeight()/10.0));
		ap = new ArenaParser();
		pp = new PokeParser();

		this.ui = ui;
				
		ap.loadArenaSprites("core/battlearenasforparsing.png", 240, 112, 4, 4, 0);
		pp.loadPokeSprites("core/PokeFrontBack.png");
		
		poke1HealthFactor = 1;
		poke2HealthFactor = 1;
		
		poke1 = null;
		poke2 = null;
		bg = null;
		
		userPoke = null;
		otherPoke = null;
		
		party = null;
		
		setLayout(null);
		
		bcp = new BattleChoicePanel(ui);
		bcp.setLocation(WIDTH - healthBar.getWidth()-10, HEIGHT - 120);
		bcp.setSize(healthBar.getWidth() + 10, 120);
		add(bcp);
	}
	
	public void setMap(int index){
		bg = resize(ap.getArenaSprite(index), WIDTH, HEIGHT);
		repaint();
	}
	
	public void setUserPokemon(Pokemon p){
		userPoke = p;
		poke1 = pp.getBackPokeSprite(userPoke.getNumber());
		poke1 = resize(poke1, 3*poke1.getWidth(), (int)(45*poke1.getHeight()/7.0));
		bcp.initButtons(p.getAttackList(), party);
		repaint();
	}
	
	public void setOpponentPokemon(Pokemon p){
		otherPoke = p;
		poke2 = pp.getFrontPokeSprite(otherPoke.getNumber());
		poke2 = resize(poke2, 3*poke2.getWidth(), (int)(45*poke2.getHeight()/7.0));
		repaint();
	}
	
	public void setPokeHealthPercentage(int pokemon, double percentage){
		switch(pokemon){
		case 1:
			poke1HealthFactor = percentage;
			break;
			
		case 2:
			poke2HealthFactor = percentage;
			break;
			
			default:;
		}
		
		repaint();
	}
	
	public void setParty(ArrayList<Pokemon> party){
		this.party = party;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(!(bg == null)) g.drawImage(bg, 0, 0, null);
		if(!(bg == null)) if(!(poke1 == null)) g.drawImage(poke1, 70, 405, null);
		if(!(bg == null)) if(!(poke2 == null)) g.drawImage(poke2, 445, 90, null);
		if(!(bg == null)) if(!(healthBar == null)) g.drawImage(healthBar, 100, 100, null);
		if(!(bg == null)) if(!(healthBar == null)) g.drawImage(healthBar, bg.getWidth() - healthBar.getWidth()-10, bg.getHeight() - healthBar.getHeight() - 120, null);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.GREEN);
		g2.setStroke(new BasicStroke(15));
		if(!(bg == null)) if(!(poke2 == null)) g2.draw(new Line2D.Float(161, 143, 161 + (int)(129*poke2HealthFactor), 143));
		if(!(bg == null)) if(!(poke1 == null)) g2.draw(new Line2D.Float(555, 544, 555 + (int)(129*poke1HealthFactor), 544));
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

class PokeParser{
	BufferedImage[] frontSprites = new BufferedImage[151];
	BufferedImage[] backSprites = new BufferedImage[151];
	BufferedImage[] subGroups = new BufferedImage[51];
	
	public void loadPokeSprites(String filename) throws IOException {
		BufferedImage baseImage = ImageIO.read(new File(filename));
		int subGroupWidth = 161;
		int subGroupHeight = 196;
		int pokeWidth = 64;
		int pokeHeight = 64;
		int index = 0;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(i <4) subGroups[index] = baseImage.getSubimage(j * subGroupWidth, i * subGroupHeight, subGroupWidth, subGroupHeight);
				else subGroups[index] = baseImage.getSubimage(j * subGroupWidth, i * subGroupHeight + 2, subGroupWidth, subGroupHeight - 2);
				index++;
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 5; j < 10; j++) {
				if(i <4) subGroups[index] = baseImage.getSubimage(j * subGroupWidth, i * subGroupHeight, subGroupWidth, subGroupHeight);
				else subGroups[index] = baseImage.getSubimage(j * subGroupWidth, i * subGroupHeight + 2, subGroupWidth, subGroupHeight - 2);
				index++;
			}
		}
		
		subGroups[50] = baseImage.getSubimage(10*subGroupWidth, 0, subGroupWidth, subGroupHeight);
		
		index = 0;
		
		for(int i = 0; i < 50; i++){
			for(int j = 0; j < 3; j++){
				if(j == 3){
					frontSprites[index] = subGroups[i].getSubimage(0, subGroups[i].getHeight() - pokeHeight, pokeWidth, pokeHeight);
					backSprites[index] = subGroups[i].getSubimage(pokeWidth + 1, subGroups[i].getHeight() - pokeHeight, pokeWidth - 1, pokeHeight);
				}else{
					frontSprites[index] = subGroups[i].getSubimage(0, j * pokeHeight, pokeWidth, pokeHeight);
					backSprites[index] = subGroups[i].getSubimage(pokeWidth + 1, j * pokeHeight, pokeWidth - 1, pokeHeight);						
				}
				
				index++;
			}
		}
		
		frontSprites[150] = subGroups[50].getSubimage(0, 0, pokeWidth, pokeHeight);
		backSprites[150] = subGroups[50].getSubimage(pokeWidth, 0, pokeWidth, pokeHeight);
		
	}

	public BufferedImage getFrontPokeSprite(int id) {
		return frontSprites[(id - 1)];
	}
	
	public BufferedImage getBackPokeSprite(int id){
		return backSprites[(id - 1)];
	}
}