package graphics;

import gameElements.Map;
import gameElements.MapCalculator;
import gameElements.Player;
import gameElements.Sprites;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import scriptEngine.unownInterpreter;

@SuppressWarnings("serial")
public class CustomPanel extends JPanel implements KeyListener, ActionListener{
	private static final int WIDTH = 720/15;
	private static final int HEIGHT = 720/15;
	
	private BufferedImage map;
	private BufferedImage curPlayerSprite;
	private Map mapClass;
	private ArrayList<Player> NPCs;
	private Player player;
	private int playerSpeedX;
	private int playerSpeedY;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	private int nextPos;
	private int lastDirection;
	private int cellX;
	private int cellY;
	private boolean moving;
	public Console console;
	private Sprites sprites=null;
	private MapCalculator mc;
	private unownInterpreter ui;

	public CustomPanel(Map mapClass, ArrayList<Player> characters, Sprites s, unownInterpreter ui) throws FileNotFoundException{			
		this.mapClass = mapClass;
		map = mapClass.getMap();
		this.player = characters.get(0);
		this.sprites=s;
		curPlayerSprite = resize(player.getSprite(Sprites.forward_idle), WIDTH, HEIGHT);
		
		playerSpeedX = 4;
		playerSpeedY = 4;
		addKeyListener(this);
		setFocusable(true);
		
		up = down = left = right = false;
		
		player.posx = 7*WIDTH;
		player.posy = 7*HEIGHT;
		
		cellX = 7;
		cellY = 7;
		
		nextPos = player.posy;
		lastDirection = 0;
		moving = false;
		
		mc = new MapCalculator();
		this.ui = ui;
		
		Timer timer = new Timer(1000/60, this);
		timer.start();
	}
	
