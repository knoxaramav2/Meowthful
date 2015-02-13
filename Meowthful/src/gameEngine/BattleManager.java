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
			if(defender.type == Attack.Type.rock) 		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.ghost)		damageMultiplier = 0.0f;
			break;
			
		case fighting:
			if(defender.type == Attack.Type.normal) 	damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.rock)   	damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.ice)	 	damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.flying) 	damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.poison) 	damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.bug)	 	damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.psychic)	damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.ghost)	 	damageMultiplier = 0.0f;			
			break;
			
		case flying:
			if(defender.type == Attack.Type.fighting)	damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.rock)	  	damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.bug)	  	damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.grass)	  	damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.electric)	damageMultiplier = 0.5f;
			break;
			
		case poison:
			if(defender.type == Attack.Type.poison)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.ground)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.rock)  		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.ghost) 		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.grass) 		damageMultiplier = 2.0f;
			break;
			
		case ground:
			if(defender.type == Attack.Type.flying)  	damageMultiplier = 0.0f;
			if(defender.type == Attack.Type.poison)  	damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.rock)	  	damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.bug)	  	damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.fire)	  	damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.electric)	damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.grass)   	damageMultiplier = 0.5f;
			break;
			
		case rock:
			if(defender.type == Attack.Type.fighting)	damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.flying)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.ground)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.bug)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.fire)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.ice)		damageMultiplier = 2.0f;
			break;
			
		case bug:
			if(defender.type == Attack.Type.fighting)	damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.flying)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.poison)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.ghost)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.fire)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.grass)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.psychic)	damageMultiplier = 2.0f;
			break;
			
		case ghost:
			if(defender.type == Attack.Type.normal)		damageMultiplier = 0.0f;
			if(defender.type == Attack.Type.ghost)  	damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.psychic)	damageMultiplier = 2.0f;
			break;
			
		case fire:
			if(defender.type == Attack.Type.rock)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.bug)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.fire)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.water)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.grass)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.ice)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.dragon)		damageMultiplier = 0.5f;
			break;
			
		case water:
			if(defender.type == Attack.Type.ground)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.rock)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.fire)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.water)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.grass)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.dragon)		damageMultiplier = 0.5f;
			break;
			
		case grass:
			if(defender.type == Attack.Type.flying)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.poison)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.ground)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.rock)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.bug)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.fire)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.water)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.grass)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.dragon)		damageMultiplier = 0.5f;
			break;
			
		case electric:
			if(defender.type == Attack.Type.flying)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.ground)		damageMultiplier = 0.0f;
			if(defender.type == Attack.Type.water)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.grass)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.electric)	damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.dragon)		damageMultiplier = 0.5f;
			break;
			
		case psychic:
			if(defender.type == Attack.Type.fighting)	damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.poison)  	damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.psychic) 	damageMultiplier = 0.5f;
			break;
			
		case ice:
			if(defender.type == Attack.Type.flying)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.ground)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.fire)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.water)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.grass)		damageMultiplier = 2.0f;
			if(defender.type == Attack.Type.ice)		damageMultiplier = 0.5f;
			if(defender.type == Attack.Type.dragon)		damageMultiplier = 2.0f;
			break;
			
		case dragon:
			if(defender.type == Attack.Type.dragon)		damageMultiplier = 2.0f;
			break;
		}
		
		attackPower = (attack.isSpecialAttack()) ? attacker.spcAttack : attacker.attackStrength;
		defenseStrength = (attack.isSpecialAttack()) ? defender.spcDefense : defender.defenseStrength;
		if(stabBonus()) damageMultiplier *= 1.5;
		if(criticalHit()) damageMultiplier *= 2;
		damageMultiplier *= randomModifier();
		
		damageApplied = (int)((((((2*attacker.level) + 10)/250.0f)*((float)attackPower/defenseStrength)*(attack.getBasePower()))+2)*damageMultiplier);
	}
	
	public void ApplyDamage(){
		defender.health -= damageApplied;
		if(defender.health < 0) defender.health = 0;
	}
	
	private boolean criticalHit(){
		return (rand.nextFloat() <= (attacker.speed/512.0f));
	}
	
	private boolean stabBonus(){
		return (attack.getAttackType() == attacker.type);
	}
	
	private float randomModifier(){
		return (rand.nextInt(15) + 85)/100.0f;
	}
}
