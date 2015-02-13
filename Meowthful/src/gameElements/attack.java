package gameElements;

public class attack {

	int basePP;
	String name;
	int type;
	int damage;
	int effect;
	
	
	//creates base attack object (to be referenced only)
	public attack(String name, int type, int damage, int basePP, int effect)
	{
		this.basePP=basePP;
		this.name=name;
		this.type=type;
		this.damage=damage;
		this.effect=effect;
	}
	
}
