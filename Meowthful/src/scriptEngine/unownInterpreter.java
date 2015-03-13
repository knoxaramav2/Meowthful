package scriptEngine;

import gameElements.Player;
import gameElements.Pokemon;
import gameEngine.BattleManager;
import gameEngine.gameGlobal;

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
	
	
	private void quitGame()
	{
		system.active=false;
	}
	
	private void reportError(String mess, String[]raw)
	{
		System.out.print(mess+" : \t");
		for (String ele: raw)
			System.out.print(ele+" ");
		System.out.println("");
	}
	
	private boolean changeActivePokemon(ArrayList<String> params)
	{
		//swap out active pokemon
		
		return true;
	}
	
	private boolean startBattle(ArrayList<String> params)
	{
		//find active pokemon of each
		
		return true;
	}
	
	private boolean attack(ArrayList<String> params)
	{
		//enemy  pokemon attack attack
		
		//get pokemon
		Player p1 = g.getPlayer(0);//user player
		Player p2 = g.getPlayer(Integer.parseInt(params.get(0)));
		
		Pokemon pk1 = p1.party.get(0);
		Pokemon pk2 = p2.party.get(0);
		
		//get attacks
		
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
				if (check=="register")
					param = system.lastResult;
				if (check=="lastOpponent")
					param = Integer.toString(system.enemy.id);
				if (check=="lastEnemyPokemon")
					param = Integer.toString(system.enemy.party.get(0).id);
				if (check=="lastPlayerPokemon")
					param = Integer.toString(system.player.party.get(0).id);
				break;
			case '#'://variable
				int index = system.variables.indexOf(check);
				if (index==-1)
				{
					param="";
					reportError("Error: symbol undefined",p.raw);
					break;
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
