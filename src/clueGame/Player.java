package clueGame;

import java.awt.Color;
import java.util.ArrayList;





public abstract class Player {
	private String name;
	private ArrayList<Card> cardList;
	private int startR;
	private int startC;
	private Color color;
	
	
	public Player(String name, String color, int row, int col){
		this.name = name;
		this.color = convertColor(color);
		this.startR = row;
		this.startC = col;
		
	}
	
	public Card disproveSuggestion(String person, String room, String weapon) {
		return null;
	}
	
	public Color convertColor(String strColor) {
		Color color; 
		try {     
			// We can use reflection to convert the string to a color
			Field field = Class.forName("java.awt.Color").getField(strColor.trim());     
			color = (Color)field.get(null); } 
		catch (Exception e) {  
			color = null; // Not defined } 
		}
		return color;
	}


}
