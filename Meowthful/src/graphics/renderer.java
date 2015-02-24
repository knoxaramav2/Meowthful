package graphics;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//make window 720 x 1280
 
public class renderer {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     * @throws IOException 
     */
    public static void createAndShowGUI() throws IOException {
        //Create and set up the window.
        JFrame frame = new JFrame("Meowthful");
        JPanel panel = new JPanel();
        BufferedImage testPic = ImageIO.read(new File("src/gameFiles/mewtwoTestPic.png"));
        JLabel picLabel = new JLabel(new ImageIcon(testPic));
        
        frame.getContentPane().setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add the ubiquitous "Hello World" label.
//        JLabel label = new JLabel("Hello World");
//        frame.getContentPane().add(label);
 
        panel.setPreferredSize(new Dimension(1280, 720));
        frame.getContentPane().add(panel);
        frame.setResizable(false);
        panel.add(picLabel);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}

//1280*720
//JPanel
//Non resizeable
