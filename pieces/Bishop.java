package pieces;
import java.awt.image.BufferedImage;

import main.Board;

public class Bishop extends Piece{
	public Bishop(Board board, int row, int col, boolean iswhite) {
		super(board);
		
		this.row = row;
		this.col = col;
		this.xpos = col * board.tileSize;
		this.ypos = row * board.tileSize;
		
		this.iswhite = iswhite;
		
		this.name = "Bishop";
		
		this.sprite= sheet.getSubimage(2 * sheetscale, iswhite ? 0 : sheetscale, sheetscale, sheetscale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
		
	}
	
	
	public boolean canMove(int newrow, int newcol) {
		int x1 = this.row;
		int y1 = this.col;
		int x2 = newrow;
		int y2 = newcol;
		
		if (x1 == x2 && y1 == y2) {
			return false;
		}
		else if ((x1 - y1) != (x2 - y2) && (x1 + y1) != (x2 + y2)) {
			return false;
		}
		else {
			if (board.get(x2, y2) == null ||(board.get(x2, y2) != null && board.get(x2, y2).iswhite != this.iswhite)) {
				if (y1 == y2) {
					if (Math.abs(x2 - x1) == 1) {
						return true;
					}
					if (x1 < x2) {
						for (int i = x1 + 1; i < x2; i++) {
							if (board.get(i, y1) != null) {
								return false;
							}else {
								continue;
							}
						}
						return true;
					}
					if (x1 > x2) {
						for (int i = x2 + 1; i < x1; i++) {
							if (board.get(i, y1) != null) {
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
								if (board.get(x1 + i, y1 + i) != null) {
									return false;
								}else {
									continue;
								}
							}
							return true;
						}else {
							for (int i = 1; i < x1 - x2; i++) {
								if (board.get(x2 + i, y2 + i) != null) {
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
								if (board.get(x1 + i, y1 - i) != null) {
									return false;
								}else {
									continue;
								}
							}
							return true;
						}else {
							for (int i = 1; i < x1 - x2; i++) {
								if (board.get(x2 + i, y2 - i) != null) {
									return false;
								}else {
									continue;
								}
							}
							return true;
						}
					}
				}
			}else {
				return false;
			}
		}
		return false;
	}
	
	public boolean canAttack(int newrow, int newcol) {
		int x1 = this.row;
		int y1 = this.col;
		int x2 = newrow;
		int y2 = newcol;
		
		if (x1 == x2 && y1 == y2) {
			return false;
		}
		else if ((x1 - y1) != (x2 - y2) && (x1 + y1) != (x2 + y2)) {
			return false;
		}
		else {
			if (board.get(x2, y2) == null ||(board.get(x2, y2) != null)) {
				if (y1 == y2) {
					if (Math.abs(x2 - x1) == 1) {
						return true;
					}
					if (x1 < x2) {
						for (int i = x1 + 1; i < x2; i++) {
							if (board.get(i, y1) != null) {
								return false;
							}else {
								continue;
							}
						}
						return true;
					}
					if (x1 > x2) {
						for (int i = x2 + 1; i < x1; i++) {
							if (board.get(i, y1) != null) {
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
								if (board.get(x1 + i, y1 + i) != null) {
									return false;
								}else {
									continue;
								}
							}
							return true;
						}else {
							for (int i = 1; i < x1 - x2; i++) {
								if (board.get(x2 + i, y2 + i) != null) {
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
								if (board.get(x1 + i, y1 - i) != null) {
									return false;
								}else {
									continue;
								}
							}
							return true;
						}else {
							for (int i = 1; i < x1 - x2; i++) {
								if (board.get(x2 + i, y2 - i) != null) {
									return false;
								}else {
									continue;
								}
							}
							return true;
						}
					}
				}
			}else {
				return false;
			}
		}
		return false;
	}

}