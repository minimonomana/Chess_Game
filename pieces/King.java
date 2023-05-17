package pieces;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import main.Board;
import main.Move;

public class King extends Piece{
	public King(Board board, int row, int col, boolean iswhite) {
		super(board);
		
		this.row = row;
		this.col = col;
		this.xpos = col * board.tileSize;
		this.ypos = row * board.tileSize;
		
		this.iswhite = iswhite;
		
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
		else if((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) > 2) {
			return false;
		}
		else {
			if (board.get(x2, y2) == null ||(board.get(x2, y2) != null && board.get(x2, y2).iswhite != this.iswhite)) {
				if (board.checkAttacked(x2, y2, this.iswhite)) {
					return false;
				}
				else {
					return true;
				}
			}
			else {
				return false;
			}
		}
	}
	
	public boolean canCastle(int newrow, int newcol) {
		
		if (this.row == newrow) {
			Piece rook;
			if (newcol < this.col) {
				rook = board.get(newrow, 0);
				if (rook != null && !rook.hasMoved && !hasMoved) {
					if (board.checkAttacked(newrow, 3, this.iswhite) || board.checkAttacked(newrow, 2, this.iswhite)) {
						return false;
					}
					else {
						if (board.get(newrow, 1) != null || board.get(newrow, 2) != null || board.get(newrow, 3) != null){
							return false;
						}
						
					}
				}
			}
			else {
				rook = board.get(newrow, 7);
				if (rook != null && !rook.hasMoved && !hasMoved) {
					if (board.checkAttacked(newrow, 5, this.iswhite) || board.checkAttacked(newrow, 6, this.iswhite)) {
						return false;
					}
					else {
						if (board.get(newrow, 5) != null || board.get(newrow, 6) != null){
							return false;
						}
						
					}
				}
			}
		}
		
		return false;
		
	}

	public boolean isCheckMated() {
		boolean isAttacked = false;
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (board.get(r, c) != null) {
					Piece attacker = board.get(r, c);
					if (attacker.canAttack(row, col) && attacker.iswhite != iswhite) {
						isAttacked = true;
						break;
					}
				}
			}
		}
		if (!isAttacked) {
			return false;
		}
		ArrayList<Piece> curPieceList = board.pieceList;
		for (Piece defender : curPieceList) {
			if (defender.iswhite == iswhite) {
				for (int r1 = 0; r1 < 8; r1++) {
					for (int c1 = 0; c1 < 8; c1++) {
						if (defender.canMove(r1, c1)) {
							Piece backupStart = defender;
							Piece backupEnd = board.get(r1, c1);
							defender.row = r1;
							defender.col = c1;
							if (board.get(r1, c1) != null) {
								board.pieceList.remove(board.get(r1, c1));
							}
							isAttacked = false;
							for (Piece attacker : board.pieceList) {
								if (attacker.iswhite != iswhite) {
									if (attacker.canAttack(row, col)) {
										isAttacked = true;
										break;
									}
								}
							}
							defender.row = backupStart.row;
							defender.col = backupStart.col;
							if (backupEnd != null) {
								board.pieceList.add(backupEnd);
							}
							if (!isAttacked) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	public boolean isStaleMated() {
		boolean isAttacked = false;
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (board.get(r, c) != null) {
					Piece attacker = board.get(r, c);
					if (attacker.canAttack(row, col) && attacker.iswhite != iswhite) {
						isAttacked = true;
						break;
					}
				}
			}
		}
		if (isAttacked) {
			return false;
		}
		ArrayList<Piece> curPieceList = board.pieceList;
		for (Piece defender : curPieceList) {
			if (defender.iswhite == iswhite) {
				for (int r1 = 0; r1 < 8; r1++) {
					for (int c1 = 0; c1 < 8; c1++) {
						if (defender.canMove(r1, c1)) {
							Piece backupStart = defender;
							Piece backupEnd = board.get(r1, c1);
							defender.row = r1;
							defender.col = c1;
							if (board.get(r1, c1) != null) {
								board.pieceList.remove(board.get(r1, c1));
							}
							isAttacked = false;
							for (Piece attacker : board.pieceList) {
								if (attacker.iswhite != iswhite) {
									if (attacker.canAttack(row, col)) {
										isAttacked = true;
										break;
									}
								}
							}
							defender.row = backupStart.row;
							defender.col = backupStart.col;
							if (backupEnd != null) {
								board.pieceList.add(backupEnd);
							}
							if (!isAttacked) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	public boolean canAttack(int row, int col) {
		return canMove(row, col);
	}

}
