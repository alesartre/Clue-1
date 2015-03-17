package clueGame;

import java.util.ArrayList;

public class Solution {

	private String person;
	private String weapon;
	private String room;
	private ArrayList<Card> solution = new ArrayList<Card>();
	
	public Solution(Card w, Card p, Card r){
		this.person = p.getName();
		this.weapon = w.getName();
		this.room = r.getName();
		solution.add(p);
		solution.add(w);
		solution.add(r);
		
	}
	
	public boolean suggest(Card w, Card p, Card r){
		if(!w.getName().equals(weapon)){
			return false;
		
		}
		else if(!p.getName().equals(person)){
			return false;
		}
		else if(!r.getName().equals(room)){
			return false;
		}
		else{
			return true;
		}
	}


	public ArrayList<Card> getSolution(){
		return solution;
	}
	
	
}
