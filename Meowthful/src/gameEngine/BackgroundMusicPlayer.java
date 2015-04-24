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
	private Clip clip;
	private AudioInputStream exterior;
	private AudioInputStream battle;
	private AudioInputStream menu;
	private AudioInputStream curStream;
	private AudioFormat format;
	private DataLine.Info info;
	
	public BackgroundMusicPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		exterior = AudioSystem.getAudioInputStream(new File("music/IslandExterior.wav"));
		battle = AudioSystem.getAudioInputStream(new File("music/Battle.wav"));
		menu = AudioSystem.getAudioInputStream(new File("music/Mainmenu.wav"));
		curStream = menu;
		format = curStream.getFormat();
		info = new DataLine.Info(Clip.class, format);
		clip = (Clip) AudioSystem.getLine(info);
		clip.open(curStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void playTrack(String track) throws LineUnavailableException, IOException{
		clip.stop();
		
		if(track.equalsIgnoreCase("menu")) curStream = menu;
		else if(track.equalsIgnoreCase("exterior")) curStream = exterior;
		else if(track.equalsIgnoreCase("battle")) curStream = battle;
		else curStream = null;
		
		if(curStream != null){
			format = curStream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(curStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);			
		}
	}
}