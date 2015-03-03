package scriptEngine;

import java.util.ArrayList;

public class Packet {
int hash;

ArrayList <String> params = new <String> ArrayList();

public Packet(String[] input)
{
	if (input.length<1)
		return;

	//copy remaining parameters
	hash=input[0].hashCode();
	for (int x=1; x<input.length; x++)
		params.add(input[x]);
}
}
