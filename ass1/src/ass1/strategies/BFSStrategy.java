
package ass1.strategies;
import ass1.Robot;
import ass1.RobotAction;
import ass1.Solution;

public class BFSStrategy implements SearchStrategyInterface {
    private Solution solution;//
    
    public BFSStrategy() {
    	solution = new Solution();	//
    }
    
	public void search(Robot robot) {
		//
	}

	public Solution getSolution() {
		return solution;//
	}
	
}