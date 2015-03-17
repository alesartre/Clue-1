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
			loadPeople("HIMYM_CHARACTERS.txt");
			loadCardConfig("HIMYM_CARDS.txt");
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
	
	public void loadPeople(String textfile) throws FileNotFoundException{
		FileReader charFile = new FileReader(textfile);
		Scanner scan = new Scanner(charFile);
		people = new ArrayList<Player>();
		while(scan.hasNextLine()){
			String temp = scan.nextLine();
			String[] temp1 = temp.split(",");
			if(people.size()==0){
				people.add(new HumanPlayer(temp1[0], temp1[1], Integer.parseInt(temp1[2]), Integer.parseInt(temp1[3].trim())));
			}
			else
				people.add(new ComputerPlayer(temp1[0], temp1[1], Integer.parseInt(temp1[2]), Integer.parseInt(temp1[3].trim())));
		}
		
	}
	
	public void loadCardConfig(String textfile) throws FileNotFoundException {
		FileReader cardFile = new FileReader(textfile);
		Scanner scan = new Scanner(cardFile);
		deck = new ArrayList<Card>();
		while(scan.hasNextLine()){
			String temp = scan.nextLine();
			String[] temp1 = temp.split(",");
			deck.add(new Card(temp1[0].charAt(0),temp1[1].trim()));
		}
		
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
