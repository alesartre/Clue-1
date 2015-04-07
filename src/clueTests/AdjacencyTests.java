package clueTests;

import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ClueGame;


public class AdjacencyTests {
	private static Board board;
	private Set<BoardCell> targets;

	
	@BeforeClass
	public static void setUp(){
		ClueGame game = new ClueGame("ourBoard.csv","Legend.txt");
		game.loadConfigFiles();
		board = game.getBoard();
		board.calcAdjacencies();
	}
	
	@Test
	public void targetsAlongWalkWays(){
		board.calcTargets(9, 8, 2);
		targets = board.getTargets();
		
	
		Assert.assertTrue(targets.contains(board.getCellAt(7,8)));
		Assert.assertTrue(targets.contains(board.getCellAt(9,6)));
		Assert.assertTrue(targets.contains(board.getCellAt(9,10)));
		Assert.assertTrue(targets.contains(board.getCellAt(8,7)));
		Assert.assertTrue(targets.contains(board.getCellAt(8,9)));
		
	}
	
	@Test
	public void edgeLocations(){
		
		board.calcTargets(4, 4, 1);
		targets=board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt(4,5)));
				
		board.calcTargets(3, 20, 1);
		targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt( 3, 19)));
		
		board.calcTargets(23, 0, 1);
		targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt( 22, 0)));
	
		board.calcTargets(22, 19 ,1);
		targets=board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt( 21, 19)));
	}
	
	@Test
	public void besideRoomCell(){
	
		board.calcTargets(16, 16, 1);
		targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt(16,15)));
		Assert.assertTrue(targets.contains(board.getCellAt(15,16)));
		Assert.assertTrue(targets.contains(board.getCellAt(17,16)));
		
	}
	
	@Test
	public void adjacentDoorway(){
		
		board.calcTargets(20, 16, 1);
		targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt(20,15)));
		//Assert.assertTrue(targets.contains(board.getCellAt(20,14)));
		Assert.assertTrue(targets.contains(board.getCellAt(19,16)));
		Assert.assertTrue(targets.contains(board.getCellAt(21,16)));
	}
	
	@Test
	public void doorways(){
		
		board.calcTargets(4, 4, 1);
		targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt(4,5)));
		
		
		board.calcTargets(5, 5, 1);
		targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt(5,4)));
		
		
		board.calcTargets(9, 3, 1);
		targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt(8,3)));
	}
	
	@Test
	public void walkwayTargets(){
		
		board.calcTargets(13, 15, 3);
		targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt( 10, 15)));
		Assert.assertTrue(targets.contains(board.getCellAt( 16, 15)));
		Assert.assertTrue(targets.contains(board.getCellAt( 11,16 )));
		Assert.assertTrue(targets.contains(board.getCellAt(12, 15)));
		Assert.assertTrue(targets.contains(board.getCellAt( 14,15 )));
		Assert.assertTrue(targets.contains(board.getCellAt( 15, 16)));
		Assert.assertTrue(targets.contains(board.getCellAt(11 ,14 )));
		Assert.assertTrue(targets.contains(board.getCellAt(15 ,14)));
		Assert.assertTrue(targets.contains(board.getCellAt(13 , 16)));
		Assert.assertTrue(targets.contains(board.getCellAt( 13, 14)));
		
	}
	
	@Test
	public void enterRoomTargets(){
		
		board.calcTargets(20, 15, 2);
		targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt( 20,17 )));
		Assert.assertTrue(targets.contains(board.getCellAt(19 ,16 )));
		Assert.assertTrue(targets.contains(board.getCellAt(21 ,16 )));
		Assert.assertTrue(targets.contains(board.getCellAt( 19, 14)));
		Assert.assertTrue(targets.contains(board.getCellAt( 21,14 )));
		Assert.assertTrue(targets.contains(board.getCellAt( 20,13 )));
		Assert.assertTrue(targets.contains(board.getCellAt( 18,15 )));
	}
	
	@Test
	public void leavingRoomTargets(){
		
		board.calcTargets(8, 15, 4);
		targets=board.getTargets();
		Assert.assertTrue(targets.contains(board.getCellAt(8,17)));
		Assert.assertTrue(targets.contains(board.getCellAt( 10,17 )));
		Assert.assertTrue(targets.contains(board.getCellAt( 10, 15)));
		Assert.assertTrue(targets.contains(board.getCellAt( 12,15 )));
		Assert.assertTrue(targets.contains(board.getCellAt(11 ,14 )));
		Assert.assertTrue(targets.contains(board.getCellAt( 9, 12)));
		Assert.assertTrue(targets.contains(board.getCellAt(8 ,13 )));
		Assert.assertTrue(targets.contains(board.getCellAt( 9, 16)));
	}
	


}
