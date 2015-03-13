package jUnitTests;

import static org.junit.Assert.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

import java.util.Scanner;

import gameEngine.BattleManager;
import gameEngine.gameGlobal;

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
		
		while (true)
		{
			String line = new String(in.nextLine());
			toClipBoard(""+line.hashCode());
		}
		
		//ui.interpret(line);
		//in.close();
	}
	
	private void toClipBoard(String myString)
	{
		StringSelection stringSelection = new StringSelection (myString);
		Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
		clpbrd.setContents (stringSelection, null);
	}
}
