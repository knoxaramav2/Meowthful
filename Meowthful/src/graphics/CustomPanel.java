package graphics;

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

import javax.swing.JPanel;
import javax.swing.Timer;

import scriptEngine.unownInterpreter;

@SuppressWarnings("serial")
public class CustomPanel extends JPanel implements KeyListener, ActionListener{
	private BufferedImage map;
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
	private Console console;

	public CustomPanel(BufferedImage map, Player player){			
		this.map = map;
		this.player = player;
		
		playerSpeedX = 5;
		playerSpeedY = 4;
		addKeyListener(this);
		setFocusable(true);
		
		up = down = left = right = false;
		
		player.posx = player.posy = 0;
		
		cellX = cellY = 0;
		
		nextPos = player.posy;
		lastDirection = 0;
		moving = false;
		
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
		g.drawImage(player.getSprite(Sprites.forward_idle), player.posx, player.posy, null);
	}

	public void actionPerformed(ActionEvent arg0) {
		step();
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

	public void keyTyped(KeyEvent e){
		
	}

	public void step(){
/*		if(up) player.posy = (player.posy - playerSpeed > 0) ? player.posy - playerSpeed : 0;
		else if(down) player.posy = (player.posy + playerSpeed + player.getSprite(0).getHeight() < map.getHeight()) ? player.posy + playerSpeed : map.getHeight() - player.getSprite(0).getHeight(); 
		else if(left) player.posx = (player.posx - playerSpeed > 0) ? player.posx - playerSpeed : 0;
		else if(right) player.posx = (player.posx + playerSpeed + player.getSprite(0).getWidth() < map.getWidth()) ? player.posx + playerSpeed : map.getWidth() - player.getSprite(0).getWidth();
		repaint();
*/				
		if(!moving){
			if(up){
				moving = true;
				nextPos = player.posy - 720/15;
				if(nextPos < 0) nextPos = 0;
				cellY = cellY - 1 < 0 ? 0 : cellY - 1;
				lastDirection = 1;
				sop("Direction: UP	nextPos: " + nextPos + " moving: " + moving);
			}else if(down){
				moving = true;
				nextPos = player.posy + 720/15;
				if(nextPos >= map.getHeight()) nextPos = map.getHeight() - 720/15;
				cellY = cellY + 1 > 14 ? 14 : cellY + 1;
				lastDirection = 2;
				sop("Direction: DOWN	nextPos: " + nextPos + " moving: " + moving);
			}else if(left){
				moving = true;
				nextPos = player.posx - 1280/15;
				if(nextPos < 0) nextPos = 0;
				cellX = cellX - 1 < 0 ? 0 : cellX - 1;
				lastDirection = 3;
				sop("Direction: LEFT	nextPos: " + nextPos + " moving: " + moving);
			}else if(right){
				moving = true;
				nextPos = player.posx + 1280/15;
				if(nextPos >= 1190) nextPos = 1190;
				cellX = cellX + 1 > 14 ? 14 : cellX + 1;
				lastDirection = 4;
				sop("Direction: RIGHT	nextPos: " + nextPos + " moving: " + moving);
			}
		}else{
			
			switch(lastDirection){
			case 1:
				if(player.posy == nextPos) moving = false;
				else player.posy -= playerSpeedY;
				sop("X: " + player.posx + "		Y:" + player.posy);
				break;
				
			case 2:
				if(player.posy == nextPos) moving = false;
				else player.posy += playerSpeedY;
				sop("X: " + player.posx + "		Y:" + player.posy);
				break;
				
			case 3:
				if(player.posx == nextPos) moving = false;
				else player.posx -= playerSpeedX;
				sop("X: " + player.posx + "		Y:" + player.posy);
				break;
				
			case 4:
				if(player.posx == nextPos) moving = false;
				else player.posx += playerSpeedX;
				sop("X: " + player.posx + "		Y:" + player.posy);
				break;
				
				default:;
			}
			
			repaint();
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
