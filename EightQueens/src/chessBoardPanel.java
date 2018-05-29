
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Jarhead
 *
 *This gets the solutions of the Eight Queens Project
 *
 *
 */

public class chessBoardPanel {

	/*
	 *This sets the size of the board for the panels
	 */
	public final static int ROWS = 8;
	public final static int COLS = 8;
	public final static int SCREEN_SIZE = 800;
	
	//This is the number of solutions
	public static int solutionsNumber = 0;
	/**
	 * These are the colors of the background
	 */
	private static final Color FOOTER_COLOR = Color.BLUE;
	private static final Color HEADER_COLOR = Color.RED;

	private JFrame window;
	private JPanel panelOne, panelTwo, panelThree;
	
	/**
	 * This is a specific space of the board
	 */
	chessSpacePanel[][] squares = new chessSpacePanel[ROWS][COLS];
	// boolean[][] queenSquares = new boolean[ROWS][COLS];
	
	/**
	 * QueenList is a single set of queens that need to be found
	 */
	
	/**
	 * Solutions is the number of solutions to be found
	 */
	ArrayList<Queen> queenList = new ArrayList<Queen>();
	ArrayList<ArrayList<Queen>> solutions = new ArrayList<ArrayList<Queen>>();
/**
 * Constructor:
 * build each frame then set the visibility of the window to true
 */
	chessBoardPanel() {
		
		  buildFrame(); panelOne = buildHeaderPanel(); panelTwo = buildGridPanels();
		  panelThree = buildFooterPanel();
		  
		  window.add(panelOne); window.add(panelTwo); window.add(panelThree);
		  
		  // window.pack(); // Adjusts the frame size, so - collapses it ...
		  window.setVisible(true);
		 

	}
/**
 * This creates the frame of the board
 */
	private void buildFrame() {
		window = new JFrame("Queen");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));
		// could set min, max, and preferred dimensions, I think
		window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));

	}

	
	/**
	 * This is the helper method to distinguish colors 
	 * @param x
	 * This is the number of the square sent in
	 * @return
	 * it returns either 1 or 0
	 */
	private boolean isEven(int x) {
		return x % 2 == 0;
	}
/**
 * This alternates the colors of each square
 * @param row the row of the boardspace being used
 * @param col the col of the boardspace being used
 * @return returns the color of the board space
 */
	private Color setPanelColor(int row, int col) {
		// Come up with an algorithm that will provide alternate colors
		if (isEven((row + col)))
			return Color.WHITE;
		else
			return Color.BLACK;
	}
/**
 * This creates the header of the board
 * @return This returns the created header
 */
	private JPanel buildHeaderPanel() {
		JPanel p = new JPanel();
		p.setMinimumSize(new Dimension(SCREEN_SIZE, 10));
		p.setMaximumSize(new Dimension(SCREEN_SIZE, 50));
		p.setPreferredSize(new Dimension(SCREEN_SIZE, 40));
		p.setBackground(HEADER_COLOR);
		p.add(new JLabel("A checkerboard"));
		return p;
	}
/**
 * This is the footer panel
 * @return This returns the footer panel
 */
	private JPanel buildFooterPanel() {
		JPanel p = new JPanel();
		p.setMinimumSize(new Dimension(SCREEN_SIZE, 10));
		p.setMaximumSize(new Dimension(SCREEN_SIZE, 50));
		p.setPreferredSize(new Dimension(SCREEN_SIZE, 40));
		p.setBackground(FOOTER_COLOR);
		p.add(new JLabel("The number of found solutions i: " + solutionsNumber));
		return p;
	}

	private JPanel buildGridPanels() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(8, 8));
		Color bg;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				bg = setPanelColor(r, c);
				chessSpacePanel m = new chessSpacePanel(bg, false);
				squares[r][c] = m;
				// keep a reference to the panel, so we can change it
				p.add(m);
			}
		}

		return p;
	}
/**
 * This will update the board according to the queenList
 * @param queen This is the queen sent in to be set to true
 */
	public void updatePanel(Queen queen) {
		squares[queen.getRow()][queen.getCol()].setQueen(!squares[queen.getRow()][queen.getRow()].getQueen());
	}
