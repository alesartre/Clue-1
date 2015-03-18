package clueGame;

import java.util.ArrayList;
import java.util.Set;

public class ComputerPlayer extends Player{
	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
		// TODO Auto-generated constructor stub
	}
	
	public ComputerPlayer(){
		super();
	}

	private char lastRoomVisited;
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
		return null;
		
	}
	
	public char getVisited(){
		return lastRoomVisited;
	}
	
	public void setVisited(char x){
		lastRoomVisited = x;
	}
	
	public void createSuggestion() {
		
	}
	
	public void updateSeen(Card seen) {
		
	}

}
