package clueGame;

public class Solution {

	private String person;
	private String weapon;
	private String room;
	
	public Solution(Card p, Card w, Card r){
		this.person = p.getName();
		this.weapon = w.getName();
		this.room = r.getName();
		
	}
	
	
}
