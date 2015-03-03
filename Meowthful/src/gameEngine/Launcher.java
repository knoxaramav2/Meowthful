package gameEngine;
import java.io.IOException;
import java.util.ArrayList;

import scriptEngine.UnownInterpreter;
import graphics.Renderer;



public class Launcher {
	
	ArrayList <String> scriptStack = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		 
		//initialize classes
		BattleManager battleManager = new BattleManager();
		gameGlobal global = new gameGlobal();
		UnownInterpreter iEngine = new UnownInterpreter(global, battleManager);
		
		//load base files (complete)
		global = FileSystem.loadGlobals(global, 
				"src/gameFiles/BaseValues.csv",
				"src/gameFiles/Attacks.csv",
				"src/gameFiles/Actors.csv", 
				"src/gameFiles/spritesheet.png");
		
		//start graphics
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {//game peripheral loop in renderer class
            	try {
					Renderer.createAndShowGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
	}

}
