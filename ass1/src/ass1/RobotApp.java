package ass1;

import ass1.strategies.*;
import ass1.Robot;
import ass1.RobotAction;
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
		
		int[] startPosition = {2,3};
		
		int orientation = Robot.ORIENTATION_WEST;
		
		try {
			app.generateGrid(gridSize, dirtyCells, obstacleCells, startPosition, orientation);
			app.search(STRATEGY_DFS);
			app.printSolution();
		} catch (Exception e) {
			System.err.println("Caugth exception");
			System.err.println(e.getMessage());
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
				throw new Exception("A* not implemented");
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
		
		RobotAction[] solution = strategy.getSolution();
		
		if (solution == null) {
			return;
		} //if
		
		for(int i = 0; i < solution.length; i++) {
			
		} //for
	}

	/**
	 * Generates the grid and sets the robot 
	 * to point to the starting position
	 */
	public void generateGrid(int gridSize, int[][] dirt, int[][] obstacles, int[] startPos, int orientation) {
		
		Cell[][] grid = new Cell[gridSize][gridSize];
		//@TODO: grid with all the cells
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				grid[i][j] = new Cell(i,j);
				// @TODO set cell properties
			}
		} //for
		
		// update child cells
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				// @TODO: set valid child cells
			}
		} //for
		
		
		robot = new Robot(grid, orientation, startPos[0], startPos[1]);
	}
}
