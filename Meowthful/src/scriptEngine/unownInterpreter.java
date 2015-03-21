package scriptEngine;

import gameElements.Attack;
import gameElements.Player;
import gameElements.Pokemon;
import gameEngine.BattleCache;
import gameEngine.BattleManager;
import gameEngine.gameGlobal;
import gameEngine.Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import system.data;



public class unownInterpreter {
	
	//connection to database
	gameGlobal g = null;
	BattleManager bm = null;
	CommandCodes codes;
	data system = null;
	BattleCache bc=new BattleCache();
	
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
		for (String ele: raw)
			System.out.print(ele+" ");
		System.out.println("");
	}
	
	//player/opponent, to_switch
	private boolean changeActivePokemon(Packet p)
	{
		//swap out active pokemon
		
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
	
	private void endBattle()
	{
		System.out.println("Battle end between " + bc.p1.name + " and " + bc.p2.name);	
		bc.endSession();
	}
	
	//attack 1, attack 2
	private boolean attack(Packet p)
	{		
		if (p.params.size()!=2)
		{
			reportError("Error: Function accepts 2 parameters (atk1, atk2)",p.raw);
			return false;
		}
		
		Pokemon pk1 = bc.p1.party.get(0);
		Pokemon pk2 = bc.p2.party.get(0);
		
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
		
		bm.executeRound(pk1, pk2, a1, a2);
		
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
	}
	
	public unownInterpreter(gameGlobal g, BattleManager bm, data d)
	{
		this.g=g;
		this.bm=bm;
		this.system = d;
	}
	
	public void interpret(String raw)
	{
		//creates command code with parameters. No tokenizing
		Packet p = new Packet(raw.split(" |\\+|\\,|/|\\-|\\*|\\%"));
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
	@SuppressWarnings("static-access")
	public void execute(Packet p)
	{
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
