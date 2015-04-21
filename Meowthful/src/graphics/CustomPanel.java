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
	private Map mapClass;
	private int playerSpeedX;
	private int playerSpeedY;
	private int lastDirection;
	private int cellX;
	private int cellY;
	public Console console;
	private Sprites sprites=null;
	private MapCalculator mc;
	private unownInterpreter ui;
	private ArrayList <Player> actors = new ArrayList <Player>();

	public CustomPanel(Map mapClass, Sprites s, unownInterpreter ui) throws FileNotFoundException{			
		this.mapClass = mapClass;
		map = mapClass.getMap();
		this.sprites=s;
		
		playerSpeedX = 4;
		playerSpeedY = 4;
		addKeyListener(this);
		setFocusable(true);
		
		cellX = 7;
		cellY = 7;
	
		lastDirection = 0;
		
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
		for(Player p : actors){
			g.drawImage(p.sprite, p.posx, p.posy, null);
			//g.drawImage(resize(p.getSprite(Sprites.forward_idle), WIDTH, HEIGHT), p.posx, p.posy, null);
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			Player p = getActor(0);
			if (p==null)
				return;
			step(p, p.moveSwitch[0], p.moveSwitch[1], p.moveSwitch[2], p.moveSwitch[3], false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void keyPressed(KeyEvent e){
		Player p = getActor(0);
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) p.moveSwitch[0] = true;
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) p.moveSwitch[2] = true;
		if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) p.moveSwitch[1] = true;
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) p.moveSwitch[3] = true;
		if (e.getKeyCode()==KeyEvent.VK_F1) console.toggleVisible();
	}

	public void keyReleased(KeyEvent e){
		Player p = getActor(0);
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) p.moveSwitch[0] = false;
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) p.moveSwitch[2] = false;
		if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) p.moveSwitch[1] = false;
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) p.moveSwitch[3] = false;		
	}

	public void keyTyped(KeyEvent e){}
	
	public void step(Player player, boolean up, boolean down, boolean left, boolean right, boolean isAI) throws IOException{
		if(!player.moving){
			if(up){
				player.sprite = resize(sprites.getPlayerSprite(Sprites.backward_idle, player.type), WIDTH, HEIGHT);
				if(!mapClass.isBlocked(cellX, (cellY - 1 > 0 ? cellY - 1 : 0)) && !isActorBlocked()){
					player.moving = true;
					player.nextPos = player.posy - HEIGHT;
					if(player.nextPos < 0) player.nextPos = 0;
					cellY = cellY - 1 < 0 ? 0 : cellY - 1;
					lastDirection = 1;
				}
				sop("Direction: UP	player.nextPos: " + player.nextPos + " player.moving: " + player.moving);
			}else if(down){
				player.sprite = resize(sprites.getPlayerSprite(Sprites.forward_idle, player.type), WIDTH, HEIGHT);
				if(!mapClass.isBlocked(cellX, (cellY + 1 < 14 ? cellY + 1 : 14)) && !isActorBlocked()){
					player.moving = true;
					player.nextPos = player.posy + HEIGHT;
					if(player.nextPos >= map.getHeight()) player.nextPos = map.getHeight() - HEIGHT;
					cellY = cellY + 1 > 14 ? 14 : cellY + 1;
					lastDirection = 2;
				}
				sop("Direction: DOWN	player.nextPos: " + player.nextPos + " player.moving: " + player.moving);
			}else if(left){
				player.sprite = resize(sprites.getPlayerSprite(Sprites.left_idle, player.type), WIDTH, HEIGHT);
				if(!mapClass.isBlocked((cellX - 1 > 0 ? cellX - 1 : 0), cellY) && !isActorBlocked()){
					player.moving = true;
					player.nextPos = player.posx - WIDTH;
					if(player.nextPos < 0) player.nextPos = 0;
					cellX = cellX - 1 < 0 ? 0 : cellX - 1;
					lastDirection = 3;
				}
				sop("Direction: LEFT	player.nextPos: " + player.nextPos + " player.moving: " + player.moving);
			}else if(right){
				player.sprite = resize(sprites.getPlayerSprite(Sprites.right_idle, player.type), WIDTH, HEIGHT);
				if(!mapClass.isBlocked((cellX + 1 < 14 ? cellX + 1 : 14), cellY) && !isActorBlocked()){
					player.moving = true;
					player.nextPos = player.posx + WIDTH;
					if(player.nextPos >= map.getWidth()) player.nextPos = map.getWidth() - WIDTH;
					cellX = cellX + 1 > 14 ? 14 : cellX + 1;
					lastDirection = 4;
				}
				sop("Direction: RIGHT	player.nextPos: " + player.nextPos + " player.moving: " + player.moving);
			}
		}else{
			
			switch(lastDirection){
			case 1:
				if(player.posy == player.nextPos){
					player.moving = false;
					player.sprite = resize(sprites.getPlayerSprite(Sprites.backward_idle, player.type), WIDTH, HEIGHT);
					printSpecialMessage();
				}else{
					player.posy -= playerSpeedY;
				}
				break;
				
			case 2:
				if(player.posy == player.nextPos){
					player.moving = false;
					player.sprite = resize(sprites.getPlayerSprite(Sprites.forward_idle, player.type), WIDTH, HEIGHT);
					printSpecialMessage();
				}else{
					player.posy += playerSpeedY;
				}
				break;
				
			case 3:
				if(player.posx == player.nextPos){
					player.moving = false;
					player.sprite = resize(sprites.getPlayerSprite(Sprites.left_idle, player.type), WIDTH, HEIGHT);
					printSpecialMessage();
				}else{
					player.posx -= playerSpeedX;
				}
				break;
				
			case 4:
				if(player.posx == player.nextPos){
					player.moving = false;
					player.sprite = resize(sprites.getPlayerSprite(Sprites.right_idle, player.type), WIDTH, HEIGHT);
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

	
	public void swapMap(int teleporterID, Player player) throws IOException{
		player.posx = mc.getNewMapX(mapClass.getMapFileName(), teleporterID);
		player.posy = mc.getNewMapY(mapClass.getMapFileName(), teleporterID);
		cellX = mc.getNewMapCellX(mapClass.getMapFileName(), teleporterID);
		cellY = mc.getNewMapCellY(mapClass.getMapFileName(), teleporterID);
		switch(mc.getNewDirection(mapClass.getMapFileName(), teleporterID)){
		case 1:
			for (Player p: actors)
				if (p.id==0)
					p.sprite = resize(sprites.getPlayerSprite(Sprites.backward_idle, "player"), WIDTH, HEIGHT);
			break;
			
		case 2:
			for (Player p: actors)
				if (p.id==0)
					p.sprite = resize(sprites.getPlayerSprite(Sprites.forward_idle, "player"), WIDTH, HEIGHT);			
			break;
		}
		mapClass = new Map(mc.getNewMapPath(mapClass.getMapFileName(), teleporterID));
		map = mapClass.getMap();
		actors.clear();
	}
	
	public void addActor(Player p) {
		for (int x = 0; x < actors.size(); x++) if (actors.get(x).id == p.id) return;

		actors.add(p);
		revalidate();
		repaint();
	}
	
	public void updateAI()
	{
		for(Player p : actors){
			try {
				p.AI_Move(p.moving);
				step(p,p.moveSwitch[0],p.moveSwitch[1],p.moveSwitch[2],p.moveSwitch[3],true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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

	private boolean isActorBlocked()
	{
		
		return false;
	}
	
	private Player getActor(int id)
	{
		for (Player p: actors)
			if (id==p.id)
				return p;
		
		return null;
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
