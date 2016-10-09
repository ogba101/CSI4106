package ass1;

import java.util.ArrayList;

public class StateNode {
	
	private StateNode parentNode;
	private int dirtyCellCount;
	private ArrayList<RobotAction> actions;
	private int x;
	private int y;
	private int orientation;
	private int cost;
		
	public StateNode () {
		actions = new ArrayList<RobotAction>();
		parentNode = null;
		cost = -1;
	}
	
	public void setParentNode(StateNode node) {
		parentNode = node;
	}
	
	public StateNode getParentNode() {
		return parentNode;
	}
	
	public void addAction(RobotAction action) {
		cost = -1; // force cost to be recalculated
		actions.add(action);
	}
	
	public ArrayList<RobotAction> getActions() {
		return actions;
	}
	
	public int getDirtyCellCount() {
		return dirtyCellCount;
	}
	
	public void setDirtyCellCount(int value) {
		dirtyCellCount = value;
	}
	
	public int getCost() {
		if (cost < 0) {
			cost = 0;
			for(RobotAction action: actions) {
				cost += action.getCost();
			} //for
		} //if
		return cost;
	}
	
	public int getTotalCost() {
		if (parentNode == null) {
			return this.getCost();
		} //if
		return parentNode.getCost() + this.getCost();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	
}
