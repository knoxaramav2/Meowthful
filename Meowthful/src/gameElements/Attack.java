package gameElements;

import gameElements.Types.Type;

public class Attack {	
	public String 				name;
	public String				desc;
	private Types.Type 			type;
	private Types.AttackEffects effect;
	private int 				basePP;
	private int 				currentPP;
	private int 				baseAccuracy;
	private int 				currentAccuracy;
	private int 				baseDamage;
	private int 				currentDamage;
	private int					cooldownTimer;
	private int					currentCooldown;
	private double  			baseFlinchProbability;
	private double  			currentFlinchProbability;
	private boolean 			isSpcAtk;
	boolean						explicitEffect;
	private int					priority;

	// creates base attack object (to be referenced only)
	public Attack(String params) {
String list[] = params.split(",");
		
		for (int i=1; i<params.length()-1;i++)
		{
			switch(i)
			{
			case 0:
				name=list[i];
				break;
			case 1:
				type=Type.getType(Integer.parseInt(list[i]));
				break;
			case 2:
				baseDamage=Integer.parseInt(list[i]);
				break;
			case 3: 
				baseAccuracy=Integer.parseInt(list[i]);
				break;
			case 4:
				basePP=Integer.parseInt(list[i]);
				break;
			case 5:
				if (list[i]=="exp")
					explicitEffect=true;
				else
					explicitEffect=false;
				break;
			case 6:
					effect=Types.AttackEffects.getType(Integer.parseInt(list[i]));
				break;
			case 7:
				priority=Integer.parseInt(list[i]);
				break;
			case 8:
				desc=list[i];
				break;
			}
		}
	}

	public String getName(){
		return name;
	}
	
	public Types.Type getAttackType(){
		return type;
	}
	
	public void setAttackType(Types.Type newType){
		type = newType;
	}
	
	public int getBasePP(){
		return basePP;
	}
	
	public int getCurrentPP(){
		return currentPP;
	}
	
	public void setCurrentPP(int value){
		currentPP = (value > basePP) ? basePP : (value < 0) ? 0 : value;
	}
	
	public void modCurrentPP(int value){
		currentPP = (currentPP + value > basePP) ? basePP : (currentPP + value < 0) ? 0 : currentPP + value;
	}
	
	public void resetCurrentPP(){
		currentPP = basePP;
	}
	
	public int getAccuracy(){
		return currentAccuracy;
	}
	
	public void setAccuracy(int value){
		currentAccuracy = (value > 100) ? 100 : (value < 0) ? 0 : value;
	}
	
	public void modAccuracy(int value){
		currentAccuracy = (currentAccuracy + value > 100) ? 100 : (currentAccuracy + value < 0) ? 0 : currentAccuracy + value;
	}
	
	public void resetAccuracy(){
		currentAccuracy = baseAccuracy;
	}
	
	public int getDamage(){
		return currentDamage;
	}
	
	public void setDamage(int value){
		currentDamage = (value < 0) ? 0 : value;
	}
	
	public void modDamage(int value){
		currentDamage = (currentDamage + value < 0) ? 0 : currentDamage + value;
	}
	
	public void resetDamage(){
		currentDamage = baseDamage;
	}
	
	public Types.AttackEffects getEffect(){
		return effect;
	}
	
	public void setEffect(Types.AttackEffects newEffect){
		effect = newEffect;
	}
	
	public boolean isSpecialAttack(){
		isSpcAtk = calculateIsSpecialAttack();
		return isSpcAtk;
	}
	
	public int getBasePower(){
		return baseDamage;
	}
	
	public double getFlinchProbability(){
		return currentFlinchProbability;
	}
	
	public void setFlinchProbability(double value){
		currentFlinchProbability = (value > 1.0) ? 1.0 : (value < 0.0) ? 0.0 : value;
	}
	
	public void modFlinchProbability(double value){
		currentFlinchProbability = (currentFlinchProbability + value > 1.0) ? 1.0 : (currentFlinchProbability + value < 0.0) ? 0.0 : currentFlinchProbability + value;
	}
	
	public void resetFlinchProbability(){
		currentFlinchProbability = baseFlinchProbability;
	}
	
	public boolean canAttack(){
		return (currentCooldown == 0 && currentPP > 0);
	}
	
	public void activateCooldownTimer(){
		currentCooldown = cooldownTimer;
	}
	
	public void decreaseCooldown(){
		currentCooldown = (currentCooldown > 0) ? currentCooldown - 1 : 0;
	}
	
	public int getCurrentCooldown(){
		return currentCooldown;
	}
	
	private boolean calculateIsSpecialAttack(){
		return (type == Types.Type.water || type == Types.Type.grass || type == Types.Type.fire || type == Types.Type.ice || type == Types.Type.electric || type == Types.Type.psychic || type == Types.Type.dragon);
	}
}
