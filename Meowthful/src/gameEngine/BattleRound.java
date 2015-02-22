package gameEngine;

import gameElements.Pokemon;
import gameElements.Attack;

public class BattleRound {
	public BattleRound(){

	}

	public void executeRound(Pokemon poke1, Pokemon poke2, Attack att1, Attack att2){
		BattleManager bm1 = new BattleManager(poke1, poke2, att1);
		BattleManager bm2 = new BattleManager(poke2, poke1, att2);;
		
		if(poke1.getSpeed() >= poke2.getSpeed()){
			if(poke1.getHealth() > 0) bm1.ApplyDamage();
			if(poke2.getHealth() > 0) bm2.ApplyDamage();
		}else{
			if(poke2.getHealth() > 0) bm2.ApplyDamage();
			if(poke1.getHealth() > 0) bm1.ApplyDamage();			
		}
		
		postRoundAilments(poke1, poke2);
	}
	
	private void postRoundAilments(Pokemon poke1, Pokemon poke2){
		switch(poke1.getExplicitStatus()){
		case paralysis:
			
			break;
			
		case poison:
			
			break;
			
		case sleep:
			
			break;
			
		case burn:
			
			break;
			
		case frozen:
			
			break;
			
			default:;
		}
		
		switch(poke1.getImplicitStatus()){
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
		
		switch(poke2.getExplicitStatus()){
		case paralysis:
			
			break;
			
		case poison:
			
			break;
			
		case sleep:
			
			break;
			
		case burn:
			
			break;
			
		case frozen:
			
			break;
			
			default:;
		}
		
		switch(poke2.getImplicitStatus()){
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
	}
}
