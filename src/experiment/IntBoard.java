package experiment;
import java.util.*;
import java.util.Map.Entry;

public class IntBoard {
	
	private Set<BoardCell> boardSet;
	private BoardCell bc;
	private int rows;
	private int columns;
	private Set<BoardCell> visited;
	private HashSet<BoardCell> targets;
	private Map<BoardCell, LinkedList<BoardCell>> adjMtx;

	IntBoard(){
		super();
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		boardSet = new HashSet<BoardCell>();
		adjMtx = new HashMap<BoardCell, LinkedList<BoardCell>>();
		rows = 3;
		columns = 3;
		for (int i = rows; i >= 0; i--){
			for (int j = columns; j >= 0; j--){
				bc = new BoardCell(i,j);
				adjMtx.put(bc, new LinkedList<BoardCell>()  );
				boardSet.add(bc);
				
			}
		}
		for(BoardCell cell:boardSet){
			calcAdjacencies(cell);
		}
		
	}
	
	public BoardCell getCell(int row, int col){
		
		for (BoardCell cell : boardSet){
			if (cell.getRow()==row && cell.getCol()==col){
				
				return cell;
			}
		}
		return new BoardCell(-1,-1);
	}
	
	public void calcAdjacencies(BoardCell cell){
		//System.out.print(cell.getRow() + "   " + cell.getCol());
		LinkedList <BoardCell> adjList = new LinkedList<BoardCell>();
		if (!(cell.getRow()-1 < 0)){
			adjList.add(getCell(cell.getRow()-1,cell.getCol()) );
		//	System.out.print(" 1 ");
		}
		if(!(cell.getRow()+1 > rows)){
			adjList.add(getCell(cell.getRow()+1,cell.getCol()));
		//	System.out.print(" 2 ");
		}
		if (!(cell.getCol()-1 < 0)){
			adjList.add(getCell(cell.getRow(),cell.getCol()-1) );
		//	System.out.print(" 3 ");
		}
		if(!(cell.getCol()+1 > columns)){
			adjList.add(getCell(cell.getRow(),cell.getCol()+1));
		//	System.out.print(" 4 ");
		} 
		adjMtx.put(cell, adjList);
	//	System.out.println();
	}
	
	public void calcTargets(BoardCell cell, int steps){
		visited.add(cell);
		findTargets(cell, steps);
	}
	
	public void findTargets(BoardCell current, int steps){
		for(BoardCell cell: getAdjList(current)){
			
			if(steps == 1){
				if(!visited.contains(cell)){
					targets.add(cell);
				}
				
			}
			else{
				if(!visited.contains(cell)){
					visited.add(cell);
					findTargets(cell, steps - 1);
					visited.remove(cell);
				}
				
			}
			
			
		}
		
	}
	
	public HashSet<BoardCell> getTargets(){
		return targets;
	}
	
	 public LinkedList<BoardCell> getAdjList(BoardCell cell){
		
		 
		 return adjMtx.get(cell);
	}
	 

	/* public static void main(String[] arg0){
		 IntBoard board = new IntBoard();
		 BoardCell cell = board.getCell(1,1);
		 System.out.println(cell.getRow() + "     " + cell.getCol());
		 
		 board.calcTargets(cell, 4);
		 HashSet<BoardCell> testList = board.getTargets();
		 for(BoardCell i:testList){
			 System.out.println(i.getRow() + "     " + i.getCol());
		 }
	 } */
	 
}

