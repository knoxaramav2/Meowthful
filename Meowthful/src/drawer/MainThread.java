package drawer;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainThread {
	public static void main(String[] args) throws IOException{
		JFrame frame = new JFrame("Map Creator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		MapButton[][] buttons = new MapButton[15][15];
		TerrainButton[][] terrainButtons = new TerrainButton[43][13];
		MoveButton[] moveButtons = new MoveButton[4];
		JLabel mapLabel = new JLabel("Map", JLabel.CENTER);
		JLabel terrainLabel = new JLabel("Terrain", JLabel.CENTER);
		JLabel moveLabel = new JLabel("Move Type", JLabel.CENTER);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = c.weighty = 0.0;
		c.gridx = c.gridy = 0;
		c.gridwidth = 15;
		mapLabel.setForeground(Color.RED);
		mapLabel.setOpaque(true);
		mapLabel.setBackground(Color.YELLOW);
		frame.add(mapLabel, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = c.weighty = 0.0;
		c.gridwidth = 4;
		c.gridx = 17;
		c.gridy = 0;
		terrainLabel.setForeground(Color.BLACK);
		terrainLabel.setOpaque(true);
		terrainLabel.setBackground(Color.WHITE);
		frame.add(terrainLabel, c);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = c.weighty = 0.0;
		c.gridx = 22;
		c.gridy = 0;
		c.gridwidth = 2;
		moveLabel.setForeground(Color.GREEN);
		moveLabel.setOpaque(true);
		moveLabel.setBackground(Color.BLUE);
		frame.add(moveLabel, c);
		
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				buttons[i][j] = new MapButton();
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 0.0;
				c.weighty = 0.0;
				c.gridwidth = 1;
				c.gridx = i;
				c.gridy = j + 2;
				frame.add(buttons[i][j], c);
			}
		}
		
		int index = 1;
		
		for(int i = 0; i < 43; i++){
			for(int j = 0; j < 13; j++){
				terrainButtons[i][j] = new TerrainButton(index, buttons[0][0]);
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 0.0;
				c.weighty = 0.0;
				c.gridx = i;
				c.gridy = j + 17;
				frame.add(terrainButtons[i][j], c);
				index++;
			}
		}
		
		index = 1;
		
		for(int i = 0; i < 4; i++){
			moveButtons[i] = new MoveButton(index, buttons[0][0]);
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 0.0;
			c.weighty = 0.0;
			c.gridx = 59;
			c.gridy = i + 2;
			frame.add(moveButtons[i], c);
			index++;
		}

		SaveButton save = new SaveButton(buttons, terrainLabel);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.gridx = 23;
		c.gridy = 16;
		frame.add(save, c);		
		
		frame.setSize(1875, 1009);
		frame.setResizable(true);
		frame.setVisible(true);
	}	
}
