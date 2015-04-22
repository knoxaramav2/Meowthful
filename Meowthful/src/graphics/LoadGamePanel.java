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

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
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
	
	private unownInterpreter ui;
	
	public LoadGamePanel(unownInterpreter ui){
		this.ui = ui;
		try {
		    bi = ImageIO.read(new File("core/loadGameMenu.png"));
		} catch (IOException e) {
		}

	}
	
    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) {
        	ui.interpret("newGameDialogue");
        } else if ("load".equals(e.getActionCommand())) {
        	ui.interpret("loadGameDialogue");
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
