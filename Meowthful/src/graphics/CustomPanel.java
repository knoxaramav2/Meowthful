package graphics;

import gameElements.Map;
import gameElements.MapCalculator;
import gameElements.Player;
import gameElements.Sprites;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

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
	public Console console;
	private Sprites sprites=null;
	private MapCalculator mc;
	private unownInterpreter ui;
	private ArrayList <Player> actors = new ArrayList <Player>();
	private KeyboardFocusManager manager;

	public CustomPanel(Map mapClass, Sprites s, unownInterpreter ui) throws FileNotFoundException{			
		this.mapClass = mapClass;
		map = mapClass.getMap();
		this.sprites=s;
		
		playerSpeedX = 4;
		playerSpeedY = 4;
		addKeyListener(this);
		setFocusable(true);
		
		mc = new MapCalculator();
		this.ui = ui;
	
		Timer timer = new Timer(1000/60, this);
		timer.start();
	}
	
	public void focus()
	{
		//addKeyListener(this);
		setFocusable(true);
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
		updateAI();
	}

	public void keyPressed(KeyEvent e){
		Player p = getActor(0);
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) p.moveSwitch[0] = true;
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) p.moveSwitch[2] = true;
		if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) p.moveSwitch[1] = true;
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) p.moveSwitch[3] = true;
		if(e.getKeyCode() == KeyEvent.VK_F1) console.toggleVisible();
		if(e.getKeyCode() == KeyEvent.VK_ENTER) 
			interact(getForwardActor(getActor(0)));  
	}

	public void keyReleased(KeyEvent e){
		Player p = getActor(0);
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) p.moveSwitch[0] = false;
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) p.moveSwitch[2] = false;
		if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) p.moveSwitch[1] = false;
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) p.moveSwitch[3] = false;		
	}

	public void keyTyped(KeyEvent e){Player p = getActor(0);
	if (p==null)
		return;
	try {
		step(p, false);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}}
	
	public void step(Player player, boolean isAI) throws IOException{
		int cID = player.id;
		if(!player.moving){
			if(player.moveSwitch[0]){
				player.sprite = resize(sprites.getPlayerSprite(Sprites.backward_idle, player.type), WIDTH, HEIGHT);
				if(!mapClass.isBlocked(player.getCellX(), (player.getCellY() - 1 > 0 ? player.getCellY() - 1 : 0)) && !isActorBlocked(player)){
					player.moving = true;
					player.nextPos = player.posy - HEIGHT;
					if(player.nextPos < 0) player.nextPos = 0;
					//player.setCellY(player.getCellY() - 1 < 0 ? 0 : player.getCellY() - 1);
					player.orientation = 1;
				}
				
				//sop("Direction: UP	player.nextPos: " + player.nextPos + " player.moving: " + player.moving);
			}else if(player.moveSwitch[1]){
				player.sprite = resize(sprites.getPlayerSprite(Sprites.forward_idle, player.type), WIDTH, HEIGHT);
				if(!mapClass.isBlocked(player.getCellX(), (player.getCellY() + 1 < 14 ? player.getCellY() + 1 : 14)) && !isActorBlocked(player)){
					player.moving = true;
					player.nextPos = player.posy + HEIGHT;
					if(player.nextPos >= map.getHeight()) player.nextPos = map.getHeight() - HEIGHT;
					//player.setCellY(player.getCellY() + 1 > 14 ? 14 : player.getCellY() + 1);
					player.orientation = 2;
				}
				
				//sop("Direction: DOWN	player.nextPos: " + player.nextPos + " player.moving: " + player.moving);
			}else if(player.moveSwitch[2]){
				player.sprite = resize(sprites.getPlayerSprite(Sprites.left_idle, player.type), WIDTH, HEIGHT);
				if(!mapClass.isBlocked((player.getCellX() - 1 > 0 ? player.getCellX() - 1 : 0), player.getCellY()) && !isActorBlocked(player)){
					player.moving = true;
					player.nextPos = player.posx - WIDTH;
					if(player.nextPos < 0) player.nextPos = 0;
					//player.setCellX(player.getCellX() - 1 < 0 ? 0 : player.getCellX() - 1);
					player.orientation = 3;
				}
				
				//sop("Direction: LEFT	player.nextPos: " + player.nextPos + " player.moving: " + player.moving);
			}else if(player.moveSwitch[3]){
				player.sprite = resize(sprites.getPlayerSprite(Sprites.right_idle, player.type), WIDTH, HEIGHT);
				if(!mapClass.isBlocked((player.getCellX() + 1 < 14 ? player.getCellX() + 1 : 14), player.getCellY()) && !isActorBlocked(player)){
					player.moving = true;
					player.nextPos = player.posx + WIDTH;
					if(player.nextPos >= map.getWidth()) player.nextPos = map.getWidth() - WIDTH;
					//player.setCellX(player.getCellX() + 1 > 14 ? 14 : player.getCellX() + 1);
					player.orientation = 4;
				}
				
				//sop("Direction: RIGHT	player.nextPos: " + player.nextPos + " player.moving: " + player.moving);
			}
			if (cID!=player.id)
				System.out.println("ID Switch\n");
		}else{
			
			switch(player.orientation){
			case 1:
				if(player.posy == player.nextPos){
					player.moving = false;
					player.sprite = resize(sprites.getPlayerSprite(Sprites.backward_idle, player.type), WIDTH, HEIGHT);
					printSpecialMessage(player);
				}else{
					player.posy -= playerSpeedY;
				}
				break;
				
			case 2:
				if(player.posy == player.nextPos){
					player.moving = false;
					player.sprite = resize(sprites.getPlayerSprite(Sprites.forward_idle, player.type), WIDTH, HEIGHT);
					printSpecialMessage(player);
				}else{
					player.posy += playerSpeedY;
				}
				break;
				
			case 3:
				if(player.posx == player.nextPos){
					player.moving = false;
					player.sprite = resize(sprites.getPlayerSprite(Sprites.left_idle, player.type), WIDTH, HEIGHT);
					printSpecialMessage(player);
				}else{
					player.posx -= playerSpeedX;
				}
				break;
				
			case 4:
				if(player.posx == player.nextPos){
					player.moving = false;
					player.sprite = resize(sprites.getPlayerSprite(Sprites.right_idle, player.type), WIDTH, HEIGHT);
					printSpecialMessage(player);
				}else{
					player.posx += playerSpeedX;
				}
				break;
				
				default:;
			}
			if (cID!=player.id)
				System.out.println("ID Switch\n");
			//sop("X: " + player.posx + "		Y:" + player.posy + "	CellX: " + cellX + "	CellY: " + cellY);			
			repaint();
		}
	}

	public void interact(Player p)
	{
		if (p==null)
			sop("Empty");
		else
			sop(p.name);
	}
	
	public void swapMap(int teleporterID, Player player) throws IOException{
		player.posx = mc.getNewMapCellX(mapClass.getMapFileName(), teleporterID);
		player.posy = mc.getNewMapCellY(mapClass.getMapFileName(), teleporterID);
		player.setCellX(mc.getNewMapCellX(mapClass.getMapFileName(), teleporterID));
		player.setCellY(mc.getNewMapCellY(mapClass.getMapFileName(), teleporterID));
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
		p.sprite = resize(sprites.getPlayerSprite(p.orientation, p.type), WIDTH, HEIGHT);
		actors.add(p);
		revalidate();
		repaint();
	}
	
	public void updateAI()
	{
		for (int i=0; i<actors.size(); i++){
			try {
				actors.get(i).AI_Move(!actors.get(i).moving, (int)(System.currentTimeMillis()/1000));
				step(actors.get(i),true);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ConcurrentModificationException e)
			{
				System.exit(1);
			}
		}
	}
	
	private void printSpecialMessage(Player p) throws IOException{
		switch(mapClass.getMoveType(p.getCellX(), p.getCellY())){
		case 3:
			sop("Entered Teleporter: " + mapClass.getSpecialID(p.getCellX(), p.getCellY()));
			//swapMap(mapClass.getSpecialID(cellX, cellY));
			ui.interpret("loadMap " + mc.getNewMapPath(mapClass.getMapFileName(), mapClass.getSpecialID(p.getCellX(), p.getCellY())) + 
					" " + mc.getNewMapCellX(mapClass.getMapFileName(), mapClass.getSpecialID(p.getCellX(), p.getCellY())) + " " + mc.getNewMapCellY(mapClass.getMapFileName(), mapClass.getSpecialID(p.getCellX(), p.getCellY()))+" "+mapClass.getSpecialID(p.getCellX(), p.getCellY()));
			break;
			
		case 4:
			sop("Entered Event Zone: " + mapClass.getSpecialID(p.getCellX(), p.getCellY()));			
			break;
			
			default:;
		}
	}
	
	private static <T> void sop(T output){
		System.out.println(output);
	}

	private boolean isActorBlocked(Player p)
	{
		if (p.moveSwitch[0])//up
			for (Player pl: actors)
			{
				if (pl.id==p.id)
					continue;
				if (pl.posy<p.posy && pl.posx==p.posx)
					if (p.posy-pl.posy<=48)
						return true;
			}
		if (p.moveSwitch[1])//down
			for (Player pl: actors)
			{
				if (pl.id==p.id)
					continue;
				if (pl.posy>p.posy && pl.posx==p.posx)
					if (pl.posy-p.posy<=48)
						return true;
			}
		if (p.moveSwitch[2])//left
			for (Player pl: actors)
			{
				if (pl.id==p.id)
					continue;
				if (pl.posy==p.posy && pl.posx<p.posx)
					if (p.posx-pl.posx<=48)
						return true;
			}
		if (p.moveSwitch[3])//right
			for (Player pl: actors)
			{
				if (pl.id==p.id)
					continue;
				if (pl.posy==p.posy && pl.posx>p.posx)
					if (pl.posx-p.posx<=48)
						return true;
			}
		
		if (getForwardActor(p)!=null)
			return true;
		
		return false;
	}
	
	private Player getForwardActor(Player p)
	{
		for (Player pl: actors)
		{
			if (p==pl)
				continue;
			
			if (p.orientation==1)//up
				 if (p.posx==pl.posx || p.getCellX() == pl.getCellX())//if same x axis
				 {
					 int diff = p.posy-pl.posy;
					 if (diff>0 && diff<48)
						 return pl;
				 }
			 else if (p.orientation==2)//down
				 if (p.posx==pl.posx || p.getCellX() == pl.getCellX())//if same x axis
				 {
					 int diff = pl.posy-p.posy;
					 if (diff>0 && diff<48)
						 return pl;
				 }
			 else if (p.orientation==3)//left
				 if (p.posy==pl.posy || p.getCellY() == pl.getCellY())//if same y axis
				 {
					 int diff = p.posx-pl.posx;
					 if (diff>0 && diff<48)
						 return pl;
				 }
			 else if (p.orientation==4)//right
				 if (p.posy==pl.posy || p.getCellY() == pl.getCellY())//if same y axis
				 {
					 int diff = pl.posx-p.posx;
					 if (diff>0 && diff<48)
						 return pl;
				 }
		}
		
		return null;
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
