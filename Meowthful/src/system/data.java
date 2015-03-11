package system;

import gameElements.Map;
import gameElements.Player;
import gameElements.Pokemon;


//random comment
public class data {
	public data()
	{
		active=true;
	}
	
	public boolean active;
	//active player battle pokemon
	public Pokemon playerActive;
	public Pokemon enemyActive;
	//active map
	public Map current;
	public Player player;
}
