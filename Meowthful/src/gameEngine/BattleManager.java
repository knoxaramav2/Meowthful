package gameEngine;

import java.util.Random;
import gameElements.Pokemon;
import gameElements.Attack;

public class BattleManager {
	private Pokemon attacker;
	private Pokemon defender;
	private Attack  attack;
	private Random  rand;
	private int 	damageApplied;
	
	public BattleManager(Pokemon attacker, Pokemon defender, Attack attack){
		this.attacker = attacker;
		this.defender = defender;
		this.attack = attack;
		damageApplied = 0;
		rand = new Random();
	}
	
	public void CalculateDamage(){
		float damageMultiplier = 1.0f;
		int   attackPower = 0;
		int   defenseStrength = 0;
		
		switch(attack.getAttackType()){
		case normal:
			if(defender.getType() == Attack.Type.rock) 		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.ghost)		damageMultiplier = 0.0f;
			break;
			
		case fighting:
			if(defender.getType() == Attack.Type.normal) 	damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.rock)   	damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.ice)	 	damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.flying) 	damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.poison) 	damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.bug)	 	damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.psychic)	damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.ghost)	 	damageMultiplier = 0.0f;			
			break;
			
		case flying:
			if(defender.getType() == Attack.Type.fighting)	damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.rock)	  	damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.bug)	  	damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.grass)	  	damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.electric)	damageMultiplier = 0.5f;
			break;
			
		case poison:
			if(defender.getType() == Attack.Type.poison)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.ground)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.rock)  		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.ghost) 		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.grass) 		damageMultiplier = 2.0f;
			break;
			
		case ground:
			if(defender.getType() == Attack.Type.flying)  	damageMultiplier = 0.0f;
			if(defender.getType() == Attack.Type.poison)  	damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.rock)	  	damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.bug)	  	damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.fire)	  	damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.electric)	damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.grass)   	damageMultiplier = 0.5f;
			break;
			
		case rock:
			if(defender.getType() == Attack.Type.fighting)	damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.flying)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.ground)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.bug)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.fire)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.ice)		damageMultiplier = 2.0f;
			break;
			
		case bug:
			if(defender.getType() == Attack.Type.fighting)	damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.flying)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.poison)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.ghost)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.fire)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.grass)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.psychic)	damageMultiplier = 2.0f;
			break;
			
		case ghost:
			if(defender.getType() == Attack.Type.normal)		damageMultiplier = 0.0f;
			if(defender.getType() == Attack.Type.ghost)  	damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.psychic)	damageMultiplier = 2.0f;
			break;
			
		case fire:
			if(defender.getType() == Attack.Type.rock)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.bug)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.fire)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.water)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.grass)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.ice)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.dragon)		damageMultiplier = 0.5f;
			break;
			
		case water:
			if(defender.getType() == Attack.Type.ground)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.rock)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.fire)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.water)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.grass)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.dragon)		damageMultiplier = 0.5f;
			break;
			
		case grass:
			if(defender.getType() == Attack.Type.flying)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.poison)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.ground)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.rock)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.bug)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.fire)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.water)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.grass)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.dragon)		damageMultiplier = 0.5f;
			break;
			
		case electric:
			if(defender.getType() == Attack.Type.flying)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.ground)		damageMultiplier = 0.0f;
			if(defender.getType() == Attack.Type.water)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.grass)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.electric)	damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.dragon)		damageMultiplier = 0.5f;
			break;
			
		case psychic:
			if(defender.getType() == Attack.Type.fighting)	damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.poison)  	damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.psychic) 	damageMultiplier = 0.5f;
			break;
			
		case ice:
			if(defender.getType() == Attack.Type.flying)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.ground)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.fire)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.water)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.grass)		damageMultiplier = 2.0f;
			if(defender.getType() == Attack.Type.ice)		damageMultiplier = 0.5f;
			if(defender.getType() == Attack.Type.dragon)		damageMultiplier = 2.0f;
			break;
			
		case dragon:
			if(defender.getType() == Attack.Type.dragon)		damageMultiplier = 2.0f;
			break;
		}
		
		attackPower = (attack.isSpecialAttack()) ? attacker.getSpecialAttack() : attacker.getAttack();
		defenseStrength = (attack.isSpecialAttack()) ? defender.getSpecialDefense() : defender.getDefense();
		if(stabBonus()) damageMultiplier *= 1.5;
		if(criticalHit()) damageMultiplier *= 2;
		damageMultiplier *= randomModifier();
		
		damageApplied = (int)((((((2*attacker.getLevel()) + 10)/250.0f)*((float)attackPower/defenseStrength)*(attack.getBasePower()))+2)*damageMultiplier);
	}
	
	public void ApplyDamage(){
		defender.setHealth(defender.getHealth() - damageApplied);
	}
	
	private boolean criticalHit(){
		return (rand.nextFloat() <= (attacker.getSpeed()/512.0f));
	}
	
	private boolean stabBonus(){
		return (attack.getAttackType() == attacker.getType());
	}
	
	private float randomModifier(){
		return (rand.nextInt(15) + 85)/100.0f;
	}
}
