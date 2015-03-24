package gameEngine;
import graphics.Renderer;

import java.io.IOException;
import java.util.ArrayList;

import scriptEngine.unownInterpreter;
import system.data;



public class Launcher {
	
	ArrayList <String> scriptStack = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		 
		//initialize classes
		BattleManager battleManager = new BattleManager();
		gameGlobal global = new gameGlobal();
		data system = new data();
		Renderer graphics = new Renderer();
		unownInterpreter iEngine = new unownInterpreter(global, battleManager, system, graphics);
		
		//load base files (complete)
		FileSystem.loadGlobals(global);
		
		for (int x=0; x<global.playerList.size();x++)
		{
			System.out.println(global.playerList.get(x).name);
			
			for (int y=0; y<global.playerList.get(x).party.size();y++)
			{
				String s = global.playerList.get(x).party.get(y).name;
				if (s==null)
					System.out.println("null name");
				else 
					System.out.println(s);
			}
		}
		
		//start graphics
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {//game peripheral loop in renderer class
            	try {
					//Renderer.createAndShowGUI(iEngine,global);
					graphics.createAndShowGUI(iEngine, global);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
	}

}
