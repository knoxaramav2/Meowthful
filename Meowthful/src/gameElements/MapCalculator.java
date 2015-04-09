package gameElements;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapCalculator {
	private ArrayList<String> curMapNames = new ArrayList<String>();
	private ArrayList<String> teleporterID = new ArrayList<String>();
	private ArrayList<String> eventZoneID = new ArrayList<String>();
	private ArrayList<String> newMapName = new ArrayList<String>();
	private ArrayList<String> newMapX = new ArrayList<String>();
	private ArrayList<String> newMapY = new ArrayList<String>();
	private ArrayList<String> newMapCellX = new ArrayList<String>();
	private ArrayList<String> newMapCellY = new ArrayList<String>();
	private ArrayList<String> newDirection = new ArrayList<String>();	
	
	public MapCalculator() throws FileNotFoundException{
		Scanner s = new Scanner(new File("core/MapCalculator.csv"));
		s.useDelimiter(",");

		for(int i = 0; i < 9; i++){
			@SuppressWarnings("unused")
			String dummy = s.next();
		}
		
		for(int i = 0; i < 10; i++){
			String next = s.next();
			curMapNames.add(next);
			next = s.next();
			teleporterID.add(next);
			next = s.next();
			eventZoneID.add(next);
			next = s.next();
			newMapName.add(next);
			next = s.next();
			newMapX.add(next);
			next = s.next();
			newMapY.add(next);
			next = s.next();
			newMapCellX.add(next);
			next = s.next();
			newMapCellY.add(next);
			next = s.next();
			newDirection.add(next);
		}
		
		s.close();
	}
	
	public String getNewMapPath(String oldFileName, int teleporterID){		
		for(int i = 0; i < curMapNames.size(); i++){
			if(curMapNames.get(i).equals("\r\n" + oldFileName) && Integer.parseInt(this.teleporterID.get(i)) == teleporterID){
				return newMapName.get(i);
			}
		}
		
		return null;
	}
	
	public int getNewMapX(String oldFileName, int teleporterID){
		for(int i = 0; i < curMapNames.size(); i++){
			if(curMapNames.get(i).equals("\r\n" + oldFileName) && Integer.parseInt(this.teleporterID.get(i)) == teleporterID){
				return Integer.parseInt(newMapX.get(i));
			}
		}
		
		return -1;		
	}

	public int getNewMapY(String oldFileName, int teleporterID){
		for(int i = 0; i < curMapNames.size(); i++){
			if(curMapNames.get(i).equals("\r\n" + oldFileName) && Integer.parseInt(this.teleporterID.get(i)) == teleporterID){
				return Integer.parseInt(newMapY.get(i));
			}
		}
		
		return -1;		
	}
	
	public int getNewMapCellX(String oldFileName, int teleporterID){
		for(int i = 0; i < curMapNames.size(); i++){
			if(curMapNames.get(i).equals("\r\n" + oldFileName) && Integer.parseInt(this.teleporterID.get(i)) == teleporterID){
				return Integer.parseInt(newMapCellX.get(i));
			}
		}
		
		return -1;		
	}

	public int getNewMapCellY(String oldFileName, int teleporterID){
		for(int i = 0; i < curMapNames.size(); i++){
			if(curMapNames.get(i).equals("\r\n" + oldFileName) && Integer.parseInt(this.teleporterID.get(i)) == teleporterID){
				return Integer.parseInt(newMapCellY.get(i));
			}
		}
		
		return -1;		
	}
	
	public int getNewDirection(String oldFileName, int teleporterID){
		for(int i = 0; i < curMapNames.size(); i++){
			if(curMapNames.get(i).equals(oldFileName) && Integer.parseInt(this.teleporterID.get(i)) == teleporterID){
				return Integer.parseInt(newDirection.get(i));
			}
		}
		
		return -1;		
	}

}
