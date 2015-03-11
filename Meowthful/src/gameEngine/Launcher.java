package gameEngine;
import java.io.IOException;
import java.util.ArrayList;

import scriptEngine.unownInterpreter;
import system.data;
import graphics.renderer;



public class Launcher {
	
	ArrayList <String> scriptStack = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		 
		//initialize classes
		BattleManager battleManager = new BattleManager();
		gameGlobal global = new gameGlobal();
		data system = new data();
		unownInterpreter iEngine = new unownInterpreter(global, battleManager, system);
		
		//load base files (complete)
		global = FileSystem.loadGlobals(global, 
				"src/gameFiles/BaseValues.csv",
				"src/gameFiles/Attacks.csv",
				"src/gameFiles/Actors.csv", 
				"src/gameFiles/firered-leafgreen.png");
		
		//start graphics
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {//game peripheral loop in renderer class
            	try {
					renderer.createAndShowGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
	}

}
