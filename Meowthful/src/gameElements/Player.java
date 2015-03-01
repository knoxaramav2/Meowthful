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
			case 3://load party data
				String partyList[] = list[i].split("|");
				for (int l=0; l<partyList.length-1; l++)
				{
					//get base pokemon and level
					String pokeParam[] = partyList[l].split(":");
					Pokemon temp= new Pokemon();
					
					temp.generateAtLevel(pokeParam[0], Integer.parseInt(pokeParam[1]), temp);
					party.add(temp);
				}
				break;
			case 4:
				level=Integer.parseInt(list[i]);
				break;
			case 5:
				money=Integer.parseInt(list[i]);
				break;
			case 6:
				if (Integer.parseInt(list[i])>0)
					trainer=true;
				else
					trainer=false;
				break;
			case 7:
				//load items
				break;
			case 8:
				map=Integer.parseInt(list[i]);
				break;
			case 9:
				String coord[] = list[i].split("|");
				posx=Integer.parseInt(coord[0]);
				posy=Integer.parseInt(coord[1]);
				break;
			case 10:
				AI=Integer.parseInt(list[i]);
				break;
			case 11:
				orientation=Integer.parseInt(list[i]);
				break;
			case 12:
				coolDown=Integer.parseInt(list[i]);
				break;
			}
		}
	}

}
