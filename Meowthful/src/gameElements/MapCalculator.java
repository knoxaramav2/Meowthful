package gameElements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		BufferedReader br = new BufferedReader(new FileReader("core/MapCalculator.csv"));
		
		String line = new String();
		boolean header=true;
		
		try {
			while ((line=br.readLine())!=null)
			{
				if (header)
				{
					header=false;
					continue;
				}
				String [] params = line.split(",");
				for (int i=0; i<6; i++)
				{
					switch (i)
					{
					case 0: curMapNames.add(params[i]); break;
					case 1: teleporterID.add(params[i]); break;
					case 2: newMapName.add(params[i]); break;
					case 3: newMapCellX.add(params[i]); break;
					case 4: newMapCellY.add(params[i]); break;
					case 5: newDirection.add(params[i]); break;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getNewMapPath(String oldFileName, int tID){		
		for(int i = 0; i < curMapNames.size(); i++)
				if (oldFileName.equals(curMapNames.get(i)) && tID == Integer.parseInt(teleporterID.get(i)))
					return newMapName.get(i);

		
		System.out.println("Error: No file found");
		return null;
	}
	
	public int getNewMapCellX(String oldFileName, int tID){
		for(int i = 0; i < curMapNames.size(); i++)
			if (oldFileName.equals(curMapNames.get(i)) && tID == Integer.parseInt(teleporterID.get(i)))
				return Integer.parseInt(newMapCellX.get(i));

	
	System.out.println("Error: No file found");
	return 0;	
	}

	public int getNewMapCellY(String oldFileName, int tID){
		for(int i = 0; i < curMapNames.size(); i++)
			if (oldFileName.equals(curMapNames.get(i)) && tID == Integer.parseInt(teleporterID.get(i)))
				return Integer.parseInt(newMapCellX.get(i));

	
	System.out.println("Error: No file found");
	return 0;		
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
