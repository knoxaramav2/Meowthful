package gameElements;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
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
	final int actorSpriteCol=10;
	final int actorSpriteRow=5;
	
	public final static int playerSeq = 0;
	public final static int maleGruntSeq=1;
	public final static int femaleGruntSeq=2;
	public final static int bossSeq=3;
	public final static int noneSeq=4;
	
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
	BufferedImage[] actorSpritesInverted = new BufferedImage[actorSpriteCol*actorSpriteRow];
	
	//does not work for sub images like sprites
	private static BufferedImage deepCopy(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null).getSubimage(0, 0, bi.getWidth(), bi.getHeight());
		}
	
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
		BufferedImage invImage = ImageIO.read(new File(fileName));
		
		//inverts image
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-invImage.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		invImage = op.filter(invImage, null);
		
		for (int i = 0; i < actorSpriteRow; i++)
		{
		    for (int j = 0; j < actorSpriteCol; j++)
		    {
		    	BufferedImage pSwitch = null;
		    	
		    	int nj = j;
		    	
		    	if (j>6)
		    		{
		    		pSwitch = invImage;
		    		switch (j)
		    		{
		    		case right_idle:
		    			nj=4;
		    			break;
		    		case right_step:
		    			nj=1;
		    			break;
		    		case right_step2:
		    			nj=0;
		    			break;
		    		}
		    		}else
		    			pSwitch = baseImage;
		    	
		    	actorSprites[(i * actorSpriteCol) + j] = pSwitch.getSubimage(
		            nj * actorSpriteWidth,
		            i * actorSpriteHeight,
		            actorSpriteWidth,
		            actorSpriteHeight
		        );
		    }
		}
	}

	public BufferedImage getPlayerSprite(int ico, String type)
	{
		int mult=1;
		
		type = new String(type.toLowerCase());
		
		if (type.equals("player"))
			mult=0;
		if (type.equals("mgrunt"))
			mult=1;
		if (type.equals("fgrunt"))
			mult=2;
		if (type.equals("boss"))
			mult=3;
		
		if ((10*mult)+ico>40)
			return null;
			
		return actorSprites[(10*mult)+ico];
	};
}
