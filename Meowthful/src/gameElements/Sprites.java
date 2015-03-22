package gameElements;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//stores sprite sheets and returns requested image copy
public class Sprites {
	//basic pokemon sprites
	final int pokeSpriteWidth=60;//pixel width
	final int pokeSpriteHeight=60;//pixel height
	final int pokeSpriteCol=15;
	final int pokeSpriteRow=11;
	
	final int actorSpriteWidth=60;
	final int actorSpriteHeight=60;
	final int actorSpriteCol=7;
	final int actorSpriteRow=5;
	
	final static int playerSeq = 0;
	final static int maleGruntSeq=1;
	final static int femaleGruntSeq=2;
	final static int bossSeq=3;
	final static int noneSeq=4;
	
	public final static int forward_idle = 0;
	public final static int backward_idle = 1;
	public final static int left_idle = 2;
	public final static int forward_step = 3;
	public final static int backward_step = 4;
	public final static int left_step = 5;
	public final static int left_step2 = 6;
	public final static int right_idle = 7;
	public final static int right_step = 8;
	public final static int right_step2 = 9;
	
	BufferedImage[] sprites = new BufferedImage[pokeSpriteCol*pokeSpriteRow];
	BufferedImage[] actorSprites = new BufferedImage[actorSpriteCol*actorSpriteRow];
	
	public void loadPokeSprites(String filename) throws IOException
	{
		BufferedImage baseImage = ImageIO.read(new File(filename));
		
		for (int i = 0; i < pokeSpriteRow; i++)
		{
		    for (int j = 0; j < pokeSpriteCol; j++)
		    {
		        sprites[(i * pokeSpriteCol) + j] = baseImage.getSubimage(
		            j * pokeSpriteWidth,
		            i * pokeSpriteHeight,
		            pokeSpriteWidth,
		            pokeSpriteHeight
		        );
		    }
		}
	}
	
	public BufferedImage getPokemonSprite(int id)
	{
		return sprites[(id-1)];
	}

	public void loadPlayerSprites(String fileName) throws IOException
	{
		BufferedImage baseImage = ImageIO.read(new File(fileName));
		
		for (int i = 0; i < actorSpriteRow; i++)
		{
		    for (int j = 0; j < actorSpriteCol; j++)
		    {
		    	actorSprites[(i * actorSpriteCol) + j] = baseImage.getSubimage(
		            j * actorSpriteWidth,
		            i * actorSpriteHeight,
		            actorSpriteWidth,
		            actorSpriteHeight
		        );
		    }
		}
	}

	BufferedImage getPlayerSprite(int ico, String type)
	{
		int mult=1;
		
		if (type.equals("player"))
			mult=0;
		if (type.equals("mGrunt"))
			mult=1;
		if (type.equals("fGrunt"))
			mult=2;
		if (type.equals("boss"))
			mult=3;
		
		if (mult*ico>34)
			return null;
		
		return actorSprites[mult*ico];
	};
}
