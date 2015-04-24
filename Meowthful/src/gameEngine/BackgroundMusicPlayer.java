/*package gameEngine;

import java.io.*;

import sun.audio.*;

public class BackgroundMusicPlayer{
	ContinuousAudioDataStream exterior;
	ContinuousAudioDataStream battle;
	ContinuousAudioDataStream menu;
	ContinuousAudioDataStream curStream;
	
	public BackgroundMusicPlayer() throws IOException {
		InputStream extStream = new FileInputStream("music/IslandExterior.wav");
		InputStream batStream = new FileInputStream("music/Battle.wav");
		InputStream menStream = new FileInputStream("music/Mainmenu.wav");
		
		exterior = new ContinuousAudioDataStream(new AudioStream(extStream).getData());
		battle = new ContinuousAudioDataStream(new AudioStream(batStream).getData());
		menu = new ContinuousAudioDataStream(new AudioStream(menStream).getData());
		
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
*/

package gameEngine;

import java.io.*;

import javax.sound.sampled.*;

public class BackgroundMusicPlayer{
	private Clip extClip;
	private Clip batClip;
	private Clip menClip;
	private AudioInputStream exterior;
	private AudioInputStream battle;
	private AudioInputStream menu;
	private AudioFormat format;
	private DataLine.Info info;
	private int playingClip;
	
	public BackgroundMusicPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		exterior = AudioSystem.getAudioInputStream(new File("music/IslandExterior.wav"));
		battle = AudioSystem.getAudioInputStream(new File("music/Battle.wav"));
		menu = AudioSystem.getAudioInputStream(new File("music/Mainmenu.wav"));
		
		format = menu.getFormat();
		info = new DataLine.Info(Clip.class, format);
		menClip = (Clip) AudioSystem.getLine(info);
		menClip.open(menu);
		
		format = exterior.getFormat();
		info = new DataLine.Info(Clip.class, format);
		extClip = (Clip) AudioSystem.getLine(info);
		extClip.open(exterior);
		
		format = battle.getFormat();
		info = new DataLine.Info(Clip.class, format);
		batClip = (Clip) AudioSystem.getLine(info);
		batClip.open(battle);
		
		menClip.loop(Clip.LOOP_CONTINUOUSLY);
		playingClip = 1;
	}
	
	public void playTrack(String track) throws LineUnavailableException, IOException{
		switch(playingClip){
		case 1:
			menClip.stop();
			menClip.setFramePosition(0);
			break;
			
		case 2:
			extClip.stop();
			extClip.setFramePosition(0);
			break;
			
		case 3:
			batClip.stop();
			batClip.setFramePosition(0);
			break;
		}
		
		if(track.equalsIgnoreCase("menu")){
			System.out.println("menu music");
			menClip.loop(Clip.LOOP_CONTINUOUSLY);	
			playingClip = 1;
		}else if(track.equalsIgnoreCase("exterior")){
			System.out.println("outside music");
			extClip.loop(Clip.LOOP_CONTINUOUSLY);			
			playingClip = 2;
		}else if(track.equalsIgnoreCase("battle")){
			System.out.println("battle music");
			batClip.loop(Clip.LOOP_CONTINUOUSLY);			
			playingClip = 3;
		}
	}
}