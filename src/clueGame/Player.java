package clueGame;



import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public abstract class Player {
	private String name;
	private ArrayList<Card> cardList = new ArrayList<Card>();
	private int row;
	private int col;
	private Color color;
	protected boolean isHuman;

	public Player(String name, String color, int row, int col){
		this.name = name;
		this.color = convertColor(color);
		this.row = row;
		this.col = col;
	}

	public Player(){
		super();
	}
	
	// If player has a card that can disprove current suggestion, choose one randomly from options and show.
	public Card disproveSuggestion(String person, String room, String weapon) {
		ArrayList<Card> choices = new ArrayList<Card>();
		for(Card c: cardList){
			
			if(c.getName().equals(person)){
				choices.add(c);
			}
			else if(c.getName().equals(room)){
				choices.add(c);
			}
			else if(c.getName().equals(weapon)){
				choices.add(c);
			}
		}
		if(choices.size() != 0){
			int rand = new Random().nextInt(choices.size());
			return choices.get(rand);
		}
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
	
	public void draw(Graphics g){
		g.setColor(color);
		g.fillOval(col*Board.CELL_DIMENSION, row*Board.CELL_DIMENSION, Board.CELL_DIMENSION, Board.CELL_DIMENSION);
	}
	
	public void move(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public String getName(){
		return name;
	}
	
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public boolean isHuman() {
		return isHuman;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", startR=" + row + ", startC="
				+ col + ", color=" + color + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + col;
		result = prime * result + row;
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
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	//For Testing
	public ArrayList<Card> getCardList(){
		return cardList;
	}

	public void setCardList(ArrayList<Card> cardList) {
		this.cardList = cardList;
	}
}
