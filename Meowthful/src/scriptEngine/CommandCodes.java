package scriptEngine;


public class CommandCodes{

	public final static int attackMenu 	= -1961684025;
	public final static int attack 		= -1407259064;
	public final static int flee			= 3145702;
	public final static int swapParty		= -1285939949;
	public final static int usePokeBall	= 1794789343;
	public final static int heal			= 3198440;
	public final static int useBattleItem = 639617234;
	public final static int startBattle = 1924640698;
	public final static int endBattle = 1105032563;
	
	public final static int giveItem		= 1293248964;
	public final static int dropItem		= -432861534;
	public final static int viewInventory	= 1671571511;
	
	public final static int faceNorth		= -1787300696;
	public final static int faceWest		= 496793004;
	public final static int faceEast		= 496252922;
	public final static int faceSouth		= -1782680208;
	public final static int faceActor		= -1799662184;
	public final static int mapFaceActor	= -1650845796;
	
	public final static int speech		= -896071454;
	public final static int prompt		= -979805852;
	public final static int sellInterface	= -1563371001;
	public final static int buyInterface	= 178586963;
	
	public final static int talkTo		= -881081273;
	public final static int open			= 3417674;
	public final static int interact		= 570398262;
	public final static int buyItem		= 244693689;
	public final static int buyPokemon	= -1404955795;
	public final static int sellItem		= 1197262213;
	public final static int sellPokemon	= -284981983;
	
	public final static int viewParty		= -1587425471;
	public final static int selectParty	= 201044810;
	public final static int evolve		= -1291033219;
	public final static int levelUp		= 69784895;
	public final static int teachMove		= -454855194;
	
	public final static int useItemOnParty=1543764397;
	public final static int releaseParty	=1543764397;
	public final static int addToParty	=739199306;
	
	public final static int random		= -938285885;
	public final static int print			= 106934957;
	public final static int setCache		= 1389129792;
	public final static int setVar		= -905797787;
	public final static int quitGame		= -1051778335;
	public final static int setMessage	= -366376635;
	public final static int saveGame		= -2073003249;
	public final static int loadGame		= 1845207544;
	public final static int deleteVar		= -358718084;
	public final static int getCache		= 1950242252;
	public final static int newGameDialogue	= -1664350806;
	public final static int loadGameDialogue= -452958224;
	public final static int battleDialogue  = 1318213296;
	public final static int setWindow		= 693646066;
	
	public final static int teleport		= -1360201941;
	public final static int event			= 96891546;
	public final static int loadMap			= 336623254;
	public final static int startProcedure  = -1449546607;
	public final static int endProcedure	= -1506418632;
	public final static int placeActor		= -294163154;
	public final static int loadScript		= -231490223;
	public final static int callFunction	= 1449071702;
	public final static int moveActor		= 1040267428;
	public final static int setSpeech		= 585326564;
	public final static int baseSpeech		= -821830893;
	public final static int idleSpeech		= 1655375318;
	public final static int failSpeech		= 61439392;
	public final static int successSpeech	= 913565381;
	public final static int ifCondition		= 3357;//command just 'if'
	public final static int subProc			= -1868646986;
	public final static int swapMap			= -1811394871;
	public final static int battleWin		= -487938044;
	
	@SuppressWarnings("unused")
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
	
	public int value;
	
	public CommandCodes()
	{
		value=0;
	}
	
	public CommandCodes(String s)
	{
		value=s.hashCode();
	}
}
