package graphics;

import gameElements.Player;
import gameEngine.gameGlobal;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import scriptEngine.unownInterpreter;

//make window 720 x 1280

@SuppressWarnings("serial")
public class Renderer extends JPanel implements ActionListener{
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
	
	protected JTextField consoleWindow;
	protected JTextArea consoleFrame;
	private final static String newLine = "\n";
	private CustomPanel panel = null;
	
	public Renderer()
	{
		super (new GridBagLayout());
		
		consoleWindow = new JTextField(20);
		consoleWindow.addActionListener(this);
		
		consoleFrame = new JTextArea(5,20);
		consoleFrame.setEditable(false);
		JScrollPane scroll = new JScrollPane(consoleFrame);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth=GridBagConstraints.REMAINDER;
		
		c.fill=GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		add(scroll,c);
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		String text = consoleWindow.getText();
		consoleFrame.append(text+newLine);
		consoleFrame.selectAll();
		
		consoleFrame.setCaretPosition(consoleFrame.getDocument().getLength());
	}

	// flatten and render current image
	public void paint(Graphics g) {
//		Graphics2D g2d = (Graphics2D) g;
	}

	public void appendText(String text)
	{
		panel.console.appendText(text);
	}
	
	public void createAndShowGUI(unownInterpreter ui, gameGlobal Gg) throws IOException {
		// Create and set up the window.
		BufferedImage[] player = new BufferedImage[1];
		player[0] = ImageIO.read(new File("src/gameFiles/singlePerson.png"));
		ArrayList<Player> players = new ArrayList<Player>();
		for(int i = 0; i < Gg.getPlayerCount(); i++){
			Player p = Gg.getPlayer(i);
			players.add(p);
		}
		
		gameElements.Map map = new gameElements.Map("src/gameFiles/MonkMountainV2.WORLD");
		JFrame frame = new JFrame("Meowthful");
		panel = new CustomPanel(map, players, Gg.getSpriteSheets(), ui);
		panel.setConsole(ui);
		
		BattlePanel bp = new BattlePanel(6);
		
		frame.setSize(720, 750);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//frame.add(new Renderer());
		frame.add(panel);
//		frame.add(bp);

		// Display the window.
		//frame.pack();
		frame.setVisible(true);
	}
}
