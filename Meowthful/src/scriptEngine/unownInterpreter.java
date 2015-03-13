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
	
	private void quitGame()
	{
		system.active=false;
	}
	
	private void reportError(String mess, String[]raw)
	{
		System.out.print(mess+" : \t");
		for (String ele: raw)
			System.out.print(ele);
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
		if (p.hash==null)
			return;
		
		for (String param: p.params)
		{
			//identify and replace macros
			if (param.length()<2)
				continue;
			
			//check for identifier
			String check = new String(param);
			char c = check.charAt(0);
			check = check.replace(""+c, "");
			switch (c)
			{
			case '$'://cache reference
				param = system.lastResult;
				break;
			case '#':
				
				break;
			case ';'://comment
				continue;
			}
		}
		
		System.out.println(p.hash.toString());
		System.out.println(p.hash);
		
		for (String param: p.params)
			System.out.println(param);
		
		execute(p);
	}
	
	//distribute or execute messages
	public void execute(Packet p)
	{
		//battle
		//if (p.hash==codes.attack)
			if (attack(p.params)==false)
				reportError("Bad parameter set",p.raw);
		
		//items
		
		//AI
		
		//System
		//if (p.hash==codes.quitGame)
			quitGame();
		
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
