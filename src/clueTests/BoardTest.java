package clueTests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ClueGame;
import clueGame.RoomCell;
import clueGame.RoomCell.DoorDirection;

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
		Assert.assertEquals(rooms.get('M'), "MacLaren's Pub");
		Assert.assertEquals(rooms.get('B'), "Barney's Bachelor Pad");
		Assert.assertEquals(rooms.get('T'), "Ted's Apartment");
		Assert.assertEquals(rooms.get('L'), "Laser Tag Arena");
		Assert.assertEquals(rooms.get('C'), "The Captain's Boat");
		Assert.assertEquals(rooms.get('R'), "Lily's Classroom");
		Assert.assertEquals(rooms.get('N'), "Metro News 1");
		Assert.assertEquals(rooms.get('A'), "Cafe L'amour");
		Assert.assertEquals(rooms.get('F'), "Farhampton Inn");
		Assert.assertEquals(rooms.get('W'), "Walkway");
		
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
		
		Assert.assertEquals('N', testBoard.getRoomCellAt(3,3).getInitial());
		Assert.assertEquals('M', testBoard.getRoomCellAt(24,12).getInitial());
		Assert.assertEquals('L', testBoard.getRoomCellAt(15,1).getInitial());
		Assert.assertEquals('T', testBoard.getRoomCellAt(0,8).getInitial());

	}
	
	
	
}
