package graphics;

import gameElements.Player;

import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

//make window 720 x 1280

public class Renderer {
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 * 
	 * @throws IOException
	 */

	BufferedImage mapUL = null;
	BufferedImage mapUM = null;
	BufferedImage mapUR = null;

	BufferedImage mapML = null;
	BufferedImage mapMM = null;
	BufferedImage mapMR = null;

	BufferedImage mapLL = null;
	BufferedImage mapLM = null;
	BufferedImage mapLR = null;

	// flatten and render current image
	public void paint(Graphics g) {
//		Graphics2D g2d = (Graphics2D) g;
	}

	public static void createAndShowGUI() throws IOException {
		// Create and set up the window.
		BufferedImage[] player = new BufferedImage[1];
		player[0] = ImageIO.read(new File("src/gameFiles/singlePerson.png"));
		Player testPlayer = new Player("1,Test Grunt,grunt,zubat:5:40:0:tackle%35|screech%35,n/a,n/a,n/a,n/a,n/a,5,10,1,potion(2),0,5|10,0,0,10", player);
		
		gameElements.Map map = new gameElements.Map("src/gameFiles/testMap.WORLD");
		JFrame frame = new JFrame("Meowthful");
		CustomPanel panel = new CustomPanel(map.getMap(), testPlayer);
		
		frame.setSize(1280, 750);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(panel);

		// Display the window.
		frame.setVisible(true);
	}
}