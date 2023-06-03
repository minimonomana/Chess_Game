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
		this.hasFakeMoved = true;
		
		this.name = "King";
		
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
		else if (canCastle(newrow, newcol)) {
			return true;
		}
		else if((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) > 2) {
			return false;
		}
		else {
			if (board.get(x2, y2) == null ||(board.get(x2, y2) != null && board.get(x2, y2).iswhite != this.iswhite)) {
				if (!board.checkAttacked(x2, y2, this.iswhite)) {
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
	
	public boolean canCastle(int newrow, int newcol) {
		if (hasMoved){
			return false;
		}
		
		if (this.row == newrow) {
			Piece rook;
			if (newcol == 2) {
				rook = board.get(newrow, 0);
				if (rook != null && rook.name.equals("Rook") && !rook.hasMoved && !hasMoved) {
					if (board.checkAttacked(newrow, 4, this.iswhite) || board.checkAttacked(newrow, 3, this.iswhite) || board.checkAttacked(newrow, 2, this.iswhite)) {
						return false;
					}
					else {
						if (board.get(newrow, 1) != null || board.get(newrow, 2) != null || board.get(newrow, 3) != null){
							return false;
						}
						else {
							return true;
						}
						
					}
				}
				
			}
			else if (newcol == 6){
				rook = board.get(newrow, 7);
				if (rook != null && rook.name.equals("Rook") && !rook.hasMoved && !hasMoved) {
					if (board.checkAttacked(newrow, 4, this.iswhite) || board.checkAttacked(newrow, 5, this.iswhite) || board.checkAttacked(newrow, 6, this.iswhite)) {
						return false;
					}
					else {
						if (board.get(newrow, 5) != null || board.get(newrow, 6) != null){
							return false;
						}
						else {
							return true;
						}
						
					}
				}
				
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
		else if((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) > 2) {
			return false;
		}
		else {
			if (board.get(x2, y2) == null ||(board.get(x2, y2) != null)) {
				if (!board.checkAttacked(x2, y2, this.iswhite)) {
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
	
	public boolean isAttacked() {
		return board.checkAttacked(row, col, iswhite);
	}

}