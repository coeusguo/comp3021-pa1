import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.io.PrintWriter;


public class Game {
	private Map map = null;
	private Player player= null;
	private HashMap<Location,Pokemon> pokemons = null;
	private HashMap<Location,Station> stations = null;
	private Location startPoint = null;
	private int RLevel = 0;//this variable is used to record the recurrence level of the find path method
	
	
	
	public void initialize(File inputFile) throws Exception{
		Scanner input = new Scanner(inputFile);
		
		// Read the first of the input file
		String line = input.nextLine();
		int M = Integer.parseInt(line.split(" ")[0]);
		int N = Integer.parseInt(line.split(" ")[1]);
		
		// To do: define a map
		char[][]  map = new char[M][N];
		this.map = new Map(M,N);
		
		// Read the following M lines of the Map
		for (int i = 0; i < M; i++) {
			line = input.nextLine();
			for(int j = 0;j < N; j++){
				map[i][j] = line.charAt(j);
				switch(map[i][j]){
					case ' ':
						this.map.setMapElement(new Empty(i,j));
						break;
					case '#':
						this.map.setMapElement(new Wall(i,j));
						break;
					case 'B':
						this.startPoint = new Empty(i,j);
						this.map.setMapElement(startPoint);
						break;
					case 'D':
						this.map.setMapElement(new Destination(i,j));
						break;
					case 'S':
					case 'P'://set pokemons and stations late
						break;
					default: 
						throw new Exception("Errow eccured in map initialization part");
				}
			}
			// Read the map line by line
		}
		
		
		
		while(input.hasNext()){
			String content[] = input.nextLine().split(",");
			for(int i = 0;i < content.length;i++){
				content[i].trim();
			}
			/*
			 * if length of the content of one line is larger than 3,the content of the line contains the information of pokemon
			 * otherwise,it contains the information of station
			 */
			if(content.length > 3){
				//e.g. content = { <2 , 10> , Spearow , fly , 120 , 2 }
				Pokemon pokemon = new Pokemon(content[2]/*name*/,content[3]/*type*/,Integer.parseInt(content[4])/*combat power*/,Integer.parseInt(content[5])/*required pokeballs*/,Integer.parseInt(content[0].substring(1))/*row, remove '<'*/,Integer.parseInt(content[1].substring(0, content[1].length()-1))/*column,remove'>'*/);
				this.map.addPokemon(pokemon);
			}
			else{
				Station station = new Station(Integer.parseInt(content[0].substring(1))/*row, remove '<' */,Integer.parseInt(content[1].substring(0, content[1].length()-1))/*column, remove '>'*/,Integer.parseInt(content[2])/*number of pokeballs*/);
				this.map.addStation(station);
			}
			
			
		}
		//initialize pokemons
		for(Location i:this.map.getPokemons())
			this.map.setMapElement(i);
		//initialize stations
		for(Location i:this.map.getStaion())
			this.map.setMapElement(i);
		
		// to do
		// Find the number of stations and pokemons in the map 
		
		input.close();
	}
	
	public void outputResult(Player player,File outputFile)throws Exception{
		try(PrintWriter output = new PrintWriter(outputFile)){
			output.println(Player.maxScore);
			output.println(player.getNumPokeBalls() + ":" + player.getPokemonCaught().size() + ":" + player.getTypeCaught().size() + ":" + player.getMaxCombatPower());
			Iterator<Location> ite =player.getPathVisited().listIterator();
			output.print(ite.next());
			while(ite.hasNext()){
				output.print("->" + ite.next().toString());
			}
		}
	}
	
	public void findPath(Player player,int level){
		
	}
	
	public static void main(String[] args) throws Exception{
		File inputFile = new File("./sampleIn.txt");
		File outputFile = new File("./sampleOut.txt");
		
		if (args.length > 0) {
			inputFile = new File(args[0]);
		} 

		if (args.length > 1) {
			outputFile = new File(args[1]);
		}
		
		Game game = new Game();
		game.initialize(inputFile);
		// TO DO 
		// Read the configures of the map and pokemons from the file inputFile
		// and output the results to the file outputFile
	}
}
