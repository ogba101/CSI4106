
package ass1.strategies;
import java.util.ArrayList;
import java.util.LinkedList;
import ass1.Cell;
import ass1.Robot;
import ass1.RobotAction;
import ass1.Solution;
import ass1.StateNode;

public class BFSStrategy  extends SearchStrategyBase {
	final int dummyv=-100; // Dummy value
    private Solution solution;
	  LinkedList<StateNode> path = new LinkedList<StateNode>();// list to store final path
	  Cell startCell= null;
    public BFSStrategy(Cell[][] grid, Robot robot) {
    	super(grid, robot);
    }
    
	public void search() {
    	solution = new Solution();
        startCell=grid[robot.getX()][robot.getY()]; //initialize start cell
        StateNode dummy= new StateNode();
        startCell.statenode.setParentNode(dummy);
        solution.start();
      while(dirtyCellCount!=0){
    LinkedList<Cell> closedList = new LinkedList<Cell>();
    LinkedList<Cell> openList = new LinkedList<Cell>(); // open list
    openList.add(startCell); //insert initial Cell
    while (!openList.isEmpty()) 
    {
        Cell node = (Cell)openList.removeFirst(); 
        if(node.getState()==Cell.STATE_DIRTY)
        {
          dirtyCellCount--;
          node.statenode.setDirtyCellCount(dummyv); 
          node.setState(Cell.STATE_CLEAN);
         constructPath(node.statenode);
         break;
        }
                else {
            closedList.add(node);
            // add neighbors to the open list
            ArrayList<Cell> list= new ArrayList<Cell>();
            		list=genSuccessors(node);
            for(int j=0;j<list.size();j++)
            {
            	Cell neighborNode=list.get(j);	  
              if (!closedList.contains(neighborNode) && !openList.contains(neighborNode)) 
                 {
                  neighborNode.statenode.setParentNode(node.statenode);
                 // neighborNode.statenode.setDirtyCellCount(node.statenode.getDirtyCellCount());
                   openList.add(neighborNode);
                 }
            }
            
          }

    }
	}
/*-----------------------------------------------------
 * if Previous state exists
      compare previous state and current state to determine direction
      if dir==0(just move) */
      StateNode next= new StateNode();
	  StateNode state=new StateNode();
	  solution.setDepth(path.size()+1); //depth of tree
	  for( int index=0;index<path.size();index++) // for every node in the path
	  {
		state=path.get(index); 
		robot.setX(state.getX());
		robot.setY(state.getY());
		  if (index!=path.size()-1) 
		  {
		 next=path.get(index+1);
		  }
		  if(index==0) // The start Node
		  {
			  solution.addAction(new RobotAction(robot, RobotAction.ACTION_START)); 
		  }
		  else {
			  
		  solution.addAction(new RobotAction(robot,RobotAction.ACTION_MOVE));
		
			  if(state.getDirtyCellCount()==dummyv)
		       {
			     solution.addAction(new RobotAction(robot,RobotAction.ACTION_SUCK));  
		       }
			  
			
		  }
		  
		  if(index<path.size()-1)
		  {
			rotate(state,next);  
		  }
		  
//	  robot.setX(cell.getX());
//	  robot.setY(cell.getY()); 
//		  
//	solution.addAction(new RobotAction(robot, RobotAction.ACTION_START, 0));
	  } 
	  solution.end();
}
	public int getDir(StateNode now, StateNode next) //Returns desired orientation of robot
	{
		if (next.getY()>now.getY()){return Robot.ORIENTATION_EAST;} // North
		if (next.getY()<now.getY()){return Robot.ORIENTATION_WEST;} // South
		if(next.getX()<now.getX()) {return Robot.ORIENTATION_NORTH;} //East
		if(next.getX()>now.getX()) {return Robot.ORIENTATION_SOUTH;}  //West
		return 0;
	}
public void rotate(StateNode now, StateNode next)
{
	
	int desiredOr= getDir(now,next);
	
	int currOr= robot.getOrientation();
	int change=desiredOr-currOr;
	robot.setOrientation(desiredOr); //
	if (change==1  || change == -3)
	{solution.addAction(new RobotAction(robot,RobotAction.ACTION_LOOK_RIGHT));  } // Move Right
	if (change==-1 || change ==3)
	{solution.addAction(new RobotAction(robot,RobotAction.ACTION_LOOK_LEFT));  } // Move left
	if(change==2   || change ==-2)
	{ 
		int value= currOr-1;
	   if(value!=-1)
	   {robot.setOrientation(value);}else{robot.setOrientation(3);}
		{solution.addAction(new RobotAction(robot,RobotAction.ACTION_LOOK_LEFT));  } // Move left
		{solution.addAction(new RobotAction(robot,RobotAction.ACTION_LOOK_LEFT));  } // Move left
		robot.setOrientation(desiredOr); //
	} // Turn 180
	
}
	public Solution getSolution() {
		
	 return solution;
	}
	protected void constructPath(StateNode node) {
		  LinkedList<StateNode> tempPath = new LinkedList<StateNode>();

		  while (node.getParentNode() != null) 
		  {
		    tempPath.addFirst(node);
		    node=node.getParentNode();
		  }
		  path.addAll(tempPath); // append new path
		  StateNode sNode=path.get(path.size() - 1);
		 startCell=new Cell(sNode.getX(),sNode.getY()); // Make end of path new startNode
		  } 
	
	public ArrayList<Cell> genSuccessors(Cell cell)
	{
		ArrayList<Cell> list = new ArrayList<Cell>();
		int x=cell.getX();
		int y=cell.getY();
		
		if (x<3 && grid[x+1][y].getState()!=Cell.STATE_OBSTACLE)
		{
			list.add(grid[x+1][y]);
		}
		if (x>0 && grid[x-1][y].getState()!=Cell.STATE_OBSTACLE)
		{
			list.add(grid[x-1][y]);
		}
		if (y>0 && grid[x][y-1].getState()!=Cell.STATE_OBSTACLE)
		{
			list.add(grid[x][y-1]);
		}
		if (y<3 && grid[x][y+1].getState()!=Cell.STATE_OBSTACLE)
		{
			list.add(grid[x][y+1]);
		}	
		return list;
	}

	
}