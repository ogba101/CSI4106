package ass1;

import java.util.ArrayList;

public class StateNode {
	
	private StateNode parentNode;
	private int dirtyCellCount;
	private ArrayList<RobotAction> actions;
		
	public StateNode () {
		actions = new ArrayList<RobotAction>();
		dirtyCellCount = 0;
		parentNode = null;
	}
	
	public void setParentNode(StateNode node) {
		parentNode = node;
	}
	
	public StateNode getParentNode() {
		return parentNode;
	}
	
	public void addAction(RobotAction action) {
		actions.add(action);
	}
	
	public int getDirtyCellCount() {
		return dirtyCellCount;
	}
	
	public void setDirtyCellCount(int value) {
		dirtyCellCount = value;
	}
	
	public int getCost() {
		int cost = 0;
		for(RobotAction action: actions) {
			cost += action.getCost();
		}
		return cost;
	}
	
}
