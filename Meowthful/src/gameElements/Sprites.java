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
	BufferedImage[] sprites = new BufferedImage[pokeSpriteCol*pokeSpriteRow];
	
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
}
