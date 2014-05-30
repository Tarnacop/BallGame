// The Board class, used for the game board
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("serial")
public class Board extends JFrame{
	
	public static int boardWidth = 500; // Width
	public static int boardHeight = 400; // Height
	
	private Ball circle; // the ball object named circle
	private Bar movingBar; // the bar object named movingBar
	//Constructor
	public Board() {
		
		this.setTitle("Game Board");
		this.setSize(boardWidth, boardHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		// Define a new board
		DrawingBoard theBoard = new DrawingBoard();
		
		// Create a panel to put theBoard in
		JPanel theDrawingPanel = new JPanel();
		// Set BorderLayout and center theBoard in the panel
		theDrawingPanel.setLayout(new BorderLayout());
		theDrawingPanel.add(theBoard, BorderLayout.CENTER);
		
		// Create a KeyboardListener object and add it to the frame
		KeyboardListener listenTheBoard = new KeyboardListener();
		this.addKeyListener(listenTheBoard);
		this.add(theDrawingPanel, BorderLayout.CENTER); // Add the panel to the frame and center it
		
		// Used to create a thread pool with a timer
		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(2);
		exec.scheduleAtFixedRate(new RedrawBoard(this), 0L, 20L, TimeUnit.MILLISECONDS);
		this.requestFocus();
		this.setVisible(true);
		
	}// END OF CONSTRUCTOR
	
	// Class used for the thread pool. It repaints the Board every 20 milliseconds
	class RedrawBoard implements Runnable{
		
		Board tempBoard;
		public RedrawBoard(Board givenBoard) {
			tempBoard=givenBoard;
			
		}// END OF CONSTRUCTOR
		
		public void run() {
			
			tempBoard.repaint();
		}// END OF run METHOD
	}// END OF RedrawBoard CLASS
	
	// Class used to draw the shapes on the frame
	class DrawingBoard extends JComponent{
		
		public DrawingBoard() {
			double randomXPoint = (Math.random() * (boardWidth-40)+1); // Random starting points are given
			double randomYPoint = (Math.random() * (boardHeight-40)+1); // to the Ball constructor
			
			circle = new Ball(randomXPoint, randomYPoint);
			
			movingBar = new Bar(); // The movingBar is starting in the center
		}// END OF CONSTRUCTOR
		
		// Method used to paint the graphics
		public void paint(Graphics g) {
			
			Graphics2D graphicsSettings = (Graphics2D)g;
			graphicsSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			// Move the ball and add draw it with the new position
			circle.move();
			graphicsSettings.setColor(Color.BLACK);
			graphicsSettings.fill((Shape)circle.createdBall);
			
			// Draw the bar
			graphicsSettings.setColor(Color.BLACK);
			graphicsSettings.fill((Shape)movingBar.createdBar);
		}// END OF paint METHOD
	}// END OF DrawingBoard CLASS
	
	// Class which implements the KeyListener
	class KeyboardListener implements KeyListener {
		// Check if we press the right/left arrow or a/d buttons
		// and move the bar in that direction
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==e.VK_RIGHT||e.getKeyCode()==e.VK_D) {
				movingBar.moveBar(1);
			}
			else if(e.getKeyCode()==e.VK_LEFT||e.getKeyCode()==e.VK_A) {
				movingBar.moveBar(-1);
			}
		}// END OF keyPressed METHOD
		// These methods don't do anything. Just overriding.
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
		
	}// END OF KeyboardListener CLASS
	
}
