import java.util.ArrayList;

public class Map {
	//
	private Location[][] map = null;
	private Location start = null;
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
	
	public void setStartLocation(Location start){
		this.start = start;
	}
	
	public Location getStartLocation(){
		return this.start;
	}
	
	public void setMapElement(Location element){
		map[element.getRow()][element.getColumn()] = element;
	}
	
	public boolean isOutOfBound(int row,int column){
		if(row < 0 || column < 0 || row >= this.max_row || column >= this.max_column)
			return true;
		return false;	
	}
	
	public Location getMapElement(int row, int column){
		return map[row][column];
	}
	
}
