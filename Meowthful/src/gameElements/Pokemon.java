package gameElements;

import gameElements.Types.Type;
import gameEngine.gameGlobal;

import java.util.ArrayList;
import java.util.Random;

public class Pokemon {

	// create graphic variables (sprite, front, back)
	
	//TODO: add stat multipliers to load file. Use to level pokemon stats

	public String name = new String();

	// stats
	private int baseHealth;
	private int currentHealth;
	private double healthMultiplier;

	private int baseSpeed;
	private int currentSpeed;
	private double speedMultiplier;

	private int baseEvasiveness;
	private int currentEvasiveness;
	private double evasivenessMultiplier;

	private int baseAttack;
	private int currentAttack;
	private double attackMultiplier;

	private int baseDefense;
	private int currentDefense;
	private double defenseMultiplier;

	private int baseSpecialAttack;
	private int currentSpecialAttack;
	private double specialAttackMultiplier;

	private int baseSpecialDefense;
	private int currentSpecialDefense;
	private double specialDefenseMultiplier;

	private int level;
	private int baseStatMultiplier;
	private double currentStatMultiplier;

	private int exp;// current exp overall
	private int nextLevelExp;// countdown to next level
	
	private Types.Type type;
	private Types.ExplicitStatus explicitStatus;
	private Types.ImplicitStatus implicitStatus;
	
	private int number;
	public int id;
	
	public ArrayList<Integer> evolveTo = new ArrayList<Integer>();
	private int evolveLvl;
	
	private ArrayList <Attack> attacks = new ArrayList<Attack>();

	gameGlobal g;
	
	public Pokemon(gameGlobal g) {
		name = new String();

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

		level = 5;
		baseStatMultiplier = 0;
		currentStatMultiplier = 0;

		exp = 0;// current exp overall
		nextLevelExp = 10;// countdown to next level

		type = Types.Type.normal;
		explicitStatus = Types.ExplicitStatus.none;
		implicitStatus = Types.ImplicitStatus.none;	
		
		evolveLvl=0;
		
		number=0;
		id=0;
		
		this.setLevel(level);
	}

	// loads base pokemon. Specific stats must be loaded seperately

	public Pokemon(String params, gameGlobal g) {
		//parse into base values
		//set current to base
		
		String list[] = params.split(",");
		
		if (list.length<16)
			return;
		
		for (int i=0; i<params.length();i++)
		{
			switch(i)
			{
			case 0:
				number=Integer.parseInt(list[i]);
				break;
			case 1:
				name=list[i];
				break;
			case 2:
				baseHealth=Integer.parseInt(list[i]);
				break;
			case 3:
				baseAttack=Integer.parseInt(list[i]);
				break;
			case 4:
				baseDefense=Integer.parseInt(list[i]);
				break;
			case 5:
				baseSpecialAttack=Integer.parseInt(list[i]);
				break;
			case 6:
				baseSpecialDefense=Integer.parseInt(list[i]);
				break;
			case 7:
				baseSpeed=Integer.parseInt(list[i]);
				break;
			case 8:
				healthMultiplier=Double.parseDouble(list[i]);
				break;
			case 9:
				attackMultiplier=Double.parseDouble(list[i]);
				break;
			case 10:
				defenseMultiplier=Double.parseDouble(list[i]);
				break;
			case 11:
				specialAttackMultiplier=Double.parseDouble(list[i]);
				break;
			case 12:
				specialDefenseMultiplier=Double.parseDouble(list[i]);
				break;
			case 13:
				speedMultiplier=Double.parseDouble(list[i]);
				break;
			case 14://handle multi evolution
				String evos[]=list[i].split("\\|");
				if (evos.length>1)
					System.out.println("");
				for (String s:evos)
					evolveTo.add(Integer.parseInt(s));
				break;
			case 15:
				evolveLvl=Integer.parseInt(list[i]);
				break;
			case 16:
				type=Type.getType(Integer.parseInt(list[i]));
				break;
			}
			
			level=5;
			nextLevelExp = (int) Math.pow(100-level, 3)/50;
		}
		
		restoreStats();
		
	}

