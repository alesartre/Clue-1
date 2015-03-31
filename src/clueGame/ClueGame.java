package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;

import clueGame.Card.CardType;

public class ClueGame extends JFrame{
	private static int TOP_BAR = 32;
	private static int BORDER = 8;
	private static Map<Character, String> rooms;
	private String layoutFileName;
	private String legendFileName;
	private Board board;
	private ArrayList<Card> deck;
	private ArrayList<Player> people;
	private ArrayList<Card> dealt;
	private Solution solved;	
	
	public ClueGame(String layoutFileName, String legendFileName){
		super();
		this.layoutFileName = layoutFileName;
		this.legendFileName = legendFileName;
		rooms = new HashMap<Character, String>();
		board = new Board(rooms, layoutFileName, people);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2,1));
		setTitle("Clue Game");
		loadConfigFiles();
		setSize(board.getWidth() + 2*BORDER, board.getHeight() + TOP_BAR + BORDER);
		System.out.println("board: " + board.getSize());
	}
	
	public ClueGame(){
		super();
		layoutFileName = "Board.csv";
		legendFileName = "Legend.txt";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Clue Game");
		loadConfigFiles();
		setSize(board.getWidth() + 2*BORDER, board.getHeight() + TOP_BAR  + BORDER);
		System.out.println("board: " + board.getSize());
	}
	
	// Call all methods to read and parse config files.
	public void loadConfigFiles(){
		try {
			loadRoomConfig();
			loadPeople("HIMYM_CHARACTERS.txt");
			board = new Board(rooms, layoutFileName, people);
			board.loadBoardConfig();
			drawBoard(board);
			loadCardConfig("HIMYM_CARDS.txt");
			deal();
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
	
	// Load the players and designate one as a Human.
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
	
	// Load the deck, and shuffle
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
	
	// Deal the deck to players.
	public void deal(){
		Set<Card> weapons = new HashSet<Card>();
		Set<Card> peoples = new HashSet<Card>();
		Set<Card> rooms = new HashSet<Card>();
		for(Card c : deck){
			if(c.getCardType()== CardType.WEAPON){
				weapons.add(c);
			}
			else if(c.getCardType()==CardType.PERSON){
				peoples.add(c);
			}
			else if(c.getCardType()==CardType.ROOM){
				rooms.add(c);
			}
		}
		
		int W = new Random().nextInt(weapons.size());//get weapon
		int P = new Random().nextInt(peoples.size());
		int R = new Random().nextInt(rooms.size());
		
		Card temp = null;
		Card temp2 = null;
		Card temp3 = null;
		int i = 0;
		for(Card c : weapons){
			if(i==W){
				temp = c;
			}
			i=i+1;
		}
		i=0;
		for(Card c : peoples){
			if(i==P){
				temp2 = c;
			}
			i = i+1;
		}
		i = 0;
		for(Card c: rooms){
			if(i==R){
				temp3 = c;
			}
			i = i+1;
		}
		
		solved = new Solution(temp, temp2, temp3);
		
		dealt = new ArrayList<Card>();
		dealt.add(temp);
		dealt.add(temp2);
		dealt.add(temp3);
		
		for(Player p : people){
			i = 0;
			while (i!=3){
				int index = new Random().nextInt(deck.size());
				if(!dealt.contains(deck.get(index))){
					p.getCardList().add(deck.get(index));
					dealt.add(deck.get(index));
					i++;
				}
			}
		}			
	}
	
	// Circulates table to see if any player can disprove the current suggestion.
	public Card handleSuggestion(String person, String room, String weapon, ArrayList<Player> players, int currentPlayer){
		int player = currentPlayer + 1;
		while(player%players.size() != currentPlayer){
			Card result = players.get(player%players.size()).disproveSuggestion(person, room, weapon);
			if (result != null){
				return result;
			}
			player++;
		}
		return null;
	}
	
	// Check if a player's accusation is correct.
	public boolean checkAccusation(ArrayList<Card> verdict){
		return solved.accuse(verdict.get(0), verdict.get(1), verdict.get(2));
	}
	
	public void drawBoard(Board b){
		add(b, BorderLayout.CENTER);
	}
	
	public static String letterToName(char letter){
		return rooms.get(letter);
	}
	
	public Board getBoard(){
		return board;
	}
	
	public ArrayList<Player> getPeople(){
		return people;
	}
	
	public Player getPerson(int x){
		return people.get(x);
	}
	
	public ArrayList<Card> getDeck(){
		return deck;
	}

	//Testing only
	public void setPeople(ArrayList<Player> people) {
		this.people = people;
	}
	
	// Choose the three cards that are the murder items.
	public void setSolution(Card w, Card p, Card r){
		solved = new Solution(w,p,r);
	}
	
	
	
	/*************************************/
	/*************************************/
	// 			Main is here 			 //
	/*************************************/
	/*************************************/
	public static void main(String[] args){
		ClueGame game = new ClueGame();
		game.getBoard().calcAdjacencies();
		game.setVisible(true);
		ControlGui gui = new ControlGui(game);
		gui.setVisible(true);
	}

}
