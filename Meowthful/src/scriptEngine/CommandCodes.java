package scriptEngine;


public enum CommandCodes{
	//Battle
	attackMenu(1), attack(2), flee(3), useBattleItem(4), swapParty(5), usePokeBall(6), heal(7),
	//Items
	useItem(10),
	//Inventory
	giveItem(20), dropItem(21), viewInventory(22),
	//Movement
	faceNorth(30), faceSouth(31), faceWest(32), faceEast(33), moveTo(34),
	//Speech
	speech(40), prompt(41), sellInterface(42), buyInterface(43),
	//Actions
	talkTo(50), open(51), interact(52), buyItem(53), buyPokemon(54), sellItem(55), sellPokemon(56),
	//Pokemon
	viewParty(60), selectParty(61), evolve(62), levelUp(63), teachMove(64),
	//Party
	useItemOnParty(70), releaseParty(71),
	//Utility
	random(80), print(81), setMessage(82); 
	
	private int type;
	private CommandCodes(int i)
	{
	this.type=i;	
	}
	
	//destination defines
	public static final int BattleManager=1;//to battle manager
	public static final int Party=2;
	public static final int Player=3;
	public static final int Actor=4;
	public static final int Inventory=5;
	
	public static int getCodeDestination(int i)
	{
		return 0;
	}
	
	//convert string to command
	public static CommandCodes getType(String raw)
	{
		//Battle
		if (raw.equals(""))
			return CommandCodes.attack;
		
		return null;
	}
	
	public static CommandCodes getType(int type)
	{
		CommandCodes val = null;
		for (CommandCodes t: CommandCodes.values())
			if (t.type==type)
				return t;
		return val;
	}

	public boolean isEqual(int c)
	{
		if (c==1)
			return true;
		return false;
	}
}
