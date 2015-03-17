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


	public ArrayList<Card> getSolution(){
		return solution;
	}
	
	
}
