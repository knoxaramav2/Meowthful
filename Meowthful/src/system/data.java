package system;

import gameElements.Map;
import gameElements.Player;
import gameElements.Pokemon;

import java.util.ArrayList;


//random comment
public class data {
	public data()
	{
		active=true;
		playerActive=null;
		enemyActive =null;
		current=null;
		player=null;
		lastResult=null;
		variables=null;
	}
	
	public String getVar(String name)
	{
		for (String res:variables)
			if (name.equals(res))
				return res;
		
		return null;
	}
	
	public boolean active;
	//active player battle pokemon
	public Pokemon playerActive;
	public Pokemon enemyActive;
	//active map
	public Map current;
	public Player player;
	//system caches
	public String lastResult;
	public ArrayList <String> variables;
}
