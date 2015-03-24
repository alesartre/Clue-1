package clueGame;

import java.awt.*;

public class Walkway extends BoardCell{

	Walkway(int row, int col) {
		super(row, col);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isWalkway(){
		return true;
		
	}
	
	@Override
	public void draw(Graphics g, Board b){
		g.setColor(Color.YELLOW);
		g.fillRect(getCol()*CELL_DIMENSION, getRow()*CELL_DIMENSION, CELL_DIMENSION, CELL_DIMENSION);
		g.setColor(Color.BLACK);
		g.drawRect(this.getCol()*CELL_DIMENSION,this.getRow()*CELL_DIMENSION,CELL_DIMENSION,CELL_DIMENSION);
	}

}
