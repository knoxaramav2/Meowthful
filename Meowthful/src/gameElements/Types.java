package gameElements;

public class Types{
	
	//add method to get Type from string
	
	public enum Type{
		normal(0), 
		flying(1), 
		fighting(2), 
		ground(3), 
		rock(4), 
		bug(5), 
		grass(6), 
		water(7), 
		fire(8), 
		electric(9), 
		ghost(10), 
		psychic(11), 
		dragon(12), 
		ice(13), 
		poison(14);
	
		@SuppressWarnings("unused")
		private int typeIndex;
	
		private int type;
		
		private Type(int type){
			this.type = type;
		}
	
		public static Type getType(int type){
			Type val = null;
			for (Type t: Type.values())
				if (t.type==type)
					return t;
			return val;
		}
	}
	
	public enum ExplicitStatus{
		none(0), 
		paralysis(1), 
		poison(2), 
		sleep(3), 
		burn(4), 
		frozen(5);

		@SuppressWarnings("unused")
		private int typeIndex;
	
		private int type;
		
		private ExplicitStatus(int type){
			this.type = type;
		}
	
		public static ExplicitStatus getType(int type){
			ExplicitStatus val = null;
			for (ExplicitStatus t: ExplicitStatus.values())
				if (t.type==type)
					return t;
			return val;
		}
	}

	public enum ImplicitStatus{
		none(0), 
		confusion(1), 
		bound(2), 
		infatuation(3), 
		fear(4);

		@SuppressWarnings("unused")
		private int typeIndex;
	
		private int type;
	
		private ImplicitStatus(int type){
			this.type = type;
		}
	
		public static ImplicitStatus getType(int type){
			ImplicitStatus val = null;
			for (ImplicitStatus t: ImplicitStatus.values())
				if (t.type==type)
					return t;
			return val;
		}
	}
	
	public enum AttackEffects{
		none(0), 
		recharge(1), 
		fly(2), 
		dig(3), 
		paralysis(4), 
		poison(5), 
		sleep(6), 
		burn(7), 
		frozen(8), 
		confusion(9), 
		bound(10), 
		infatuation(11), 
		fear(12);
	
		@SuppressWarnings("unused")
		private int typeIndex;
	
		private int type;
	
		private AttackEffects(int type){
			this.type = type;
		}
	
		public static AttackEffects getType(int type){
			AttackEffects val = null;
			for (AttackEffects t: AttackEffects.values())
				if (t.type==type)
					return t;
			return val;
		}
	}
}