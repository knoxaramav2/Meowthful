package scriptEngine;

import gameElements.Attack;
import gameElements.Player;
import gameElements.Pokemon;
import gameElements.Types.Type;
import gameEngine.AI;
import gameEngine.BattleCache;
import gameEngine.BattleManager;
import gameEngine.Utility;
import gameEngine.gameGlobal;
import graphics.Renderer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import system.data;

class scriptFunction
{
	String title=null;
	public int eventNumber=0;
	public ArrayList <Packet> mapScript = new ArrayList <Packet>();
}

class PersonalFunction
{
	String function=null;
	public int id=0;
}

public class unownInterpreter {
	
	//connection to database
	gameGlobal g = null;
	BattleManager bm = null;
	CommandCodes codes;
	data system = null;
	BattleCache bc=new BattleCache();
	Renderer graphics=null;
	Random r;
	boolean invalid;
	
	ArrayList <scriptFunction> subFunctions = new ArrayList <scriptFunction>();
	ArrayList <PersonalFunction> winActivators = new ArrayList <PersonalFunction>();
	scriptFunction temp = null;
	boolean subParse=false;
	
	//super magic happy system fun time
	
	private void deleteVar(Packet p)
	{
		if (p.params.size()!=1)
		{
			reportError("Error: Illegal parameter set",p.raw);
			return;
		}
		
		system.delVar(p.params.get(0));
	}
	
	private void setVar(Packet p)
	{
		if (p.params.size()<1 || p.params.size()>2)
		{
			reportError("Error: Illegal parameter set",p.raw);
			return;
		}
		
		if (p.params.size()==1)
			system.addVar(p.params.get(0), "");
		else 
			system.addVar(p.params.get(0), p.params.get(1));
	}
	
	private void print(Packet p)
	{
		for (int x=0; x<p.params.size(); x++)
			System.out.print(p.params.get(x));
		System.out.println();
	}
	
	//none
	private void quitGame()
	{
		system.active=false;
		System.exit(0);
	}
	
	private void reportError(String mess, String[]raw)
	{
		System.out.print(mess+" : \t");
		graphics.appendText(mess+" : \t");
		String t = new String();
		for (String ele: raw)
		{
			System.out.print(ele+" ");
			t = new String(ele+" ");
		}
		graphics.appendText(t);
		System.out.println("");
	}	
	//player/opponent, to_switch
	private boolean changeActivePokemon(Packet p)
	{
		//swap out active pokemon
		
		//index to swap
		if (p.params.size()!=2 && p.params.size()!=3)
		{
			reportError("Error: Must have 2 or 3 parameters\n[player] [hotswap]\n[player] [pkmn 1] [pkmn 2]",p.raw);
			return false;
		}
		
		if (p.params.size()==2)//in battle hotswap
		{
		if (!bc.battleActive)
		{
			reportError("Error: must be in battle to hotswap",p.raw);
			return false;
		}
		
		int id=Integer.parseInt(p.params.get(0));
		int pkIndex=Integer.parseInt(p.params.get(1));
		Player player=null;
		
		if (!(pkIndex>=0 && pkIndex<=5) || !(pkIndex>=0 && pkIndex<=5))
		{
			reportError("Error: ",p.raw);
			return false;
		}
		
		if (bc.p1.id==id)
			player=bc.p1;
		else if (bc.p2.id==id)
			player=bc.p2;
		else
		{
			reportError("Error: Neither actor matches input",p.raw);
			return false;
		}
		
		if (pkIndex>=player.party.size())
		{
			reportError("Error: Invalid selection",p.raw);
			return false;
		}
		
		Pokemon pkmn = player.party.get(pkIndex);
		if (pkmn==null)
		{
			reportError("Error: Null pokemon",p.raw);
			return false;
		}
		
		if (player==bc.p1)
		{
			bc.p1Active=pkmn;
			graphics.battle.setPokeHealthPercentage(1, (bc.p1Active.getHealth()/(double)bc.p1Active.getBaseHealth()));
		}
		else
		{
			bc.p2Active=pkmn;
			graphics.battle.setPokeHealthPercentage(2, (bc.p2Active.getHealth()/(double)bc.p2Active.getBaseHealth()));
		}
			
		
		graphics.battle.repaint();
		
		interpret("dialogue "+player.name + " sends out " + pkmn.name);
		System.out.println(player.name + " sends out " + pkmn.name);
		
		graphics.battle.setUserPokemon(bc.p1Active);
		graphics.battle.setOpponentPokemon(bc.p2Active);
		graphics.battle.repaint();
		
		}
		else {
			
		}
		
		return true;
	}
	
