package gameEngine;
import java.util.ArrayList;

import scriptEngine.unownInterpreter;
import system.data;
import graphics.renderer;



public class Launcher {
	
	ArrayList scriptStack = new ArrayList();

	public static void main(String[] args) {
		
		//initialize classes
		unownInterpreter iEngine = new unownInterpreter();
		data Sys = new data();
		
		//load base files
		
		//start graphics
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	renderer.createAndShowGUI();
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
