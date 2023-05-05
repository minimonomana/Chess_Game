package Board;
import ChessPieces.*;

public class Spot {
	private Piece piece;
	private int x;
	private int y;
	
	Spot(Piece piece, int x, int y){
		this.setpiece(piece);
		this.setx(x);
		this.sety(y);
	}
	
	public Piece getpiece() {
		return this.piece;
	}
	public void setpiece(Piece piece) {
		this.piece = piece;
	}
	
	public int getx() {
		return this.x;
	}
	public void setx(int x) {
		this.x = x;
	}
	
	public int gety() {
		return this.y;
	}
	public void sety(int y) {
		this.y = y;
	}
	
	public boolean checkAttacked(Chessboard board, Spot end, boolean white) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.square(i, j).getpiece().canMove(board, board.square(i, j), end)) {
					if (board.square(i, j).getpiece().isWhite() != white) {
						return true;
					}else {
						return false;
					}
				}else {
					continue;
				}
			}
		}
		return false;
	}

}
