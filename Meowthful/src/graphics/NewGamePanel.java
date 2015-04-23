package graphics;

import gameElements.Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import scriptEngine.unownInterpreter;

@SuppressWarnings("serial")
public class NewGamePanel extends JPanel implements ActionListener, KeyListener{
	public static final int SCREEN_WIDTH = 720;
	public static final int SCREEN_HEIGHT = 720;
	BufferedImage bi;
	BufferedImage [] pkballs = new BufferedImage[5];
	File[] listOfFiles;
	JTextField text = new JTextField(50);
	JLabel label = new JLabel();
	
	private unownInterpreter ui;
	
	public NewGamePanel(unownInterpreter ui){
		this.ui = ui;
		
		setFocusable(true);
		setLayout(null);
		
		try {
		    bi = ImageIO.read(new File("core/newGameMenu.png"));
		    BufferedImage pkTemp = ImageIO.read(new File("core/pokeballs.png"));
		    
		    for (int i=0; i<5; i++)
		    	pkballs[i]=pkTemp.getSubimage(i*60, 0, 60, 60);
		} catch (IOException e) {
		}
		
		File folder = new File("saves");
		listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }

		label.setText("File Name");
		label.setForeground(Color.GREEN);
		label.setBounds(280,360,100,20);
		text.addKeyListener(this);
		text.setBounds(160, 400, 300, 20);
		add(text);
		add(label);  
	
		revalidate();
		repaint();
	}
	
    public void actionPerformed(ActionEvent e) {
        
    }
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bi, 0, 0, null);
	}
	
	private boolean inBounds(double x, double y, boolean isTopButton){
		boolean inX = x < (3.0/4)*SCREEN_WIDTH && x > (1.0/4)*SCREEN_WIDTH;
		boolean inYTop = y < (6.0/8)*SCREEN_HEIGHT && y > (5.0/8)*SCREEN_HEIGHT;
		boolean inYBottom = y < (7.0/8)*SCREEN_HEIGHT && y > (6.0/8)*SCREEN_HEIGHT;
		return inX && (isTopButton ? inYTop : inYBottom);
	}

	public void keyPressed(KeyEvent e){
		if (e.getKeyCode()!=KeyEvent.VK_ENTER)
			return;
		
		if (text.getText().length()==0)
			return;
		
		if (text.getText().charAt(0) == ' ' || (text.getText().charAt(0)>='0' && text.getText().charAt(0)<='9'))
			return;
		
		boolean charVal=false;
		
		String val = new String(text.getText().toLowerCase());
		
		for (int i=0; i<val.length(); i++)
		{
			if ((val.charAt(i)>='a' && val.charAt(i)<='z'))
				charVal=true;
		}
		
		if (!charVal)
			return;
		
		ui.interpret("setWindow world");
		ui.interpret("placeActor 0 7 7");
		ui.interpret("loadScript maps/Island1Exterior.scpt");
		ui.interpret("saveGame "+val+".sav");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
