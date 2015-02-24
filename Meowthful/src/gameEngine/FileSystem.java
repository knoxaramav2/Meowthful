package gameEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

//loads and saves different file types into memory
public class FileSystem {
	File a;
	FileReader in;
	
	//load flags
	static int _actor=0, _pkmn=1;
	
	public static gameGlobal loadGlobals(gameGlobal g, String fileName)
	{
		BufferedReader br=null;
		
		String cache = new String();
	
		
		//determine file to read
		if (fileName.equals("Actors.csv"))
		{//read AI data/Player
			
		}
		else if (fileName.equals("BaseValues.csv"))
		{
			
		}
		else if (fileName.equals("Attacks.csv"))
		{
			
		}
		return g;
	}
}

/*
 * gameGlobal gG = new gameGlobal();
		BufferedReader br=null;
		String line = "";
		String delim = ",|\\n";
		int mode=0;
		//serial load index
		int index=0;
		
		try {
			br = new BufferedReader(new FileReader(fileName));
 */
*/