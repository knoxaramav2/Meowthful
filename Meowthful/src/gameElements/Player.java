package gameElements;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Player {
	//Player, AI
	
	public String name;
	public int money;
	public int rank;
	public int level;
	public ArrayList <Pokemon> party = new  ArrayList <Pokemon> ();
	
	public int posx, posy;
	public int map;
	public int orientation;
	
	public boolean controllable;
	public boolean trainer;
	public int AI;
	public int coolDown;
	
	public int id;
	
	public Player(String params)
	{
		
		
		//Parse from line into base
String list[] = params.split(",");
		
		for (int i=1; i<list.length-1;i++)
		{
			switch(i)
			{
			case 0:
				id=Integer.parseInt(list[i]);
				break;
			case 1:
				name=list[i];
				break;
			case 2:
				if (list[i].equals("grunt"))
					rank=0;
				if (list[i].equals("Private"))
					rank=1;
				if (list[i].equals("General"))
					rank=2;
				if (list[i].equals("Executive"))
					rank=3;
				if (list[i].equals("Admin"))
					rank=4;
				if (list[i].equals("Leader"))
					rank=5;
				break;
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				//load party data
				//1:pokemon name
				//2:pokemon level
				//3:pokemon health
				//4:pokemon explicit status
				//5:attack list
				//	1:attack name
				//	2:attack pp
					//get base pokemon and level
				
				//skip empty slot
				if (list[i]=="n/a")
					continue;
				
				String partyList[] = list[i].split("\\|");
				
				for (int loop=0; loop<partyList.length; loop++)
				{
					String pokeParam[] = partyList[loop].split(":");//individual parameters
					
					for (int j=0; j<pokeParam.length; j++)
						party.add(new Pokemon(pokeParam[j]));
				}
				
				break;
			case 9:
				level=Integer.parseInt(list[i]);
				break;
			case 10:
				money=Integer.parseInt(list[i]);
				break;
			case 11:
				if (Integer.parseInt(list[i])>0)
					trainer=true;
				else
					trainer=false;
				break;
			case 12:
				//load items
				break;
			case 13:
				map=Integer.parseInt(list[i]);
				break;
			case 14:
				String coord[] = list[i].split("\\|");
				posx=Integer.parseInt(coord[0]);
				posy=Integer.parseInt(coord[1]);
				break;
			case 15:
				AI=Integer.parseInt(list[i]);
				break;
			case 16:
				orientation=Integer.parseInt(list[i]);
				break;
			case 17:
				coolDown=Integer.parseInt(list[i]);
				break;
			}
		}
	}

}
