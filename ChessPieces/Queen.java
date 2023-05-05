package ChessPieces;
import Board.*;
//import java.util.*;

public class Queen extends Piece{
	public Queen(boolean white) {
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
		else if (x1 != x2 || y1 != y2 || (x1 - y1) != (x2 - y2) || (x1 + y1) != (x2 + y2)) {
			return false;
		}
		else {
			if (end.getpiece() != null) {
				if (end.getpiece().isWhite() == start.getpiece().isWhite()) {
					return false;
				}
			}else {
				if (x1 == x2) {
					if (Math.abs(y2 - y1) == 1) {
						return true;
					}
					if (y1 < y2) {
						for (int i = y1 + 1; i < y2; i++) {
							if (board.square(x1, i).getpiece() != null) {
								return false;
							}else {
								continue;
							}
						}
						return true;
					}
					if (y1 > y2) {
						for (int i = y2 + 1; i < y1; i++) {
							if (board.square(x1, i).getpiece() != null) {
								return false;
							}else {
								continue;
							}
						}
						return true;
					}
				}
				else if (y1 == y2) {
					if (Math.abs(x2 - x1) == 1) {
						return true;
					}
					if (x1 < x2) {
						for (int i = x1 + 1; i < x2; i++) {
							if (board.square(i, y1).getpiece() != null) {
								return false;
							}else {
								continue;
							}
						}
						return true;
					}
					if (x1 > x2) {
						for (int i = x2 + 1; i < x1; i++) {
							if (board.square(i, y1).getpiece() != null) {
								return false;
							}else {
								continue;
							}
						}
						return true;
					}
				}
				else if ((x1 - y1) == (x2 - y2)) {
					if (Math.abs(x1 - x2) == 1) {
						return true;
					}
					else {
						if (x1 < x2) {
							for (int i = 1; i < x2 - x1; i++) {
								if (board.square(x1 + i, y1 + i).getpiece() != null) {
									return false;
								}else {
									continue;
								}
							}
							return true;
						}else {
							for (int i = 1; i < x1 - x2; i++) {
								if (board.square(x2 + i, y2 + i).getpiece() != null) {
									return false;
								}else {
									continue;
								}
							}
							return true;
						}
					}
				}
				else {
					if (Math.abs(x1 - x2) == 1) {
						return true;
					}
					else {
						if (x1 < x2) {
							for (int i = 1; i < x2 - x1; i++) {
								if (board.square(x1 + i, y1 - i).getpiece() != null) {
									return false;
								}else {
									continue;
								}
							}
							return true;
						}else {
							for (int i = 1; i < x1 - x2; i++) {
								if (board.square(x2 + i, y2 - i).getpiece() != null) {
									return false;
								}else {
									continue;
								}
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public void move(Chessboard board, Spot start, Spot end) {
		if (canMove(board, start, end)) {
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
	
	public boolean attack(Chessboard board, Spot start, Spot end) {
		if (end.getpiece() == null) {
			return false;
		}
		else {
			return canMove(board, start, end);
		}
	}

}
