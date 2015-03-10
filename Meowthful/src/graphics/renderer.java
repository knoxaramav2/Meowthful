package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//make window 720 x 1280
 
public class Renderer {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
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
	
	//flatten and render current image
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
	}
	
    public static void createAndShowGUI() throws IOException {
        //Create and set up the window.
    	gameElements.Map map = new gameElements.Map("src/gameFiles/testMap.WORLD");
        JFrame frame = new JFrame("Meowthful");
//		BufferedImage testPic = ImageIO.read(new File("src/gameFiles/mewtwoTestPic.png"));
        JPanel panel = new JPanel();
        JLabel label = new JLabel(new ImageIcon(map.getMap()));

        frame.getContentPane().setSize(1280, 720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        panel.add(label);
        frame.add(panel);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}