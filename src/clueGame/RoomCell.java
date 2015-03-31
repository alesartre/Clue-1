 package clueGame;

import java.awt.*;

public class RoomCell extends BoardCell  {
	private boolean hasName = false;
	
	RoomCell(int row, int col, String str) {
		super(row, col);
		// TODO Auto-generated constructor stub
		initial = str.charAt(0); // Placeholder for code testing
		
		if(str.length() == 2){
			//System.out.println(str.charAt(1));
			switch(str.charAt(1)){
			case 'U': doorDirection = DoorDirection.UP;
				isDoor = true;
				break;
			case 'D': doorDirection = DoorDirection.DOWN;
				isDoor = true;
				break;
			case 'L': doorDirection = DoorDirection.LEFT;
				isDoor = true;
				break;
			case 'R': doorDirection = DoorDirection.RIGHT;
				isDoor = true;
				break;
			case 'Q':
				hasName = true;
				break;
			}	
		}
		else{
			isDoor = false;
		}
	}
	
	
	
	
	public enum DoorDirection{
		UP, DOWN, LEFT, RIGHT;
	}
	
	private Character initial;
	private DoorDirection doorDirection;
	private boolean isDoor = false;

	@Override
	public boolean isRoom(){
		return true;
	}
	
	@Override
	public boolean isDoorway(){
		return isDoor;
	}
	
	public DoorDirection getDoorDirection(){
		return doorDirection;
	}
	
	public char getInitial(){
		return initial;
	}
	
	public boolean isHasName() {
		return hasName;
	}
	
	@Override
	public void draw(Graphics g, Board b) {
		g.setColor(Color.BLACK);
		if(this.isHasName()) {
			String name = b.getRooms().get(this.getInitial());
			g.drawString(name, getCol()*Board.CELL_DIMENSION, getRow()*Board.CELL_DIMENSION);
		}
		
		g.setColor(Color.decode("#C2E0F2"));
		g.fillRect(getCol()*Board.CELL_DIMENSION, getRow()*Board.CELL_DIMENSION, Board.CELL_DIMENSION, Board.CELL_DIMENSION);
		
		if (isDoorway()) {
			g.setColor(Color.decode("#514F59"));
			if (getDoorDirection()==DoorDirection.DOWN) {
				g.fillRect(getCol()*Board.CELL_DIMENSION, getRow()*Board.CELL_DIMENSION + (int)(0.8*Board.CELL_DIMENSION), Board.CELL_DIMENSION, (int)(0.2*Board.CELL_DIMENSION));
			}
			else if (getDoorDirection()==DoorDirection.UP) {
				g.fillRect(getCol()*Board.CELL_DIMENSION, getRow()*Board.CELL_DIMENSION, Board.CELL_DIMENSION, (int)(0.2*Board.CELL_DIMENSION));
			}
			else if (getDoorDirection()==DoorDirection.LEFT) {
				g.fillRect(getCol()*Board.CELL_DIMENSION, getRow()*Board.CELL_DIMENSION, (int)(0.2*Board.CELL_DIMENSION), Board.CELL_DIMENSION);
			}
			else if (getDoorDirection()==DoorDirection.RIGHT) {
				g.fillRect(getCol()*Board.CELL_DIMENSION + (int)(0.8*Board.CELL_DIMENSION), getRow()*Board.CELL_DIMENSION, (int)(0.2*Board.CELL_DIMENSION), Board.CELL_DIMENSION);
			}
		}
	}
}
