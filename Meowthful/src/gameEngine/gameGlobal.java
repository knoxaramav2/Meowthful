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
		Pokemon p = new Pokemon(this);
		for (int x=0; x<pokemonDB.size(); x++)
		{
			p=(Pokemon)pokemonDB.get(x);
			if (p.name.toLowerCase().equals(name.toLowerCase()))
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
				return (Player)playerList.get(x);
			}
		}
		return null;
	}
	
	public boolean isPlayerDefined(int id)
	{
		for (int x=0; x<playerList.size(); x++)
		if (playerList.get(x).id==id)
			return true;
		return false;
	}
	
	public int getPlayerCount(){
		return playerList.size();
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

	public Pokemon getPokemon(int i) {
		
		for (int x=0; x<pokemonDB.size(); x++)
			if (pokemonDB.get(x).getNumber()==i)
				return pokemonDB.get(x);
		
		return null;
	}

	public Sprites getSpriteSheets()
	{
		return spriteDB;
	}

	public ArrayList <Attack> getAttackList()
	{
		return attackListDB;
	}
}
