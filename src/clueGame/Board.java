package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.awt.*;

//import experiment.BoardCell;

public class Board extends JPanel{
	public final static int CELL_DIMENSION = 30;
	public static int MAX_CELLS = 50;
	private String layoutFileName;
	private BoardCell[][] layout = new BoardCell[MAX_CELLS][MAX_CELLS];
	public Map<Character,String> rooms;
	private int numRows;
	private int numCols;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private ArrayList<Player> players;
	private Map<BoardCell, LinkedList<BoardCell>> adjMtx;
	
	Board(Map<Character, String> rooms, String layoutFileName, ArrayList<Player> players){
		this.rooms = rooms;
		this.layoutFileName = layoutFileName;
		this.players = players;
	}

	public void loadBoardConfig() throws BadConfigFormatException{
		fillLayout(layoutFileName);
		setSize(CELL_DIMENSION * numCols,CELL_DIMENSION * numRows);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i=0;i<numRows;i++){
			for (int j=0;j<numCols;j++){
				layout[i][j].draw(g, this);
			}
		}
		for(int i = 0; i < players.size(); i++){
			players.get(i).draw(g);
		}
	}

	//Create board by filling layout array
	public void fillLayout(String layoutFileName) throws BadConfigFormatException{
		//System.out.println(layoutFileName);
		FileReader fileIn = null;
		try{
			fileIn = new FileReader(layoutFileName);

		}catch (FileNotFoundException e){
			System.out.println("File not found");
		}
		Scanner scan = new Scanner(fileIn);
		String scannedData = "";
		int row = 0;
		String[]cells;
		int column = 0;

		//For each line in the file
		while(scan.hasNextLine()){
			scannedData = scan.nextLine();
			cells = scannedData.split(",");

			column = 0;
			numCols = 0;
			for(String str: cells){

				if(str.equals("W")){
					layout[row][column] = new Walkway(row,column);
				}
				else{
					//System.out.println(str.charAt(0));
					if((!rooms.containsKey(str.charAt(0)) && str.charAt(0) != 'X')){
						//System.out.println("Here");
						throw new BadConfigFormatException("Bad Room");
					}
					layout[row][column] = new RoomCell(row, column, str);
				}
				/*if(numCols != 0 && numCols != column ){
					throw new BadConfigFormatException("Uneven rows");
				}*/
				//numCols = column + 1 ;
				column++;
			}
			if(numCols != 0 && numCols != column ){
				throw new BadConfigFormatException("Uneven rows");
			}
			numCols = column;

			row++;
			numRows++;
			//count the row before going back to get the next row
		}
		scan.close();
		try{
			fileIn.close();
		}catch (IOException e){
			System.out.println("IOException");
		}
	}
	
	// Calculate matrix of all cells adjacent to each other cell, stored in adjMtx.
	public void calcAdjacencies(){
		adjMtx = new HashMap<BoardCell,LinkedList<BoardCell>>();
		for(int row = 0; row < numRows; row++){
			for(int col = 0; col < numCols; col++){
				LinkedList <BoardCell> adjList = new LinkedList<BoardCell>();
				if(getCellAt(row,col).isWalkway() || getCellAt(row,col).isDoorway()){
					calcWalkwayAdj(row, col, adjList);
				} 
				adjMtx.put(layout[row][col], adjList);
			}
		}

	}

	// Get all adjacent cells to a specified walkway cell.
	public void calcWalkwayAdj(int row, int col, LinkedList<BoardCell> adjList){
		if (!(row - 1 < 0)){
			row = row - 1;
			if(getCellAt(row,col).isWalkway()){
				adjList.add(getCellAt(row,col));
				//System.out.println("1");
			}
			else if(getCellAt(row,col).isDoorway()){
				RoomCell roomy = (RoomCell) getCellAt(row,col);
				if(roomy.getDoorDirection() == RoomCell.DoorDirection.DOWN){
					adjList.add(getCellAt(row,col));
				}
			}
		}
		if(!(row + 1 >= numRows)){
			row = row + 1;
			if(getCellAt(row,col).isWalkway()){
				adjList.add(getCellAt(row,col));
				//System.out.println("2");
			}else if(getCellAt(row,col).isDoorway()){
				RoomCell roomy = (RoomCell) getCellAt(row,col);
				if(roomy.getDoorDirection() == RoomCell.DoorDirection.UP){
					adjList.add(getCellAt(row,col));
				}
			}
		}
		if (!(col-1 < 0)){
			col = col - 1;
			if(getCellAt(row,col).isWalkway()){
				adjList.add(getCellAt(row,col));
				//System.out.println("3");
			}else if(getCellAt(row,col).isDoorway()){
				RoomCell roomy = (RoomCell) getCellAt(row,col);
				if(roomy.getDoorDirection() == RoomCell.DoorDirection.RIGHT){
					adjList.add(getCellAt(row,col));
				}
			}
		}
		if(!(col+1 >= numCols)){
			col = col + 1;
			if(getCellAt(row,col).isWalkway()){
				adjList.add(getCellAt(row,col));
				//System.out.println("4");
			}else if(getCellAt(row,col).isDoorway()){
				RoomCell roomy = (RoomCell) getCellAt(row,col);
				if(roomy.getDoorDirection() == RoomCell.DoorDirection.LEFT){
					adjList.add(getCellAt(row,col));
				}
			}
		}
	}
	
	// Calculate potential cells player can move to based on number of steps rolled.
	public void calcTargets(int row, int col, int steps){
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		visited.add(getCellAt(row,col));
		findTargets(row, col, steps);
	}

	// Calculate potential cells player can move to based on number of steps rolled, with currentPlayer indicated for highlinting
	public void calcTargets(int row, int col, int steps, Player currentPlayer){
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		visited.add(getCellAt(row,col));
		findTargets(row, col, steps);
		if(currentPlayer.isHuman()) {
			for(BoardCell b : targets) {
				b.setTarget(true);
			}
			repaint();
		}
	}

	// Recursive call for calcTargets
	public void findTargets(int row, int col, int steps){
		for(BoardCell cell: getAdjList(row,col)){

			if(steps == 1 || cell.isDoorway()){
				if(!visited.contains(cell)){
					targets.add(cell);
				}
			}
			else{
				if(!visited.contains(cell)){
					visited.add(cell);
					findTargets(cell.getRow(), cell.getCol(), steps - 1);
					visited.remove(cell);
				}
			}
		}
	}

	public BoardCell getCellAt(int row, int col){
		return layout[row][col];
	}
	
	public HashSet<BoardCell> getTargets(){
		return (HashSet<BoardCell>) targets;
	}

	public LinkedList<BoardCell> getAdjList(int row, int col){
		return adjMtx.get(getCellAt(row,col));
	}

	public int getNumRows(){
		return numRows;
	}

	public int getNumColumns(){
		return numCols;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public RoomCell getRoomCellAt(int rows, int cols){
		BoardCell tempcell = layout[rows][cols];
		if(tempcell.isRoom()){
			return (RoomCell) tempcell;
		}
		return null;
	}


}
