package gameElements;

public class Attack {
	public enum Type{normal, flying, fighting, ground, rock, bug, grass, water, fire, electric, ghost, psychic, dragon, ice, poison};
	
	private int basePP;
	private String name;
	private int accuracy;
	private Type type;
	private int damage;
	private int effect;
	private int basePower;
	private boolean isSpcAtk;

	// creates base attack object (to be referenced only)
	public Attack(String name, int accuracy, Type type, int damage, int basePP, int effect) {
		this.basePP = basePP;
		this.name = name;
		this.accuracy = accuracy;
		this.type = type;
		this.damage = damage;
		this.effect = effect;
	}

	public Type getAttackType(){
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
}
