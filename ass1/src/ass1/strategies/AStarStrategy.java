package ass1.strategies;

import java.util.Comparator;
import java.util.PriorityQueue;

import ass1.Cell;
import ass1.Robot;
import ass1.RobotAction;
import ass1.Solution;
import ass1.StateNode;

public class AStarStrategy extends SearchStrategyBase {
    private Solution solution;
    
    
    public AStarStrategy(Cell[][] grid, Robot robot) {
    	super(grid, robot);
    	solution = new Solution();	
    }
    
	public void search() {
		solution.start();
		
		int gCost = 0;
		StateNode startState;
		
		PriorityQueue<StateNode> openList = new PriorityQueue<StateNode>(0, new Comparator<StateNode>() {
			public int compare(StateNode node1, StateNode node2) {
	            return node1.getCost() - node2.getCost();
	        }
		});
		
		startState = new StateNode();
		startState.setParentNode(null);
		startState.setDirtyCellCount(this.getDirtyCellCount());
		startState.setX(robot.getX());
		startState.setY(robot.getY());
		startState.setOrientation(robot.getOrientation());
		startState.addAction(new RobotAction(robot, RobotAction.ACTION_START, 0));
		
		openList.offer(startState);
		
		while (!openList.isEmpty()) {
			StateNode currentState = openList.poll();
			if (currentState.getDirtyCellCount() == 0) {
				// @TODO: set solution
				break;
			} //if	
			
			// TODO: create available state nodes
			
		} //while
		
		solution.end();
	}

	public Solution getSolution() {
		return solution;
	}

	private StateNode createStateNode(Cell targetCell, StateNode currentState) {
		StateNode newState = new StateNode();
		newState.setParentNode(currentState);
		
		if (currentState.getY() > targetCell.getY()) { // need to go North
			rotateToTargetOrientation(Robot.ORIENTATION_NORTH, currentState, newState);
		} else if (currentState.getX() > targetCell.getX()) { // need to go East
			rotateToTargetOrientation(Robot.ORIENTATION_EAST, currentState, newState);
		} else if (currentState.getY() < targetCell.getY()) { // need to go South
			rotateToTargetOrientation(Robot.ORIENTATION_SOUTH, currentState, newState);
		} else if (currentState.getX() < targetCell.getX()) { // need to go West
			rotateToTargetOrientation(Robot.ORIENTATION_WEST, currentState, newState);
		} else {
			// should not end up here
		}
		newState.addAction(new RobotAction(targetCell.getX(), targetCell.getY(), newState.getOrientation(), RobotAction.ACTION_MOVE, Robot.MOVE));
		
		if (targetCell.getState() == Cell.STATE_DIRTY) {
			newState.addAction(new RobotAction(targetCell.getX(), targetCell.getY(), newState.getOrientation(), RobotAction.ACTION_SUCK, Robot.SUCK));
			newState.setDirtyCellCount(currentState.getDirtyCellCount() - 1);
		} else {
			newState.setDirtyCellCount(currentState.getDirtyCellCount());
		} //if
		
		return newState;
	}
	
	private void rotateToTargetOrientation(int targetOrientation, StateNode currentState, StateNode newState) {
		
		// check if already in right orientation
		if (targetOrientation == currentState.getOrientation()) {
			newState.setOrientation(targetOrientation);
			return;
		} //if
		
		int x = currentState.getX();
		int y = currentState.getY();
		switch (currentState.getOrientation()) {
			case Robot.ORIENTATION_NORTH:
				if (targetOrientation == Robot.ORIENTATION_SOUTH) {
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_EAST, RobotAction.ACTION_LOOK_RIGHT, Robot.LR));
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_SOUTH, RobotAction.ACTION_LOOK_RIGHT, Robot.LR));
				} else if (targetOrientation == Robot.ORIENTATION_EAST) {
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_EAST, RobotAction.ACTION_LOOK_RIGHT, Robot.LR));
				} else { // West
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_EAST, RobotAction.ACTION_LOOK_LEFT, Robot.LL));
				} //if
				break;
			case Robot.ORIENTATION_EAST:
				if (targetOrientation == Robot.ORIENTATION_WEST) {
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_SOUTH, RobotAction.ACTION_LOOK_RIGHT, Robot.LR));
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_WEST, RobotAction.ACTION_LOOK_RIGHT, Robot.LR));
				} else if (targetOrientation == Robot.ORIENTATION_SOUTH) {
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_SOUTH, RobotAction.ACTION_LOOK_RIGHT, Robot.LR));
				} else { // North
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_NORTH, RobotAction.ACTION_LOOK_LEFT, Robot.LL));
				} //if
				break;
			case Robot.ORIENTATION_SOUTH:
				if (targetOrientation == Robot.ORIENTATION_NORTH) {
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_WEST, RobotAction.ACTION_LOOK_RIGHT, Robot.LR));
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_NORTH, RobotAction.ACTION_LOOK_RIGHT, Robot.LR));
				} else if (targetOrientation == Robot.ORIENTATION_WEST) {
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_WEST, RobotAction.ACTION_LOOK_RIGHT, Robot.LR));
				} else { // East
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_EAST, RobotAction.ACTION_LOOK_LEFT, Robot.LL));
				} //if
				break;
			case Robot.ORIENTATION_WEST:
				if (targetOrientation == Robot.ORIENTATION_EAST) {
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_NORTH, RobotAction.ACTION_LOOK_RIGHT, Robot.LR));
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_EAST, RobotAction.ACTION_LOOK_RIGHT, Robot.LR));
				} else if (targetOrientation == Robot.ORIENTATION_NORTH) {
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_NORTH, RobotAction.ACTION_LOOK_RIGHT, Robot.LR));
				} else { // South
					newState.addAction(new RobotAction(x, y, Robot.ORIENTATION_SOUTH, RobotAction.ACTION_LOOK_LEFT, Robot.LL));
				} //if
				break;
		}
		newState.setOrientation(targetOrientation);		
	}

	
}