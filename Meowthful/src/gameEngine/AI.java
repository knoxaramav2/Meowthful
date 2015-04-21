package gameEngine;

import gameElements.Attack;
import gameElements.Player;

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
	
	public static void actorMove(Player actor, int mode)
	{
		
	}
	
	public static Attack battleAI(Player p1, Player p2)
	{
		
		return null;
	}
	
}
