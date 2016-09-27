public class Cell 
{
private int state; //1- clean, 2- dirty, 3- obstacle, (4 visited)?
private int x; // x coordinate
private int y; // y coordinate
int max=3; int min=0;
public Cell(int x,int y,int state)
{
	if (x>max|| x<min) throw new IllegalArgumentException("invalid x-coordinate");
	if (y>max|| y<min) throw new IllegalArgumentException("invalid y-coordinate");
	if (state>3|| state<1) throw new IllegalArgumentException("invalid State");
this.x=x;
this.y=y;
this.state=state;
}
public Cell(int x,int y)
{
	if (x>max|| x<min) throw new IllegalArgumentException("invalid x-coordinate");
	if (y>max|| y<min) throw new IllegalArgumentException("invalid y-coordinate");
this.x=x;
this.y=y;
}

 int stateInfo()
{
	return state;
}
void setState(int value)
{
	this.state=value;
}
}
