package ass1.strategies;

import ass1.Cell;
import ass1.Robot;
import ass1.RobotAction;
import ass1.Solution;

public class AStarStrategy extends SearchStrategyBase {
    private Solution solution;
    
    public AStarStrategy(Cell[][] grid, Robot robot) {
    	super(grid, robot);
    	solution = new Solution();	
    }
    
	public void search() {
		solution.start();
		solution.addAction(new RobotAction(robot, RobotAction.ACTION_START, 0));
		solution.end();
	}

	public Solution getSolution() {
		return solution;
	}


	
}