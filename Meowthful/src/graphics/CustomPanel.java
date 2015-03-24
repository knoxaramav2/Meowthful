package graphics;

import gameElements.Map;
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
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import scriptEngine.unownInterpreter;

@SuppressWarnings("serial")
public class CustomPanel extends JPanel implements KeyListener, ActionListener{
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

	public CustomPanel(Map mapClass, Player player, ArrayList<Player> NPCs, Sprites s){			
		this.mapClass = mapClass;
		map = mapClass.getMap();
		this.player = player;
		this.sprites=s;
		curPlayerSprite = player.getSprite(Sprites.forward_idle);
		
		playerSpeedX = 5;
		playerSpeedY = 4;
		addKeyListener(this);
		setFocusable(true);
		
		up = down = left = right = false;
		
		player.posx = 595;
		player.posy = 336;
		
		cellX = 7;
		cellY = 7;
		
		nextPos = player.posy;
		lastDirection = 0;
		moving = false;
		
		this.NPCs = NPCs;
		
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
				g.drawImage(p.getSprite(Sprites.forward_idle), p.posx, p.posy, null);
			}
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		step();
	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
			{
			//curPlayerSprite = sprites.getPlayerSprite(Sprites.backward_idle, "player");
			up = true;
			}
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
			{
			//curPlayerSprite = sprites.getPlayerSprite(Sprites.left_idle, "player");
			left = true;
			}
		if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
			{
			//curPlayerSprite = sprites.getPlayerSprite(Sprites.forward_idle, "player");
			down = true;
			}
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
			//curPlayerSprite = sprites.getPlayerSprite(Sprites.right_idle, "player");
			right = true;
			}
		if (e.getKeyCode()==KeyEvent.VK_F1) console.toggleVisible();
	}

	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) up = false;
		if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
		if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) down = false;
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;		
	}

	public void keyTyped(KeyEvent e){
		
	}

	public void step(){
		if(!moving){
			if(up){
				if(!mapClass.isBlocked(cellX, (cellY - 1 > 0 ? cellY - 1 : 0))){
					moving = true;
					nextPos = player.posy - 720/15;
					if(nextPos < 0) nextPos = 0;
					cellY = cellY - 1 < 0 ? 0 : cellY - 1;
					lastDirection = 1;
				}
//				sop("Direction: UP	nextPos: " + nextPos + " moving: " + moving);
			}else if(down){
				if(!mapClass.isBlocked(cellX, (cellY + 1 < 14 ? cellY + 1 : 14))){
					moving = true;
					nextPos = player.posy + 720/15;
					if(nextPos >= map.getHeight()) nextPos = map.getHeight() - 720/15;
					cellY = cellY + 1 > 14 ? 14 : cellY + 1;
					lastDirection = 2;
				}
//				sop("Direction: DOWN	nextPos: " + nextPos + " moving: " + moving);
			}else if(left){
				if(!mapClass.isBlocked((cellX - 1 > 0 ? cellX - 1 : 0), cellY)){
					moving = true;
					nextPos = player.posx - 1280/15;
					if(nextPos < 0) nextPos = 0;
					cellX = cellX - 1 < 0 ? 0 : cellX - 1;
					lastDirection = 3;
				}
//				sop("Direction: LEFT	nextPos: " + nextPos + " moving: " + moving);
			}else if(right){
				if(!mapClass.isBlocked((cellX + 1 < 14 ? cellX + 1 : 14), cellY)){
					moving = true;
					nextPos = player.posx + 1280/15;
					if(nextPos >= 1190) nextPos = 1190;
					cellX = cellX + 1 > 14 ? 14 : cellX + 1;
					lastDirection = 4;
				}
//				sop("Direction: RIGHT	nextPos: " + nextPos + " moving: " + moving);
			}
		}else{
			
			switch(lastDirection){
			case 1:
				if(player.posy == nextPos){
					moving = false;
					curPlayerSprite = player.getSprite(Sprites.backward_idle);
					printSpecialMessage();
				}else{
					player.posy -= playerSpeedY;
				}
				break;
				
			case 2:
				if(player.posy == nextPos){
					moving = false;
					curPlayerSprite = player.getSprite(Sprites.forward_idle);
					printSpecialMessage();
				}else{
					player.posy += playerSpeedY;
				}
				break;
				
			case 3:
				if(player.posx == nextPos){
					moving = false;
					curPlayerSprite = player.getSprite(Sprites.left_idle);
					printSpecialMessage();
				}else{
					player.posx -= playerSpeedX;
				}
				break;
				
			case 4:
				if(player.posx == nextPos){
					moving = false;
					curPlayerSprite = player.getSprite(Sprites.right_idle);
					printSpecialMessage();
				}else{
					player.posx += playerSpeedX;
				}
				break;
				
				default:;
			}

//			sop("X: " + player.posx + "		Y:" + player.posy + "	CellX: " + cellX + "	CellY: " + cellY);			
			repaint();
		}
	}
	
	private void printSpecialMessage(){
		switch(mapClass.getMoveType(cellX, cellY)){
		case 3:
			sop("Entered Teleporter: " + mapClass.getSpecialID(cellX, cellY));
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
		
	@SuppressWarnings("unused")
	private BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}

	
}
