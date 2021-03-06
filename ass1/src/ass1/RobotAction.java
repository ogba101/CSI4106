package ass1;

public class RobotAction {
	
	public static final int ACTION_START = 1;
	public static final int ACTION_MOVE = 2;
	public static final int ACTION_LOOK_RIGHT = 3;
	public static final int ACTION_LOOK_LEFT = 4;
	public static final int ACTION_SUCK = 5;
	
	private int x;
	private int y;
	private int action;
	private int cost;
	private int orientation;
	
	public RobotAction(Robot robot, int action, int cost) {
		this.x = robot.getX();
		this.y = robot.getY();
		this.orientation = robot.getOrientation();
		this.action = action;
		this.cost = cost;
	}
	
	public RobotAction(int x, int y, int orientation, int action, int cost) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
		this.action = action;
		this.cost = cost;
	}
	
	public RobotAction(Cell robot, int action, int cost) {
		this.x = robot.getX();
		this.y = robot.getY();
		//this.orientation = robot.getOrientation();
		this.action = action;
		this.cost = cost;}
	
	public RobotAction(Robot robot, int action) {
			this.x = robot.getX();
			this.y = robot.getY();
			this.orientation = robot.getOrientation();
			this.action = action;
			switch (action)
			{
			case RobotAction.ACTION_LOOK_LEFT:
			this.cost = 20;
			break;
			case RobotAction.ACTION_LOOK_RIGHT:
			this.cost = 20;
			break;
		   case RobotAction.ACTION_MOVE:
				this.cost=50;
				break;
			case RobotAction.ACTION_SUCK:
				this.cost=10;
				break;
				
			case RobotAction.ACTION_START:
				this.cost=0;
				break;
			default :
	            System.out.println("Invalid Action");


			}
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getOrientation() {
		return orientation;
	}
	
	public int getAction() {
		return action;
	}
	
	public int getCost() {
		return cost;
	}
}
