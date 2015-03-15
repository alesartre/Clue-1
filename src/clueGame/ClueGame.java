package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ClueGame {
	
	public ClueGame(String layoutFileName, String legendFileName){
		super();
		this.layoutFileName = layoutFileName;
		this.legendFileName = legendFileName;
		rooms = new HashMap<Character, String>();
		board = new Board(rooms, layoutFileName);
	}
	
	public ClueGame(){
		super();
		layoutFileName = "ClueLayout.csv";
		legendFileName = "ClueLegend.txt";
	}
	
	private Map<Character, String> rooms;
	private String layoutFileName;
	private String legendFileName;
	private Board board;
	private ArrayList<Card> deck;
	private ArrayList<Player> people;
	private ArrayList<Card> dealt;
	
	public void loadConfigFiles(){
		try {
			loadRoomConfig();
			board = new Board(rooms, layoutFileName);
			board.loadBoardConfig();
			loadCardConfig();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Read in and create the legend
	public void loadRoomConfig() throws BadConfigFormatException, FileNotFoundException{
		FileReader fileIn = new FileReader(legendFileName);
		Scanner scan = new Scanner(fileIn);
		rooms = new HashMap<Character, String>();
		while(scan.hasNextLine()){
			//take each line and separate it by the comma, and put it into the rooms map
			String temp = scan.nextLine();
			String[] temp1 = temp.split(",");
			if( temp1.length != 2){
				throw new BadConfigFormatException("A line of the legend does not have a key and a name.");
			}
			else if( temp1[0].length() != 1 ){
				throw new BadConfigFormatException("Each key in the legend should have exactly one character.");
			}			
			else{
				rooms.put(temp1[0].charAt(0),temp1[1].trim());
			}
		}
		scan.close();
		try{
			fileIn.close();
		}catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public void loadCardConfig() {
		
		
	}
	
	public void deal(){
		
		
	}
	
	public Board getBoard(){
		return board;
	}
	
	public Player getPerson(int x){
		return people.get(x);
	}
	
	public ArrayList<Card> getDeck(){
		return deck;
	}
}
