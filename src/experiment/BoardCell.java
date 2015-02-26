package experiment;

public class BoardCell {

	private int row;
	private int col;
	
	BoardCell( int row, int col){
		super();
		this.row = row;
		this.col = col;
	}
	
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	
	public boolean isWalkway(){
		return false;
	}
	
	public boolean isRoom(){
		return false;
	}
	
	public boolean isDoorway(){
		return false;
	}

}
