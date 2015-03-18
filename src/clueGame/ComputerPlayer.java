package clueGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
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
	private ArrayList<Card> seen;
	
	public BoardCell pickLocation(HashSet<BoardCell> targets) {
		//System.out.println(targets.size());
		boolean rem = false;
		BoardCell temp = null;
		for(BoardCell c: targets){
			if(c.isDoorway()){
					if(((RoomCell)c).getInitial() == lastRoomVisited){
						temp = c;
						rem = true;
					}
					else{
						return c;
					}
			}
		}
		if(rem == true){
			targets.remove(temp);
		}
		//System.out.println(targets.size());
		int R = new Random().nextInt(targets.size());
		int i = 0;
		for(BoardCell c: targets){
			if(R==i){
				return c;
			}
			i++;
		}
		return null;
		
	}
	
	public char getVisited(){
		return lastRoomVisited;
	}
	
	public void setVisited(char x){
		lastRoomVisited = x;
	}
	
	public ArrayList<Card> createSuggestion() {
		return null;
		
	}
	
	public void updateSeen(Card seen) {
		
	}

}
