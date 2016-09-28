package ass1.strategies;

import ass1.Robot;
import ass1.Solution;

public interface SearchStrategyInterface {

	public void search(Robot robot);
	
	public Solution getSolution();
}
