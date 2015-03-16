package gameEngine;

import gameElements.Player;
import gameElements.Pokemon;

//saves temporary battle information

public class BattleCache {
	public Player p1;
	public Player p2;
	
	public Pokemon p1Active;
	public Pokemon p2Active;
	
	public BattleCache()
	{
		p1 = null;
		p2 = null;
		p1Active=null;
		p2Active =null;
	}
	
	//trainer vs. trainer
	public void startSession(Player p1, Player p2)
	{
		this.p1=p1;
		this.p2=p2;
		this.p1Active=p1.party.get(0);
		this.p2Active=p2.party.get(0);
	}
	
	//trainer vs. pokemon
	public void startSession(Player p1, Pokemon p2)
	{
		this.p1=p1;
		this.p1Active=p1.party.get(0);
		this.p2Active=p2;
	}
	
	//pokemon vs. pokemon
	public void startSession(Pokemon p1, Pokemon p2)
	{
		this.p1Active=p1;
		this.p2Active=p2;
	}
	
	public void endSession()
	{
		p1 = null;
		p2 = null;
		p1Active=null;
		p2Active =null;
	}
}
