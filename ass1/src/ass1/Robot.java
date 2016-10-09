package ass1;


public class Robot {
	
	public static final int MOVE=50;
	public static final int LR=20;
	public static final int LL=20;
	public static final int SUCK=10;
	
	public static final int ORIENTATION_NORTH = 0;
	public static final int ORIENTATION_EAST  = 1;
	public static final int ORIENTATION_SOUTH = 2;
	public static final int ORIENTATION_WEST  = 3;
	
    int cost=0;
    private int x;
	int y;
	int orientation;
	Cell grid[][];
	
	public Robot(Cell[][]grid,int orientation,int x,int y)
	{
		this.grid = grid;
		this.orientation = orientation;
		this.setX(x);
		this.y = y;
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
	
	void move()
	{
		updateCord();
		cost+=MOVE;
	}
	void left()
	{
		cost+=LR;
	}
	void right()
	{
		cost+=LR;
	}
	void suck()
	{
		cost+=SUCK;
	}
	public void setOrientation(int or)
	{
		this.orientation=or;
	}
	private void updateCord() // update the coordinates
	{
	
	if(orientation==ORIENTATION_NORTH)
	{
		y--;
	if(y<0){throw new IndexOutOfBoundsException("Index " + y + " is out of bounds!");}

	};
	if(orientation==ORIENTATION_SOUTH)
	{
		y++;
		if(y>3){throw new IndexOutOfBoundsException("Index " + y + " is out of bounds!");}

	};
	if(orientation==ORIENTATION_EAST)
	{
		setX(getX() + 1);
		if(getX()>3){throw new IndexOutOfBoundsException("Index " + getX() + " is out of bounds!");}
	};
	if(orientation==ORIENTATION_WEST)
	{
		setX(getX() - 1);
		if(getX()<0){throw new IndexOutOfBoundsException("Index " + getX() + " is out of bounds!");}

	};
	}
//	int orientToNum(char val) // map orientation to numbers
//	{
//		return 0;
//	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y=y;
		
	}
}