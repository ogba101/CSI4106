
package ass1.strategies;
import java.util.ArrayList;
import java.util.LinkedList;
import ass1.Cell;
import ass1.Robot;
import ass1.RobotAction;
import ass1.Solution;
import ass1.StateNode;

public class BFSStrategy  extends SearchStrategyBase {
    private Solution solution;
	  LinkedList<StateNode> path = new LinkedList<StateNode>();
	  Cell startCell= null;
    public BFSStrategy(Cell[][] grid, Robot robot) {
    	super(grid, robot);
    }
    
	public void search() {
    	solution = new Solution();
  	  solution.addAction(new RobotAction(robot, RobotAction.ACTION_START, 0));
        startCell=grid[robot.getX()][robot.getY()];
        
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
                  neighborNode.statenode.setDirtyCellCount(node.statenode.getDirtyCellCount());
                   openList.add(neighborNode);
                 }
            }
            
          }

    }
	}
solution.start();

	  for(StateNode cell: path) // for every cell in the path
	  
	  {
	  robot.setX(cell.getX());
	  robot.setY(cell.getY());
		  
	solution.addAction(new RobotAction(robot, RobotAction.ACTION_START, 0));
	  } 
	  solution.end();
}

	public Solution getSolution() {
		
	 return solution;
	}
	protected void constructPath(StateNode node) {
		  LinkedList<StateNode> tempPath = new LinkedList<StateNode>();

		  while (node.getParentNode() != null) 
		  {
		    tempPath.addFirst(node);
			//path.add(node);
		    node=node.getParentNode();
		  }
		  path.addAll(tempPath);
		  StateNode sNode=path.get(path.size() - 1);
		 startCell=new Cell(sNode.getX(),sNode.getY());
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
		
		
//		
		return list;
	}

	
}