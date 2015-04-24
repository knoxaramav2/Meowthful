package gameEngine;

import java.io.*;

import sun.audio.*;

public class BackgroundMusicPlayer {
	AudioStream exterior;
	AudioStream battle;
	AudioStream menu;
	AudioStream curStream;
	
	public BackgroundMusicPlayer() throws IOException{
		InputStream extStream = new FileInputStream("music/IslandExterior.wav");
		InputStream batStream = new FileInputStream("music/Battle.wav");
		InputStream menStream = new FileInputStream("music/Mainmenu.wav");
		
		exterior = new AudioStream(extStream);
		battle = new AudioStream(batStream);
		menu = new AudioStream(menStream);
		
		curStream = menu;
		
		AudioPlayer.player.start(curStream);
	}
	
	public void playTrack(String track){
		AudioPlayer.player.stop(curStream);
	
		if(track.equalsIgnoreCase("menu")) curStream = menu;
		else if(track.equalsIgnoreCase("exterior")) curStream = exterior;
		else if(track.equalsIgnoreCase("battle")) curStream = battle;
		else curStream = null;
		
		if(curStream != null) AudioPlayer.player.start(curStream);
	}
}
