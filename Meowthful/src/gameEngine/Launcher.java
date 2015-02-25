package gameEngine;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import scriptEngine.unownInterpreter;
import system.data;
import graphics.renderer;



public class Launcher {
	
	ArrayList scriptStack = new ArrayList();

	public static void main(String[] args) throws Exception {
		 
		//initialize classes
		unownInterpreter iEngine = new unownInterpreter();
		data Sys = new data();
		gameGlobal global = new gameGlobal();
		
		//load base files
		global = FileSystem.loadGlobals(global, "src/gameFiles/BaseValues.csv");
		
		//start graphics
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	try {
					renderer.createAndShowGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		//game loop
		while (Sys.active)
		{
			//get keystrokes
			
			//check movement
			
			//send events
			
			//receive events
			
			//update graphics
		}
		
	}

}
