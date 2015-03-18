package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ClueGame;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

public class GameSetupTests {

	
	private static ClueGame game;
	private static ArrayList<Card> testdeck;
	@BeforeClass
	public static void setup(){
		game = new ClueGame();
		game.loadConfigFiles(); // This function creates the board, loads the rooms, cards, and the people
		game.getBoard().calcAdjacencies();
		testdeck = new ArrayList<Card>();
		testdeck.add(new Card('P',"Barney Stinson"));
		testdeck.add(new Card('W',"Ducky Tie"));
		testdeck.add(new Card('R',"Metro News 1"));
	}
	
	@Test
	public void loadPeople(){
		Assert.assertEquals(game.getPerson(0), new HumanPlayer("Ted Mosby", "MAGENTA", 21, 21) ); // Human Player is stored at 0 in the array List of players
		Assert.assertEquals(game.getPerson(2), new ComputerPlayer("Marshall Eriksen", "GREEN" , 0, 19) );
		Assert.assertEquals(game.getPerson(5), new ComputerPlayer("Tracy McDonald", "ORANGE", 11, 0 ) );
		
		
	}
	
	
	@Test
	public void loadCards(){
		ArrayList<Card> deck = game.getDeck();
		Assert.assertEquals(deck.size(), 21);
		Assert.assertTrue(deck.contains(testdeck.get(0)));
		Assert.assertTrue(deck.contains(testdeck.get(1)));
		Assert.assertTrue(deck.contains(testdeck.get(2)));
	}
	
	@Test
	public void dealCardsTest(){
		ArrayList<Player> peeps = game.getPeople();
		ArrayList<Card> deck = game.getDeck();
		ArrayList<Card> deckTest = new ArrayList<Card>();
		for( Player p: peeps){
			if(p.getCardList().size()!=3){ //makes sure every player has 3 cards
				System.out.println(p.getCardList().size());
				fail();
			}
			for( Card c: p.getCardList()){
				if(deckTest.contains(c)){//makes sure no two players have the same card
					fail();
				}
				else{
					deckTest.add(c);
				}
			}
		}
		for(Card c: deckTest){//makes sure all cards were dealt
			if(!deck.contains(c)){
				fail();
			}
		}
		
		
	}
	
	@Test
	public void accusations(){
		Solution testSol = new Solution(testdeck.get(1),testdeck.get(0),testdeck.get(2));
		Assert.assertTrue(testSol.accuse(new Card('W',"Ducky Tie"), new Card ('P',"Barney Stinson"), new Card('R',"Metro News 1")));
		Assert.assertFalse(testSol.accuse(new Card('W',"Ducky Tie"),new Card('P',"Ted Mosby"), new Card('R',"Metro News 1")));
		Assert.assertFalse(testSol.accuse(new Card('W',"Yellow Umbrella"),new Card('P',"Barney Stinson"), new Card('R',"Metro News 1")));
		Assert.assertFalse(testSol.accuse(new Card('W',"Ducky Tie"),new Card('P',"Barney Stinson"), new Card('R',"Lily's Classroom")));
		Assert.assertFalse(testSol.accuse(new Card('W',"Yellow Umbrella") ,new Card('P',"Ted Mosby"), new Card('R',"Lily's Classroom")));
	}
	
	@Test
	public void oneplayeronematch(){
		Card tedcard  = new Card('P', "Ted Mosby");
		Card barneycard = new Card('P', "Barney Stinson");
		Card slapcard = new Card('W', "A Slap");
		Card tiecard = new Card('W', "Ducky Tie");
		Card pubcard = new Card('R', "MacLaren's Pub");
		Card newscard = new Card('R', "Metro News 1");
		Player testplayer = new ComputerPlayer("Ted Mosby", "MAGENTA", 0, 0);
		testplayer.getCardList().add(tedcard);
		testplayer.getCardList().add(barneycard);
		testplayer.getCardList().add(slapcard);
		testplayer.getCardList().add(tiecard);
		testplayer.getCardList().add(pubcard);
		testplayer.getCardList().add(newscard);
		Assert.assertEquals(testplayer.disproveSuggestion("Ted Mosby", "Farhampton Inn", "Yellow Umbrella"), new Card('P', "Ted Mosby"));
		Assert.assertEquals(testplayer.disproveSuggestion("Marshall Eriksen", "Metro News 1", "Yellow Umbrella"), new Card('R', "Metro News 1"));
		Assert.assertEquals(testplayer.disproveSuggestion("Marshall Eriksen", "Farhampton Inn", "A Slap"), new Card('W', "A Slap"));
		Assert.assertEquals(testplayer.disproveSuggestion("Marshall Eriksen", "Farhampton Inn", "Yellow Umbrella"), null);
	}
	
