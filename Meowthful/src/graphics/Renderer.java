package graphics;

import gameElements.Player;
import gameEngine.gameGlobal;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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

	
	protected JTextField consoleWindow;
	protected JTextArea consoleFrame;
	protected unownInterpreter ui;
	private final static String newLine = "\n";
	public JPanel			base = null;
	public CustomPanel  	panel = null;
	public MainMenuPanel	menu = null;
	public BattlePanel		battle = null;
	public JFrame			master = null;
	public NewGamePanel		newGame = null;
	public LoadGamePanel 	loadGame = null;
	public CardLayout 		deck = null;
	
	int panelMode = 0;
	
	public final static int WORLD_FRAME = 1;
	public final static int MENU_FRAME	= 2;
	public final static int BATTLE_FRAME= 3;
	public final static int LOAD_FRAME = 4;
	public final static int NEW_FRAME = 5;
	
	
	public Renderer () 
	{
		super (new GridBagLayout());
		
		base = new JPanel();
		deck = new CardLayout();
		
		base.setLayout(deck);
		
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
	
	public void AI()
	{
		if (panel!=null)
			panel.updateAI();
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
		this.ui=ui;
		//player[0] = ImageIO.read(new File("src/gameFiles/singlePerson.png"));
		ArrayList<Player> players = new ArrayList<Player>();
		for(int i = 0; i < Gg.getPlayerCount(); i++){
			Player p = Gg.getPlayer(i);
			players.add(p);
		}
		
		gameElements.Map map = new gameElements.Map("maps/Island1Exterior.WORLD");
		master = new JFrame("Meowthful");
		panel = new CustomPanel(map, Gg.getSpriteSheets(), ui);
		panel.setConsole(ui);
		
		battle = new BattlePanel(ui);
		
		menu = new MainMenuPanel(ui);
		loadGame = new LoadGamePanel(ui);
		newGame = new NewGamePanel(ui);
		
		master.setSize(720, 750);
		master.setResizable(false);
		master.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		base.add(panel, "world");
		base.add(battle,"battle");
		base.add(menu,"menu");
		base.add(loadGame,"load");
		base.add(newGame,"new");
		
		deck.show(base, "menu");

		//frame.add(new Renderer());
		master.add(base);
//		frame.add(bp);

		// Display the window.
		//frame.pack();
		master.setVisible(true);
	}

	public int getMode()
	{
		return panelMode;
	}
	
	public void switchFrame(int mode)
	{                
		panelMode=mode;
		
		switch (mode)
		{
		case WORLD_FRAME:
			deck.show(base, "world");
			System.out.println("Switched to world");
			if (panel.requestFocusInWindow())
				System.out.println("Success");
			else
				System.out.println("Failure");
			break;
		case MENU_FRAME:
			deck.show(base, "menu");
			System.out.println("Switched to menu");
			if (menu.requestFocusInWindow())
				System.out.println("Success");
			else
				System.out.println("Failure");
			break;
		case BATTLE_FRAME:
			deck.show(base, "battle");
			System.out.println("Switched to battle");
			if (battle.requestFocusInWindow())
				System.out.println("Success");
			else
				System.out.println("Failure");
			break;
		case LOAD_FRAME:
			deck.show(base, "load");
			System.out.println("Switched to load");
			if (loadGame.requestFocusInWindow())
				System.out.println("Success");
			else
				System.out.println("Failure");
			break;
		case NEW_FRAME:
			deck.show(base, "new");
			System.out.println("Switched to new");
			if (newGame.requestFocusInWindow())
				System.out.println("Success");
			else
				System.out.println("Failure");
			break;
		}
	
		this.setVisible(true);
		this.revalidate();
        this.repaint();
	}

	public void pushKey(KeyEvent key)
	{
		switch (panelMode)
		{
		case WORLD_FRAME:
			break;
		case MENU_FRAME:
			break;
		case BATTLE_FRAME:
			break;
		case LOAD_FRAME:
			break;
		case NEW_FRAME:
			break;
		}
	}
}
