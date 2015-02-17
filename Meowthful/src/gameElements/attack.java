package gameElements;

public class Attack {	
	private int basePP;
	private String name;
	private int accuracy;
	private Types.Type type;
	private int damage;
	private int effect;
	private int basePower;
	private boolean isSpcAtk;
	private double flinchProbability;

	// creates base attack object (to be referenced only)
	public Attack(String name, int accuracy, Types.Type type, int damage, int basePP, int effect, double flinchProbability) {
		this.basePP = basePP;
		this.name = name;
		this.accuracy = accuracy;
		this.type = type;
		this.damage = damage;
		this.effect = effect;
		this.flinchProbability = flinchProbability;
	}

	public Types.Type getAttackType(){
		return type;
	}
	
	public int getBasePP(){
		return basePP;
	}
	
	public String getName(){
		return name;
	}
	
	public int getAccuracy(){
		return accuracy;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public int getEffect(){
		return effect;
	}
	
	public boolean isSpecialAttack(){
		return isSpcAtk;
	}
	
	public int getBasePower(){
		return basePower;
	}
	
	public double getFlinchProbability(){
		return flinchProbability;
	}
}
