import java.util.ArrayList;

public class Queen {
int row;
int col;

public Queen(int row, int col) {
	this.row= row;
	this.col = col;
}

public int getRow() {
return row;
}

public int getCol() {
	return col;
}

public void setRow(int row) {
	this.row = row;
}

public void setCol(int col) {
	this.col = col;
}

public String toString() {
	
	return ("The queen has row " + this.row + " and col " + this.col);
}





}
