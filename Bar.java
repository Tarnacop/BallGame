// Class to create the bar
import java.awt.geom.Rectangle2D;
import java.awt.event.*;

import javax.swing.event.*;

public class Bar {
	
	public Rectangle2D createdBar;
	
	private int width = Board.boardWidth; // Width of the game board
	private int height = Board.boardHeight; // Height of the game board
	
	private int xDirection=4; // The direction (the bar will move 4 pixels - to right if xDirection = -4
							  // to left if xDirection = 4;
	
	private double uLeftXPos=150D; // The bar will be placed in the middle of the bottom width
	private double uLeftYPos=325D;
	
	// Create our bar (150 px width, 25 px height)
	public Bar() {
		createdBar=new Rectangle2D.Double(uLeftXPos, uLeftYPos, 150, 25);
	}// END OF CONSTRUCTOR
	
	// Function to move the bar (if Direction = -1 move to left, if Direction = 1 move to right)
	public void moveBar(int Direction) {
		
		if((createdBar.getMinX()-4)>0) {
			if(Direction<0&&xDirection>0) {
				xDirection *= (-1);
				uLeftXPos += xDirection;
			}
			if(Direction<0&&xDirection<0) uLeftXPos += xDirection;
		}
		if((createdBar.getMaxX()+7)<width) {
			if(Direction>0&&xDirection<0) {
				xDirection *= (-1);
				uLeftXPos += xDirection;
			}
			if(Direction>0&&xDirection>0) {
				uLeftXPos += xDirection;
			}
		}
			createdBar.setFrame(uLeftXPos, uLeftYPos, 150, 25); // Set the new coordinates for the bar
	}// END OF moveBar METHOD
	
	
}// END OF Bar CLASS
