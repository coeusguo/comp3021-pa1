import java.util.*;

public class Player {
	//used to store the current max score of each game
	public static int maxScore = -100000000;
	
	//used to store the caught pokemons and visited stations
	//get element from hash map and hash set is O(1),can increase performance 
	private HashMap<Location,Pokemon> pokemonCaught = null;
	private HashMap<Location,Station> stationVisited = null;
	//store distinct types of pokemons caught
	private HashSet<String> caughtTypes = null;
	
	private Location currentLocation = null;
	
	//record the current max combat power of all the pokemon caught
	private int maxCombatPowerCaught = 0;
	
	//store the path visited
	LinkedList<Location> pathVisited = null;
	
	private int numPokeBalls;
	
	public Player(){
		pokemonCaught = new HashMap<>();
		stationVisited = new HashMap<>();
		caughtTypes = new HashSet<>();
		currentLocation = new Location(-1,-1);
		pathVisited = new LinkedList<>();
	}
	
	public Player(Player player)throws Exception{
		this.pokemonCaught = new HashMap<>();
		this.pokemonCaught.putAll(player.pokemonCaught);
		this.stationVisited = new HashMap<>();
		this.stationVisited.putAll(player.stationVisited);
		this.caughtTypes = new HashSet<>();
		this.caughtTypes.addAll(player.caughtTypes);
		this.currentLocation = new Location(player.currentLocation);
		this.pathVisited = new LinkedList<>();
		this.pathVisited.addAll(player.pathVisited);
		this.numPokeBalls = player.numPokeBalls;
		this.maxCombatPowerCaught = player.maxCombatPowerCaught;
	}
	
	//calculate the score of the player according to the formula
	public int calculateScore(){
		return numPokeBalls + 5*pokemonCaught.size()+10*caughtTypes.size() + maxCombatPowerCaught - pathVisited.size()+1;
	}
	
	public int getMaxCombatPower(){
		return this.maxCombatPowerCaught;
	}
	public void setMaxCombatPower(int a){
		this.maxCombatPowerCaught = a;
	}
	
	

	
	public HashMap<Location,Pokemon> getPokemonCaught(){
		return this.pokemonCaught;
	}
	
	public HashMap<Location,Station> getStationVisited(){
		return this.stationVisited;
	}
	
	public HashSet<String> getTypeCaught(){
		return this.caughtTypes;
	}
	
	public Location getCurrentLocation(){
		return currentLocation;
	}
	
	public void setCurrentLocation(Location currentLocation){
		this.currentLocation = currentLocation;
	}
	
	public LinkedList<Location> getPathVisited(){
		return pathVisited;
	}
	
	public int getNumPokeBalls(){
		return this.numPokeBalls;
	}
	
	public void setNumPokeBalls(int num){
		this.numPokeBalls = num;
	}
	
	//check whether a given pokemon is caught
	public boolean hasCaught(Pokemon pokemon){
		if(pokemonCaught.containsKey(pokemon.getLocation()))
			return true;
		return false;
	}
	//check whether a given supply station is visited
	public boolean hasVisited(Station station){
		if(stationVisited.containsKey(station.getLocation()))
			return true;
		return false;
	}
}
