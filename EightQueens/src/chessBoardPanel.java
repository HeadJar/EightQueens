
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

public class chessBoardPanel {

	public final static int ROWS = 8;
	public final static int COLS = 8;
	public final static int SCREEN_SIZE = 800;

	private static final Color FOOTER_COLOR = Color.MAGENTA;
	private static final Color HEADER_COLOR = Color.CYAN;

	private JFrame window;
	private JPanel panelOne, panelTwo, panelThree;
	// chessSpacePanel[][] squares = new chessSpacePanel[ROWS][COLS];
	boolean[][] queenSquares = new boolean[ROWS][COLS];
	ArrayList<Queen> queenList = new ArrayList<Queen>();

	chessBoardPanel() {
		/*
		 * buildFrame(); panelOne = buildHeaderPanel(); panelTwo = buildGridPanels();
		 * panelThree = buildFooterPanel();
		 * 
		 * window.add(panelOne); window.add(panelTwo); window.add(panelThree);
		 * 
		 * // window.pack(); // Adjusts the frame size, so - collapses it ...
		 * window.setVisible(true);
		 */

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < ROWS; j++) {
				queenSquares[i][j] = false;

			}

		}
	}

	/*
	 * private void buildFrame() { window = new JFrame("Queen");
	 * window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); window.setSize(new
	 * Dimension(SCREEN_SIZE, SCREEN_SIZE)); // could set min, max, and preferred
	 * dimensions, I think window.setLayout(new BoxLayout(window.getContentPane(),
	 * BoxLayout.Y_AXIS));
	 * 
	 * }
	 * 
	 * private boolean isEven(int x) { return x % 2 == 0; }
	 * 
	 * private Color setPanelColor(int row, int col) { // Come up with an algorithm
	 * that will provide alternate colors if (isEven((row + col))) return
	 * Color.WHITE; else return Color.BLACK; }
	 * 
	 * private JPanel buildHeaderPanel() { JPanel p = new JPanel();
	 * p.setMinimumSize(new Dimension(SCREEN_SIZE, 10)); p.setMaximumSize(new
	 * Dimension(SCREEN_SIZE, 50)); p.setPreferredSize(new Dimension(SCREEN_SIZE,
	 * 40)); p.setBackground(HEADER_COLOR); p.add(new
	 * JLabel("A Checkerboard of Sorts")); return p; }
	 * 
	 * private JPanel buildFooterPanel() { JPanel p = new JPanel();
	 * p.setMinimumSize(new Dimension(SCREEN_SIZE, 10)); p.setMaximumSize(new
	 * Dimension(SCREEN_SIZE, 50)); p.setPreferredSize(new Dimension(SCREEN_SIZE,
	 * 40)); p.setBackground(FOOTER_COLOR); p.add(new
	 * JLabel("What will we put here")); return p; }
	 * 
	 * private JPanel buildGridPanels() { JPanel p = new JPanel(); p.setLayout(new
	 * GridLayout(8, 8)); Color bg; for (int r = 0; r < ROWS; r++) { for (int c = 0;
	 * c < COLS; c++) { bg = setPanelColor(r, c); chessSpacePanel m = new
	 * chessSpacePanel(bg, false); squares[r][c] = m; // keep a reference to the
	 * panel, so we can change it p.add(m); } }
	 * 
	 * return p; }
	 */
	public void updatePanel(Queen queen) {
		queenSquares[queen.getRow()][queen.getCol()] = true;
	}

	public boolean addQueen(ArrayList<Queen> queenList) {
		if (queenList.size() == 8) {
			return true;
		}
		Queen queen;
		for (int row = 0; row < queenSquares[0].length; row++) {
			/////////////////////////////////////////////////////////////////////////////

			queen = new Queen(row, queenList.size());
			System.out.println("checking: " + queen.getRow() + ", " + queen.getCol());

			if (isLegal(queen.getRow(), queen.getCol())) {
				queenList.add(queen);
				if (queenList.size() == 8) {
					return true;
				}

				setBoard(queenList);
				if (!addQueen(queenList)) {
					System.out.println(addQueen(queenList));
					Queen tempQueen = queenList.remove(queenList.size() - 1);
					System.out.println("The removed queen is" + tempQueen.getRow() + ", " + tempQueen.getCol());
					row = tempQueen.getRow() + 1;

				}

				toString(queenList);

			}
		}

		return false;

	}

	// Checks the first row then goes down
	// Then checks up left diagonal and up right diagonal since youre only looking
	// up

	public boolean isLegal(int row, int col) {

		for (int i = 0; i < queenSquares.length; i++) {
			if (queenSquares[i][col])
				return false;
		}
		for (int i = 0; i < queenSquares[0].length; i++) {
			if (queenSquares[row][i])
				return false;
		}

		if (!checkDiagonal(row, col)) {
			return false;
		}
		return true;
	}

	public boolean checkDiagonal(int row, int col) {
		int r = row;
		int c = col;

		while (r >= 0 && c < COLS) {

			if (queenSquares[r][c]) {
				return false;
			}
			r--;
			c++;
		}

		r = row;
		c = col;

		while (r >= 0 && c >= 0) {

			if (queenSquares[r][c]) {
				return false;
			}
			r--;
			c--;
		}
		r = row;
		c = col;

		while (r < ROWS && c >= 0) {

			if (queenSquares[r][c]) {
				return false;
			}
			r++;
			c--;
		}

		r = row;
		c = col;

		while (r < ROWS && c < COLS) {

			if (queenSquares[r][c]) {
				return false;
			}
			r++;
			c++;
		}

		return true;

	}

	public void setBoard(ArrayList<Queen> queenList) {

		for (int i = 0; i < queenSquares.length; i++) {
			for (int j = 0; j < queenSquares[0].length; j++) {
				queenSquares[i][j] = (false);
			}

		}

		for (int i = 0; i < queenList.size(); i++) {
			updatePanel(queenList.get(i));
		}
		/*
		 * try { if (queenList.size() == ROWS) { Thread.sleep(100); } else {
		 * Thread.sleep(10); } } catch (InterruptedException e) {
		 * 
		 * }
		 * 
		 * this.window.repaint();
		 */
	}

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
