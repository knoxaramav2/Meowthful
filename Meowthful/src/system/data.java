package system;

import gameElements.Map;

import java.util.ArrayList;


//random comment
public class data {
	public data()
	{
		active=true;
		lastResult="";
		variables= new ArrayList <String>();
		values= new ArrayList <String>();
	}
	
	public String getVar(String name)
	{
		for (String res:variables)
			if (name.equals(res))
				return res;
		
		return null;
	}
	
	public void delVar(String var)
	{
		int index = variables.indexOf(var);
		
		if (index==-1)
			return;
		
		variables.remove(index);
		values.remove(index);
	}
	
	public void addVar(String var, String val)
	{
		int index = variables.indexOf(var);
		if (index==-1)//add new
		{
			variables.add(var);
			values.add(val);
		}else
		{
			values.set(index, val);
		}
	}
	
	public boolean active;
	//active player battle pokemon
	
	//active map
	public Map current;
	//system caches
	public String lastResult;
	
	public ArrayList <String> variables;
	public ArrayList <String> values;
}
