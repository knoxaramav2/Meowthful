package drawer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//stores sprite sheets and returns requested image copy

public class GetImage {
	// basic pokemon sprites
	final int pokeSpriteCol = 43;
	final int pokeSpriteRow = 13;
	BufferedImage[] sprites = new BufferedImage[pokeSpriteCol * pokeSpriteRow];

	public void loadPokeSprites(String filename, int pokeSpriteWidth, int pokeSpriteHeight, int pokeSpriteCol, int pokeSpriteRow, int buffer) throws IOException {
		BufferedImage baseImage = ImageIO.read(new File(filename));
		
		for (int i = 0; i < pokeSpriteRow; i++) {
			for (int j = 0; j < pokeSpriteCol; j++) {
				sprites[(i * pokeSpriteCol) + j] = baseImage.getSubimage(j * pokeSpriteWidth + buffer, i * pokeSpriteHeight + buffer, pokeSpriteWidth - buffer, pokeSpriteHeight - buffer);
			}
		}
	}

	public BufferedImage getPokemonSprite(int id) {
		return sprites[(id - 1)];
	}
}
