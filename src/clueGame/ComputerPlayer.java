package clueGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import clueGame.Card.CardType;

public class ComputerPlayer extends Player{
	private char lastRoomVisited;
	private ArrayList<Card> seen = new ArrayList<Card>();
	
	public ComputerPlayer(String name, String color, int row, int col) {
		super(name, color, row, col);
		// TODO Auto-generated constructor stub
	}
	
	public ComputerPlayer(){
		super();
		isCurrentPlayer = false;
	}
	
	// Chooses the cell that the computer player will move to.
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
	
	//LastRoom Visited must be updated before a suggestion can be made after a computer player moves into a room
	public ArrayList<Card> createSuggestion(ArrayList<Card> deck) {
		ArrayList<Card> accuse = new ArrayList<Card>();
		Set<Card> weapons = new HashSet<Card>();
		Set<Card> peoples = new HashSet<Card>();
		Set<Card> rooms = new HashSet<Card>();
		for(Card c : deck){
			if(c.getCardType()== CardType.WEAPON && !seen.contains(c)){
				weapons.add(c);
			}
			else if(c.getCardType()==CardType.PERSON && !seen.contains(c)){
				peoples.add(c);
			}	
		}
		
		int W = new Random().nextInt(weapons.size());//get weapon
		int P = new Random().nextInt(peoples.size());
		
		int i = 0;
		for(Card c : weapons){
			if(i==W){
				accuse.add(c);
			}
			i=i+1;
		}
		i=0;
		for(Card c : peoples){
			if(i==P){
				accuse.add(c);
			}
			i = i+1;
		}
		System.out.println(lastRoomVisited);
		System.out.println(ClueGame.letterToName(lastRoomVisited));
		accuse.add(new Card('R',ClueGame.letterToName(lastRoomVisited)));
		System.out.println(accuse.get(2));
		return accuse;
	}
	
	public void updateSeen(Card x) {
		seen.add(x);
	}
	
	public char getVisited(){
		return lastRoomVisited;
	}
	
	public void setVisited(char x){
		lastRoomVisited = x;
	}
}
