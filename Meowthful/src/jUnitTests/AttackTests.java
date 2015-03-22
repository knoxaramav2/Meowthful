package jUnitTests;


public class AttackTests {
	@SuppressWarnings("unused")
	private static final double EPSILON = 10e-07;
	@SuppressWarnings("unused")
	private String[] msgs = {"An initialized attack of name \"Hyper Beam\" should have the name \"Hyper Beam\"",
							 "An initialized attack of type normal should have type normal",
							 "Changing the attack type to dragon should result in an attack type of dragon",
							 "Initializing an attack with 5 base PP should result in a base PP of 5",
							 "Initializing an attack with 5 base PP should currently have 5 PP",
							 "Setting an attack's current PP to 3 should result in a current PP of 3",
							 "Setting an attack's PP to 10 with max 5 should result in a current PP of 5",
							 "Setting an attack's PP to -10 should result in a current PP of 0",
							 "Modding an attack's PP by 2 from 2 should result in a current PP of 4",
							 "Modding an attack's PP by -2 from 3 should result in a current PP of 1",
							 "Modding an attack's PP by 3 from 3 should result in a current PP of 5 (max PP = 5)",
							 "Modding an attack's PP by -3 from 2 should result in a current PP of 0",
							 "Resetting an attack's PP to its base PP of 5 should result in a current PP of 5",
							 "Initializing an attack with 90 accuracy should have an accuracy of 90",
							 "Setting an attack's accuracy to 50 should result in an accuracy of 50",
							 "Setting an attack's accuracy to 120 should result in an accuracy of 100",
							 "Setting an attack's accuracy to -20 should result in an accuracy of 0",
							 "Modding an attack's accuracy by 30 from 30 should result in an accuracy of 60",
							 "Modding an attack's accuracy by -30 from 60 should result in an accuracy of 30",
							 "Modding an attack's accuracy by 60 from 60 should result in an accuracy of 100",
							 "Modding an attack's accuracy by -60 from 30 should result in an accuracy of 0",
							 "Resetting an attack's accuracy to its base of 90 should result in an accuracy of 90",
							 "Initializing an attack with 100 power should have a power of 100",
							 "Setting an attack's power to 30 should result in a power of 30",
							 "Setting an attack's power to -20 should result in a power of 0",
							 "Modding an attack's power by 20 from 30 should result in a power of 50",
							 "Modding an attack's power by -20 from 30 should result in a power of 10",
							 "Modding an attack's power by -40 from 30 should result in an accuracy of 0",
							 "Resetting an attack's power to its base of 100 should result in a power of 1030",
							 "Getting Hyper Beam's initial effect should result in an effect of recharge",
							 "Setting Hyper Beam's effect to sleep should result in an effect of sleep",
							 "IsSpecialAttack should return true for a fire type attack",
							 "IsSpecialAttack should return false for a normal type attack",
							 "Getting Hyper Beam's base power should result in a base power of 100",
							 "Initializing an attack with 0.5 flinch probability should result in a flinch probability of 0.5",
							 "Setting an attack's flinch probability to 0.35 should result in a flinch probability of 0.35",
							 "Setting an attack's flinch probability to 1.2 should result in a flinch probability of 1.0",
							 "Setting an attack's flinch probability to -0.35 should result in a flinch probability of 0.0",
							 "Modding an attack's flinch probability by 0.35 from 0.35 should result in a flinch probability of 0.7",
							 "Modding an attack's flinch probability by -0.35 from 0.7 should result in a flinch probability of 0.35",
							 "Modding an attack's flinch probability by 0.7 from 0.35 should result in a flinch probability of 1.0",
							 "Modding an attack's flinch probability by -0.7 from 0.35 should result in a flinch probability of 0.0",
							 "Resetting an attack's flinch probability to its base of 0.5 should result in a flinch probability of 0.5",
							 "Attack's should be able to be used immediately after initialization",
							 "An attack should not be able to be used after the cooldown timer is activated",
							 "Initiallly, the cooldown timer should be 0",
							 "With a cooldown of 2, the current cooldown should be 2 after calling activate cooldown",
							 "With a cooldown of 2, the current cooldown should be 1 after activating the cooldown and waiting 1 turn"};

