package ChessPieces;

import Board.*;

public class King extends Piece{
	public King(boolean white) {
		super(white);
	}
	
	public boolean canMove(Chessboard board, Spot start, Spot end) {
		int x1 = start.getx();
		int y1 = start.gety();
		int x2 = end.getx();
		int y2 = end.gety();
		
		if (x1 == x2 && y1 == y2) {
			return false;
		}
		else if((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) > 2) {
			return false;
		}
		else {
			if (end.getpiece().isWhite() == start.getpiece().isWhite()) {
				return false;
			}
			else {
				if (end.checkAttacked(board, end, start.getpiece().isWhite())) {
					return false;
				}
				else if (!canCastle(board, start, end)) {
					return false;
				}
				else {
					return true;
				}
			}
		}
	}
	
	public void move(Chessboard board, Spot start, Spot end) {
		if (canMove(board, start, end)) {
			start.getpiece().hasMoved = true;
			if (end.getpiece() == null) {
				end.setpiece(start.getpiece());
				start.setpiece(null);
			}
			else {
				end.getpiece().setkilled(true);
				end.setpiece(start.getpiece());
				start.setpiece(null);
			}
		}
		else {
			
		}
	}
	
	public boolean canCastle(Chessboard board, Spot king, Spot rook) {
		if (king.getpiece().hasMoved || rook.getpiece().hasMoved) {
			return false;
		}
		else if (king.checkAttacked(board, king, king.getpiece().isWhite())) {
			return false;
		}
		else {
			if (rook.getx() == 0) {
				int y = rook.gety();
				if (board.square(1, y).getpiece() != null || board.square(2, y).getpiece() != null){
					return false;
				}
				else {
					if(board.square(2, y).checkAttacked(board, board.square(2, y), king.getpiece().isWhite())) {
						return false;
					}
					else {
						return true;
					}
				}
			}
			if (rook.getx() == 7) {
				int y = rook.gety();
				if (board.square(4, y).getpiece() != null || board.square(5, y).getpiece() != null || board.square(6, y).getpiece() != null){
					return false;
				}
				else {
					if(board.square(4, y).checkAttacked(board, board.square(4, y), king.getpiece().isWhite())) {
						return false;
					}
					else {
						return true;
					}
				}
			}
			else {
				return false;
			}
		}
	}
	
	public void castle(Chessboard board, Spot king, Spot rook) {
		if (!canCastle(board, king, rook)) {
			
		}
		else {
			if (rook.getx() == 0) {
				int y = king.gety();
				board.square(0, y).setpiece(null);
				board.square(1, y).setpiece(king.getpiece());
				board.square(2, y).setpiece(rook.getpiece());
				board.square(3, y).setpiece(null);
			}
			if (rook.gety() == 7) {
				int y = king.gety();
				board.square(7, y).setpiece(null);
				board.square(6, y).setpiece(null);
				board.square(5, y).setpiece(king.getpiece());
				board.square(4, y).setpiece(rook.getpiece());
				board.square(3, y).setpiece(null);
			}
			king.getpiece().hasMoved = true;
			rook.getpiece().hasMoved = true;
		}
	}
	
	public boolean attack(Chessboard board, Spot start, Spot end) {
		if (end.getpiece() == null) {
			return false;
		}
		else {
			return canMove(board, start, end);
		}
	}
	
}