	public Pokemon (Pokemon pk)
	{	
		if (pk==null)
			return;
		
		System.out.println(pk.name);
		
		name = new String(pk.name);

		baseHealth = pk.baseHealth;
		currentHealth = pk.currentHealth;
		healthMultiplier = pk.healthMultiplier;

		baseSpeed = pk.baseSpeed;
		currentSpeed = pk.currentSpeed;
		speedMultiplier = pk.speedMultiplier;

		baseEvasiveness = pk.baseEvasiveness;
		currentEvasiveness = pk.currentEvasiveness;
		currentEvasiveness = pk.currentEvasiveness;

		baseAttack = pk.baseAttack;
		currentAttack = pk.currentAttack;
		attackMultiplier = pk.attackMultiplier;

		baseDefense = pk.baseDefense;
		currentDefense = pk.currentDefense;
		defenseMultiplier = pk.defenseMultiplier;

		baseSpecialAttack = pk.baseSpecialAttack;
		currentSpecialAttack = pk.currentSpecialAttack;
		specialAttackMultiplier = pk.specialAttackMultiplier;

		baseSpecialDefense = pk.baseSpecialDefense;
		currentSpecialDefense = pk.currentSpecialDefense;
		specialDefenseMultiplier = pk.defenseMultiplier;

		level = pk.level;
		baseStatMultiplier = pk.baseStatMultiplier;
		currentStatMultiplier = pk.currentStatMultiplier;

		exp = pk.exp;// current exp overall
		nextLevelExp = pk.nextLevelExp;// countdown to next level

		type = pk.type;
		explicitStatus = Types.ExplicitStatus.none;
		implicitStatus = Types.ImplicitStatus.none;	
		
		evolveLvl=pk.evolveLvl;
		
		number=pk.number;
		id=pk.id;
		
		setLevel(level);
	}
	
	public Pokemon(String Name, int lvl, gameGlobal gb)
	{
		Pokemon pk = new Pokemon(gb.getPokemon(Name));
		
		name = new String(Name);

		baseHealth = pk.baseHealth;
		currentHealth = pk.currentHealth;
		healthMultiplier = pk.healthMultiplier;

		baseSpeed = pk.baseSpeed;
		currentSpeed = pk.currentSpeed;
		speedMultiplier = pk.speedMultiplier;

		baseEvasiveness = pk.baseEvasiveness;
		currentEvasiveness = pk.currentEvasiveness;
		currentEvasiveness = pk.currentEvasiveness;

		baseAttack = pk.baseAttack;
		currentAttack = pk.currentAttack;
		attackMultiplier = pk.attackMultiplier;

		baseDefense = pk.baseDefense;
		currentDefense = pk.currentDefense;
		defenseMultiplier = pk.defenseMultiplier;

		baseSpecialAttack = pk.baseSpecialAttack;
		currentSpecialAttack = pk.currentSpecialAttack;
		specialAttackMultiplier = pk.specialAttackMultiplier;

		baseSpecialDefense = pk.baseSpecialDefense;
		currentSpecialDefense = pk.currentSpecialDefense;
		specialDefenseMultiplier = pk.defenseMultiplier;

		level = pk.level;
		baseStatMultiplier = pk.baseStatMultiplier;
		currentStatMultiplier = pk.currentStatMultiplier;

		exp = pk.exp;// current exp overall
		nextLevelExp = pk.nextLevelExp;// countdown to next level
		
		type = pk.type;
		explicitStatus = Types.ExplicitStatus.none;
		implicitStatus = Types.ImplicitStatus.none;	
		
		evolveLvl=pk.evolveLvl;
		
		number=pk.number;
		id=pk.id;
		
		setLevel(level);
	}
	
	public void setAttackList(ArrayList <Attack> atL)
	{
		attacks = atL;
	}
	
