package gameEngine;

import java.util.*;
import gameElements.*;


//stores global databases of base objects
public class gameGlobal {

	ArrayList pokemonDB = new ArrayList();
	ArrayList attackListDB = new ArrayList();
	ArrayList playerList = new ArrayList();
	
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
	
	public Player getPlayer()
	{
		return null;
	}
	
	public attack getAttack()
	{
		return null;	
	}
}
