package ass1;

import ass1.strategies.*;
import ass1.Robot;
import java.lang.System;

public class RobotApp {
	
	public static final int STRATEGY_DFS   = 1;
	public static final int STRATEGY_BFS   = 2;
	public static final int STRATEGY_ASTAR = 3;
	
	private SearchStrategyInterface strategy = null;
	private Robot robot = null;

	/**
	 * Point of entry to our app
	 * will build an instance and run the search strategy
	 * @param args
	 */
	public static void main(String[] args) {
		
		RobotApp app = new RobotApp();
		int gridSize = 4;
		int[][] dirtyCells = {
				{0,1,0,0},
				{1,0,0,0},
				{0,0,1,0},
				{0,1,0,0}				
		};
		
		int[][] obstacleCells = {
				{0,0,0,0},
				{0,1,1,0},
				{0,1,0,0},
				{0,0,0,0}				
		};
		
		int[] startPosition = {3,2};
		
		int orientation = Robot.ORIENTATION_WEST;
		int chosenStrategy;
		
		//chosenStrategy = STRATEGY_DFS;
		//chosenStrategy = STRATEGY_BFS;
		chosenStrategy = STRATEGY_ASTAR;
		
		try {
			app.generateGrid(gridSize, dirtyCells, obstacleCells, startPosition, orientation);
			app.search(chosenStrategy);
			app.printSolution();
		} catch (Exception e) {
			System.err.println("Caugth exception");
			System.err.println(e);	
		}
	}
	
	/**
	 * App Constructor
	 */
	public RobotApp() {
		// nothing here yet
	}
	
	/**
	 * Takes the selected algorithm as input 
	 * setup the app to run the proper algorithm 
	 * 
	 * @param chosenStrategy
	 * @throws Exception
	 */
	public void search(int chosenStrategy) throws Exception {
		switch(chosenStrategy) {
			case STRATEGY_DFS:
				strategy = new DFSStrategy();
				break;
			case STRATEGY_BFS:
				throw new Exception("BFS not implemented");
			case STRATEGY_ASTAR:
				strategy = new AStarStrategy();
				break;
			default: 
				throw new Exception("Unknown search strategy");
		} //switch
		
		//perform the search using our 
		// newly created strategy
		strategy.search(robot);
	}
	
	public void printSolution() {
		
		System.out.println("Printing solution...");
		
		if (strategy == null) {
			return;
		} //if
		
		strategy.getSolution().print();
		
	}

	/**
	 * Generates the grid and sets the robot 
	 * to point to the starting position
	 */
	public void generateGrid(int gridSize, int[][] dirt, int[][] obstacles, int[] startPos, int orientation) {
		
		Cell[][] grid = new Cell[gridSize][gridSize];
		for (int row = 0; row < gridSize; row++) {
			for (int col = 0; col < gridSize; col++) {
				grid[row][col] = new Cell(row,col);
				if (dirt[row][col] == 1) {
					grid[row][col].setState(Cell.STATE_DIRTY);
				} else if (obstacles[row][col] == 1) {
					grid[row][col].setState(Cell.STATE_OBSTACLE);
				} else {
					grid[row][col].setState(Cell.STATE_CLEAN);
				} //if
			}
		} //for row
		
		// update child cells
		for (int row = 0; row < gridSize; row++) {
			for (int col = 0; col < gridSize; col++) {
				// check cell above
				if (row > 0 && grid[(row-1)][col].getState() != Cell.STATE_OBSTACLE) {
					grid[(row-1)][col].addChild(grid[row][col]);
				} //if
				
				// check cell bellow
				if (row < (gridSize - 1) && grid[(row+1)][col].getState() != Cell.STATE_OBSTACLE) {
					grid[(row+1)][col].addChild(grid[row][col]);
				} //if
				
				// check cell left
				if (col > 0 && grid[row][(col-1)].getState() != Cell.STATE_OBSTACLE) {
					grid[row][(col-1)].addChild(grid[row][col]);
				} //if
				
				// check cell right
				if (col < (gridSize - 1) && grid[row][(col+1)].getState() != Cell.STATE_OBSTACLE) {
					grid[row][(col+1)].addChild(grid[row][col]);
				} //if
				
			} // for col
		} //for row
		
		robot = new Robot(grid, orientation, startPos[0], startPos[1]);
	}

}