	//actor 1, actor 2
	//actor 1, pokemon name, pkmn level	-	autogenerate rest
	//pokemon 1 name, lvl, pokemon 2 name, lvl
	private boolean startBattle(Packet p)
	{
		int psize=p.params.size();
		if (psize<1 || psize>4)
		{
			reportError("Error: Function accepts 2,3 or 4 parameters",p.raw);
			return false;
		}
		
		Player p1=null;
		Player p2=null;
		
		switch (psize)
		{
		case 2:
			if (!Utility.isInteger(p.params.get(0)) || !Utility.isInteger(p.params.get(1)))
			{
				reportError("Error: Parameters must be (int actor id, int actor id)",p.raw);
				return false;
			}
			
			p1 = g.getPlayer(Integer.parseInt(p.params.get(0)));
			p2 = g.getPlayer(Integer.parseInt(p.params.get(1)));
			
			if (p1==null || p2==null)
			{
				if (bc.p1==null)
					reportError("Error: Actor 1 not viable",p.raw);
				if (bc.p2==null)
					reportError("Error: Actor 2 not viable",p.raw);
				return false;
			}
			
			if (!p1.hasReadyPokemon() || !p2.hasReadyPokemon())
			{
				reportError("Both sides must have at least one viable pokemon",p.raw);
				return true;
			}
			
			bc.startSession(p1, p2);
			
			graphics.battle.setParty(p1.party);
			for (Pokemon pk:p1.party)
				if (!pk.isKO())
				{
					graphics.battle.setUserPokemon(p1.party.get(0));
					break;
				}
					
			for (Pokemon pk:p2.party)
				if (!pk.isKO())
				{
					graphics.battle.setOpponentPokemon(p2.party.get(0));
					break;
				}		
			graphics.battle.setPokeHealthPercentage(1, bc.p1Active.getHealth()/(double)bc.p1Active.getBaseHealth());
			graphics.battle.setPokeHealthPercentage(2, bc.p2Active.getHealth()/(double)bc.p2Active.getBaseHealth());
			break;
		case 3:
			if (!Utility.isInteger(p.params.get(0)) || Utility.isInteger(p.params.get(1)) || !Utility.isInteger(p.params.get(2)))
			{
				reportError("Error: Parameters must be (int actor id, string pokemon, int level)",p.raw);
				return false;
			}
			break;
		case 4:
			if (!Utility.isInteger(p.params.get(0)) || Utility.isInteger(p.params.get(1)) || !Utility.isInteger(p.params.get(2)) || Utility.isInteger(p.params.get(3)))
			{
				reportError("Error: Parameters must be (string pokemon, int level, string pokemon, int level)",p.raw);
				return false;
			}
			break;
		}
		
		graphics.battle.setMap(1);
		
		graphics.switchFrame(Renderer.BATTLE_FRAME);
		
		switch(p2.rank)
		{
		case 0:
			interpret("dialogue Grunt "+p2.name+" wants to battle");
			break;
		case 1:
			interpret("dialogue Private "+p2.name+" wants to battle");
			break;
		case 2:
			interpret("dialogue General "+p2.name+" wants to battle");
			break;
		case 3:
			interpret("dialogue Executive "+p2.name+" wants to battle");
			break;
		case 4:
			interpret("dialogue Admin "+p2.name+" wants to battle");
			break;
		case 5:
			interpret("dialogue Leader "+p2.name+" demands to battle");
			break;
		}
			
		
		return true;
	}
	
	private void declareWinner(Player winner, Player loser)
	{
		int exchange = (int)(loser.money*0.2)+5;
		if (loser.money<exchange)
			exchange=loser.money;
		loser.money-=exchange;
		winner.money+=exchange;
		loser.coolDownTime=loser.coolDown;
		
		graphics.battle.reset();
		
		if (winner.id!=0)
		{
			switch(winner.rank)
			{
			case 0:
				interpret("dialogue "+winner.id+" Grunt "+winner.name+" has won the battle");
				break;
			case 1:
				interpret("dialogue "+winner.id+" Private "+winner.name+" has won the battle");
				break;
			case 2:
				interpret("dialogue "+winner.id+" General "+winner.name+" has won the battle");
				break;
			case 3:
				interpret("dialogue "+winner.id+" Executive "+winner.name+" has won the battle");
				break;
			case 4:
				interpret("dialogue "+winner.id+" Admin "+winner.name+" has won the battle");
				break;
			case 5:
				interpret("dialogue "+winner.id+" Leader "+winner.name+" has won the battle");
				break;
			}
			interpret("successSpeech "+winner.id);
			for (Pokemon p: winner.party)
			{
				p.restoreStats();
				p.levelUp();
			}
			for (Pokemon p: loser.party)
			{
				p.restoreStats();
				p.levelUp();
			}
				
		}
		else
		{
			interpret("failureSpeech "+loser.id);
			switch(loser.rank)
			{
			case 0:
				interpret("dialogue "+winner.id+" Grunt "+loser.name+"has lost the battle");
				break;
			case 1:
				interpret("dialogue "+winner.id+" Private "+loser.name+"has lost the battle");
				break;
			case 2:
				interpret("dialogue "+winner.id+" General "+loser.name+"has lost the battle");
				break;
			case 3:
				interpret("dialogue "+winner.id+" Executive "+loser.name+"has lost the battle");
				break;
			case 4:
				interpret("dialogue "+winner.id+" Admin "+loser.name+"has won lost battle");
				break;
			case 5:
				interpret("dialogue "+winner.id+" Leader "+loser.name+"has has been defeated");
				break;
			}
			
			loser.coolDownTime=loser.coolDown;
			
			for (Pokemon p: winner.party)
			{
				p.restoreStats();
				p.levelUp();
			}
			
			for (int i=0; i<winActivators.size(); i++)
			{
				if (winActivators.get(i).id==loser.id)
					interpret("subProc "+winActivators.get(i).function);
			}
		}
			
		
		System.out.println(winner.name + " won $"+exchange);
	}
	
	private void endBattle()
	{
		System.out.println("Battle end between " + bc.p1.name + " and " + bc.p2.name);	
		bc.endSession();
		graphics.battle.reset();
	}
	