/**
 * This method will take in an arrayList of queens and try to test whether or not a certain queen can be legal in solving the eight queens problem
 * @param queenList The queenList will hold a maximum of eight queens
 * @return It will return whether or not the queenList is at max capacity
 */
	public boolean addQueen(ArrayList<Queen> queenList) {
		if (queenList.size() == ROWS) {
			return true;
		} else {

			Queen queen;
			for (int row = 0; row < squares[0].length; row++) {
				/////////////////////////////////////////////////////////////////////////////

				if (queenList.size() == 8) {
					return true;
				}
				queen = new Queen(row, queenList.size());

				if (isLegal(queen.getRow(), queen.getCol())) {
					queenList.add(queen);

					setBoard(queenList);
					for (int j = 0; j < queenList.size() - 1; j++) {
						System.out.println(queenList.get(j));
					}
					System.out.println("The queen added is: " + queen.toString());
					if (!addQueen(queenList)) {
						if (queenList.size() == 8) {
							solutions.add((ArrayList<Queen>) queenList.clone());
							queen = queenList.remove(queenList.size() - 1);
						System.out.println("The queen is " + queen.toString());
						row = queen.getRow();
							System.out.println("Found Solution, removing and continuing: " + queen.getRow() + ", "	+ queen.getCol());
							solutionsNumber++;
							return true;
						}

						System.out.println("The size is first" + queenList.size());
						queen = queenList.remove(queenList.size() - 1);

						System.out.println();
						squares[queen.getRow()][queen.getCol()].setQueen(false);
						System.out.println("The size is now" + queenList.size());
						System.out.println("The removed queen is: " + queen.toString());
						row = queen.getRow();

					}
					
				} else {
					if (queenList.size() == 8) {
						solutions.add((ArrayList<Queen>) queenList.clone());
						queen = queenList.remove(queenList.size() - 1);
						System.out.println("The queen is " + queen.toString());
						row = queen.getRow();
						System.out.println(
								"Found Solution, removing and continuing: " + queen.getRow() + ", " + queen.getCol());
					}
				}

			}

			return false;
		}
	}
/**
 * This will check if a queen can be placed legally or not, first according to row and column then by diagonals
 * 
 * @param row This is the row of the queen sent in
 * @param col This is the col of the queen sent in
 * @return It will return whether or not the queen can be placed legally
 */
	// Checks the first row then goes down
	// Then checks up left diagonal and up right diagonal since youre only looking
	// up

	public boolean isLegal(int row, int col) {

		for (int i = 0; i < squares.length; i++) {
			if (squares[i][col].getQueen())
				return false;
		}
		for (int i = 0; i < squares[0].length; i++) {
			if (squares[row][i].getQueen())
				return false;
		}

		if (!checkDiagonal(row, col)) {
			return false;
		}
		return true;
	}
/**
 * This checks the diagonals of the queen sent in 
 * @param row This is the row of the queen sent in
 * @param col This is the column of the queen sent in
 * @return This returns whether or not it can be placed according to the digaonals
 * 
 */
	public boolean checkDiagonal(int row, int col) {
		int r = row;
		int c = col;

		while (r >= 0 && c < COLS) {

			if (squares[r][c].getQueen()) {
				return false;
			}
			r--;
			c++;
		}

		r = row;
		c = col;

		while (r >= 0 && c >= 0) {

			if (squares[r][c].getQueen()) {
				return false;
			}
			r--;
			c--;
		}
		r = row;
		c = col;

		while (r < ROWS && c >= 0) {

			if (squares[r][c].getQueen()) {
				return false;
			}
			r++;
			c--;
		}

		r = row;
		c = col;

		while (r < ROWS && c < COLS) {

			if (squares[r][c].getQueen()) {
				return false;
			}
			r++;
			c++;
		}

		return true;

	}
/**
 * This will first set the entire board to false then it will update the board according to the queenList
 * If a solution is found it will stop else it will cycle through each combination
 * @param queenList
 */
	public void setBoard(ArrayList<Queen> queenList) {

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[0].length; j++) {
				squares[i][j].setQueen(false);
			}

		}

		for (int i = 0; i < queenList.size(); i++) {
			updatePanel(queenList.get(i));
		}

		try {
			if (queenList.size() == ROWS) {
				Thread.sleep(100);
			} else {
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {

		}

		this.window.repaint();

	}
/**
 * This will print out in the console the solution
 * @param queenList
 */
	public void toString(ArrayList<Queen> queenList) {
		for (int i = 0; i < queenList.size(); i++) {
			Queen queen = queenList.get(i);
			System.out.println("The queen is at" + queen.getRow() + ", " + queen.getCol());
			System.out.println();
		}
	}

	public static void main(String[] args) {
		chessBoardPanel pg = new chessBoardPanel();
		ArrayList<Queen> list = new ArrayList<Queen>();
		pg.addQueen(list);

	}

}
