package gameEngine;

import java.util.Random;
import gameElements.Pokemon;
import gameElements.Attack;
import gameElements.Types;

public class BattleManager {
	private Random  rand;
	private boolean canAttack;
	private Pokemon poke1;
	private Pokemon poke2;
	private Pokemon winner;
	private Attack  atk1;
	private Attack  atk2;
	
	//TODO: Figure out what needs to go in here
	public BattleManager(){
		rand = new Random();
		canAttack = true;
	}
	
	public void executeRound(Pokemon pokemon1, Pokemon pokemon2, Attack attack1, Attack attack2){
		poke1 = pokemon1;
		poke2 = pokemon2;
		atk1  = attack1;
		atk2  = attack2;
		
		if(poke1.getSpeed() > poke2.getSpeed()){
			if(atk1.canAttack() && poke1.getHealth() > 0) applyDamage(poke1, poke2, atk1);
			if(atk2.canAttack() && poke2.getHealth() > 0) applyDamage(poke2, poke1, atk2);
		}else{
			if(atk2.canAttack() && poke2.getHealth() > 0) applyDamage(poke2, poke1, atk2);			
			if(atk1.canAttack() && poke1.getHealth() > 0) applyDamage(poke1, poke2, atk1);
		}
		
		calculateStatusEffect(poke1, false);
		calculateWinner();
		if(winner == null) calculateStatusEffect(poke2, false);
		calculateWinner();
	}
	
	public Pokemon getWinner(){
		return winner;
	}
	
	public void resetWinner(){
		winner = null;
	}
	
	private void calculateWinner(){
		if(poke1.getHealth() == 0 && poke2.getHealth() > 0) winner = poke2;
		else if(poke1.getHealth() > 0 && poke2.getHealth() == 0) winner = poke1;
		else winner = null;
	}
		
	private void applyDamage(Pokemon attacker, Pokemon defender, Attack attack){
		defender.modHealth(-calculateDamage(attacker, defender, attack));
	}
	
	private boolean criticalHit(Pokemon poke){
		return (rand.nextFloat() <= (poke.getSpeed()/512.0f));
	}
	
	private boolean stabBonus(Pokemon poke, Attack atk){
		return (atk.getAttackType() == poke.getType());
	}
	
	private float randomModifier(){
		return (rand.nextInt(15) + 85)/100.0f;
	}
	
	//TODO: finish all status effects and group by when the effect takes place
	private boolean calculateStatusEffect(Pokemon poke, boolean beforeTurn){
		switch(poke.getExplicitStatus()){
			case paralysis:
				if(rand.nextFloat() < 0.25f && beforeTurn) return false;
				break;
				
			case burn:
			case poison:
				if(!beforeTurn){
					float healthMod = 1.0f*poke.getBaseHealth();
					healthMod *= 0.0625f;
					poke.modHealth((int)(-healthMod));
				}
				break;
				
			case sleep:
			case frozen:
				if(beforeTurn) return false;
				break;
				
			default:;
		}
		
		switch(poke.getImplicitStatus()){			
			case confusion:
				
				break;
				
			case bound:
				
				break;
				
			case infatuation:
				
				break;
				
			case fear:
				
				break;
				
			default:;
		}
		
		return true;
	}
	
	private int calculateDamage(Pokemon attacker, Pokemon defender, Attack attack){
		float   damageMultiplier = 1.0f;
		int     attackPower = 0;
		int   	defenseStrength = 0;
		int   	damageApplied = 0;		
		
		canAttack = calculateStatusEffect(attacker, true);
		
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
			if(stabBonus(attacker, attack)) damageMultiplier *= 1.5;
			if(criticalHit(attacker)) damageMultiplier *= 2;
			damageMultiplier *= randomModifier();
			
			if (attack.getBasePower()>0)
				damageApplied = (int)((((((2*attacker.getLevel()) + 10)/250.0f)*((float)attackPower/defenseStrength)*(attack.getBasePower()))+2)*damageMultiplier);
		}
		
		return damageApplied;
	}
}
