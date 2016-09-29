
package ass1.strategies;
import ass1.Cell;
import ass1.Robot;
import ass1.Solution;

public class BFSStrategy  extends SearchStrategyBase {
    private Solution solution;//
    
    public BFSStrategy(Cell[][] grid, Robot robot) {
    	super(grid, robot);
    }
    
	public void search() {
		//
	}

	public Solution getSolution() {
		return solution;//
	}
	
}