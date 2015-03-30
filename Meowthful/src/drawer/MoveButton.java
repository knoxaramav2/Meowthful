package drawer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MoveButton extends JButton implements ActionListener{
	MapButton button;

	JLabel image = new JLabel();
	int index;
	
	public MoveButton(int index, MapButton button){
		this.button = button;
		setForeground(Color.BLUE);
		setBackground(Color.GREEN);
		addActionListener(this);
		this.index = index;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		button.setBlockType(559+index);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		switch(index){
		case 1:
			
			break;
			
		case 2:
			Graphics2D g2 = (Graphics2D)g;
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(8, 8, getWidth()-8, getHeight()-8);
			g2.drawLine(8, getHeight()-8, getWidth()-8, 8);
			break;
			
		case 3:
			g.fillRect(4,4,getWidth()-8,5);
			g.fillRect((getWidth()/2)-2,4,5,getHeight()-8);			
			break;
			
		case 4:
			g.fillRect(4,4,getWidth()-8,5);
			g.fillRect(4,4,5,getHeight()-8);			
			g.fillRect(4,getHeight()/2-2,getWidth()/2,5);
			g.fillRect(4,getHeight()-8,getWidth()-8,5);
			break;
		}
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(17,17);
	}
	
}
