package gameEngine;

import java.util.ArrayList;
import java.util.Random;

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
	private static Random r = new Random();
	
	int step_cycle=0;
	int time=0;
	
	//0 = up, 1 = down, 2 = left, 3 = right
	
	public void actorMove(int mode, boolean[] pos, boolean next, int t)
	{	
		if (time==0)
			time=t;
		if (time==t || !next)
			return;
			
		time=t;

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
			if (step_cycle++<3)
			{
				pos[0]=false;
				pos[1]=false;
				pos[2]=true;
				pos[3]=false;
			}
			else 
				if (step_cycle<6)
				{
					pos[0]=false;
					pos[1]=false;
					pos[2]=false;
					pos[3]=true;
				}
				else
					step_cycle=0;
			break;
		case MapAI_Mode_4:
			if (step_cycle++<3)
			{
				pos[0]=true;
				pos[1]=false;
				pos[2]=false;
				pos[3]=false;
			}
			else 
				if (step_cycle++<6)
				{
					pos[0]=false;
					pos[1]=true;
					pos[2]=false;
					pos[3]=false;
				}
				else
					step_cycle=0;
			break;
		case MapAI_Mode_5:
			pos[0]=false;
			pos[1]=false;
			pos[2]=false;
			pos[3]=false;
			break;
		}
	}
	
	//npc actor, player actor, npc pokemon, player pokemon
	public static Attack battleAI(Player p1, Player p2, Pokemon pk1, Pokemon pk2)
	{
		Attack ret = null;
		
		while (ret == null)
		{
			
			for (int i=0; i<pk1.getAttackList().size(); i++)
				{
				int rand = r.nextInt()%10;
				if (rand==1 && pk1.getAttack(i)!=null)
					return pk1.getAttack(i);
				}
		}
		
		return ret;
	}
	
	public static Pokemon switchAI(Player p1, Player p2, Pokemon pk2, ArrayList<Pokemon> party)
	{
		for (Pokemon p: party)
			if (!p.isKO())
				return p;
		
		return null;
	}
}