	@Test
	public void oneplayerMultiplematches(){
		Card barneycard = new Card('P', "Barney Stinson");
		Card tiecard = new Card('W', "Ducky Tie");
		Card newscard = new Card('R', "Metro News 1");
		Player testplayer = new ComputerPlayer("Ted Mosby", "MAGENTA", 0, 0);
		testplayer.getCardList().add(barneycard);
		testplayer.getCardList().add(tiecard);
		testplayer.getCardList().add(newscard);
		int p = 0;
		int r = 0;
		int w = 0;
		int i = 0;
		while(i < 100){
			Card testCard = testplayer.disproveSuggestion("Barney Stinson", "Ducky Tie", "Metro News 1");
			if(testCard.getName().equals("Barney Stinson")){
				p++;
			}
			if(testCard.getName().equals("Ducky Tie")){
				w++;
			}
			if(testCard.getName().equals("Metro News 1")){
				r++;
			}
			i++;
		}
		Assert.assertTrue(p>1);
		Assert.assertTrue(r>1);
		Assert.assertTrue(w>1);
		
	}
	
	@Test
	public void playerQueryTest(){
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new HumanPlayer("Ted Mosby", "MAGENTA", 0,0));
		players.add(new ComputerPlayer("Barney Stinson", "RED", 0, 0));
		players.add(new ComputerPlayer("Marshall Eriksen", "GREEN", 0, 0));
		players.add(new ComputerPlayer("Lily Aldrin", "YELLOW", 0, 0));
		players.get(0).getCardList().add(new Card('P', "Ted Mosby"));
		players.get(0).getCardList().add(new Card('W', "A Slap"));
		players.get(0).getCardList().add(new Card('R', "Metro News 1"));
		players.get(1).getCardList().add(new Card('P', "Tracy McDonald"));
		players.get(1).getCardList().add(new Card('W', "Yellow Umbrella"));
		players.get(1).getCardList().add(new Card('R', "Farhampton Inn"));
		players.get(2).getCardList().add(new Card('P', "Lily Aldrin"));
		players.get(2).getCardList().add(new Card('W', "Pineapple"));
		players.get(2).getCardList().add(new Card('R', "Laser Tag Arena"));
		players.get(3).getCardList().add(new Card('P', "Barney Stinson"));
		players.get(3).getCardList().add(new Card('W', "Cockamouse"));
		players.get(3).getCardList().add(new Card('R', "MacLaren's Pub"));
		//New ClueGame for this test so all other tests remain unchanged
		ClueGame queryTestGame = new ClueGame("ClueLayout.csv", "ClueLegend.txt");
		queryTestGame.loadConfigFiles();
		queryTestGame.setPeople(players);
		//Test that null is returned when no players can disprove
		Assert.assertEquals(queryTestGame.handleSuggestion("Marshall Eriksen", "Cafe L'amour", "Red Cowboy Boots", queryTestGame.getPeople(), 0), null);
		//Test that the correct card is returned when only one player can disprove
		Assert.assertEquals(queryTestGame.handleSuggestion("Ted Mosby", "Cafe L'amour", "Red Cowboy Boots", queryTestGame.getPeople(), 1), new Card('P', "Ted Mosby"));
		//Test that null is returned when only the active player can disprove, for both human and computer players
		Assert.assertEquals(queryTestGame.handleSuggestion("Ted Mosby", "Cafe L'amour", "Red Cowboy Boots", queryTestGame.getPeople(), 0), null);
		Assert.assertEquals(queryTestGame.handleSuggestion("Lily Aldrin", "Cafe L'amour", "Red Cowboy Boots", queryTestGame.getPeople(), 2), null);
		//Test that the first player in line disproves a suggestion that multiple players can disprove
		Assert.assertEquals(queryTestGame.handleSuggestion("Tracy McDonald", "MacLaren's Pub", "Red Cowboy Boots", queryTestGame.getPeople(), 0), new Card('P', "Tracy McDonald"));
		//Test that all players are queried
		Assert.assertEquals(queryTestGame.handleSuggestion("Marshall Eriksen", "MacLaren's Pub", "Red Cowboy Boots", queryTestGame.getPeople(), 0), new Card('R', "MacLaren's Pub"));
		
	}
	
	@Test
	public void targetLocationsRandom(){//no doors
		ComputerPlayer p1 = new ComputerPlayer();
		game.getBoard().calcTargets(9, 7, 2);
		int location1 = 0;
		int location2 = 0;
		int location3 = 0;
		int location4 = 0;
		int location5 = 0;
		int location6 = 0;
		for(int i =0; i<100; i++){
			BoardCell choice = p1.pickLocation(game.getBoard().getTargets());
			if(choice == game.getBoard().getCellAt(9,9)){
				location1++;
			}
			else if(choice == game.getBoard().getCellAt(7,7)){
				location2++;
			}
			else if(choice == game.getBoard().getCellAt(9,5)){
				location3++;
			}
			else if(choice == game.getBoard().getCellAt(8,8)){
				location4++;
			}
			else if (choice == game.getBoard().getCellAt(8,6)){
				location5++;
			}
			else if(choice == game.getBoard().getCellAt(10, 6)){
				location6++;
			}
			else 
				fail();
		}
		Assert.assertEquals(100, location1+location2+location3+location4+location5+location6);
		Assert.assertTrue(location1>7);
		Assert.assertTrue(location2>7);
		Assert.assertTrue(location3>7);
		Assert.assertTrue(location4>7);
		Assert.assertTrue(location5>7);
		Assert.assertTrue(location6>7);
	}
	
	@Test
	public void targetLocationsRoomPref(){
		ComputerPlayer p1 = new ComputerPlayer();
		game.getBoard().calcTargets(7, 11, 4);//one door
		Assert.assertEquals(p1.pickLocation(game.getBoard().getTargets()), game.getBoard().getCellAt(5, 12));
	}
	
	@Test
	public void targetLocationVisitedRoom(){//same as targetLocationsRandom but a list with a door is passed
		ComputerPlayer p1 = new ComputerPlayer();
		p1.setVisited('C');
		game.getBoard().calcTargets(10, 15, 2);
		int location1 = 0;
		int location2 = 0;
		int location3 = 0;
		int location4 = 0;
		int location5 = 0;
		int location6 = 0;
		for(int i =0; i<100; i++){
			
			BoardCell choice = p1.pickLocation(game.getBoard().getTargets());
			System.out.println(choice.getRow());
			System.out.println(choice.getCol());
			System.out.println("-----------");
			if(choice == game.getBoard().getCellAt(10,17)){
				location1++;
			}
			else if(choice == game.getBoard().getCellAt(12,15)){
				location2++;
			}
			else if(choice == game.getBoard().getCellAt(9,14)){
				location3++;
			}
			else if(choice == game.getBoard().getCellAt(11,16)){
				location4++;
			}
			else if(choice == game.getBoard().getCellAt(9,16)){
				location5++;
			}
			else if(choice == game.getBoard().getCellAt(11,14)){
				location6++;
			}
			else 
				fail();
		}
		Assert.assertEquals(100, location1+location2+location3+location5+location6+location4);
		Assert.assertTrue(location1>7);
		Assert.assertTrue(location2>7);
		Assert.assertTrue(location3>7);
		Assert.assertTrue(location4>7);
		Assert.assertTrue(location5>7);
		Assert.assertTrue(location6>7);
		
	}
	
	@Test
	public void computerSuggestionTrue(){
		ComputerPlayer p1 = new ComputerPlayer();
		p1.setVisited('M');
		game.setSolution(new Card('W',"Ducky Tie"),new Card('P', "Ted Mosby"),new Card('R', "MacLaren's Pub"));
		for(Card c : game.getDeck()){
			if(!c.equals(new Card('P',"Ted Mosby"))&&!c.equals(new Card('W', "Ducky Tie"))&&!c.equals(new Card('R', "MacLaren's Pub"))){	
				p1.updateSeen(c);
			}
		}
		Assert.assertTrue(game.checkAccusation(p1.createSuggestion(game.getDeck())));
	}
	
	@Test
	public void computerSuggestionRandom(){
		ComputerPlayer p1 = new ComputerPlayer();
		p1.updateSeen(game.getDeck().get(2));
		p1.updateSeen(game.getDeck().get(7));
		
		Assert.assertFalse(p1.createSuggestion(game.getDeck()).contains(game.getDeck().get(2)));
		Assert.assertFalse(p1.createSuggestion(game.getDeck()).contains(game.getDeck().get(7)));
		
		
	}
	
}

