package scriptEngine;


public class CommandCodes{

	public static int attackMenu 	= -1961684025;
	public static int attack 		= -1407259064;
	public static int flee			= 3145702;
	public static int swapParty		= -1285939949;
	public static int usePokeBall	= 1794789343;
	public static int heal			= 3198440;
	public static int useBattleItem = 639617234;
	
	public static int giveItem		= 1293248964;
	public static int dropItem		= -432861534;
	public static int viewInventory	= 1671571511;
	
	public static int faceNorth		= -1787300696;
	public static int faceWest		= 496793004;
	public static int faceEast		= 496252922;
	public static int faceSouth		= -1782680208;
	public static int faceActor		= -1799662184;
	public static int mapFaceActor	= -1650845796;
	
	public static int speech		= -896071454;
	public static int prompt		= -979805852;
	public static int sellInterface	= -1563371001;
	public static int buyInterface	= 178586963;
	
	public static int talkTo		= -881081273;
	public static int open			= 3417674;
	public static int interact		= 570398262;
	public static int buyItem		= 244693689;
	public static int buyPokemon	= -1404955795;
	public static int sellItem		= 1197262213;
	public static int sellPokemon	= -284981983;
	
	public static int viewParty		= -1587425471;
	public static int selectParty	= 201044810;
	public static int evolve		= -1291033219;
	public static int levelUp		= 69784895;
	public static int teachMove		= -454855194;
	
	public static int useItemOnParty=1543764397;
	public static int releaseParty	=1543764397;
	public static int addToParty	=739199306;
	
	public static int random		= -938285885;
	public static int print			= 106934957;
	public static int setCache		= 1389129792;
	public static int addVar		= -1422522682;
	public static int quitGame		= -1051778335;
	public static int setMessage	= -366376635;
	public static int saveGame		= -2073003249;
	public static int loadGame		= 1845207544;
	public static int deleteVar		= -358718084;
	public static int getCache		= 1950242252;
	
	public static int teleport		= -1360201941;
	public static int event			= 96891546;
	
	
	private int type;
	
	//destination defines
	public static final int BattleManager=1;//to battle manager
	public static final int Party=2;
	public static final int Player=3;
	public static final int Actor=4;
	public static final int Inventory=5;
	
	public int getCode(String s)
	{
		return s.hashCode();
	}
}
