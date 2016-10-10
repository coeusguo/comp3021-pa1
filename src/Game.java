import java.io.File;
import java.util.Scanner;
import java.util.Iterator;
import java.io.PrintWriter;


public class Game {
	private File inputFile;
	private File outputFile;
	private Map map = null;
	private Player player= null;
	

	
	
	public Game(String inputFile,String outputFile,Player player)throws Exception{
			this.inputFile = new File(inputFile);
			this.outputFile = new File(outputFile);
			this.player = new Player(player);
			initialize();
	}
	
	private void initialize() throws Exception{
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
						Location startPoint = new Empty(i,j);
						this.map.setMapElement(startPoint);
						this.map.setStartLocation(new Location(i,j));
						break;
					case 'D':
						this.map.setMapElement(new Destination(i,j));
						break;
					case 'S':
					case 'P'://set pokemons and stations later
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

				Pokemon pokemon = new Pokemon(content[2]/*name*/,content[3]/*type*/,Integer.parseInt(content[4].trim())/*combat power*/,Integer.parseInt(content[5].trim())/*required pokeballs*/,Integer.parseInt(content[0].substring(1))/*row, remove '<'*/,Integer.parseInt(content[1].substring(0, content[1].length()-1))/*column,remove '>'*/);
				
				this.map.addPokemon(pokemon);
			}
			else{
				Station station = new Station(Integer.parseInt(content[0].substring(1).trim())/*row, remove '<' */,Integer.parseInt(content[1].substring(0, content[1].length()-1).trim())/*column, remove '>'*/,Integer.parseInt(content[2].trim())/*number of pokeballs*/);
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
		this.player.setCurrentLocation(this.map.getStartLocation());
	}
	
	private void outputResult(Player player)throws Exception{
		try(PrintWriter output = new PrintWriter(outputFile)){
			System.out.println("OK");
			output.println(Player.maxScore);
			output.println(player.getNumPokeBalls() + ":" + player.getPokemonCaught().size() + ":" + player.getTypeCaught().size() + ":" + player.getMaxCombatPower());
			Iterator<Location> ite =player.getPathVisited().listIterator();
			output.print(ite.next().toString());
			while(ite.hasNext()){
				output.print("->" + ite.next().toString());
			}
		}
	}
	
	public void findPath()throws Exception{
		findPath(player,1,"null");
	}
	
	private void findPath(Player player,int level,String comeFrom)throws Exception{
		Location currentLocation = player.getCurrentLocation();
		int currentRow = currentLocation.getRow();
		int currentColumn = currentLocation.getColumn();
		
		if(this.map.isOutOfBound(currentRow, currentColumn))
			return;
		
		Location element = this.map.getMapElement(currentRow, currentColumn);
		player.getPathVisited().add(player.getCurrentLocation());
		
		if(element instanceof Destination){
			if(player.calculateScore()>Player.maxScore){
				Player.maxScore = player.calculateScore();
				
				outputResult(player);
			}
			return;
		}
		
		if(level > 200)
			return;
		
		if(element instanceof Wall){
			return;
		}	
		
		Player playerUpdate = new Player(player);
		
		boolean refresh =false;
		if(element instanceof Station){
			refresh = true;
			Station station = (Station)element;
			if(!playerUpdate.hasVisited(station)){
				playerUpdate.getStationVisited().put(station.getLocation(), station);
				playerUpdate.setNumPokeBalls(playerUpdate.getNumPokeBalls()+station.getNumBalls());
				
				refresh = true;
			}
		}
		
		if(element instanceof Pokemon){
			Pokemon pokemon = (Pokemon)element;
			if(!playerUpdate.hasCaught(pokemon)){
				if(playerUpdate.getNumPokeBalls() >= pokemon.getNumRequiredBalls()){
					playerUpdate.getPokemonCaught().put(pokemon.getLocation(), pokemon);
					playerUpdate.setNumPokeBalls(player.getNumPokeBalls()-pokemon.getNumRequiredBalls());
					playerUpdate.getTypeCaught().add(pokemon.getType());
					if(pokemon.getCombatPower() > playerUpdate.getMaxCombatPower())
						playerUpdate.setMaxCombatPower(pokemon.getCombatPower());
					
					refresh = true;
				}
			}
		}
		
		level++;
				
		
		if(!comeFrom.equals("left")||refresh){
			Location newLocation = new Location(currentRow,currentColumn-1);
			Player player1 = new Player(playerUpdate);
			player1.setCurrentLocation(newLocation);
			findPath(player1,level,"right");
		}
		
		if(!comeFrom.equals("up")||refresh){
			Location newLocation = new Location(currentRow-1,currentColumn);
			Player player2 = new Player(playerUpdate);
			player2.setCurrentLocation(newLocation);
			findPath(player2,level,"down");
		}
		
		if((!comeFrom.equals("right"))|| refresh){
			Location newLocation = new Location(currentRow,currentColumn+1);
			Player player3 = new Player(playerUpdate);
			player3.setCurrentLocation(newLocation);
			findPath(player3,level,"left");
			
		}
		
		if((!comeFrom.contentEquals("down"))|| refresh){
			Location newLocation = new Location(currentRow+1,currentColumn);
			Player player4 = new Player(playerUpdate);
			player4.setCurrentLocation(newLocation);
			findPath(player4,level,"up");
		}
	}
	
	public void setInputFile(String file)throws Exception{
		this.inputFile = new File(file);
	}
	
	public void setOutputFile(String file)throws Exception{
		this.outputFile = new File(file);
	}
	
	public Map getMap(){
		return this.map;
	}
	

	
	public static void main(String[] args) throws Exception{
		Game game = new Game("./sampleIn.txt","./sampleOut.txt",new Player());
		if (args.length > 0) 
			game.setInputFile(args[0]);
	
		if (args.length > 1) 
			game.setOutputFile(args[1]);
		
		game.findPath();
		
		// TO DO 
		// Read the configures of the map and pokemons from the file inputFile
		// and output the results to the file outputFile
	}
}
