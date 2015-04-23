package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import scriptEngine.unownInterpreter;

@SuppressWarnings("serial")
public class LoadGamePanel extends JPanel implements ActionListener, MouseListener{
	public static final int SCREEN_WIDTH = 720;
	public static final int SCREEN_HEIGHT = 720;
	BufferedImage bi;
	BufferedImage [] pkballs = new BufferedImage[5];
	File[] listOfFiles;
	JButton buttons[];
	
	private unownInterpreter ui;
	
	public LoadGamePanel(unownInterpreter ui){
		this.ui = ui;
		
		setFocusable(true);
		setLayout(null);
		
		try {
		    bi = ImageIO.read(new File("core/loadGameMenu.png"));
		    BufferedImage pkTemp = ImageIO.read(new File("core/pokeballs.png"));
		    
		    for (int i=0; i<5; i++)
		    	pkballs[i]=pkTemp.getSubimage(i*60, 0, 60, 60);
		} catch (IOException e) {
		}
		
		File folder = new File("saves");
		listOfFiles = folder.listFiles();
		
		buttons = new JButton[listOfFiles.length];
		
		Random rand = new Random();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	        System.out.println("File " + listOfFiles[i].getName());
	        JButton temp = new JButton(new ImageIcon(pkballs[rand.nextInt(listOfFiles.length-1)]));
	        temp.setActionCommand("select "+listOfFiles[i].toString());
	        temp.setBorderPainted(false);
	        temp.setText(listOfFiles[i].toString());
	        temp.setBounds(20, (60*i) + 120, 520, 60);
	        temp.addActionListener(this);
	        buttons[i]=temp;
	        add(temp);
	        
	      } else if (listOfFiles[i].isDirectory()) {
	        System.out.println("Directory " + listOfFiles[i].getName());
	      }
	    }

	}
	
    public void actionPerformed(ActionEvent e) {
        String line = e.getActionCommand();
        String [] params = line.split(" ");
        
        if (params.length!=2)
        	return;
        
        if (params[0].equals("select"))
        {
        	ui.interpret("placeActor 0 7 7");
        	ui.interpret("loadGame "+params[1]);
        }
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
