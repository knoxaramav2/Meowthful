package gameEngine;

public class Utility {
	public static boolean isInteger(String s)
	{
		for (int x=0; x<s.length(); x++)
			if (s.charAt(x)<'0' || s.charAt(x)>'9')
				return false;
		
		return true;
	}
}
