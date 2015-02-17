package gameElements;

import java.util.ArrayList;

public class Pokemon {

	// create graphic variables (sprite, front, back)

	public String name = new String();

	// stats
	private int baseHealth;
	private int currentHealth;

	private int baseSpeed;
	private int currentSpeed;

	private int baseEvasiveness;
	private int currentEvasiveness;

	private int baseAttack;
	private int currentAttack;

	private int baseDefense;
	private int currentDefense;

	private int baseSpecialAttack;
	private int currentSpecialAttack;

	private int baseSpecialDefense;
	private int currentSpecialDefense;

	private int level;
	private int baseStatMultiplier;
	private int statMultiplier;

	private int exp;// current exp overall
	private int nextLevelExp;// countdown to next level
	private int expToNextLevel;// current amount to reach to next level

	private int currentWeight;
	
	private Types.Type type;
	private Types.ExplicitStatus explicitStatus;
	private Types.ImplicitStatus implicitStatus;
	
	ArrayList attacks = new ArrayList();

	public Pokemon() {
		String name = new String();

		baseHealth = 0;
		currentHealth = 0;

		baseSpeed = 0;
		currentSpeed = 0;

		baseEvasiveness = 0;
		currentEvasiveness = 0;

		baseAttack = 0;
		currentAttack = 0;

		baseDefense = 0;
		currentDefense = 0;

		baseSpecialAttack = 0;
		currentSpecialAttack = 0;

		baseSpecialDefense = 0;
		currentSpecialDefense = 0;

		level = 0;
		baseStatMultiplier = 0;
		statMultiplier = 0;

		exp = 0;// current exp overall
		nextLevelExp = 0;// countdown to next level
		expToNextLevel = 0;// current amount to reach to next level

		type = Types.Type.normal;
		explicitStatus = Types.ExplicitStatus.none;
		implicitStatus = Types.ImplicitStatus.none;
		
		currentWeight = 0;
	}

	// loads base pokemon. Specific stats must be loaded seperately
	public Pokemon(String name, int baseHealth, int baseAttack,
			int baseDefense, int baseSpeed, int baseEvasiveness,
			int baseSpecialAttack, int baseSpecialDefense, int ExpToNextLevel,
			int baseStatMultiplier) {
		this.name = name;

		this.baseHealth = baseHealth;
		this.baseAttack = baseAttack;
		this.baseDefense = baseDefense;
		this.baseEvasiveness = baseEvasiveness;
		this.baseSpecialAttack = baseSpecialAttack;
		this.baseSpecialDefense = baseSpecialDefense;
		this.baseSpeed = baseSpeed;
		this.baseStatMultiplier = baseStatMultiplier;
	}

	// create specific pokemon instance from name and specific stats
	public Pokemon(String name, ArrayList pokeList, ArrayList attacks,
			ArrayList attackList, String baseStats, String currentStats) {
		for (int x = 0; x < pokeList.size(); x++) {
			Pokemon p = (Pokemon) pokeList.get(x);
			if (p.name == name) {
				// check attack list against attack database
				for (int y = 0; y < attackList.size(); y++) {
					Attack atk = (Attack) attackList.get(y);
					for (int z = 0; z < attacks.size(); z++) {
						String atkStr = (String) attacks.get(z);

						if (atkStr.equals(atk.getName()))
							this.attacks.add(atk);// load attack into pokemon
					}
				}

				break;
			}
		}
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return currentHealth;
	}

	public void setHealth(int value) {
		currentHealth = value;
		if (currentHealth < 0) currentHealth = 0;
		if (currentHealth > baseHealth) currentHealth = baseHealth;
	}
	
	public void modHealth(int value){
		currentHealth += value;
		if (currentHealth < 0) currentHealth = 0;
		if (currentHealth > baseHealth) currentHealth = baseHealth;
	}
	
	public void resetHealth(){
		currentHealth = baseHealth;
	}

	public int getAttack() {
		return currentAttack;
	}

	public void setAttack(int value) {
		currentAttack = value;
		if (currentAttack < 0) currentAttack = 0;
	}
	
	public void modAttack(int value){
		currentAttack += value;
		if (currentAttack < 0) currentAttack = 0;
	}

	public void resetAttack(){
		currentAttack = baseAttack;
	}

	public int getDefense() {
		return currentDefense;
	}

	public void setDefense(int value) {
		currentDefense = value;
		if (currentDefense< 0) currentDefense= 0;
	}

	public void modDefense(int value){
		currentDefense += value;
		if (currentDefense< 0) currentDefense= 0;
	}
	
	public void resetDefense(){
		currentDefense = baseDefense;
	}
	
	public int getSpecialAttack() {
		return currentSpecialAttack;
	}

	public void setSpecialAttack(int value) {
		currentSpecialAttack = value;
		if (currentSpecialAttack < 0) currentSpecialAttack = 0;
	}
	
	public void modSpecialAttack(int value){
		currentSpecialAttack += value;
		if (currentSpecialAttack < 0) currentSpecialAttack = 0;
	}

	public void resetSpecialAttack(){
		currentSpecialAttack = baseSpecialAttack;
	}
	
	public int getSpecialDefense() {
		return currentSpecialDefense;
	}

	public void setSpecialDefense(int value) {
		currentSpecialDefense = value;
		if (currentSpecialDefense < 0) currentSpecialDefense = 0;
	}
	
	public void modSpecialDefense(int value){
		currentSpecialDefense += value;
		if (currentSpecialDefense < 0) currentSpecialDefense = 0;
	}

	public void resetSpecialDefense(){
		currentSpecialDefense = baseSpecialDefense;
	}
	
	public int getSpeed() {
		return currentSpeed;
	}

	public void setSpeed(int value) {
		currentSpeed = value;
		if (currentSpeed < 0) currentSpeed = 0;
	}
	
	public void modSpeed(int value){
		currentSpeed += value;		
		if (currentSpeed < 0) currentSpeed = 0;
	}

	public void resetSpeed(){
		currentSpeed = baseSpeed;
	}
	
	public Types.Type getType() {
		return type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int value) {
		level = value;
		if(level < 0) level = 0;
	}
	
	public void modLevel(int value){
		level += value;
		if(level < 0) level = 0;
	}
	
	public int getWeight(){
		return currentWeight;
	}

	public void setWeight(int value){
		currentWeight = value;
		if(currentWeight < 0) currentWeight = 0;
	}

	public void modWeight(int value){
		currentWeight += value;
		if(currentWeight < 0) currentWeight = 0;
	}
}
