package gameEngine;

import gameElements.Attack;
import gameElements.Player;
import gameElements.Pokemon;

/*
 * Handles game AI's from battle to movement
 * 
 */

public class AI {

	public static int MapAI_Mode_1	=	1;//stand still
	public static int MapAI_Mode_2	=	2;//stand in place, rotate
	public static int MapAI_Mode_3	=	3;//walk 5 spaces horizontally
	public static int MapAI_Mode_4	=	4;//walk 4 spaces vertically
	public static int MapAI_Mode_5	=	5;//free move
	
	int step_cycle=0;
	
	public void actorMove(int mode, boolean[] pos)
	{	
		pos[0]=false;
		pos[1]=true;
		pos[2]=false;
		pos[3]=false;
	}
	
	public static Attack battleAI(Player p1, Player p2, Pokemon pk1, Pokemon pk2)
	{
		
		return null;
	}
	
}
