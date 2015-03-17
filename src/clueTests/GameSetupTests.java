package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Card;
import clueGame.ClueGame;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;

public class GameSetupTests {

	
	private static ClueGame game;
	
	@BeforeClass
	public static void setup(){
		game = new ClueGame("ClueLayout.csv", "ClueLegend.txt");
		game.loadConfigFiles(); // This function creates the board, loads the rooms, cards, and the people
		
	}
	
	@Test
	public void loadPeople(){
		Assert.assertEquals(game.getPerson(0), new HumanPlayer("Ted Mosby", "MAGENTA", 21, 21) ); // Human Player is stored at 0 in the array List of players
		Assert.assertEquals(game.getPerson(2), new ComputerPlayer("Marshall Eriksen", "GREEN" , 0, 19) );
		Assert.assertEquals(game.getPerson(5), new ComputerPlayer("Tracy McDonald", "ORANGE", 11, 0 ) );
		
		
	}
	
	
	
	
}