	private void subProc(Packet p)
	{
		if (p.params.size()!=1)
		{
			reportError("Error: Must have function name or event id",p.raw);
			return;
		}
		
		for (int i=0; i<subFunctions.size(); i++)
		{
			if (subFunctions.size()==0)
				return;
			try {
				if (subFunctions.size()==0)
					return;
				if (subFunctions.get(i).eventNumber==Integer.parseInt(p.params.get(0)))
				{
					for (int x=0; x<subFunctions.get(i).mapScript.size(); x++)
						if (subFunctions.get(i).mapScript.get(x).code.value==CommandCodes.ifCondition)
						{
							String concat = new String("if");
							for (String s: subFunctions.get(i).mapScript.get(x).params)
								concat= new String(concat+" "+s);
							interpret(concat);
						}
						else 
							execute(subFunctions.get(i).mapScript.get(x));
				}
			}catch (NumberFormatException e)
			{
				if (subFunctions.get(i).title.equals(p.params.get(0)))
					try{
						for (int x=0; x<subFunctions.get(i).mapScript.size(); x++)
							if (subFunctions.get(i).mapScript.get(x).code.value==CommandCodes.ifCondition)
							{
								String concat = new String("if");
								for (String s: subFunctions.get(i).mapScript.get(x).params)
									concat= new String(" "+s);
								interpret(concat);
								if (subFunctions.size()==0)
									return;
							}
							else
							execute(subFunctions.get(i).mapScript.get(x));
					}catch (IndexOutOfBoundsException e1)
				{
						return;
				}
					
			}catch (IndexOutOfBoundsException e1)
			{
				return;
		} 
			
		}
	}
	
	private void ifCondition(Packet p)
	{
		if (p.params.size()!=4)
		{
			reportError("Error: Must be in form: [p1] [CONDITION] [p2] [function]",p.raw);
			return;
		}
		
		if (p.params.get(1).length()>1)
		{
			reportError("Error: 2nd parameter must be conditional operator",p.raw);
			return;
		}
		
		if (!(p.params.get(0)==null || p.params.get(2)==null))
		{
			if (p.params.get(1).equals("<"))
				if (Integer.parseInt(p.params.get(0)) < Integer.parseInt(p.params.get(2)))
					interpret("subProc "+p.params.get(3));
			
			if (p.params.get(1).equals(">"))
				if (Integer.parseInt(p.params.get(0)) > Integer.parseInt(p.params.get(2)))
					interpret("subProc "+p.params.get(3));
			
			if (p.params.get(1).equals("="))
				if (p.params.get(0).equals(p.params.get(2)))
					interpret("subProc "+p.params.get(3));
		}
		
		if (p.params.get(1).equals("!"))
		{
			if(p.params.get(0)==null ^ p.params.get(2)==null)
			{
				interpret("subProc "+p.params.get(3));
			}
			else if(!(p.params.get(0).equals(p.params.get(2))))
				interpret("subProc "+p.params.get(3));
		}
	}
	
	//file system
	private boolean loadMap(Packet p) throws IOException
	{
		//map to load, start position x, y
		
		if (p.params.size()!=4)
		{
			reportError("Error: Must have 4 parameters (Map.WORLD, posx, posy, teleport id)",p.raw);
			return false;
		}
		
		winActivators.clear();
		subFunctions.clear();
		subParse=false;
		temp=null;
		
		int posx= Integer.parseInt(p.params.get(1));
		int posy= Integer.parseInt(p.params.get(2));
		
		Player plr = g.getPlayer(0);
		plr.posx=posx;
		plr.posy=posy;
		
		//set map
		graphics.panel.swapMap(Integer.parseInt(p.params.get(3)), g.getPlayer(0));
		
		//load map script
		String scpt = p.params.get(0).split("\\.")[0]+".scpt";
		interpret("loadScript "+scpt);
		
		graphics.battle.setMap(1);
		
		return true;
	}
	
	private void swapMap(Packet p)
	{
		
		if (p.params.size()!=4)
		{
			reportError("Error: Must have 4 parameters (Map.WORLD, posx, posy, orientation)",p.raw);
			return;
		}
		
		String world=p.params.get(0);
		int posx=0, posy=0, orientation;
		try{
			posx=Integer.parseInt(p.params.get(1));
			posy=Integer.parseInt(p.params.get(2));
			orientation=Integer.parseInt(p.params.get(1));
		}catch (NumberFormatException e)
		{
			reportError("Error: Parameters 2,3,4 must be integers",p.raw);
			return;
		}
		
		subFunctions.clear();
		winActivators.clear();
		
		//set map
		graphics.panel.swapMap(g.getPlayer(0), world,posx,posy,orientation);
		
		//load map script
		String scpt = world.split("\\.")[0]+".scpt";
		interpret("loadScript "+scpt);
		
		graphics.battle.setMap(1);
	}
	
