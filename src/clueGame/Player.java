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
			java.lang.reflect.Field field = Class.forName("java.awt.Color").getField(strColor.trim());     
			color = (Color)field.get(null); } 
		catch (Exception e) {  
			color = null; // Not defined } 
		}
		return color;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", startR=" + startR + ", startC="
				+ startC + ", color=" + color + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + startC;
		result = prime * result + startR;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startC != other.startC)
			return false;
		if (startR != other.startR)
			return false;
		return true;
	}
	
	
	//For Testing
	public ArrayList<Card> getCardList(){
		return cardList;
	}


}
