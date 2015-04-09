package scriptEngine;

import gameElements.Attack;
import gameElements.Player;
import gameElements.Pokemon;
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

import system.data;

class scriptFunction
{
	public int eventNumber=0;
	public ArrayList <Packet> mapScript = new ArrayList <Packet>();
}

public class unownInterpreter {
	
	//connection to database
	gameGlobal g = null;
	BattleManager bm = null;
	CommandCodes codes;
	data system = null;
	BattleCache bc=new BattleCache();
	Renderer graphics=null;
	
	ArrayList <scriptFunction> subFunctions = new ArrayList <scriptFunction>();
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
			bc.p1Active=pkmn;
		else
			bc.p2Active=pkmn;
		
		System.out.println(player.name + " sends out " + pkmn.name);
		
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
		
		switch (psize)
		{
		case 2:
			if (!Utility.isInteger(p.params.get(0)) || !Utility.isInteger(p.params.get(1)))
			{
				reportError("Error: Parameters must be (int actor id, int actor id)",p.raw);
				return false;
			}
			
			Player p1 = g.getPlayer(Integer.parseInt(p.params.get(0)));
			Player p2 = g.getPlayer(Integer.parseInt(p.params.get(1)));
			
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
		
		return true;
	}
	
	private void declareWinner(Player winner, Player loser)
	{
		int exchange = (int)(loser.money*0.2)+5;
		if (loser.money<exchange)
			exchange=loser.money;
		loser.money-=exchange;
		winner.money+=exchange;
		
		System.out.println(winner.name + " won $"+exchange);
	}
	
	private void endBattle()
	{
		System.out.println("Battle end between " + bc.p1.name + " and " + bc.p2.name);	
		bc.endSession();
	}
	
	//file system
	private boolean loadMap(Packet p) throws IOException
	{
		//map to load, start position x, y
		
		if (p.params.size()!=4)
		{
			reportError("Error: Must have 3 parameters (Map.WORLD, posx, posy, teleport id)",p.raw);
			return false;
		}
		
		subFunctions.clear();
		subParse=false;
		temp=null;
		
		int posx= Integer.parseInt(p.params.get(1));
		int posy= Integer.parseInt(p.params.get(2));
		
		Player plr = g.getPlayer(0);
		plr.posx=posx;
		plr.posy=posy;
		
		//set map
		graphics.panel.swapMap(Integer.parseInt(p.params.get(3)));
		
		//load map script
		String scpt = p.params.get(0).split("\\.")[0]+".scpt";
		interpret("loadScript "+scpt);
		
		return true;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void callFunction(Packet p)
	{
		
	}
	
	private void startProcedure(Packet p)
	{
		if (p.params.size()!=1)
		{
			reportError("Error: Must specify event code",p.raw);
		}
		
		int id = Integer.parseInt(p.params.get(0));
		
		temp = new scriptFunction();
		temp.eventNumber=id;
		
		subParse=true;
	}
	
	private void endProcedure(Packet p)
	{
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
					execute(sf.mapScript.get(y));
				}
				return;
			}
		
		reportError("Error: Specified event not available",p.raw);
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
		g.getPlayer(id).posx=Integer.parseInt(p.params.get(1));
		g.getPlayer(id).posy=Integer.parseInt(p.params.get(2));
		graphics.panel.addActor(g.getPlayer(id));
		
	}
	
	
	//attack 1, attack 2 --only supports actor vs. actor battle. No wild
	private boolean attack(Packet p)
	{		
		if (p.params.size()!=2)
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
			System.out.println("Must swap to useable pokemon");
			return false;
		}
		
		//get attacks
		int indx = pk1.getAttackIndex(p.params.get(0));
		if (indx==-1)
		{
			reportError("Error: Pokemon 1 does not have requested attack (atk1, atk2)",p.raw);
			return false;
		}
		
		Attack a1 = pk1.getAttack(indx);
		
		indx = pk2.getAttackIndex(p.params.get(1));
		if (indx==-1)
		{
			reportError("Error: Pokemon 2 does not have requested attack (atk1, atk2)",p.raw);
			return false;
		}
		
		Attack a2 = pk2.getAttack(indx);
		
		int h1=pk1.getHealth();
		int h2=pk2.getHealth();
		
		bm.resetWinner();
		bm.executeRound(pk1, pk2, a1, a2);
		
		System.out.println(pk1.name+" took "+(h1-pk1.getHealth()+" damage. Health is "+pk1.getHealth()));
		System.out.println(pk2.name+" took "+(h2-pk2.getHealth()+" damage. Health is "+pk2.getHealth()));
		
		Pokemon winner = bm.getWinner();
		
		if (winner!=null)//announce battle change
		{
			if (winner==pk1)
			{
				pk1.increaseEXP((int)(pk2.getLevel()*9.5)/7);
				System.out.println(bc.p2.name+"'s "+pk2.name+" fainted\n"+bc.p1.name+"'s "+pk1.name+" is the winner.");
				if (!bc.p2.hasReadyPokemon())
				{
					System.out.println(bc.p2.name+" is out of pokemon");
					declareWinner(bc.p1,bc.p2);
					bc.endSession(bc.p1.name);
					return true;
				}else
				{
					System.out.println("Choose next pokemon");
					for (int x=0; x<bc.p2.party.size();x++)
						if (!bc.p2.party.get(x).isKO())
							System.out.println(bc.p2.party.get(x).name+"\t"+x);
				}
			}else
			{
				pk2.increaseEXP((int)(pk1.getLevel()*9.5)/7);
				System.out.println(bc.p1.name+"'s "+pk1.name+" fainted\n"+bc.p2.name+"'s "+pk2.name+" is the winner.");
				if (!bc.p1.hasReadyPokemon())
				{
					System.out.println(bc.p1.name+" is out of pokemon.");
					declareWinner(bc.p2,bc.p1);
					bc.endSession(bc.p2.name);
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
	
	public void loadScript(String fName) throws IOException
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
			char c = p.params.get(0).charAt(0);
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
				int index = system.variables.indexOf(check);
				if (index==-1)
				{
					param="";
					reportError("Error: symbol undefined",p.raw);
					return;
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
		if (subParse)
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
			
			//File Sys
		case CommandCodes.loadMap:
			try {
				loadMap(p);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
