package gameEngine;

import gameElements.Attack;
import gameElements.Player;
import gameElements.Pokemon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//loads and saves different file types into memory
public class FileSystem {
	File a;
	FileReader in;
	
	//load flags
	static int _actor=0, _pkmn=1;
	
	@SuppressWarnings("unused")
	public static gameGlobal loadGlobals(gameGlobal g)
	{
		BufferedReader br=null;
		String buffer=null;
		String filebuffer=null;
		
		String cache = new String();
	
		//load each base file into respective database
		for (int x=0; x<5; x++)
		{
			try
			{
				switch (x)
				{
				case 0://baseFile
					filebuffer="src/gameFiles/BaseValues.csv";
					break;
				case 1://attackFile
					filebuffer="src/gameFiles/Attacks.csv";
					break;
				case 2://actorFile
					filebuffer="src/gameFiles/Actors.csv";
					break;
				case 3://sprite sheet
					g.spriteDB.loadPokeSprites("src/gameFiles/spritesheet.png");
					continue;
				case 4:
					g.spriteDB.loadPlayerSprites("src/gameFiles/sprites.png");
					break;
				}
				br = new BufferedReader(new FileReader(filebuffer));
				boolean firstLine=false;
				while ((buffer=br.readLine())!=null)
				{
					//ignore first line of metadata
					if (!firstLine)
					{
						firstLine=true;
						continue;
					}
					
					buffer = new String(buffer.toLowerCase());
						
					switch (x)
					{
					case 0://baseFile
						Pokemon p = new Pokemon (buffer);
						g.pokemonDB.add(p);
						break;
					case 1://attackFile
						Attack a = new Attack (buffer);
						g.attackListDB.add(a);
						break;
					case 2://actorFile
						Player pl = new Player(buffer, g.spriteDB,g);
						g.playerList.add(pl);
						break;
					}
				}
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			} finally {
				if (br!=null)
					try
				{
						br.close();
				}catch (IOException e)
				{
					e.printStackTrace();
				}
				
			}
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