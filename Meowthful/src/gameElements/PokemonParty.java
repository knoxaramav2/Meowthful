package gameElements;

public class PokemonParty {
	private Pokemon[] party = new Pokemon[6];
	private int numPokemon;
	
	public PokemonParty(){
		for(int i = 0; i < 6; i++) party[i] = null;
		numPokemon = 0;
	}
	
	public PokemonParty(Pokemon poke1){
		party[0] = poke1;
		for(int i = 1; i < 6; i++) party[i] = null;
		numPokemon = 1;
	}

	public PokemonParty(Pokemon poke1, Pokemon poke2){
		party[0] = poke1;
		party[1] = poke2;
		for(int i = 2; i < 6; i++) party[i] = null;
		numPokemon = 2;
	}

	public PokemonParty(Pokemon poke1, Pokemon poke2, Pokemon poke3){
		party[0] = poke1;
		party[1] = poke2;
		party[2] = poke3;
		for(int i = 3; i < 6; i++) party[i] = null;
		numPokemon = 3;
	}

	public PokemonParty(Pokemon poke1, Pokemon poke2, Pokemon poke3, Pokemon poke4){
		party[0] = poke1;
		party[1] = poke2;
		party[2] = poke3;
		party[3] = poke4;
		for(int i = 4; i < 6; i++) party[i] = null;
		numPokemon = 4;
	}

	public PokemonParty(Pokemon poke1, Pokemon poke2, Pokemon poke3, Pokemon poke4, Pokemon poke5){
		party[0] = poke1;
		party[1] = poke2;
		party[2] = poke3;
		party[3] = poke4;
		party[4] = poke5;
		party[5] = null;
		numPokemon = 5;
	}

	public PokemonParty(Pokemon poke1, Pokemon poke2, Pokemon poke3, Pokemon poke4, Pokemon poke5, Pokemon poke6){
		party[0] = poke1;
		party[1] = poke2;
		party[2] = poke3;
		party[3] = poke4;
		party[4] = poke5;
		party[5] = poke6;
		numPokemon = 6;
	}

	public int getPartySize(){
		return numPokemon;
	}
	
	public void removePokemonAtPosition(int position){
		party[position] = null;
		for(int i = position; i < 5; i++) party[i] = party[i+1];
		party[5] = null;
		numPokemon--;
	}
	
	public void addPokemon(Pokemon newPoke){
		if(numPokemon < 6) party[numPokemon-1] = newPoke;
	}
	
	public Pokemon getPokemonAtPosition(int position){
		return party[position];
	}
}
