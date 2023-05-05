package ChessPieces;
import Board.*;

public abstract class Piece {
	private boolean white = false;
	private boolean iskilled = false;
	public boolean hasMoved = false;
	
	public Piece(boolean white) {
		this.setcolor(white);
	}
	
	public boolean isWhite() {
		return this.white;
	}
	public void setcolor(boolean white) {
		this.white = white;
	}
	
	public boolean iskilled() {
		return this.iskilled;
	}
	public void setkilled(boolean iskilled) {
		this.iskilled = iskilled;
	}
	
	public abstract boolean canMove(Chessboard board, Spot start, Spot end);
	
	public abstract void move(Chessboard board, Spot start, Spot end);
	
	public abstract boolean attack(Chessboard board, Spot start, Spot end);

}
