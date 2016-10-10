import java.util.*;

public class Player {
	public static int maxScore = -100000000;
	
	private HashMap<Location,Pokemon> pokemonCaught = null;
	private HashMap<Location,Station> stationVisited = null;
	private HashSet<String> caughtTypes = null;
	
	private Location currentLocation = null;
	private int maxCombatPowerCaught = 0;
	
	LinkedList<Location> pathVisited = null;
	
	private int numPokeBalls;
	
	public Player(){
		pokemonCaught = new HashMap<>();
		stationVisited = new HashMap<>();
		caughtTypes = new HashSet<>();
		currentLocation = new Location(-1,-1);
		pathVisited = new LinkedList<>();
	}
	public Player(HashMap<Location,Pokemon> pokemonCaught,HashMap<Location,Station> stationVisited,HashSet<String> caughtTypes,Location currentLocation,int numPokeBalls){
		this.pokemonCaught = pokemonCaught;
		this.stationVisited = stationVisited;
		this.caughtTypes = caughtTypes;
		this.currentLocation = currentLocation;
		this.numPokeBalls = numPokeBalls;
	}
	
	public int calculateScore(){
		return numPokeBalls + 5*pokemonCaught.size()+10*caughtTypes.size() + maxCombatPowerCaught - pathVisited.size()+1;
	}
	
	public int getMaxCombatPower(){
		return this.maxCombatPowerCaught;
	}
	
	public void setMaxCombatPower(int a){
		this.maxCombatPowerCaught = a;
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
	
	public boolean hasCaught(Pokemon pokemon){
		if(pokemonCaught.containsKey(pokemon.getLocation()))
			return true;
		return false;
	}
	
	public boolean hasVisited(Station station){
		if(stationVisited.containsKey(station.getLocation()))
			return true;
		return false;
	}
}
