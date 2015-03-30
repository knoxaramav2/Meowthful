package drawer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class SaveButton extends JButton implements ActionListener{
	private MapButton[][] buttons = new MapButton[15][15];
	
	public SaveButton(MapButton[][] buttons, JLabel renderedMap){
		addActionListener(this);
		setForeground(Color.BLACK);
		setBackground(new Color(255, 102, 0));
		setText("Save");
		for(int i = 0; i < 15; i++) for(int j = 0; j < 15; j++) this.buttons[i][j] = buttons[i][j];
	}
	
	public void actionPerformed(ActionEvent e) {
		String output = "";
		int teleporterIndex = 0;
		int eventIndex = 0;
		
		for(int i = 0; i < 15; i++) for(int j = 0; j < 15; j++){
			if(buttons[i][j].getTerrainIndex() < 10) output += ("00" + buttons[i][j].getTerrainIndex());
			else if(buttons[i][j].getTerrainIndex() < 100) output += ("0" + buttons[i][j].getTerrainIndex());
			else output += buttons[i][j].getTerrainIndex();
			
			output += buttons[i][j].getMoveIndex();
			
			switch(buttons[i][j].getMoveIndex()){
			case 3:
				if(teleporterIndex < 10) output += ("0" + teleporterIndex);
				else output += teleporterIndex;
				teleporterIndex++;
				break;
				
			case 4:
				if(eventIndex < 10) output += ("0" + eventIndex);
				else output += eventIndex;
				eventIndex++;				
				break;
				
			default:
				output += "00";
				break;
			}
			
			output += ",";
		}

		NameBox name = new NameBox(this, output);
		
		name.setVisible(true);		
	}
	
	public void setFilePath(String path, String output){
		PrintWriter writer;
		try {
			writer = new PrintWriter(path + ".WORLD", "UTF-8");
			writer.println(output);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(output);
	}

}
