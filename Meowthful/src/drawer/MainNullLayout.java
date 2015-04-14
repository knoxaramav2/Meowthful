package drawer;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainNullLayout {
	public static final int CELL_SIDE = 23;
	
	public static void main(String[] args) throws IOException{
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JFrame frame = new JFrame("Map Creator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MapButton[][] buttons = new MapButton[15][15];
		TerrainButton[][] terrainButtons = new TerrainButton[61][28];
		MoveButton[] moveButtons = new MoveButton[4];
		JLabel mapLabel = new JLabel("Map", JLabel.CENTER);
		JLabel terrainLabel = new JLabel("Terrain", JLabel.CENTER);
		JLabel moveLabel = new JLabel("Move Type", JLabel.CENTER);
//		JLabel renderedLabel = new JLabel("Rendered Map", JLabel.CENTER);
		JLabel renderedMap = new JLabel();
		
		mapLabel.setLocation(0, 0);
		mapLabel.setSize(15*CELL_SIDE, CELL_SIDE);
		mapLabel.setForeground(Color.RED);
		mapLabel.setOpaque(true);
		mapLabel.setBackground(Color.YELLOW);
		panel.add(mapLabel);
		
/*		terrainLabel.setLocation(0, 16*CELL_SIDE);
		terrainLabel.setSize(43*CELL_SIDE, CELL_SIDE);
		terrainLabel.setForeground(Color.BLACK);
		terrainLabel.setOpaque(true);
		terrainLabel.setBackground(Color.WHITE);
		frame.add(terrainLabel);
*/
		moveLabel.setLocation(17*CELL_SIDE, 0);
		moveLabel.setSize(3*CELL_SIDE, CELL_SIDE);
		moveLabel.setForeground(Color.GREEN);
		moveLabel.setOpaque(true);
		moveLabel.setBackground(Color.BLUE);
		panel.add(moveLabel);
		
/*		renderedLabel.setLocation(31*CELL_SIDE, 0);
		renderedLabel.setSize(15*CELL_SIDE, CELL_SIDE);
		renderedLabel.setForeground(Color.RED);
		renderedLabel.setOpaque(true);
		renderedLabel.setBackground(Color.YELLOW);
		frame.add(renderedLabel);
*/		
		renderedMap.setLocation(31*CELL_SIDE, CELL_SIDE);
		renderedMap.setSize(15*CELL_SIDE, 15*CELL_SIDE);
		panel.add(renderedMap);
		
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				buttons[i][j] = new MapButton();
				buttons[i][j].setLocation(i*CELL_SIDE, CELL_SIDE*(j+1));
				buttons[i][j].setSize(CELL_SIDE,CELL_SIDE);
				panel.add(buttons[i][j]);
			}
		}
		
		int index = 1;
		
/*		for(int j = 0; j < 17; j++){	
			for(int i = 0; i < 39; i++){
				terrainButtons[i][j] = new TerrainButton(index, buttons[0][0]);
				terrainButtons[i][j].setLocation(((i+16)*CELL_SIDE), (CELL_SIDE*(j)));
				terrainButtons[i][j].setSize(CELL_SIDE, CELL_SIDE);
				panel.add(terrainButtons[i][j]);
				index++;
			}
			index += 22;
		}
*/
		index = 1;
		
		for(int j = 0; j < 28; j++){	
			for(int i = 0; i < 61; i++){
				terrainButtons[i][j] = new TerrainButton(index, buttons[0][0]);
				terrainButtons[i][j].setLocation(((i)*CELL_SIDE), 17*CELL_SIDE + (CELL_SIDE*(j)));
				terrainButtons[i][j].setSize(CELL_SIDE, CELL_SIDE);
				panel.add(terrainButtons[i][j]);
				index++;
			}
		}
		
		index = 1;
		
		for(int i = 0; i < 4; i++){
			moveButtons[i] = new MoveButton(index, buttons[0][0]);
			moveButtons[i].setLocation(17*CELL_SIDE, CELL_SIDE + CELL_SIDE*((3*i)));
			moveButtons[i].setSize(CELL_SIDE*3,CELL_SIDE*3);
			panel.add(moveButtons[i]);
			index++;
		}

		SaveButton save = new SaveButton(buttons, renderedMap);
		save.setLocation(61*CELL_SIDE, 28*CELL_SIDE);
		save.setSize(3*CELL_SIDE, CELL_SIDE);
		panel.add(save);		

		panel.setSize(new Dimension( 1920,1080));
		JScrollPane scrollFrame = new JScrollPane(panel);
		panel.setAutoscrolls(true);
		scrollFrame.setSize(new Dimension( 1900	,1000));
		frame.add(scrollFrame);
		
		frame.setSize(1920, 1080);
		frame.setResizable(true);
		frame.setVisible(true);		
	}
}
