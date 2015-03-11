package gameEngine;

import java.util.*;

import gameElements.*;

//stores global databases of base objects
public class gameGlobal {
	
	
	ArrayList <Pokemon> pokemonDB = new ArrayList<Pokemon>();
	ArrayList <Attack> attackListDB = new ArrayList<Attack>();
	ArrayList <Player> playerList = new ArrayList<Player>();
	Sprites spriteDB = new Sprites();
	
	//retrieve copy of base pokemon
	public Pokemon getPokemon(String name)
	{
		Pokemon p = new Pokemon();
		for (int x=0; x<pokemonDB.size(); x++)
		{
			p=(Pokemon)pokemonDB.get(x);
			if (p.name.equals(name))
			{
				return p;
			}
		}
		return null;
	}
	
	public Player getPlayer(String name)
	{
		Player p = null;
		for (int x=0; x<playerList.size(); x++)
		{
			p=(Player)playerList.get(x);
			if (p.name.equals(name))
			{
				return p;
			}
		}
		return null;
	}
	
	public Player getPlayer(int id)
	{
		Player p = null;
		for (int x=0; x<playerList.size(); x++)
		{
			p=(Player)playerList.get(x);
			if (p.id==id)
			{
				return p;
			}
		}
		return null;
	}
	
	public Attack getAttack(String name)
	{
		Attack p = null;
		for (int x=0; x<attackListDB.size(); x++)
		{
			p=attackListDB.get(x);
			if (p.name.equals(name))
			{
				return p;
			}
		}
		return null;	
	}
}
