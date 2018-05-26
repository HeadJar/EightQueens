import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class chessSpacePanel extends JPanel {

	private final static int FONTSIZE = 20;
	private Color back;
	private boolean isQueen;

	public chessSpacePanel(Color back, boolean isQueen) {
		this.back = back;
		this.isQueen = isQueen;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setFont(new Font("TimesRoman", Font.PLAIN, FONTSIZE));
		this.setBackground(back);
		g.setColor(Color.BLUE);

		int x = (this.getWidth() / 2) - FONTSIZE / 4;
		int y = (this.getHeight() / 2) + FONTSIZE / 4;

		if (getQueen()) 
			g.drawString("Q", x, y);
		
	}

	public void setColor(Color c) {
		back = c;
		repaint(); // forces paintComponent to execute
	}

	public void setQueen(boolean isQueen) {
		this.isQueen = isQueen;
		repaint();
	}

	public boolean getQueen() {
		return isQueen;
	}

}
