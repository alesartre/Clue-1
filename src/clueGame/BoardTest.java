package clueGame;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private Board testBoard;
	
	@Before
	public void setUp(){
		ClueGame game = new ClueGame("ourBoard.csv", "Legend.txt");
		game.loadConfigFiles();
		testBoard = game.getBoard();
		
	}

	@Test
	public void boardDimensions(){
		Assert.assertEquals(28, testBoard.getNumRows());
		Assert.assertEquals(22, testBoard.getNumColumns());
	}
	
	@Test
	public void roomLegend(){
		Map<Character, String> rooms = testBoard.getRooms();
		Assert.assertEquals(rooms.get('C') , "Cat Room");
		Assert.assertEquals(rooms.get('R') , "Room");
		Assert.assertEquals(rooms.get('L'), "Loomery");
		Assert.assertEquals(rooms.get('S'), "Sauna");
		Assert.assertEquals(rooms.get('H'), "Halfway House");
		Assert.assertEquals(rooms.get('O') , "Bob");
		Assert.assertEquals(rooms.get('D'), "Dog Room");
		Assert.assertEquals(rooms.get('B'), "Ballad Room");
		Assert.assertEquals(rooms.get('K') , "Kaligraphy Room");
		Assert.assertEquals(rooms.get('W') , "Walkway");
		
	}
	
	@Test
	public void doorDirection(){
		BoardCell cell = testBoard.getCellAt(5, 12);
		Assert.assertTrue(((RoomCell) cell).getDoorDirection() == RoomCell.DoorDirection.DOWN);
		cell = testBoard.getCellAt(23, 0);
		Assert.assertTrue( ((RoomCell) cell).getDoorDirection() == RoomCell.DoorDirection.UP);
		cell = testBoard.getCellAt(3,20);
		Assert.assertTrue( ((RoomCell)cell).getDoorDirection() == RoomCell.DoorDirection.LEFT);
	}
	
	@Test 
	public void roomInitials(){
		
		Assert.assertEquals('D', testBoard.getRoomCellAt(3,3).getInitial());
		Assert.assertEquals('O', testBoard.getRoomCellAt(24,12).getInitial());
		Assert.assertEquals('B', testBoard.getRoomCellAt(15,1).getInitial());
		Assert.assertEquals('R', testBoard.getRoomCellAt(0,8).getInitial());

	}
	
	
	
}
