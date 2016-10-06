
public class Station extends Location{
	private int numBalls;
	
	public Station(int row,int column,int numBalls){
		super(row,column);
		this.numBalls = numBalls;
	}
	
	public int getNumBalls(){
		return this.numBalls;
	}
	
	public Location getLocation(){
		Location currentLocation = new Location(getRow(),getColumn());
		return currentLocation;
	}
	

}
