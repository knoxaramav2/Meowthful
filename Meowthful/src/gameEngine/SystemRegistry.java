package gameEngine;

import java.util.ArrayList;

//tracks and allocates all id's used in system
public class SystemRegistry {
	public ArrayList <String> title = new ArrayList <String>();
	public ArrayList <Integer> id_table = new ArrayList <Integer>();
	
	int registeredItems;
	
	public SystemRegistry()
	{
		registeredItems=0;
	}
	
	public boolean requestID(String name, int id)
	{
		for (int x=0; x<id_table.size(); x++)//table index
		{
			if (id_table.get(x)==id)
			{
				return false;
			}
		}
		
		id_table.add(id);
		title.add(name);
		
		return true;
	}
	
	public int requestID(String name)
	{
		for (int y=0; y<Integer.MAX_VALUE; y++)//id value
		{
			boolean found=false;
			for (int x=0; x<id_table.size(); x++)//table index
			{
				if (id_table.get(x)==y)
				{
					found=true;
					break;
				}
			}
			if (!found)
			{
				id_table.add(y);
				title.add(name);
				registeredItems++;
				return y;
			}
		}
		return -1;
	}
	
	public boolean forfeitID(int id)
	{
		int index = id_table.indexOf(id);
		if (index==-1)
			return false;
		else
		{
			id_table.remove(index);
			title.remove(index);
			
			return true;
		}
	}

	public int getID(String name)
	{
		int index = title.indexOf(name);
		if (index==-1)
			return -1;
		return id_table.get(index);
	}
	
	public String getID(int id)
	{
		int index = id_table.indexOf(id);
		if (index==-1)
			return "";
		return title.get(index);
	}
}