	public boolean isKO()
	{
		if (currentHealth>0)
			return false;
		return true;
	}
	
	public void evolve()
	{
		
		if (evolveTo.size()==0)
			return;
		Random rand = new Random();
		
		Pokemon tmp = g.getPokemon(rand.nextInt(evolveTo.get(0))+evolveTo.get(evolveTo.size()-1));
		if (tmp==null)
		{
			System.out.println("Evolution failed: invalid evolve-to");
			return;
		}
		
		System.out.println(this.name+" evolved into "+tmp.name);
		
		this.attackMultiplier=tmp.attackMultiplier;
		this.defenseMultiplier=tmp.defenseMultiplier;
		this.specialAttackMultiplier=tmp.specialAttackMultiplier;
		this.specialDefenseMultiplier=tmp.specialDefenseMultiplier;
		this.speedMultiplier=tmp.speedMultiplier;
		this.evasivenessMultiplier=tmp.evasivenessMultiplier;
		this.healthMultiplier=tmp.healthMultiplier;
		
		this.baseAttack=tmp.baseAttack;
		this.baseDefense=tmp.baseDefense;
		this.baseEvasiveness=tmp.baseEvasiveness;
		this.baseHealth=tmp.baseHealth;
		this.baseSpecialAttack=tmp.baseSpecialAttack;
		this.baseSpecialDefense=tmp.baseSpecialDefense;
		this.baseSpeed=tmp.baseSpeed;
		
		this.currentAttack=(this.currentAttack+this.baseAttack)/2;
		this.currentDefense=(this.currentDefense+this.baseDefense)/2;
		this.currentEvasiveness=(this.currentEvasiveness+this.baseEvasiveness)/2;
		this.currentHealth=(this.currentHealth+this.baseHealth)/2;
		this.currentSpecialAttack=(this.currentSpecialAttack+this.baseSpecialAttack)/2;
		this.currentSpecialDefense=(this.currentSpecialDefense+this.baseSpecialDefense)/2;
		this.currentSpeed=(this.currentSpeed+this.baseSpeed)/2;
		
		this.evolveLvl=tmp.evolveLvl;
		this.evolveTo=new ArrayList<Integer>(tmp.evolveTo);
		
		this.name= new String(tmp.name);
		
		this.number=tmp.number;
	}
		
	public void assignId(int ID)
	{
		id=ID;
	}
	
	public int getAttackIndex(String s)
	{
		for (int x=0; x<attacks.size(); x++)
			if (s.equals(attacks.get(x).name))
				return x;
		return -1;
	}
	
	public ArrayList <Attack> getAttackList()
	{
		return attacks;
	}
	
	public Attack getAttack (int index)
	{
		if (index<0 || index>attacks.size())
			return null;
		return attacks.get(index);
	}
	
	//levels input base pokemon to  
	public void generateAtLevel(String name, int level, Pokemon p)
	{
		
	}
	
	public void restoreCondition()
	{
		explicitStatus = Types.ExplicitStatus.none;
		implicitStatus = Types.ImplicitStatus.none;	
	}
	
	public void restoreAllPP()
	{
		for (int x=0; x<attacks.size(); x++)
		{
			Attack a = attacks.get(x);
			a.resetCurrentPP();
		}
	}
	
	public void restoreStats()
	{
		currentHealth = baseHealth;

		currentSpeed = baseSpeed;

		currentEvasiveness = baseEvasiveness;

		currentAttack = baseAttack;

		currentDefense = baseDefense;

		currentSpecialAttack = baseSpecialAttack;

		currentSpecialDefense = baseSpecialDefense;

		explicitStatus = Types.ExplicitStatus.none;
		implicitStatus = Types.ImplicitStatus.none;		
		
		restoreAllPP();
	}
	
	public void restoreHealth()
	{
		currentHealth=baseHealth;
	}
	
	public int getNumber()
	{
		return number;
	}

	public String getName() {
		return name;
	}

