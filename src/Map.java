import java.util.ArrayList;

public class Map {
	//
	private Location[][] map = null;
	//max row and column of the map
	private int max_row;
	private int max_column;
	
	private ArrayList<Pokemon> pokemons = null;
	private ArrayList<Station> stations = null;
	
	public Map(int max_row,int max_column){
		this.max_column = max_column;
		this.max_row = max_row;
		map = new Location[max_row][max_column];
		pokemons = new ArrayList<>();
		stations = new ArrayList<>();
	}
	
	public void addPokemon(Pokemon pokemon){
		pokemons.add(pokemon);
	}
	
	public void addStation(Station station){
		stations.add(station);
	}
	
	public ArrayList<Pokemon> getPokemons(){
		return pokemons;
	}
	
	public ArrayList<Station> getStaion(){
		return stations;
	}
	
	public void setMap(Location[][] map){
		this.map = map;
	}
	
	public void setMapElement(Location element,int row,int column){
		map[row][column] = element;
	}
	
	public int getMaxRow(){
		return this.max_row;
	}
	
	public int getMaxColumn(){
		return this.max_column;
	}
}
