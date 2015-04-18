package graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import scriptEngine.unownInterpreter;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel implements MouseListener{
	public static final int SCREEN_WIDTH = 720;
	public static final int SCREEN_HEIGHT = 720;
	
	private unownInterpreter ui;
	
	public MainMenuPanel(unownInterpreter ui){
		this.ui = ui;
	}
	
	public void mouseClicked(MouseEvent e) {
		if(inBounds(e.getX(), e.getY(), true))/*ui.interpret("whatever the proper command for starting a new game is")*/;
		if(inBounds(e.getX(), e.getY(), false))/*ui.interpret("whatever the proper command for loading a level is")*/;
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
	
	private boolean inBounds(double x, double y, boolean isTopButton){
		boolean inX = x < (3.0/4)*SCREEN_WIDTH && x > (1.0/4)*SCREEN_WIDTH;
		boolean inYTop = y < (6.0/8)*SCREEN_HEIGHT && y > (5.0/8)*SCREEN_HEIGHT;
		boolean inYBottom = y < (7.0/8)*SCREEN_HEIGHT && y > (6.0/8)*SCREEN_HEIGHT;
		return inX && (isTopButton ? inYTop : inYBottom);
	}
}
