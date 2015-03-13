package scriptEngine;

import java.util.ArrayList;

public class Packet {
public CommandCodes code;

public ArrayList <String> params = new ArrayList<String>();
public String[] raw;

public Packet(String[] input)
{
	if (input.length<1)
		return;
	raw=input;
	code = new CommandCodes(input[0]);
	//copy remaining parameters
	for (int x=1; x<input.length; x++)
		params.add(input[x]);
}
}
