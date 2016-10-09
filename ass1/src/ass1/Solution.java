package ass1;

import java.util.ArrayList;
import java.util.Date;

public class Solution {
	private long startTime = 0;
	private long endTime = 0;
	
	private ArrayList<RobotAction> actions;
	
	public Solution() {
		actions = new ArrayList<RobotAction>();
	}
	
	public void addAction(RobotAction action) {
		actions.add(action);
	}
	
	public void start() {
		startTime = new Date().getTime();
	}
	
	public void end() {
		endTime = new Date().getTime();
	}
	
	public void print() {
		int totalCost = 0;
		int depth = 0;
		
		for(RobotAction action: actions) {
			System.out.printf("pos(%d,%d), %s, %s\n", 
					(action.getY()+1),
					(action.getX()+1),
					getOrientationLabel(action.getOrientation()),
					getActionLabel(action.getAction())
				);
			totalCost += action.getCost();
		} //for
		System.out.println();
		
		System.out.printf("total cost: %d\n", totalCost);
		System.out.printf("Depth: %d\n", depth);
		System.out.printf("Time: %dms\n", (endTime - startTime));
	}
	
	private String getOrientationLabel(int orientation) {
		switch (orientation) {
			case Robot.ORIENTATION_NORTH: return "North";
			case Robot.ORIENTATION_EAST: return "East";
			case Robot.ORIENTATION_SOUTH: return "South";
			case Robot.ORIENTATION_WEST: return "West";
			default: return "";
		} //switch
	}
	
	private String getActionLabel(int action) {
		switch (action) {
			case RobotAction.ACTION_START: return "start";
			case RobotAction.ACTION_MOVE: return "move";
			case RobotAction.ACTION_LOOK_LEFT: return "left";
			case RobotAction.ACTION_LOOK_RIGHT: return "right";
			case RobotAction.ACTION_SUCK: return "suck";
			default: return "";
		} //switch
	}
}
