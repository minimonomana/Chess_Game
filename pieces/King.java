package pieces;
import java.awt.image.BufferedImage;

import main.Board;

public class King extends Piece{
	public King(Board board, int row, int col, boolean iswhite) {
		super(board);
		
		this.row = row;
		this.col = col;
		this.xpos = col * board.tileSize;
		this.ypos = row * board.tileSize;
		
		this.iswhite = iswhite;
		
		this.sprite= sheet.getSubimage(0 * sheetscale, iswhite ? 0 : sheetscale, sheetscale, sheetscale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
		
	}

	@Override
	public boolean canMove(int newrow, int newcol) {
		int x1 = this.row;
		int y1 = this.col;
		int x2 = newrow;
		int y2 = newcol;
		
		if (x1 == x2 && y1 == y2) {
			return false;
		}
		else if((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) > 2) {
			return false;
		}
		else {
			if (board.get(x2, y2) == null ||(board.get(x2, y2) != null && board.get(x2, y2).iswhite != this.iswhite)) {
				if (!board.checkAttacked(x2, y2, this.iswhite)) {
					return true;
				}
				else if(canCastle(board, new Rook(board, 0, y2, this.iswhite)) || canCastle(board, new Rook(board, 7, y2, this.iswhite))) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
	}
	
	public boolean canCastle(Board board, Rook rook) {
		if (this.hasMoved || rook.hasMoved) {
			return false;
		}
		else if (board.checkAttacked(this.row, this.col, this.iswhite)) {
			return false;
		}
		else {
			if (rook.row == 0) {
				int y = rook.col;
				if (board.get(y, 1) != null || board.get(y, 1) != null){
					return false;
				}
				else {
					if(board.checkAttacked(y, 2, this.iswhite)) {
						return false;
					}
					else {
						return true;
					}
				}
			}
			if (rook.row == 7) {
				int y = rook.col;
				if (board.get(y, 4) != null || board.get(y, 5) != null || board.get(y, 6) != null){
					return false;
				}
				else {
					if(board.checkAttacked(y, 4, this.iswhite)) {
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

}