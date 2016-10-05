package ass1.strategies;

import ass1.Cell;
import ass1.Robot;
import ass1.Solution;

abstract public class SearchStrategyBase implements SearchStrategyInterface {
	
	private Cell[][] grid;
	protected Robot robot;
	protected int dirtyCellCount;
	
	public SearchStrategyBase (Cell[][] grid, Robot robot) {
		this.grid = grid;
		this.robot = robot;
		dirtyCellCount = 0;
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {				
				if (grid[row][col].getState() == Cell.STATE_DIRTY) {
					dirtyCellCount++;
				} //if
			} //for
		} //for
	}
	
	protected int getDirtyCellCount() {
		return dirtyCellCount;
	}

	abstract public void search();

	abstract public Solution getSolution();
	
}