	private void loadScript(Packet p)
	{
	if (p.params.size()!=1)
	{
		reportError("Error: Must have 1 parameter: Script.scpt",p.raw);
		return;
	}
		
		String mapLoad = new String(p.params.get(0));
		File file = new File(mapLoad);
		BufferedReader br;
		String line=new String();
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line=br.readLine())!=null)
				interpret(line);
		} catch (FileNotFoundException e) {
			reportError("Error: Script not found",p.raw);
			e.printStackTrace();
			return;
		} catch (IOException e) {
			reportError("Error: IO Exception",p.raw);
			e.printStackTrace();
			return;
		}
		
		
	}
	
	private void callFunction(Packet p)
	{
		
	}
	
	private void startProcedure(Packet p)
	{
		if (p.params.size()!=2)
		{
			reportError("Error: Must specify event code and function title",p.raw);
			return;
		}
		
		int id = Integer.parseInt(p.params.get(0));
		String title = p.params.get(1);
		
		temp = new scriptFunction();
		temp.eventNumber=id;
		temp.title=title;
		
		subParse=true;
	}
	
	private void endProcedure(Packet p)
	{
		if (p.params.size()!=0)
			reportError("Warning: Function takes no parameters",p.raw);
		
		subParse=false;
		subFunctions.add(temp);
		temp=null;
	}
	
	private void event(Packet p)
	{
		if (p.params.size()!=1)
		{
			reportError("Error: Must specify event to call",p.raw);
		}
		
		int id = Integer.parseInt(p.params.get(0));
		
		for (int x=0; x<subFunctions.size(); x++)
			if (subFunctions.get(x).eventNumber==id)
			{
				scriptFunction sf = subFunctions.get(x);
				for (int y=0; y<sf.mapScript.size(); y++)
				{
					if (sf.mapScript.get(y).code.value==CommandCodes.ifCondition)
					{
						String concat = new String("if");
						try
						{
						for (String s: sf.mapScript.get(y).params)
							concat= new String(concat+" "+s);
						interpret(concat);
						}catch(IndexOutOfBoundsException e)
						{
							return;
						}
					}else
						execute(sf.mapScript.get(y));
				}
				return;
			}
		
		reportError("Error: Specified event not available",p.raw);
	}
	
	private void givePokemon(Packet p)
	{
		if (p.params.size()!=2)
		{
			reportError("Error: Parameters are [Player id] [Pokemon name]",p.raw);
			return;
		}
		
		Player player = g.getPlayer(Integer.parseInt(p.params.get(0)));
		if (player.party.size()>5)
		{
			reportError("Error: Party already full",p.raw);
			return;
		}
		
		Pokemon pk = g.getPokemon(p.params.get(1));
		ArrayList <Attack> alist = new ArrayList <Attack>();
		
		if (pk==null)
		{
			reportError("Error: Pokemon not valid",p.raw);
		}
		
		if (pk.getType()==Type.getType(0))
		{
			Attack atk = new Attack("tackle",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			atk = new Attack("pound",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			atk = new Attack("headbutt",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			pk.setAttackList(alist);
		}
		
		if (pk.getType()==Type.getType(9))
		{
			Attack atk = new Attack("thunder",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			atk = new Attack("pound",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			atk = new Attack("thunder punch",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			pk.setAttackList(alist);
		}
		if (pk.getType()==Type.getType(8))
		{
			Attack atk = new Attack("ember",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			atk = new Attack("fire punch",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			atk = new Attack("fire blast",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			pk.setAttackList(alist);
		}
		if (pk.getType()==Type.getType(6))
		{
			Attack atk = new Attack("razor leaf",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			atk = new Attack("solar beam",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			atk = new Attack("tackle",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			pk.setAttackList(alist);
		}
		if (pk.getType()==Type.getType(11))
		{	
			Attack atk = new Attack("psychic",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			atk = new Attack("pound",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			atk = new Attack("psybeam",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			pk.setAttackList(alist);
		}
		if (pk.getType()==Type.getType(14))
		{
			Attack atk = new Attack("tackle",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			atk = new Attack("earthquake",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			atk = new Attack("horn drill",g);
			atk.setCurrentPP(Integer.parseInt("30"));
			alist.add(atk);
			pk.setAttackList(alist);
		}
		
		interpret("dialogue "+player.id +" recieved "+pk.name);
		player.AddPokemon(pk);
	}
	private void interact(Packet p)
	{
		//id, event type
		if (p.params.get(1).equals("baseSpeech"))
			interpret("baseSpeech "+Integer.parseInt(p.params.get(0)));
		else if (p.params.get(1).equals("idleSpeech"))
			interpret("baseSpeech "+Integer.parseInt(p.params.get(0)));
		else if (p.params.get(1).equals("failSpeech"))
			interpret("baseSpeech "+Integer.parseInt(p.params.get(0)));
		else if (p.params.get(1).equals("successSpeech"))
			interpret("baseSpeech "+Integer.parseInt(p.params.get(0)));
	}
	
	private void baseSpeech(Packet p)
	{
		if (p.params.size()!=1)
		{
			reportError("Error: Must have id",p.raw);
			return;
		}
		
		Player pl = g.getPlayer(Integer.parseInt(p.params.get(0)));
		
		if (pl.baseDialogue==null)
		{
			reportError("Error: No Dialogue Set",p.raw);
			return;
		}
		
		System.out.println(pl.baseDialogue);
		interpret("dialogue "+pl.id+" "+pl.baseDialogue);
	}
	
	private void idleSpeech (Packet p)
	{
		if (p.params.size()!=1)
		{
			reportError("Error: Must have id",p.raw);
			return;
		}
		
		Player pl = g.getPlayer(Integer.parseInt(p.params.get(0)));
		
		if (pl.idleDialogue==null)
		{
			reportError("Error: No Dialogue Set",p.raw);
			return;
		}
		interpret("dialogue "+pl.id+" "+pl.idleDialogue);
		System.out.println(pl.idleDialogue);
	}
	
	private void failSpeech (Packet p)
	{
		if (p.params.size()!=1)
		{
			reportError("Error: Must have id",p.raw);
			return;
		}
		
		Player pl = g.getPlayer(Integer.parseInt(p.params.get(0)));
		
		if (pl.failureDialogue==null)
		{
			reportError("Error: No Dialogue Set",p.raw);
			return;
		}
		interpret("dialogue "+pl.id+" "+pl.failureDialogue);
		System.out.println(pl.failureDialogue);
	}
	
	private void successSpeech (Packet p)
	{
		if (p.params.size()!=1)
		{
			reportError("Error: Must have id",p.raw);
			return;
		}
		
		Player pl = g.getPlayer(Integer.parseInt(p.params.get(0)));
		
		if (pl.successDialogue==null)
		{
			reportError("Error: No Dialogue Set",p.raw);
			return;
		}
		interpret("dialogue "+pl.id+" "+pl.successDialogue);
		System.out.println(pl.successDialogue);
	}
	
	private void dialogue(Packet p)
	{
		int mode = graphics.getMode();
		
		if (mode==graphics.WORLD_FRAME)
		{
			if (p.params.size()<2)
			{
				reportError("Error: Must have parameters [player id] [text]",p.raw);
				return;
			}
			
			Player actor = g.getPlayer(Integer.parseInt(p.params.get(0)));
			
			String s=new String();
			
			for (int i=1; i<p.params.size(); i++)
				s=new String(s+" "+p.params.get(i));
			
			graphics.panel.dialogueBox(actor, s);
		}else if (mode==graphics.BATTLE_FRAME)
		{
			String s = new String();
			for (int i=1; i<p.params.size(); i++)
				s=new String(s+" "+p.params.get(i));
			graphics.battle.setDialogue(s);
		}else
		{
			reportError("Error: Must be in battle or world",p.raw);
		}
	}
	
	private void placeActor (Packet p)
	{
		if (p.params.size()!=3)
		{
			reportError("Error: Must have 3 parameters: id, x, y",p.raw);
			return;
		}
		
		int id = Integer.parseInt(p.params.get(0));
		
		if (!g.isPlayerDefined(id))
		{
			reportError("Error: Player ID undefined",p.raw);
			return;
		}
		g.getPlayer(id).posx=48*Integer.parseInt(p.params.get(1));
		g.getPlayer(id).posy=48*Integer.parseInt(p.params.get(2));
		graphics.panel.addActor(g.getPlayer(id));
		
	}
	
	private void moveActor (Packet p)//implement
	{
		if (p.params.size()!=3)
		{
			reportError("Error: Must have 3 parameters: id, x, y",p.raw);
			return;
		}
		
		int id = Integer.parseInt(p.params.get(0));
		
		if (!g.isPlayerDefined(id))
		{
			reportError("Error: Player ID undefined",p.raw);
			return;
		}
		g.getPlayer(id).posx=48*Integer.parseInt(p.params.get(1));
		g.getPlayer(id).posy=48*Integer.parseInt(p.params.get(2));
	}
	
	private void setSpeech (Packet p)
	{
		if (p.params.size()<3)
		{
			reportError("Error: Must be in form: mode, id, text",p.raw);
			return;
		}
		
		Player player = g.getPlayer(Integer.parseInt(p.params.get(1)));
		if (player==null)
		{
			reportError("Error: Parameter 2 must be valid player id",p.raw);
			return;
		}
		
		String s = new String(p.params.get(2));
		for (int i=3; i<p.params.size(); i++)
			s= new String(s+" "+p.params.get(i));
		
		if (p.params.get(0).equals("base"))
			player.baseDialogue = new String(s);
		else if (p.params.get(0).equals("fail"))
			player.failureDialogue = new String(s);
		else if (p.params.get(0).equals("success"))
			player.successDialogue = new String(s);
		else if (p.params.get(0).equals("idle"))
			player.idleDialogue = new String(s);
		else
		{
			reportError("Error: Must be valid mode (base, fail, success, idle)",p.raw);
			return;
		}
			
	}
	
	private void loadGame(Packet p)
	{
	}
	
	private void saveGame(Packet p)
	{
	}
	
	private void battleWin(Packet p)
	{
		if (p.params.size()!=2)
		{
			reportError("Error: Must have id and function call",p.raw);
		}
		
		PersonalFunction pf = new PersonalFunction();
		pf.id=Integer.parseInt(p.params.get(0));
		pf.function=p.params.get(1);
		
		winActivators.add(pf);
	}
	
	private void loadGameDialogue(Packet p)
	{
		if (p.params.size()!=0)
		{
			reportError("Error: Function has no parameters",p.raw);
		}
		graphics.switchFrame(Renderer.LOAD_FRAME);
	}
	
	private void newGameDialogue(Packet p)
	{
		if (p.params.size()!=0)
		{
			reportError("Error: Function has no parameters",p.raw);
		}
		
		graphics.switchFrame(Renderer.NEW_FRAME);
	}
	
	private void battleDialogue(Packet p)
	{
		if (p.params.size()!=0)
		{
			reportError("Error: Function has no parameters",p.raw);
		}
		
		graphics.switchFrame(Renderer.BATTLE_FRAME);
	}
	
	private void setWindow(Packet p)
	{
		if (p.params.size()!=1)
		{
			reportError("Error: Function has 1 parameter",p.raw);
		}
		
		if (p.params.get(0).equals("new"))
		{
			graphics.switchFrame(graphics.NEW_FRAME);
		} else if (p.params.get(0).equals("load"))
		{
			graphics.switchFrame(graphics.LOAD_FRAME);
		} else if (p.params.get(0).equals("main"))
		{
			graphics.switchFrame(graphics.MENU_FRAME);
		} else if (p.params.get(0).equals("world"))
		{
			graphics.switchFrame(graphics.WORLD_FRAME);
		} else if (p.params.get(0).equals("battle"))
		{
			graphics.switchFrame(graphics.BATTLE_FRAME);
		} else
		{
			reportError("Error: Parameters are: new, load, main, game, battle",p.raw);
		}
	}
	
	//attack 1, attack 2 --only supports actor vs. actor battle. No wild
	private boolean attack(Packet p)
	{		
		if (p.params.size()!=2 && p.params.size()!=1)
		{
			reportError("Error: Function accepts 2 parameters (atk1, atk2)",p.raw);
			return false;
		}
		
		if (!bc.battleActive)
		{
			reportError("Error: No current battle",p.raw);
			return false;
		}
		
		
		Pokemon pk1 = bc.p1Active;
		Pokemon pk2 = bc.p2Active;
		
		if (pk1.isKO() || pk2.isKO())
		{
			reportError("Defeated pokemon must be replaced",p.raw);
			return false;
		}
		
		if (pk1.isKO() || pk2.isKO())
		{
			System.out.println("Must swap to useable pokemon");
			return false;
		}
		
		//get attacks
		Attack a1=null, a2=null;
		
		if (p.params.size()==1)//AI
		{
			int indx = pk1.getAttackIndex(p.params.get(0));
			if (indx==-1)
			{
				reportError("Error: Pokemon 1 does not have requested attack (atk1, atk2)",p.raw);
				return false;
			}
			
			a1 = pk1.getAttack(indx);
			a2 = AI.battleAI(bc.p2, bc.p1, pk2, pk1);
			
		}else//explicit
		{
			int indx = pk1.getAttackIndex(p.params.get(0));
			if (indx==-1)
			{
				reportError("Error: Pokemon 1 does not have requested attack (atk1, atk2)",p.raw);
				return false;
			}
			
			a1 = pk1.getAttack(indx);
			
			indx = pk2.getAttackIndex(p.params.get(1));
			if (indx==-1)
			{
				reportError("Error: Pokemon 2 does not have requested attack (atk1, atk2)",p.raw);
				return false;
			}
			
			a2 = pk2.getAttack(indx);
		}
		
		
		
		int h1=pk1.getHealth();
		int h2=pk2.getHealth();
		
		bm.resetWinner();
		bm.executeRound(pk1, pk2, a1, a2);
		
		interpret("dialogue "+pk1.name+" used "+a1.name+"\n"+pk2.name+" used "+a1.name);
		
		graphics.battle.setPokeHealthPercentage(1, pk1.getHealth()/(double)pk1.getBaseHealth());
		graphics.battle.setPokeHealthPercentage(2, pk2.getHealth()/(double)pk2.getBaseHealth());
		
		System.out.println(pk1.name+" took "+(h1-pk1.getHealth()+" damage. Health is "+pk1.getHealth()));
		System.out.println(pk2.name+" took "+(h2-pk2.getHealth()+" damage. Health is "+pk2.getHealth()));
		
		Pokemon winner = bm.getWinner();
		
		if (winner!=null)//announce battle change
		{
			if (winner==pk1)
			{
				pk1.increaseEXP((int)(pk2.getLevel()*9.5)/7);
				System.out.println(bc.p2.name+"'s "+pk2.name+" fainted\n"+bc.p1.name+"'s "+pk1.name+" is the winner.");
				interpret("dialogue "+bc.p2.name+"'s "+pk2.name+" fainted\n"+bc.p1.name+"'s "+pk1.name+" is the winner.");
				System.out.println("Player gained "+(int)((pk2.getLevel()*9.5)/7)+" exp");
				interpret("dialogue "+"Player gained "+(int)((pk2.getLevel()*9.5)/7)+" exp");
				if (!bc.p2.hasReadyPokemon())
				{
					System.out.println(bc.p2.name+" is out of pokemon");
					bc.p2.coolDownTime=bc.p2.coolDown;
					declareWinner(bc.p1,bc.p2);
					bc.endSession(bc.p1.name);
					graphics.switchFrame(graphics.WORLD_FRAME);
					return true;
				}else
				{
					System.out.println("Choose next pokemon");
					interpret("dialogue Choose next pokemon");
					for (int x=0; x<bc.p2.party.size();x++)
						if (!bc.p2.party.get(x).isKO())
							System.out.println(bc.p2.party.get(x).name+"\t"+x);
				}
				interpret("swapParty "+bc.p2.id+" "+AI.switchAI(bc.p2, bc.p1, bc.p1Active, bc.p2.party).id);
			}else
			{
				pk2.increaseEXP((int)(pk1.getLevel()*9.5)/7);
				interpret("dialogue "+bc.p1.name+"'s "+pk1.name+" fainted\n"+bc.p2.name+"'s "+pk2.name+" is the winner.");
				System.out.println(bc.p1.name+"'s "+pk1.name+" fainted\n"+bc.p2.name+"'s "+pk2.name+" is the winner.");
				System.out.println("Opponent gained "+(int)((pk2.getLevel()*9.5)/7)+" exp");
				interpret("dialogue "+"Opponent gained "+(int)((pk2.getLevel()*9.5)/7)+" exp");
				if (!bc.p1.hasReadyPokemon())
				{
					System.out.println(bc.p1.name+" is out of pokemon.");
					interpret("dialogue "+bc.p1.name+" is out of pokemon.");
					
					declareWinner(bc.p2,bc.p1);
					bc.endSession(bc.p2.name);
					graphics.switchFrame(graphics.WORLD_FRAME);
					return true;
				}else
				{
					System.out.println("Choose next pokemon");
					for (int x=0; x<bc.p2.party.size();x++)
						if (!bc.p2.party.get(x).isKO())
							System.out.println(bc.p2.party.get(x).name+"\t"+x);
				}
			}
			bm.resetWinner();
		}
		
		return true;
	}
	
	private void loadScript(String fName) throws IOException
	{
		File file = new File(fName);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String buffer=new String();
		
		while ((buffer=br.readLine())!=null)
		{
			interpret(buffer);
		}
		
		br.close();
	}
	
	public unownInterpreter(gameGlobal g, BattleManager bm, data d, Renderer graphics)
	{
		this.g=g;
		this.bm=bm;
		this.system = d;
		this.graphics=graphics;
		this.r= new Random();
	}
	
	public void interpret(String raw)
	{
		//creates command code with parameters. No tokenizing
		Packet p = new Packet(raw.split(" "));
		if (p.code==null)
			return;
		
		for (int x=0; x<p.params.size(); x++)
		{
			//identify and replace macros
			if (p.params.get(x).length()<2)
				continue;
			
			String param = p.params.get(x);
			//check for identifier
			String check = new String(param.substring(1, param.length()));
			if (check.length()==0)
				continue;
			char c = p.params.get(x).charAt(0);
			switch (c)
			{
			case '$'://special cache/register
				if (check.equals("register"))
					param = system.lastResult;
				if (check.equals("Opponent"))
					param = Integer.toString(bc.p2.id);
				if (check.equals("opponentActiveParty") || check.equals("OAP"))
					param = Integer.toString(bc.p2Active.id);
				if (check.equals("playerActiveParty") || check.equals("PAP"))
					param = Integer.toString(bc.p1Active.id);
				break;
			case '#'://variable
				if (subParse)
					continue;
				int index = system.variables.indexOf(check);
				if (index==-1)
				{
					param="";
					reportError("Warning: symbol undefined",p.raw);
				}
				else
					param=system.values.get(index);
				break;
			case '&'://special function codes (may not need)
				
				break;
			case ';'://comment
				continue;
			}
			p.params.set(x, param);//rewrite value with parsed one
			
		}
		
		execute(p);
	}

	
	//distribute or execute messages
	public void execute(Packet p)
	{
		if (subParse && p.code.value!=-1506418632)
		{
			temp.mapScript.add(p);
			return;
		}
		
		switch (p.code.value)
		{
			//battle
		case CommandCodes.startBattle:
			startBattle(p);
			break;
		case CommandCodes.endBattle:
			endBattle();
			break;
		case CommandCodes.attack:
			attack(p);
			break;
		case CommandCodes.swapParty:
			changeActivePokemon(p);
			break;
			//items
			
			//AI
			
			//System
		case CommandCodes.quitGame:
			quitGame();
			break;
		case CommandCodes.print:
			print(p);
			break;
		case CommandCodes.ifCondition:
			ifCondition(p);
			break;
		case CommandCodes.setVar:
			setVar(p);
			break;
		case CommandCodes.deleteVar:
			deleteVar(p);
			break;
		case CommandCodes.event:
			event(p);
			break;
		case CommandCodes.placeActor:
			placeActor(p);
			break;
		case CommandCodes.moveActor:
			moveActor(p);
			break;
		case CommandCodes.interact:
			interact(p);
			break;
		case CommandCodes.setSpeech:
			setSpeech(p);
			break;
		case CommandCodes.baseSpeech:
			baseSpeech(p);
			break;
		case CommandCodes.idleSpeech:
			idleSpeech(p);	
			break;
		case CommandCodes.failSpeech:
			failSpeech(p);
			break;
		case CommandCodes.successSpeech:
			successSpeech(p);
			break;
		case CommandCodes.dialogue:
			dialogue(p);
			break;
		case CommandCodes.battleWin:
			battleWin(p);
			break;
		case CommandCodes.givePokemon:
			givePokemon(p);
			break;
			
			//File Sys
		case CommandCodes.loadMap:
			try {
				loadMap(p);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case CommandCodes.swapMap:
			swapMap(p);
			break;
		case CommandCodes.loadScript:
			loadScript(p);
			break;
		case CommandCodes.callFunction:
			callFunction(p);
			break;
		case CommandCodes.startProcedure:
			startProcedure(p);
			break;
		case CommandCodes.endProcedure:
			endProcedure(p);
			break;
		case CommandCodes.subProc:
			subProc(p);
			break;
		case CommandCodes.saveGame:
			saveGame(p);
			break;
		case CommandCodes.loadGame:
			loadGame(p);
			break;
		case CommandCodes.loadGameDialogue:
			loadGameDialogue(p);
			break;
		case CommandCodes.newGameDialogue:
			newGameDialogue(p);
			break;
		case CommandCodes.battleDialogue:
			battleDialogue(p);
			break;
		case CommandCodes.setWindow:
			setWindow(p);
			break;
		default:
			return;
		}	
	}
}

/*
 *                              .,''':`                                                
                        `+#+''++++++#@:                                             
                      ++:;;;''''''+++++@,                                           
                    +;:::;;;;;;;;;;;'++++#                                          
                  .+:::::;;;;;;;;;;;;;'+++@`                                        
                 ';:::::::;;;;;;;;;;;;;;'++#;                                       
                '::::::::;;;+++';;;;;;;;;'++#:                                      
               ':::::::;;#;` ````'+;;;;;;;'++#;                                     
              +::::::;;#       ````:';;;;;;'++#.                                    
             ;::::::;@`         `````#;;;;;;'++@                                    
             ;:::::;+            `````#;;;;;;'+++                                   
            #:::::';              ``.`,';;;;;'+++`                                  
           ;::::;';              ``````#;;;;;;'++#                                  
           ;::::;;`   ``         ```````+;;;;;'++#                                  
          +::::;@```````        ````````#;;;;;;'+++                                 
          ';:::' `` ````      `````.```.`;;;;;;'++#                                 
         ':;::;+`    ```       ```......`+;;;;;'+++                                 
         #;;;;;`      ``      `````.....`#;;;;;''+++                                
         ;;;;;#         ;#@; ```````.....+;;;;;;'+######''':,:,.,..,..              
        :;;;;;+`       +  #@ ````````..``';;;;;+;;;;;;;'''';;;;;;;;;;;''+++#@;      
        #;;;;;.        #  ##`````````````;;;;;';;;;;;;;;;;;';;;;;;;;;;;;;;;'++;     
        #;;;;'        `#:'+#`````````````';;;;;;;;;;;;;;'''';;;;;;;;;;;;;;;'++#     
        ';;;;'        `##''#`````````````';;;;;''''+++''''''''''''''''';;;'+++'     
        ;;;;;'     `   #+'''`````````````#;;;;;''+++++++++++++++++++++';;;'++@      
        ';;;;+```  ````;#+'``````````````+;;;;;'++#@;:::;+#@@@#######+';;'+++:      
        ';;;;+`````````````..````````````';;;';;'++#                 #;;'+++@       
        ';;;;'``````````````..``.```````';;;''';;;'+                +;;''++#        
        #;;;;;.``````````````````````.``#;;''''';;;;#              .;;''++#`        
        @;;;;;;``````````````````````...;;;''+'''';;;',           ,+;;'++++         
        +;;;;;+``````````````````````..#';;'''++''';;;;#          #;;'+++@          
        `';;;;+``````````````````````.,'';''''#+++''';;'+,       +'''+++#           
         #;;;;;'``````````````````````#';;;''++##++''';;';#     .;'''+++,           
         .;;;;;+`````````````````````+;;;;;;'+++@@+++'';''''#  .''''++++            
          #;;;;;@```````````````````,;;'';;'++++` ;##+'';;;;;'#''''+++@             
          :;;;;;;'``````````````````';;'';'++++@   `@+++'';;;;;'''+++#              
           @;;;;;;#```````````````.+;;;;'''+#+#      #++++''';;;'+++#.              
           `+;;;;;'+`````````````:';;;;;''+++#.       ,#+++'''''++++'               
            ;;;;;;;;#``.```````.#;;;;;;''++++;          #++++++++++@                
             @;;;'';;'@#````,++;;;;;;;'+++++'            ,#+++++++@                 
              @;;''';;;;;;;;;;;;;;;;''+++++:               ###+++#                  
               @;'';;;;;;;;;;;;;;;''+++++#,                 .@++#,                  
                @;;;;;;;;;;;;;;;'''+++++@`                    ;#                    
                 +';;;;;;;;;;''''+++++#'                                            
                  .@';'';;;;'''+++++##                                              
                    .#';';;;''++++#+                                                
                      `';;;''++##.                                                  
                      ,';;;'++#                                                     
                      ';;;;'++@         :#+`                                        
                      +;;;;'++#       +#+++##                                       
                      @;;;;'++'   `'@+'++++++.                                      
                      #;;;;'+#+#@+;;;;'++++++'                                      
                      +;;;;;;;;;;;;;;;'+++++++                                      
                      ';;;;;;;;;;;;;;;;'+++++'                                      
                     .;;;;;;;;;;;;;;;'''++++@                                       
                     +;;;;;;''''''''''+++++@                                        
                     #;;;''++++++++++++++@.                                         
                     ';;;'++++++++++++##.                                           
                    `';;;'+#@##@@@#',`                                              
                    ;;';''+++                                                       
                    #;';'+#+.                                                       
                    +;';'+++                                                        
                    ;;';'++#                                                        
                   ;;;'''++@                                                        
                   #;''''++'                                                        
                   ';;''+++,                                                        
                  ;;;;;'++#                                                         
                  #;;;;'++#                                                         
                  ';;;;'++@                                                         
                 #;;;;;'+++                                                         
               `+';;;;;'++'                                                         
              #;;#;;;';;'+#                                                         
           ,#';;;';;;'';;''@                                                        
        `#+;;;;;;;;;;';;;;;'+,                                                      
      ,+;;;;;;;;;'';;;;;;;;;''@`                                                    
     `+;;;;;;;;'''+''';;;;;;;;'+#                                                   
     +;;;;;;'''+++++++';;;;;;;;''#.                                                 
     +;;;;'''++++++##++';;;;;;;'''+`                                                
     `''''++++++##`  +#+'';;;;;''''#                                                
      @+++++++#+       #++';;;;'++++                                                
       '#####,          @++';;'++++#                                                
                         '+++''++++#                                                
                          .@+++++++:                                                
                            :@+++##                                                 
                              `;#,                                                  

 */
