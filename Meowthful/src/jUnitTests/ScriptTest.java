package jUnitTests;

import gameEngine.BattleManager;
import gameEngine.gameGlobal;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

import org.junit.Test;

import scriptEngine.unownInterpreter;
import system.data;

public class ScriptTest {
	data system = new data();
	gameGlobal gm = new gameGlobal();
	BattleManager bm = new BattleManager();
	unownInterpreter ui = new unownInterpreter(gm, bm, system);
	
	@Test
	public void sendCommand()
	{
		Scanner in = new Scanner(System.in);
		//String line = new String(in.nextLine());
		//System.out.println(line);
		
		while (in.hasNextLine())
		{
			String line = new String(in.nextLine());
			//toClipBoard(""+line.hashCode());
			ui.interpret(line);
		}
		
		in.close();
	}
	
	@SuppressWarnings("unused")
	private void toClipBoard(String myString)
	{
		StringSelection stringSelection = new StringSelection (myString);
		Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
		clpbrd.setContents (stringSelection, null);
	}
}
