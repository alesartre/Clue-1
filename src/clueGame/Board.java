package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//import experiment.BoardCell;

public class Board {
	
	Board(Map<Character, String> rooms, String layoutFileName ){
		this.rooms = rooms;
		this.layoutFileName = layoutFileName;
	}
	
	private String layoutFileName;
	public static int MAX_CELLS = 50;
	private BoardCell[][] layout = new BoardCell[MAX_CELLS][MAX_CELLS];
	public Map<Character,String> rooms;
	private int numRows;
	private int numCols;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private Map<BoardCell, LinkedList<BoardCell>> adjMtx;
	
	public void loadBoardConfig() throws BadConfigFormatException{
		
		fillLayout(layoutFileName);
		
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
		String temp = "";
		int row = 0;
		String[]cells;
		int column = 0;
		
		//For each line in the file
		while(scan.hasNextLine()){
			temp = scan.nextLine();
			cells = temp.split(",");
			
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
	
	
	
	public BoardCell getCellAt(int row, int col){
		return layout[row][col];
	}
	public void calcAdjacencies(){
		
		adjMtx = new HashMap<BoardCell,LinkedList<BoardCell>>();
		for(int row = 0; row < numRows; row++){
			for(int col = 0; col < numCols; col++){
				LinkedList <BoardCell> adjList = new LinkedList<BoardCell>();
				if(getCellAt(row,col).isWalkway() || getCellAt(row,col).isDoorway()){
					getWalkwayAdj(row, col, adjList);
				} 
				adjMtx.put(layout[row][col], adjList);
			}
		}
	
	}
	
	public void getWalkwayAdj(int row, int col, LinkedList<BoardCell> adjList){
		if (!(row - 1 < 0)){
			if(getCellAt(row-1,col).isWalkway()){
				adjList.add(getCellAt(row-1,col));
				//System.out.println("1");
			}
			else if(getCellAt(row-1,col).isDoorway()){
				RoomCell roomy = (RoomCell) getCellAt(row-1,col);
				if(roomy.getDoorDirection() == RoomCell.DoorDirection.DOWN){
					adjList.add(getCellAt(row-1,col));
				}
			}
		}
		if(!(row + 1 >= numRows)){
			if(getCellAt(row+1,col).isWalkway()){
				adjList.add(getCellAt(row+1,col));
				//System.out.println("2");
			}else if(getCellAt(row+1,col).isDoorway()){
				RoomCell roomy = (RoomCell) getCellAt(row+1,col);
				if(roomy.getDoorDirection() == RoomCell.DoorDirection.UP){
					adjList.add(getCellAt(row+1,col));
				}
			}
		}
		if (!(col-1 < 0)){
			if(getCellAt(row,col-1).isWalkway()){
				adjList.add(getCellAt(row,col-1));
				//System.out.println("3");
			}else if(getCellAt(row,col-1).isDoorway()){
				RoomCell roomy = (RoomCell) getCellAt(row,col-1);
				if(roomy.getDoorDirection() == RoomCell.DoorDirection.RIGHT){
					adjList.add(getCellAt(row,col-1));
				}
			}
		}
		if(!(col+1 >= numCols)){
			if(getCellAt(row,col+1).isWalkway()){
				adjList.add(getCellAt(row,col+1));
				//System.out.println("4");
			}else if(getCellAt(row,col+1).isDoorway()){
				RoomCell roomy = (RoomCell) getCellAt(row,col+1);
				if(roomy.getDoorDirection() == RoomCell.DoorDirection.LEFT){
					adjList.add(getCellAt(row,col+1));
				}
			}
		}
	}
	
	
	public void calcTargets(int row, int col, int steps){
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		visited.add(getCellAt(row,col));
		findTargets(row, col, steps);
	}
	
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
