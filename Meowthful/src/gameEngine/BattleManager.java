package gameEngine;

import java.util.Random;
import gameElements.Pokemon;
import gameElements.Attack;
import gameElements.Types;

public class BattleManager {
	private Pokemon attacker;
	private Pokemon defender;
	private Attack  attack;
	private Random  rand;
	private boolean canAttack;
	
	public BattleManager(Pokemon attacker, Pokemon defender, Attack attack){
		this.attacker = attacker;
		this.defender = defender;
		this.attack = attack;
		rand = new Random();
		canAttack = true;
	}
		
	public void ApplyDamage(){
		defender.modHealth(-CalculateDamage());
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
	
	private int CalculateDamage(){
		float damageMultiplier = 1.0f;
		int   attackPower = 0;
		int   defenseStrength = 0;
		int   damageApplied = 0;

		if(canAttack){
			switch(attack.getAttackType()){
			case normal:
				if(defender.getType() == Types.Type.rock) 		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.ghost)		damageMultiplier = 0.0f;
				break;
				
			case fighting:
				if(defender.getType() == Types.Type.normal) 	damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.rock)   	damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.ice)	 	damageMultiplier = 2.0f;
				
				if(defender.getType() == Types.Type.flying) 	damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.poison) 	damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.bug)	 	damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.psychic)	damageMultiplier = 0.5f;
				
				if(defender.getType() == Types.Type.ghost)	 	damageMultiplier = 0.0f;			
				break;
				
			case flying:
				if(defender.getType() == Types.Type.fighting)	damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.bug)	  	damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.grass)	  	damageMultiplier = 2.0f;
				
				if(defender.getType() == Types.Type.rock)	  	damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.electric)	damageMultiplier = 0.5f;
				break;
				
			case poison:
				if(defender.getType() == Types.Type.grass) 		damageMultiplier = 2.0f;
				
				if(defender.getType() == Types.Type.poison)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.ground)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.rock)  		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.ghost) 		damageMultiplier = 0.5f;
				break;
				
			case ground:
				if(defender.getType() == Types.Type.poison)  	damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.fire)	  	damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.electric)	damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.rock)	  	damageMultiplier = 2.0f;
	
				if(defender.getType() == Types.Type.bug)	  	damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.grass)   	damageMultiplier = 0.5f;
	
				if(defender.getType() == Types.Type.flying)  	damageMultiplier = 0.0f;
				break;
				
			case rock:
				if(defender.getType() == Types.Type.flying)		damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.bug)		damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.fire)		damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.ice)		damageMultiplier = 2.0f;
				
				if(defender.getType() == Types.Type.fighting)	damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.ground)		damageMultiplier = 0.5f;
				break;
				
			case bug:
				if(defender.getType() == Types.Type.grass)		damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.psychic)	damageMultiplier = 2.0f;
				
				if(defender.getType() == Types.Type.fighting)	damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.flying)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.poison)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.ghost)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.fire)		damageMultiplier = 0.5f;
				break;
				
			case ghost:
				if(defender.getType() == Types.Type.ghost)  	damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.psychic)	damageMultiplier = 2.0f;
				
				if(defender.getType() == Types.Type.normal)		damageMultiplier = 0.0f;
				break;
				
			case fire:
				if(defender.getType() == Types.Type.bug)		damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.grass)		damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.ice)		damageMultiplier = 2.0f;
				
				if(defender.getType() == Types.Type.rock)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.fire)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.water)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.dragon)		damageMultiplier = 0.5f;
				break;
				
			case water:
				if(defender.getType() == Types.Type.ground)		damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.rock)		damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.fire)		damageMultiplier = 2.0f;
				
				if(defender.getType() == Types.Type.water)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.grass)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.dragon)		damageMultiplier = 0.5f;
				break;
				
			case grass:
				if(defender.getType() == Types.Type.ground)		damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.rock)		damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.water)		damageMultiplier = 2.0f;
				
				if(defender.getType() == Types.Type.flying)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.poison)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.bug)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.fire)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.grass)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.dragon)		damageMultiplier = 0.5f;
				break;
				
			case electric:
				if(defender.getType() == Types.Type.flying)		damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.water)		damageMultiplier = 2.0f;
				
				if(defender.getType() == Types.Type.grass)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.electric)	damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.dragon)		damageMultiplier = 0.5f;
				
				if(defender.getType() == Types.Type.ground)		damageMultiplier = 0.0f;
				break;
				
			case psychic:
				if(defender.getType() == Types.Type.fighting)	damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.poison)  	damageMultiplier = 2.0f;
				
				if(defender.getType() == Types.Type.psychic) 	damageMultiplier = 0.5f;
				break;
				
			case ice:
				if(defender.getType() == Types.Type.flying)		damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.ground)		damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.grass)		damageMultiplier = 2.0f;
				if(defender.getType() == Types.Type.dragon)		damageMultiplier = 2.0f;
				
				if(defender.getType() == Types.Type.fire)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.water)		damageMultiplier = 0.5f;
				if(defender.getType() == Types.Type.ice)		damageMultiplier = 0.5f;
				break;
				
			case dragon:
				if(defender.getType() == Types.Type.dragon)		damageMultiplier = 2.0f;
				break;
			}
			
			attackPower = (attack.isSpecialAttack()) ? attacker.getSpecialAttack() : attacker.getAttack();
			defenseStrength = (attack.isSpecialAttack()) ? defender.getSpecialDefense() : defender.getDefense();
			if(stabBonus()) damageMultiplier *= 1.5;
			if(criticalHit()) damageMultiplier *= 2;
			damageMultiplier *= randomModifier();
			
			damageApplied = (int)((((((2*attacker.getLevel()) + 10)/250.0f)*((float)attackPower/defenseStrength)*(attack.getBasePower()))+2)*damageMultiplier);
		}
		
		return damageApplied;
	}
}
