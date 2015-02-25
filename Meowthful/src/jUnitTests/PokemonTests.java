package jUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import gameElements.Pokemon;
import gameElements.Types;

public class PokemonTests {
	private String[] msgs = {"Initializing a new Pokemon with the name \"Squirtle\" should have the name \"Squirtle\"",
					 		 "Initializing a new Pokemon with health 60 should have a health of 60",
							 "Setting a Pokemon's health to 50 should result in a health of 50",
							 "Setting a Pokemon's health higher than its max of 60 should result in a health of 60",
							 "Setting a Pokemon's health lower than 0 should result in a health of 0",
							 "Modding a Pokemon's health by 10 from 40 should result in a health of 50",
							 "Modding a Pokemon's health by -10 from 40 should result in a health of 30",
							 "Modding a Pokemon's health by 20 from 50 should result in a health of 60",
							 "Modding a Pokemon's health by -20 from 10 should result in a health of 0",
							 "Resetting a Pokemon's health to base 60 should result in a health of 60",
							 "Initializing a new Pokemon with attack 60 should have an attack of 60",
							 "Setting a Pokemon's attack to 50 should result in an attack of 50",
							 "Setting a Pokemon's attack lower than 0 should result in an attack of 0",
							 "Modding a Pokemon's attack by 10 from 40 should result in an attack of 50",
							 "Modding a Pokemon's attack by -10 from 40 should result in an attack of 30",
							 "Modding a Pokemon's attack by 20 from 50 should result in an attack of 60",
							 "Modding a Pokemon's attack by -20 from 10 should result in an attack of 0",
							 "Resetting a Pokemon's attack to base 60 should result in an attack of 60",
							 "Initializing a new Pokemon with defense 60 should have a defense of 60",
							 "Setting a Pokemon's defense to 50 should result in a defense of 50",
							 "Setting a Pokemon's defense lower than 0 should result in a defense of 0",
							 "Modding a Pokemon's defense by 10 from 40 should result in a defense of 50",
							 "Modding a Pokemon's defense by -10 from 40 should result in a defense of 30",
							 "Modding a Pokemon's defense by 20 from 50 should result in a defense of 60",
							 "Modding a Pokemon's defense by -20 from 10 should result in a defense of 0",
							 "Resetting a Pokemon's defense to base 60 should result in a defense of 60",
							 "Initializing a new Pokemon with special attack 60 should have a special attack of 60",
							 "Setting a Pokemon's special attack to 50 should result in a special attack of 50",
							 "Setting a Pokemon's special attack lower than 0 should result in a special attack of 0",
							 "Modding a Pokemon's special attack by 10 from 40 should result in a special attack of 50",
							 "Modding a Pokemon's special attack by -10 from 40 should result in a special attack of 30",
							 "Modding a Pokemon's special attack by 20 from 50 should result in a special attack of 60",
							 "Modding a Pokemon's special attack by -20 from 10 should result in a special attack of 0",
							 "Resetting a Pokemon's special attack to base 60 should result in a special attack of 60",
							 "Initializing a new Pokemon with special defense 60 should have a special defense of 60",
							 "Setting a Pokemon's special defense to 50 should result in a special defense of 50",
							 "Setting a Pokemon's special defense lower than 0 should result in a special defense of 0",
							 "Modding a Pokemon's special defense by 10 from 40 should result in a special defense of 50",
							 "Modding a Pokemon's special defense by -10 from 40 should result in a special defense of 30",
							 "Modding a Pokemon's special defense by 20 from 50 should result in a special defense of 60",
							 "Modding a Pokemon's special defense by -20 from 10 should result in a special defense of 0",
							 "Resetting a Pokemon's special defense to base 60 should result in a special defense of 60",
							 "Initializing a new Pokemon with speed 60 should have a speed of 60",
							 "Setting a Pokemon's speed to 50 should result in a speed of 50",
							 "Setting a Pokemon's speed lower than 0 should result in a speed of 0",
							 "Modding a Pokemon's speed by 10 from 40 should result in a speed of 50",
							 "Modding a Pokemon's speed by -10 from 40 should result in a speed of 30",
							 "Modding a Pokemon's speed by 20 from 50 should result in a speed of 60",
							 "Modding a Pokemon's speed by -20 from 10 should result in a speed of 0",
							 "Resetting a Pokemon's speed to base 60 should result in a speed of 60",
							 "Initializing a new Pokemon with evasiveness 60 should have an evasiveness of 60",
							 "Setting a Pokemon's evasiveness to 50 should result in an evasiveness of 50",
							 "Setting a Pokemon's evasiveness lower than 0 should result in an evasiveness of 0",
							 "Modding a Pokemon's evasiveness by 10 from 40 should result in an evasiveness of 50",
							 "Modding a Pokemon's evasiveness by -10 from 40 should result in an evasiveness of 30",
							 "Modding a Pokemon's evasiveness by 20 from 50 should result in an evasiveness of 60",
							 "Modding a Pokemon's evasiveness by -20 from 10 should result in an evasiveness of 0",
							 "Resetting a Pokemon's evasiveness to base 60 should result in an evasiveness of 60",
							 "Initializing a new Pokemon should result in the Pokemon having a level of 60",
							 "Setting a Pokemon's level to 50 should result in a level of 50",
							 "Setting a Pokemon's level lower than 0 should result in a level of 0",
							 "Modding a Pokemon's level by 10 from 40 should result in a level of 50",
							 "Modding a Pokemon's level by -10 from 40 should result in a level of 30",
							 "Modding a Pokemon's level by 20 from 50 should result in a level of 60",
							 "Modding a Pokemon's level by -20 from 10 should result in a level of 0",
							 "Initializing a water type Pokemon should result in the Pokemon being of water type",
							 "Setting a Pokemon's type to dragon should result in a type of dragon",
							 "Initializing a Pokemon should result in no explicit status effect",
							 "Setting a Pokemon's explicit status effect to poison should result in an explicit status of poison",
							 "Removing a Pokemon's explicit status of poison should result in no explicit status effect",
							 "Initializing a Pokemon should result in no implicit status effect",
							 "Setting a Pokemon's implicit status effect to confusion should result in an implicit status of confusion",
							 "Removing a Pokemon's implicit status of confusionshould result in no implicit status effect"
	};
/*		
	@Test
	public void Name(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		assertEquals(msgs[0], "Squirtle", p.getName());
	}
	
	@Test
	public void GetHealth(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		assertEquals(msgs[1], 60, p.getHealth());
	}
	
	@Test
	public void SetHealth(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setHealth(50);
		assertEquals(msgs[2], 50, p.getHealth());
	}
	
	@Test
	public void SetHealthHigherThanMax(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setHealth(70);
		assertEquals(msgs[3], 60, p.getHealth());
	}
	
	@Test
	public void SetHealthLowerThanZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setHealth(-10);
		assertEquals(msgs[4], 0, p.getHealth());
	}
	
	@Test
	public void ModHealthUpward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setHealth(40);
		p.modHealth(10);
		assertEquals(msgs[5], 50, p.getHealth());
	}
	
	@Test
	public void ModHealthDownward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setHealth(40);
		p.modHealth(-10);
		assertEquals(msgs[6], 30, p.getHealth());
	}
	
	@Test
	public void ModHealthPastMax(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setHealth(50);
		p.modHealth(20);
		assertEquals(msgs[7], 60, p.getHealth());
	}
	
	@Test
	public void ModHealthUnderZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setHealth(10);
		p.modHealth(-20);
		assertEquals(msgs[8], 0, p.getHealth());
	}
	
	@Test
	public void ResetHealth(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setHealth(20);
		p.modHealth(10);
		p.resetHealth();
		assertEquals(msgs[9], 60, p.getHealth());
	}
	
	@Test
	public void GetAttack(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		assertEquals(msgs[10], 60, p.getAttack());
	}
	
	@Test
	public void SetAttack(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setAttack(50);
		assertEquals(msgs[11], 50, p.getAttack());
	}
		
	@Test
	public void SetAttackLowerThanZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setAttack(-10);
		assertEquals(msgs[12], 0, p.getAttack());
	}
	
	@Test
	public void ModAttackUpward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setAttack(40);
		p.modAttack(10);
		assertEquals(msgs[13], 50, p.getAttack());
	}
	
	@Test
	public void ModAttackDownward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setAttack(40);
		p.modAttack(-10);
		assertEquals(msgs[14], 30, p.getAttack());
	}
		
	@Test
	public void ModAttackUnderZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setAttack(10);
		p.modAttack(-20);
		assertEquals(msgs[15], 0, p.getAttack());
	}
	
	@Test
	public void ResetAttack(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setAttack(20);
		p.modAttack(10);
		p.resetAttack();
		assertEquals(msgs[16], 60, p.getAttack());
	}
	
	@Test
	public void GetDefense(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		assertEquals(msgs[17], 60, p.getDefense());
	}
	
	@Test
	public void SetDefense(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setDefense(50);
		assertEquals(msgs[18], 50, p.getDefense());
	}
		
	@Test
	public void SetDefenseLowerThanZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setDefense(-10);
		assertEquals(msgs[19], 0, p.getDefense());
	}
	
	@Test
	public void ModDefenseUpward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setDefense(40);
		p.modDefense(10);
		assertEquals(msgs[20], 50, p.getDefense());
	}
	
	@Test
	public void ModDefenseDownward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setDefense(40);
		p.modDefense(-10);
		assertEquals(msgs[21], 30, p.getDefense());
	}
		
	@Test
	public void ModDefenseUnderZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setDefense(10);
		p.modDefense(-20);
		assertEquals(msgs[22], 0, p.getDefense());
	}
	
	@Test
	public void ResetDefense(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setDefense(20);
		p.modDefense(10);
		p.resetDefense();
		assertEquals(msgs[23], 60, p.getDefense());
	}
	
	@Test
	public void GetSpecialAttack(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		assertEquals(msgs[24], 60, p.getSpecialAttack());
	}
	
	@Test
	public void SetSpecialAttack(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpecialAttack(50);
		assertEquals(msgs[25], 50, p.getSpecialAttack());
	}
		
	@Test
	public void SetSpecialAttackLowerThanZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpecialAttack(-10);
		assertEquals(msgs[26], 0, p.getSpecialAttack());
	}
	
	@Test
	public void ModSpecialAttackUpward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpecialAttack(40);
		p.modSpecialAttack(10);
		assertEquals(msgs[27], 50, p.getSpecialAttack());
	}
	
	@Test
	public void ModSpecialAttackDownward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpecialAttack(40);
		p.modSpecialAttack(-10);
		assertEquals(msgs[28], 30, p.getSpecialAttack());
	}
		
	@Test
	public void ModSpecialAttackUnderZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpecialAttack(10);
		p.modSpecialAttack(-20);
		assertEquals(msgs[29], 0, p.getSpecialAttack());
	}
	
	@Test
	public void ResetSpecialAttack(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpecialAttack(20);
		p.modSpecialAttack(10);
		p.resetSpecialAttack();
		assertEquals(msgs[30], 60, p.getSpecialAttack());
	}

	@Test
	public void GetSpecialDefense(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		assertEquals(msgs[31], 60, p.getSpecialDefense());
	}
	
	@Test
	public void SetSpecialDefense(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpecialDefense(50);
		assertEquals(msgs[32], 50, p.getSpecialDefense());
	}
		
	@Test
	public void SetSpecialDefenseLowerThanZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpecialDefense(-10);
		assertEquals(msgs[33], 0, p.getSpecialDefense());
	}
	
	@Test
	public void ModSpecialDefenseUpward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpecialDefense(40);
		p.modSpecialDefense(10);
		assertEquals(msgs[34], 50, p.getSpecialDefense());
	}
	
	@Test
	public void ModSpecialDefenseDownward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpecialDefense(40);
		p.modSpecialDefense(-10);
		assertEquals(msgs[35], 30, p.getSpecialDefense());
	}
		
	@Test
	public void ModSpecialDefenseUnderZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpecialDefense(10);
		p.modSpecialDefense(-20);
		assertEquals(msgs[36], 0, p.getSpecialDefense());
	}
	
	@Test
	public void ResetSpecialDefense(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpecialDefense(20);
		p.modSpecialDefense(10);
		p.resetSpecialDefense();
		assertEquals(msgs[37], 60, p.getSpecialDefense());
	}
	
	@Test
	public void GetSpeed(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		assertEquals(msgs[38], 60, p.getSpeed());
	}
	
	@Test
	public void SetSpeed(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpeed(50);
		assertEquals(msgs[39], 50, p.getSpeed());
	}
		
	@Test
	public void SetSpeedLowerThanZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpeed(-10);
		assertEquals(msgs[40], 0, p.getSpeed());
	}
	
	@Test
	public void ModSpeedUpward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpeed(40);
		p.modSpeed(10);
		assertEquals(msgs[41], 50, p.getSpeed());
	}
	
	@Test
	public void ModSpeedDownward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpeed(40);
		p.modSpeed(-10);
		assertEquals(msgs[42], 30, p.getSpeed());
	}
		
	@Test
	public void ModSpeedUnderZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpeed(10);
		p.modSpeed(-20);
		assertEquals(msgs[43], 0, p.getSpeed());
	}
	
	@Test
	public void ResetSpeed(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setSpeed(20);
		p.modSpeed(10);
		p.resetSpeed();
		assertEquals(msgs[44], 60, p.getSpeed());
	}
	
	@Test
	public void GetEvasiveness(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		assertEquals(msgs[45], 60, p.getEvasiveness());
	}
	
	@Test
	public void SetEvasiveness(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setEvasiveness(50);
		assertEquals(msgs[46], 50, p.getEvasiveness());
	}
		
	@Test
	public void SetEvasivenessLowerThanZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setEvasiveness(-10);
		assertEquals(msgs[47], 0, p.getEvasiveness());
	}
	
	@Test
	public void ModEvasivenessUpward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setEvasiveness(40);
		p.modEvasiveness(10);
		assertEquals(msgs[48], 50, p.getEvasiveness());
	}
	
	@Test
	public void ModEvasivenessDownward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setEvasiveness(40);
		p.modEvasiveness(-10);
		assertEquals(msgs[49], 30, p.getEvasiveness());
	}
		
	@Test
	public void ModEvasivenessUnderZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setEvasiveness(10);
		p.modEvasiveness(-20);
		assertEquals(msgs[50], 0, p.getEvasiveness());
	}
	
	@Test
	public void ResetEvasiveness(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setEvasiveness(20);
		p.modEvasiveness(10);
		p.resetEvasiveness();
		assertEquals(msgs[51], 60, p.getEvasiveness());
	}
	
	@Test
	public void GetLevel(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		assertEquals(msgs[52], 0, p.getLevel());
	}
	
	@Test
	public void SetLevel(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setLevel(50);
		assertEquals(msgs[53], 50, p.getLevel());
	}
		
	@Test
	public void SetLevelLowerThanZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setLevel(-10);
		assertEquals(msgs[54], 0, p.getLevel());
	}
	
	@Test
	public void ModLevelUpward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setLevel(40);
		p.modLevel(10);
		assertEquals(msgs[55], 50, p.getLevel());
	}
	
	@Test
	public void ModLevelDownward(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setLevel(40);
		p.modLevel(-10);
		assertEquals(msgs[56], 30, p.getLevel());
	}
		
	@Test
	public void ModLevelUnderZero(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setLevel(10);
		p.modLevel(-20);
		assertEquals(msgs[57], 0, p.getLevel());
	}

	@Test
	public void GetType(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		assertEquals(msgs[58], Types.Type.water, p.getType());
	}
	
	@Test
	public void SetType(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setType(Types.Type.dragon);
		assertEquals(msgs[59], Types.Type.dragon, p.getType());
	}
	
	@Test
	public void GetExplicitStatus(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		assertEquals(msgs[60], Types.ExplicitStatus.none, p.getExplicitStatus());
	}
	
	@Test
	public void SetExplicitStatus(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setExplicitStatus(Types.ExplicitStatus.poison);
		assertEquals(msgs[61], Types.ExplicitStatus.poison, p.getExplicitStatus());
	}
	
	@Test
	public void RemoveExplicitStatus(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setExplicitStatus(Types.ExplicitStatus.poison);
		p.removeExplicitStatusEffect();
		assertEquals(msgs[62], Types.ExplicitStatus.none, p.getExplicitStatus());
	}
	
	@Test
	public void GetImplicitStatus(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		assertEquals(msgs[63], Types.ImplicitStatus.none, p.getImplicitStatus());
	}
	
	@Test
	public void SetImplicitStatus(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setImplicitStatus(Types.ImplicitStatus.confusion);
		assertEquals(msgs[64], Types.ImplicitStatus.confusion, p.getImplicitStatus());
	}
	
	@Test
	public void RemoveImplicitStatus(){
		Pokemon p = new Pokemon("Squirtle", 60, 60, 60, 60, 60, 60, 60, 60, 60, Types.Type.water, Types.ExplicitStatus.none, Types.ImplicitStatus.none);
		p.setImplicitStatus(Types.ImplicitStatus.confusion);
		p.removeImplicitStatusEffect();
		assertEquals(msgs[65], Types.ImplicitStatus.none, p.getImplicitStatus());
	}
	*/
}
