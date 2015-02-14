package gameEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

//loads and saves different file types into memory
public class FileSystem {
	File a;
	FileReader in;
	
	//load flags
	static int _actor=0, _pkmn=1;
	
	public static gameGlobal loadGlobals(String fileName) throws IOException
	{
		gameGlobal gG = new gameGlobal();
		BufferedReader br=null;
		String line = "";
		String delim = ",|\\n";
		int mode=0;
		//serial load index
		int index=0;
		
		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line=br.readLine()) != null)
			{
				//get lines
				String[] input = line.split(delim);
				for (String res : input)
				{
					//ignore blank values
					if (res.equals(""))
						continue;
					
					//check for mode
					if (res.charAt(0)==':')
					{
						//begin loading pokemon base block
						if (res.equals(":pkmn"))
						{
							mode=_pkmn;
						}//begin loading actor base block
						else if (res.equals(":actor")){
							mode=_actor;
						}
					}
					
				}
					
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (br !=null)
				try {
					br.close();
				}catch (IOException e)
			{
					e.printStackTrace();
			}
		}
		
		return gG;
	}
	
}
