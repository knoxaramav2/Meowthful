package gameElements;

import gameEngine.gameGlobal;

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
	public String type;

	public int posx, posy;
	public int map;
	public int orientation;
	public int movePos;
	
	public boolean moving;
	public boolean controllable;
	public boolean trainer;
	public int AI;
	public int coolDown;

	public int id;
	
	private Sprites spriteDB;

	public Player(String params, Sprites sDb, gameGlobal g) {
		
		spriteDB=sDb;
		movePos=0;
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
				list[i]=new String(list[i].toLowerCase());
				if (list[i].equals("grunt"))
					rank = 0;
				if (list[i].equals("private"))
					rank = 1;
				if (list[i].equals("general"))
					rank = 2;
				if (list[i].equals("executive"))
					rank = 3;
				if (list[i].equals("admin"))
					rank = 4;
				if (list[i].equals("leader"))
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

				String partyParam[] = list[i].split(":");
				String title = null;
				int lvl=0;
				ArrayList <Attack> att = new ArrayList <Attack>();
				
				for (int x=0; x<partyParam.length; x++)
				{
					switch (x)
					{
					case 0:
						title=partyParam[x];
						break;
					case 1:
						lvl=Integer.parseInt(partyParam[x]);
						break;
					case 2:
						String attackList[]=partyParam[x].split("\\|");
						for (int y=0; y<attackList.length; y++)
						{
							String param[] = attackList[y].split("%");
							Attack atk = new Attack(param[0],g);
							atk.setCurrentPP(Integer.parseInt(param[1]));
							att.add(atk);
						}
						break;
					}
				}
				if (title.equals("n/a"))
					break;
				
				Pokemon pk = new Pokemon(title,lvl,g);
				if (pk.name.equals(""))
					break;
				
				pk.setAttackList(att);
				
				party.add(pk);
				
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
			case 18:
				type=list[i];
				break;
			}
		}
	}
	
	@SuppressWarnings("unused")
	private Pokemon Pokemon(String title, int lvl, gameGlobal g) {
		// TODO Auto-generated method stub
		return null;
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
		
		this.spriteDB=origin.spriteDB;
	}
		
	public BufferedImage getSprite(int index){
		return resize(spriteDB.getPlayerSprite(index, type), 1280/15, 720/15);
	}
	
	private BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}

	public boolean hasReadyPokemon()
	{
		for (int x=0; x<party.size(); x++)
			if (!party.get(x).isKO())
				return true;
		
		return false;
	}
}