package gameElements;

import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Player {
	// Player, AI

	public String name;
	public int money;
	public int rank;
	public int level;
	public ArrayList<Pokemon> party = new ArrayList<Pokemon>();

	public int posx, posy;
	public int map;
	public int orientation;

	public boolean controllable;
	public boolean trainer;
	public int AI;
	public int coolDown;

	public int id;
	
	private BufferedImage[] sprites;

	public Player(String params, BufferedImage[] sprites) {
		this.sprites = new BufferedImage[sprites.length];
		
		for(int i = 0; i < sprites.length; i++){
			this.sprites[i] = resize(sprites[i], 1280/15, 720/15);
		}
		
		// Parse from line into base
		String list[] = params.split(",");

		for (int i = 0; i < list.length; i++) {
			switch (i) {
			case 0:
				id = Integer.parseInt(list[i]);
				break;
			case 1:
				name = list[i];
				break;
			case 2:
				if (list[i].equals("grunt"))
					rank = 0;
				if (list[i].equals("Private"))
					rank = 1;
				if (list[i].equals("General"))
					rank = 2;
				if (list[i].equals("Executive"))
					rank = 3;
				if (list[i].equals("Admin"))
					rank = 4;
				if (list[i].equals("Leader"))
					rank = 5;
				break;
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				// load party data
				// 1:pokemon name
				// 2:pokemon level
				// 3:pokemon health
				// 4:pokemon explicit status
				// 5:attack list
				// 1:attack name
				// 2:attack pp
				// get base pokemon and level

				// skip empty slot
				if (list[i] == "n/a")
					continue;

				String partyList[] = list[i].split("\\|");

				for (int loop = 0; loop < partyList.length; loop++) {
					String pokeParam[] = partyList[loop].split(":");// individual
																	// parameters

					for (int j = 0; j < pokeParam.length; j++)
						party.add(new Pokemon(pokeParam[j]));
				}

				break;
			case 9:
				level = Integer.parseInt(list[i]);
				break;
			case 10:
				money = Integer.parseInt(list[i]);
				break;
			case 11:
				if (Integer.parseInt(list[i]) > 0)
					trainer = true;
				else
					trainer = false;
				break;
			case 12:
				// load items
				break;
			case 13:
				map = Integer.parseInt(list[i]);
				break;
			case 14:
				String coord[] = list[i].split("\\|");
				posx = Integer.parseInt(coord[0]);
				posy = Integer.parseInt(coord[1]);
				break;
			case 15:
				AI = Integer.parseInt(list[i]);
				break;
			case 16:
				orientation = Integer.parseInt(list[i]);
				break;
			case 17:
				coolDown = Integer.parseInt(list[i]);
				break;
			}
		}
	}
	
	//modified deep copy (certain elements shallow copied)
	public Player(Player origin)
	{
		this.name = origin.name;
		this.money = origin.money;
		this.rank = origin.rank;
		this.level=origin.level;
		this.party = new ArrayList<Pokemon>(origin.party);
		this.posx=origin.posx;
		this.posy=origin.posy;
		this.map=origin.map;
		this.orientation=origin.orientation;
		this.controllable=origin.controllable;
		this.trainer = origin.trainer;
		this.AI= origin.AI;
		this.coolDown=origin.coolDown;
		
		this.id=origin.id;
		
		this.sprites=origin.sprites;
	}
	
	public BufferedImage getSprite(int index){
		return sprites[index];
	}
	
	private BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}

}