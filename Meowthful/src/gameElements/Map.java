package gameElements;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class Map {
	private static int width=1280;
	private static int height=720;
	BufferedImage map = null;
	int attributeMatrix [] = null;
	Point loc = null;
	
	Map(String fileName)
	{
		attributeMatrix=new int [1280*720];
		//load .World file
	}
}
