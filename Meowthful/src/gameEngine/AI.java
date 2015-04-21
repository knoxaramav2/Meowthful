package gameEngine;

import gameElements.Attack;
import gameElements.Player;
import gameElements.Pokemon;

/*
 * Handles game AI's from battle to movement
 * 
 */

public class AI {

	public final static int MapAI_Mode_1	=	1;//stand still
	public final static int MapAI_Mode_2	=	2;//stand in place, rotate
	public final static int MapAI_Mode_3	=	3;//walk 5 spaces horizontally
	public final static int MapAI_Mode_4	=	4;//walk 4 spaces vertically
	public final static int MapAI_Mode_5	=	5;//free move
	
	int step_cycle=0;
	
	public void actorMove(int mode, boolean[] pos, boolean next)
	{	
		switch (mode)
		{
		case MapAI_Mode_1:
			pos[0]=false;
			pos[1]=false;
			pos[2]=false;
			pos[3]=false;
			break;
		case MapAI_Mode_2:
			pos[0]=false;
			pos[1]=false;
			pos[2]=false;
			pos[3]=false;
			break;
		case MapAI_Mode_3:
			pos[0]=false;
			pos[1]=false;
			pos[2]=false;
			pos[3]=false;
			break;
		case MapAI_Mode_4:
			pos[0]=false;
			pos[1]=false;
			pos[2]=false;
			pos[3]=false;
			break;
		case MapAI_Mode_5:
			pos[0]=false;
			pos[1]=false;
			pos[2]=false;
			pos[3]=false;
			break;
		}
	}
	
	public static Attack battleAI(Player p1, Player p2, Pokemon pk1, Pokemon pk2)
	{
		
		return null;
	}
	
}