	public int getBaseHealth(){
		return baseHealth;
	}
	
	public void setBaseHealth(int value){
		baseHealth = value;
	}
	
	public void modBaseHealth(int value){
		baseHealth += value;
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
	
	public int getEvasiveness(){
		return currentEvasiveness;
	}
	
	public void setEvasiveness(int value){
		currentEvasiveness = value;
		if(currentEvasiveness < 0) currentEvasiveness = 0;
	}
	
	public void modEvasiveness(int value){
		currentEvasiveness += value;
		if(currentEvasiveness < 0) currentEvasiveness = 0;
	}
	
	public void resetEvasiveness(){
		currentEvasiveness = baseEvasiveness;
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
	
	public void setType(Types.Type newType){
		type = newType;
	}
	
	public Types.ExplicitStatus getExplicitStatus(){
		return explicitStatus;
	}
	
	public void setExplicitStatus(Types.ExplicitStatus newStatus){
		explicitStatus = newStatus;
	}
	
	public void removeExplicitStatusEffect(){
		explicitStatus = Types.ExplicitStatus.none;
	}
	
	public Types.ImplicitStatus getImplicitStatus(){
		return implicitStatus;
	}
	
	public void setImplicitStatus(Types.ImplicitStatus newStatus){
		implicitStatus = newStatus;
	}
	
	public void removeImplicitStatusEffect(){
		implicitStatus = Types.ImplicitStatus.none;
	}

	public int getLevel() {
		return level;
	}

	public void levelUp()
	{
		setLevel(level+1);
		
	}
	
	public void setLevel(int value) {
		level = value;
		if(level < 0) level = 0;
		
		baseHealth = (int) ((baseHealth * healthMultiplier) + 1);
		baseSpeed = (int) ((baseSpeed *speedMultiplier)+1);
		baseEvasiveness = (int) ((baseEvasiveness * evasivenessMultiplier)+1);
		baseAttack = (int) ((baseAttack * attackMultiplier) + 1);
		baseDefense = (int) ((baseDefense * defenseMultiplier)+1);
		baseSpecialAttack = (int) ((baseSpecialAttack * specialAttackMultiplier)+1);
		baseSpecialDefense = (int) ((baseSpecialDefense * specialDefenseMultiplier)+1);
	}
	
	public void modLevel(int value){
		level += value;
		if(level < 0) level = 0;
	}
	
	public void resetLevel(){
		setLevel(5);
	}
	
	public int getEXP(){
		return exp;
	}
	
	public void increaseEXP(int value)
	{
		exp+=value;
	
		System.out.println(name+" gained " + value + " experience");
		
		if (value>nextLevelExp)
		{
			while (value/nextLevelExp>0)
			{
				levelUp();
				value-=nextLevelExp;
				System.out.println(name+" increased to level "+level);
				
				if (level<=50)
				{
					nextLevelExp = (int) Math.pow(100-level, 3)/50;
				}else if (level<=68)
				{
					nextLevelExp = (int) Math.pow(150-level, 3)/100;
				}else if (level<=98)
				{
					nextLevelExp = (int) Math.pow((1911-(10*level))/3, 3);
				}else if (level<=100)
				{
					nextLevelExp = (int) Math.pow(160-level, 3)/100;	
				}
			}
			
			if (evolveLvl<=level)
			{
				evolve();
			}
		}
		
		nextLevelExp-=value;
		
	}
	
	public void setEXP(int value){
		exp = value;
		if(exp < 0) exp = 0;
	}
	
	public void modEXP(int value){
		exp += value;
		if(exp < 0) exp = 0;
	}
	
	public void resetEXP(){
		exp = 0;
	}
	
	public int getNextLevelEXP(){
		return nextLevelExp;
	}
	
	public void setNextLevelEXP(int value){
		nextLevelExp = value;
		if(nextLevelExp <= exp) nextLevelExp = exp + 1;
	}
	
	public int getEXPToNextLevel(){
		return nextLevelExp - exp;
	}
}
