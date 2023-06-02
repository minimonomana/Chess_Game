package main;
import pieces.Piece;

public class Move {
	public int oldrow, oldcol;
	public int newrow;
	public int newcol;
	
	public Piece piece;
	Piece capture;
	
	
	Move(Board board, Piece piece, int newrow, int newcol){
		this.oldrow = piece.row;
		this.oldcol = piece.col;
		this.newcol = newcol;
		this.newrow = newrow;
		
		this.piece = piece;
		this.capture = board.get(newrow, newcol);
	}

}
