
public class Pokemon extends Location{
	private String name;
	private String type;
	private int combatPower;
	private int numRequiredBalls;
	
	public Pokemon(String name,String type,int combatPower,int numRequiredBalls,int row,int column){
		super(row,column);
		this.name = name;
		this.type = type;
		this.combatPower = combatPower;
		this.numRequiredBalls = numRequiredBalls;
	}
	
	public Location getLocation(){
		Location currentLocation = new Location(getRow(),getColumn());
		return currentLocation;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getType(){
		return this.type;
	}
	
	public int getNumRequiredBalls(){
		return this.numRequiredBalls;
	}
	
	public int getCombatPower(){
		return this.combatPower;
	}

}
