package clueGame;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private Board testBoard;
	
	@Before
	public void setUp(){
		//testBoard = new Board();
	}

	@Test
	public void boardDimensions(){
		Assert.assertEquals(28, testBoard.getNumRows());
		Assert.assertEquals(22, testBoard.getNumColumns());
	}
	
	@Test
	public void roomLegend(){
		Assert.assertTrue(testBoard.rooms.get("C") == "Cat Room");
		Assert.assertTrue(testBoard.rooms.get("R") == "Room");
		Assert.assertTrue(testBoard.rooms.get("L") == "Loomery");
		Assert.assertTrue(testBoard.rooms.get("S") == "Sauna");
		Assert.assertTrue(testBoard.rooms.get("H") == "Halfway House");
		Assert.assertTrue(testBoard.rooms.get("O") == "Bob");
		Assert.assertTrue(testBoard.rooms.get("D") == "Dog Room");
		Assert.assertTrue(testBoard.rooms.get("B") == "Ballad Room");
		Assert.assertTrue(testBoard.rooms.get("K") == "Kaligraphy Room");
		Assert.assertTrue(testBoard.rooms.get("W") == "Walkway");
		
	}
	
	@Test
	public void doorDirection(){
		
	}
	
	@Test 
	public void roomInitials(){
		
	}
	
	@Test
	public void exceptionHandling(){
		
	}
	
	
	
}