	public void setConsole(unownInterpreter ui)
	{
		console = new Console();
		Console.createAndShowGUI(ui);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(map, 0, 0, null);
		g.drawImage(curPlayerSprite, player.posx, player.posy, null);
		if(NPCs != null){
			for(Player p : NPCs){
				g.drawImage(resize(p.getSprite(Sprites.forward_idle), WIDTH, HEIGHT), p.posx, p.posy, null);
			}
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			step();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) up = true;
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
		if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) down = true;
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
		if (e.getKeyCode()==KeyEvent.VK_F1) console.toggleVisible();
	}

	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) up = false;
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
		if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) down = false;
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;		
	}

	public void keyTyped(KeyEvent e){}

	public void step() throws IOException{
		if(!moving){
			if(up){
				curPlayerSprite = resize(sprites.getPlayerSprite(Sprites.backward_idle, "player"), WIDTH, HEIGHT);
				if(!mapClass.isBlocked(cellX, (cellY - 1 > 0 ? cellY - 1 : 0))){
					moving = true;
					nextPos = player.posy - HEIGHT;
					if(nextPos < 0) nextPos = 0;
					cellY = cellY - 1 < 0 ? 0 : cellY - 1;
					lastDirection = 1;
				}
				sop("Direction: UP	nextPos: " + nextPos + " moving: " + moving);
			}else if(down){
				curPlayerSprite = resize(sprites.getPlayerSprite(Sprites.forward_idle, "player"), WIDTH, HEIGHT);
				if(!mapClass.isBlocked(cellX, (cellY + 1 < 14 ? cellY + 1 : 14))){
					moving = true;
					nextPos = player.posy + HEIGHT;
					if(nextPos >= map.getHeight()) nextPos = map.getHeight() - HEIGHT;
					cellY = cellY + 1 > 14 ? 14 : cellY + 1;
					lastDirection = 2;
				}
				sop("Direction: DOWN	nextPos: " + nextPos + " moving: " + moving);
			}else if(left){
				curPlayerSprite = resize(sprites.getPlayerSprite(Sprites.left_idle, "player"), WIDTH, HEIGHT);
				if(!mapClass.isBlocked((cellX - 1 > 0 ? cellX - 1 : 0), cellY)){
					moving = true;
					nextPos = player.posx - WIDTH;
					if(nextPos < 0) nextPos = 0;
					cellX = cellX - 1 < 0 ? 0 : cellX - 1;
					lastDirection = 3;
				}
				sop("Direction: LEFT	nextPos: " + nextPos + " moving: " + moving);
			}else if(right){
				curPlayerSprite = resize(sprites.getPlayerSprite(Sprites.right_idle, "player"), WIDTH, HEIGHT);
				if(!mapClass.isBlocked((cellX + 1 < 14 ? cellX + 1 : 14), cellY)){
					moving = true;
					nextPos = player.posx + WIDTH;
					if(nextPos >= map.getWidth()) nextPos = map.getWidth() - WIDTH;
					cellX = cellX + 1 > 14 ? 14 : cellX + 1;
					lastDirection = 4;
				}
				sop("Direction: RIGHT	nextPos: " + nextPos + " moving: " + moving);
			}
		}else{
			
			switch(lastDirection){
			case 1:
				if(player.posy == nextPos){
					moving = false;
					curPlayerSprite = resize(sprites.getPlayerSprite(Sprites.backward_idle, "player"), WIDTH, HEIGHT);
					printSpecialMessage();
				}else{
					player.posy -= playerSpeedY;
				}
				break;
				
			case 2:
				if(player.posy == nextPos){
					moving = false;
					curPlayerSprite = resize(sprites.getPlayerSprite(Sprites.forward_idle, "player"), WIDTH, HEIGHT);
					printSpecialMessage();
				}else{
					player.posy += playerSpeedY;
				}
				break;
				
			case 3:
				if(player.posx == nextPos){
					moving = false;
					curPlayerSprite = resize(sprites.getPlayerSprite(Sprites.left_idle, "player"), WIDTH, HEIGHT);
					printSpecialMessage();
				}else{
					player.posx -= playerSpeedX;
				}
				break;
				
			case 4:
				if(player.posx == nextPos){
					moving = false;
					curPlayerSprite = resize(sprites.getPlayerSprite(Sprites.left_idle, "player"), WIDTH, HEIGHT);
					printSpecialMessage();
				}else{
					player.posx += playerSpeedX;
				}
				break;
				
				default:;
			}

			sop("X: " + player.posx + "		Y:" + player.posy + "	CellX: " + cellX + "	CellY: " + cellY);			
			repaint();
		}
	}
	
	public void swapMap(int teleporterID) throws IOException{
		player.posx = mc.getNewMapX(mapClass.getMapFileName(), teleporterID);
		player.posy = mc.getNewMapY(mapClass.getMapFileName(), teleporterID);
		cellX = mc.getNewMapCellX(mapClass.getMapFileName(), teleporterID);
		cellY = mc.getNewMapCellY(mapClass.getMapFileName(), teleporterID);
		switch(mc.getNewDirection(mapClass.getMapFileName(), teleporterID)){
		case 1:
			curPlayerSprite = resize(sprites.getPlayerSprite(Sprites.backward_idle, "player"), WIDTH, HEIGHT);
			break;
			
		case 2:
			curPlayerSprite = resize(sprites.getPlayerSprite(Sprites.forward_idle, "player"), WIDTH, HEIGHT);			
			break;
		}
		mapClass = new Map(mc.getNewMapPath(mapClass.getMapFileName(), teleporterID));
		map = mapClass.getMap();
	}
	
	private void printSpecialMessage() throws IOException{
		switch(mapClass.getMoveType(cellX, cellY)){
		case 3:
			sop("Entered Teleporter: " + mapClass.getSpecialID(cellX, cellY));
			//swapMap(mapClass.getSpecialID(cellX, cellY));
			ui.interpret("loadMap " + mc.getNewMapPath(mapClass.getMapFileName(), mapClass.getSpecialID(cellX, cellY)) + 
					" " + mc.getNewMapCellX(mapClass.getMapFileName(), mapClass.getSpecialID(cellX, cellY)) + " " + mc.getNewMapCellY(mapClass.getMapFileName(), mapClass.getSpecialID(cellX, cellY))+" "+mapClass.getSpecialID(cellX, cellY));
			break;
			
		case 4:
			sop("Entered Event Zone: " + mapClass.getSpecialID(cellX, cellY));			
			break;
			
			default:;
		}
	}
	
	private static <T> void sop(T output){
		System.out.println(output);
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
