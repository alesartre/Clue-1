package clueGame;

import java.awt.*;

public abstract class BoardCell {

	private int row;
	private int col;
	private boolean isTarget;
	
	BoardCell( int row, int col){
		super();
		this.row = row;
		this.col = col;
		isTarget = false;
	}

	public void draw(Graphics g, Board b){
		if(isTarget) {
			g.setColor(Color.CYAN);
		}
		g.fillRect(getCol()*Board.CELL_DIMENSION, getRow()*Board.CELL_DIMENSION, Board.CELL_DIMENSION, Board.CELL_DIMENSION);
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
	
	public boolean isTarget() {
		return isTarget;
	}

	public void setTarget(boolean isTarget) {
		this.isTarget = isTarget;
	}
}
