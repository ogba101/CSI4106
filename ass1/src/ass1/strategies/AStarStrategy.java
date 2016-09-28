package ass1.strategies;

import ass1.Robot;
import ass1.RobotAction;
import ass1.Solution;

public class AStarStrategy implements SearchStrategyInterface {
    private Solution solution;
    
    public AStarStrategy() {
    	solution = new Solution();	
    }
    
	public void search(Robot robot) {
		solution.start();
		solution.addAction(new RobotAction(robot, RobotAction.ACTION_START, 0));
		solution.end();
	}

	public Solution getSolution() {
		return solution;
	}
	
}