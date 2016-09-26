package ass1.strategies;

import ass1.RobotAction;
import ass1.Robot;

public interface SearchStrategyInterface {

	public void search(Robot robot);
	
	public RobotAction[] getSolution();
}
