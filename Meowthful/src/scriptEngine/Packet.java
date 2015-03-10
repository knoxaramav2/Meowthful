package scriptEngine;

import java.util.ArrayList;

public class Packet {
public CommandCodes hash;

public ArrayList <String> params = new <String> ArrayList();
public String[] raw;

public Packet(String[] input)
{
	if (input.length<1)
		return;
	raw=input;
	//copy remaining parameters
	hash=CommandCodes.getType(input[0]);
	for (int x=1; x<input.length; x++)
		params.add(input[x]);
}
}
