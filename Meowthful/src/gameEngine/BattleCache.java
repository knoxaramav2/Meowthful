package gameEngine;

import gameElements.Player;
import gameElements.Pokemon;

//saves temporary battle information

public class BattleCache {
	public Player p1;
	public Player p2;
	
	public Pokemon p1Active;
	public Pokemon p2Active;
	
	public boolean battleActive;
	
	public BattleCache()
	{
		p1 = null;
		p2 = null;
		p1Active=null;
		p2Active =null;
		
		battleActive=false;
	}
	
	//trainer vs. trainer
	public boolean startSession(Player p1, Player p2)
	{
		if ((p1==null || p2==null) && battleActive==false)
			return false;
		
		this.p1=p1;
		this.p2=p2;
		this.p1Active=p1.party.get(0);
		this.p2Active=p2.party.get(0);
		
		battleActive=true;
		
		System.out.println("Trainer Battle Start: " + p1.name + " vs. " + p2.name);
		
		return true;
	}
	
	//trainer vs. pokemon
	public boolean startSession(Player p1, Pokemon p2)
	{
		if ((p1==null || p2==null) && battleActive==false)
			return false;
		
		this.p1=p1;
		this.p1Active=p1.party.get(0);
		this.p2Active=p2;
		
		battleActive=true;
		
		System.out.println("Wild Battle Start: " + p1.name + " vs. " + p2.name);
		
		return true;
	}
	
	//pokemon vs. pokemon
	public boolean startSession(Pokemon p1, Pokemon p2)
	{
		if ((p1==null || p2==null) && battleActive==false)
			return false;
		
		this.p1Active=p1;
		this.p2Active=p2;
		
		battleActive=true;
		
		System.out.println("Wild Pokemon Battle Start: " + p1.name + " vs. " + p2.name);
		
		return true;
	}

	public void endSession()
	{
		endSession("");
	}
	
	public void endSession(String victor)
	{
		if (victor.length()>0)
			System.out.println(victor + " wins the battle!");
		
		p1 = null;
		p2 = null;
		p1Active = null;
		p2Active = null;
		
		battleActive=false;
	}
}
