package gameElements;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapComponents {
	private int[][] mapImageIndex;
	private int[][] moveType;
	private int[][] specialID;
	
	public MapComponents(String filename) throws FileNotFoundException{
		Scanner s = new Scanner(new File(filename));
		s.useDelimiter(",");
		
		mapImageIndex = new int[15][15];
		moveType = new int[15][15];
		specialID = new int[15][15];
		
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				int next = s.nextInt();
				specialID[j][i] = next % 100;
				next /= 100;
				moveType[j][i] = next % 10;
				next /= 10;
				mapImageIndex[j][i] = next;
			}
		}
		
		s.close();
	}
	
	public int getMapImageIndex(int x, int y){
		return  mapImageIndex[x][y];
	}
	
	public int getMoveType(int x, int y){
		return moveType[x][y];
	}
	
	public int getSpecialID(int x, int y){
		return specialID[x][y];
	}
}
