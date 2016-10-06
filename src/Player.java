import java.util.*;

public class Player {
	private static int maxScore = 0;
	
	private HashMap<Location,Pokemon> pokemonCaught = null;
	private HashMap<Location,Station> stationVisited = null;
	private HashSet<String> caughtTypes = null;
	
	private Location currentLocation = null;
	
	LinkedList<String> pathVisited = null;
	
	private int numPokeBalls;
	
	public Player(HashMap<Location,Pokemon> pokemonCaught,HashMap<Location,Station> stationVisited,HashSet<String> caughtTypes,Location currentLocation,int numPokeBalls){
		this.pokemonCaught = pokemonCaught;
		this.stationVisited = stationVisited;
		this.caughtTypes = caughtTypes;
		this.currentLocation = currentLocation;
		this.numPokeBalls = numPokeBalls;
	}
	
	public int calculateScore(){
		int maxCombatPower = 0;
		for(Pokemon p:pokemonCaught.values()){
			if(p.getCombatPower()>maxCombatPower)
				maxCombatPower = p.getCombatPower();
		}
		return numPokeBalls + 5*pokemonCaught.size()+10*caughtTypes.size() + maxCombatPower - pathVisited.size();
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public Player(Player player)throws Exception{
		this.pokemonCaught = (HashMap<Location,Pokemon>)player.pokemonCaught.clone();
		this.stationVisited = (HashMap<Location,Station>)player.stationVisited.clone();
		this.caughtTypes = (HashSet<String>)player.caughtTypes.clone();
		this.currentLocation = (Location)player.currentLocation.clone();
		this.pathVisited = (LinkedList<String>)player.pathVisited;
		this.numPokeBalls = player.numPokeBalls;
		
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
	
	public LinkedList<String> getPathVisited(){
		return pathVisited;
	}
	
	public int getNumPokeBalls(){
		return this.numPokeBalls;
	}
	
	public void setNumPokeBalls(int num){
		this.numPokeBalls = num;
	}
}
