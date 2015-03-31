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
	
	// Takes in an accusation as argument, checks against solution.
	public boolean accuse(Card w, Card p, Card r){
		if(!w.getName().equals(weapon)){
			System.out.println(w.getName());
			System.out.println(weapon);
			return false;
			
		}
		else if(!p.getName().equals(person)){
			System.out.println("person is wrong");
			return false;
		}
		else if(!r.getName().equals(room)){
			System.out.println("room is wrong");
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
