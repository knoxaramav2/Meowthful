package gameElements;

import java.util.ArrayList;

public class Pokemon {
	
	//create graphic variables (sprite, front, back)

	public String name = new String();
	
	//stats
	int baseHealth;
	int currentHealth;
	
	int baseSpeed;
	int currentSpeed;
	
	int baseEvasiveness;
	int currentEvasiveness;
	
	int baseAttack;
	int currentAttack;
	
	int baseDefense;
	int currentDefense;
	
	int baseSpecialAttack;
	int currentSpecialAttack;
	
	int baseSpecialDefense;
	int currentSpecialDefense;
	
	int level;
	int baseStatMultiplier;
	int statMultiplier;
	
	int exp;//current exp overall
	int nextLevelExp;// countdown to next level
	int expToNextLevel;//current amount to reach to next level
	
	Attack.Type type;
	
	ArrayList attacks = new ArrayList();
	
	
	public Pokemon()
	{
		String name = new String();
		
		int baseHealth=0;
		int currentHealth=0;
		
		int baseSpeed=0;
		int currentSpeed=0;
		
		int baseEvasiveness=0;
		int currentEvasivess=0;
		
		int baseAttack=0;
		int currentAttack=0;
		
		int baseDefense=0;
		int currentDefense=0;
		
		int baseSpecialAttack=0;
		int currentSpecialAttack=0;
		
		int baseSpecialDefense=0;
		int currentSpecialDefense=0;
		
		int level=0;
		int baseStatMultiplier=0;
		int statMultiplier=0;
		
		int exp=0;//current exp overall
		int nextLevelExp=0;// countdown to next level
		int ExpToNextLevel=0;//current amount to reach to next level
		
		int type=0;
	}
	
	//loads base pokemon. Specific stats must be loaded seperately
	public Pokemon(String name, int baseHealth, int baseAttack, int baseDefense, int baseSpeed,
			int baseEvasiveness, int baseSpecialAttack, int baseSpecialDefense,
			int ExpToNextLevel, int baseStatMultiplier)
	{
		this.name=name;
		
		this.baseHealth=baseHealth;
		this.baseAttack=baseAttack;
		this.baseDefense=baseDefense;
		this.baseEvasiveness=baseEvasiveness;
		this.baseSpecialAttack=baseSpecialAttack;
		this.baseSpecialDefense=baseSpecialDefense;
		this.baseSpeed=baseSpeed;
		this.baseStatMultiplier=baseStatMultiplier;
	}
	
	//create specific pokemon instance from name and specific stats
	public Pokemon (String name, ArrayList pokeList, ArrayList attacks, ArrayList attackList,
			String baseStats, String currentStats)
	{
	for (int x=0; x<pokeList.size(); x++)
	{
		Pokemon p = (Pokemon) pokeList.get(x);
		if (p.name==name)
		{
			//check attack list against attack database
			for (int y=0; y<attackList.size(); y++)
			{
				Attack atk = (Attack) attackList.get(y);
				for (int z=0; z<attacks.size(); z++)
				{
					String atkStr = (String) attacks.get(z);
					
					if (atkStr.equals(atk.getName()))
						this.attacks.add(atk);//load attack into pokemon
				}
			}
				
			
			break;
		}
	}
	}
	
	public Attack.Type getType(){
		return type;
	}
	
	public int getSpeed(){
		return currentSpeed;
	}
	
	public int getHealth(){
		return currentHealth;
	}
	
	public void setHealth(int value){
		currentHealth = value;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getSpecialAttack(){
		return currentSpecialAttack;
	}
	
	public int getSpecialDefense(){
		return currentSpecialDefense;
	}
	
	public int getAttack(){
		return currentAttack;
	}
	
	public int getDefense(){
		return currentDefense;
	}
}
