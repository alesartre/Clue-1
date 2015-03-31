package clueGame;

import java.awt.*;

public class Walkway extends BoardCell{
	Walkway(int row, int col) {
		super(row, col);
	}
	
	@Override
	public void draw(Graphics g, Board b){
		g.setColor(Color.decode("#89888C"));
		super.draw(g, b);
		g.setColor(Color.BLACK);
		g.drawRect(this.getCol()*Board.CELL_DIMENSION,this.getRow()*Board.CELL_DIMENSION,Board.CELL_DIMENSION,Board.CELL_DIMENSION);
	}
	
	@Override
	public boolean isWalkway(){
		return true;
	}
}
