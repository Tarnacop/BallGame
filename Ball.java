// The class which creates the Ball
import java.awt.geom.Ellipse2D;

public class Ball {
	
	private int width = Board.boardWidth; // The width of the board
	private int height = Board.boardHeight; // The height of the board
	
	public Ellipse2D createdBall; // The Ball created using the constructore
	
	private int xDirection = 3; // x & y directions are used to create the movement 
	private int yDirection = 3; // and to help at the collisions with the walls
	
	private double uXPoint = 0D; // The starting x of the ball
	private double uYPoint = 0D; // The starting y of the ball
	
	// Constructor 
	public Ball(double randomXPoint, double randomYPoint){
		
		uXPoint = randomXPoint;
		uYPoint = randomYPoint;
		createdBall = new Ellipse2D.Double(uXPoint, uYPoint, 15, 15); // Create the ball using the random points received
	}// END OF CONSTRUCTOR
	
	// Move function - It will create the movement of the ball
	public void move() {
		
		// Collision with the walls (x with the widths and y with the heights)
		if((createdBall.getMinX()<0) ||((createdBall.getMaxX()+10)>width)) xDirection = xDirection * (-1); // When it collides
		if((createdBall.getMinY()<0 || ((createdBall.getMaxY()+25)>height))) yDirection = yDirection * (-1); // change direction
		uXPoint += xDirection;
		uYPoint += yDirection;
		createdBall.setFrame(uXPoint, uYPoint, 25 ,25); // Set the new upper left points 
	}// END OF move METHOD
	
}// END OF Ball CLASS
