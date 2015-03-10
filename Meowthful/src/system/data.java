package system;

import gameElements.Map;
import gameElements.Pokemon;


//random comment
public class data {
	public data()
	{
		active=true;
	}
	
	public boolean active;
	//active player battle pokemon
	Pokemon playerActive;
	Pokemon enemyActive;
	//active map
	Map current;
	
}
