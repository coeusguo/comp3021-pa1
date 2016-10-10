
public class Location{
	private int row;
	private int column;
	
	public Location(int row,int column){
		this.row = row;
		this.column = column;
	}
	
	public Location(Location l){
		this.row = l.row;
		this.column = l.column;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}
	
	public void setLocation(Location location){
		this.row = location.row;
		this.column = location.column;
	}
	
	public void setRow(int x){
		this.row = x;
	}
	
	public void setColumn(int y){
		this.column = y;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	public String toString(){
		return "<" + row + "," + column + ">"; 
	}
	
	public Location clone() throws CloneNotSupportedException{
		return (Location)super.clone();
	}
}
