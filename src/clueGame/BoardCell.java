package clueGame;

import java.awt.*;

public abstract class BoardCell {

	private int row;
	private int col;
	
	BoardCell( int row, int col){
		super();
		this.row = row;
		this.col = col;
	}
	
	public void draw(Graphics g, Board b){
		
	}
	
	public boolean isRoom(){
		return false;
	}
	
	public boolean isWalkway(){
		return false;
	}
	
	public boolean isDoorway(){
		return false;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return col;
	}
}