	/*
	@Test
	public void GetName(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		assertEquals(msgs[0], "Hyper Beam", a.getName());
	}
	
	@Test
	public void GetAttackType(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		assertEquals(msgs[1], Types.Type.normal, a.getAttackType());
	}
	
	@Test
	public void SetAttackType(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setAttackType(Types.Type.dragon);
		assertEquals(msgs[2], Types.Type.dragon, a.getAttackType());
	}
	
	@Test
	public void GetBasePP(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		assertEquals(msgs[3], 5, a.getBasePP());
	}
	
	@Test
	public void GetCurrentPP(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		assertEquals(msgs[4], 5, a.getCurrentPP());
	}
	
	@Test
	public void SetCurrentPP(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setCurrentPP(3);
		assertEquals(msgs[5], 3, a.getCurrentPP());
	}
	
	@Test
	public void SetPPAboveBase(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setCurrentPP(10);
		assertEquals(msgs[6], 5, a.getCurrentPP());
	}
	
	@Test
	public void SetPPBelowZero(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setCurrentPP(-10);
		assertEquals(msgs[7], 0, a.getCurrentPP());
	}
	
	@Test
	public void ModPPUpward(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setCurrentPP(2);
		a.modCurrentPP(2);
		assertEquals(msgs[8], 4, a.getCurrentPP());
	}
	
	@Test
	public void ModPPDownward(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setCurrentPP(3);
		a.modCurrentPP(-2);
		assertEquals(msgs[9], 1, a.getCurrentPP());		
	}
	
	@Test
	public void ModPPPastMax(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setCurrentPP(3);
		a.modCurrentPP(3);
		assertEquals(msgs[10], 5, a.getCurrentPP());
	}
	
	@Test
	public void ModPPBelowZero(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setCurrentPP(2);
		a.modCurrentPP(-3);
		assertEquals(msgs[11], 0, a.getCurrentPP());
	}
	
	@Test
	public void ResetCurrentPP(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setCurrentPP(2);
		a.modCurrentPP(-3);
		a.resetCurrentPP();
		assertEquals(msgs[12], 5, a.getCurrentPP());		
	}
	
	@Test
	public void GetAccuracy(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		assertEquals(msgs[13], 90, a.getAccuracy());
	}
	
	@Test
	public void SetAccuracy(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setAccuracy(50);
		assertEquals(msgs[14], 50, a.getAccuracy());
	}
	
	@Test
	public void SetAccuracyAbove100(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setAccuracy(120);
		assertEquals(msgs[15], 100, a.getAccuracy());
	}
	
	@Test
	public void SetAccuracyBelowZero(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setAccuracy(-20);
		assertEquals(msgs[16], 0, a.getAccuracy());
	}
	
	@Test
	public void ModAccuracyUpward(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setAccuracy(30);
		a.modAccuracy(30);
		assertEquals(msgs[17], 60, a.getAccuracy());
	}
	
	@Test
	public void ModAccuracyDownward(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setAccuracy(60);
		a.modAccuracy(-30);
		assertEquals(msgs[18], 30, a.getAccuracy());
	}
	
	@Test
	public void ModAccuracyPast100(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setAccuracy(60);
		a.modAccuracy(60);
		assertEquals(msgs[19], 100, a.getAccuracy());
	}
	
	@Test
	public void ModAccuracyBelowZero(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setAccuracy(30);
		a.modAccuracy(-60);
		assertEquals(msgs[20], 0, a.getAccuracy());
	}
	
	@Test
	public void ResetAccuracy(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setAccuracy(30);
		a.modAccuracy(20);
		a.resetAccuracy();
		assertEquals(msgs[21], 90, a.getAccuracy());
	}
	
	@Test
	public void GetDamage(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		assertEquals(msgs[22], 100, a.getDamage());
	}
	
	@Test
	public void SetDamage(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setDamage(30);
		assertEquals(msgs[23], 30, a.getDamage());
	}
	
	@Test
	public void SetDamageBelowZero(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setDamage(-20);
		assertEquals(msgs[24], 0, a.getDamage());
	}
	
	@Test
	public void ModDamageUpward(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setDamage(30);
		a.modDamage(20);
		assertEquals(msgs[25], 50, a.getDamage());
	}
	
	@Test
	public void ModDamageDownward(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setDamage(30);
		a.modDamage(-20);
		assertEquals(msgs[26], 10, a.getDamage());
	}
	
	@Test
	public void ModDamageBelowZero(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setDamage(30);
		a.modDamage(-40);
		assertEquals(msgs[27], 0, a.getDamage());
	}
	
	@Test
	public void ResetDamage(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setDamage(30);
		a.modDamage(-20);
		a.resetDamage();
		assertEquals(msgs[28], 100, a.getDamage());		
	}
	
	@Test
	public void GetEffect(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		assertEquals(msgs[29], Types.AttackEffects.recharge, a.getEffect());
	}
	
	@Test
	public void SetEffect(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setEffect(Types.AttackEffects.sleep);
		assertEquals(msgs[30], Types.AttackEffects.sleep, a.getEffect());
	}
	
	@Test
	public void IsSpecialAttackFire(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setAttackType(Types.Type.fire);
		assertEquals(msgs[31], true, a.isSpecialAttack());
	}
	
	@Test
	public void IsSpecialTestNormal(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setAttackType(Types.Type.normal);
		assertEquals(msgs[32], false, a.isSpecialAttack());
	}
	
	@Test
	public void GetBasePower(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		assertEquals(msgs[33], 100, a.getBasePower());
	}
	
	@Test
	public void GetFlinchProbability(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		assertEquals(msgs[34], 0.5, a.getFlinchProbability(), EPSILON);
	}
	
	@Test
	public void SetFlinchProbability(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setFlinchProbability(0.35);
		assertEquals(msgs[35], 0.35, a.getFlinchProbability(), EPSILON);
	}
	
	@Test
	public void SetFlinchProbabilityAbove1(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setFlinchProbability(1.2);
		assertEquals(msgs[36], 1.0, a.getFlinchProbability(), EPSILON);
	}
	
	@Test
	public void SetFlinchProbabilityBelowZero(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setFlinchProbability(-0.35);
		assertEquals(msgs[37], 0.0, a.getFlinchProbability(), EPSILON);
	}
	
	@Test
	public void ModFlinchProbabilityUpward(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setFlinchProbability(0.35);
		a.modFlinchProbability(0.35);
		assertEquals(msgs[38], 0.7, a.getFlinchProbability(), EPSILON);
	}
	
	@Test
	public void ModFlinchProbabilityDownward(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setFlinchProbability(0.7);
		a.modFlinchProbability(-0.35);
		assertEquals(msgs[39], 0.35, a.getFlinchProbability(), EPSILON);		
	}
	
	@Test
	public void ModFlinchProbabilityPast1(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setFlinchProbability(0.35);
		a.modFlinchProbability(0.7);
		assertEquals(msgs[40], 1.0, a.getFlinchProbability(), EPSILON);
	}
	
	@Test
	public void ModFlinchProbabilityBelowZero(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setFlinchProbability(0.35);
		a.modFlinchProbability(-0.7);
		assertEquals(msgs[41], 0.0, a.getFlinchProbability(), EPSILON);
	}
	
	@Test
	public void ResetFlinchProbability(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.setFlinchProbability(0.35);
		a.modFlinchProbability(0.35);
		a.resetFlinchProbability();
		assertEquals(msgs[42], 0.5, a.getFlinchProbability(), EPSILON);
	}
	
	@Test
	public void CanAttackWhenTrue(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		assertEquals(msgs[43], true, a.canAttack());
	}
	
	@Test
	public void CanAttackWhenFalse(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.activateCooldownTimer();
		assertEquals(msgs[44], false, a.canAttack());
	}
	
	@Test
	public void GetCoolDownTimer(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		assertEquals(msgs[45], 0, a.getCurrentCooldown());
	}
	
	@Test
	public void ActivateCooldownTimer(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.activateCooldownTimer();
		assertEquals(msgs[46], 2, a.getCurrentCooldown());
	}
	
	@Test
	public void DecreaseCooldownTimer(){
		Attack a = new Attack("Hyper Beam", 90, Types.Type.normal, 100, 5, Types.AttackEffects.recharge, 0.5, 2);
		a.activateCooldownTimer();
		a.decreaseCooldown();
		assertEquals(msgs[47], 1, a.getCurrentCooldown());
	}
	
	*/
}
