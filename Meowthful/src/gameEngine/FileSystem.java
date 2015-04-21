package gameEngine;

import gameElements.Attack;
import gameElements.Player;
import gameElements.Pokemon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.Calendar;

import scriptEngine.unownInterpreter;

//loads and saves different file types into memory
public class FileSystem {
	File a;
	FileReader in;
	
	//load flags
	final static int _actor=0, _pkmn=1, _meta=2;
	
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
				case 0://sprite sheet
					g.spriteDB.loadPokeSprites("core/spritesheet.png");
					continue;
				case 1:
					g.spriteDB.loadPlayerSprites("core/sprites.png");
					continue;
				case 2://baseFile
					filebuffer="core/BaseValues.csv";
					break;
				case 3://attackFile
					filebuffer="core/Attacks.csv";
					break;
				case 4://actorFile
					filebuffer="core/Actors.csv";
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
					case 2://baseFile
						Pokemon p = new Pokemon (buffer,g);
						g.pokemonDB.add(p);
						break;
					case 3://attackFile
						Attack a = new Attack (buffer);
						g.attackListDB.add(a);
						break;
					case 4://actorFile
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

	public static boolean loadGame(gameGlobal g, String file, unownInterpreter u)
	{	
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line = new String();
			int mode=0;
			
			while ((line=br.readLine())!=null)
			{
				if (line.length()==0)
					continue;
				
				//check for regions
				if (line.equals("Meta~"))
					mode=_meta;
				else if (line.equals("Actors~"))
					mode=_actor;
				else if (line.equals("~Meta") || line.equals("~Actors"))
					mode=0;
				
				else switch (mode)
				{
				case _meta:
					
					break;
				case _actor:
					Player p = new Player(line, g.spriteDB, g);
					g.playerList.add(p);
					break;
				}
					
			}
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}

	public static boolean saveGame(gameGlobal g, String file)
	{
		try {
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			
			writer.println("Meta~");//begin meta block
			writer.println(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()).toString());//time stamp
			writer.println("~Meta");//end meta block
			writer.println("Actors~");//begin actor block
			
			for (Player p:g.playerList)
			{
				writer.print(p.id+",");
				writer.print(p.name+",");
				switch(p.rank)
				{
				case 0:writer.print("grunt,");break;
				case 1:writer.print("private,");break;
				case 2:writer.print("general,");break;
				case 3:writer.print("executive,");break;
				case 4:writer.print("admin,");break;
				case 5:writer.print("leader,");break;
				}
				
				int pkmn=0;
				for (Pokemon k:p.party)
				{
					writer.print(k.name+":");
					writer.print(k.getLevel()+":");
					boolean first=true;
					for (Attack a:k.getAttackList())
					{
						if (first)
							first=false;
						else
							writer.print("|");
						writer.print(a.name+"%");
						writer.print(a.getCurrentPP());
					}
					writer.print(",");
					pkmn++;
				}
				for (int i=pkmn; i<6; i++)
					writer.print("n/a,");
				
				writer.print(p.level+",");
				writer.print(p.money+",");
				writer.print(p.trainer+",");
				writer.print(p.map+",");
				writer.print(p.AI+",");
				writer.print(p.orientation+",");
				writer.print(p.coolDown+",");
				writer.println(p.type);
			}
			
			writer.println("~Actors");//end actor block
			
			writer.close();
		} catch (FileNotFoundException e) {
			return false;
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		
		
		return true;
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