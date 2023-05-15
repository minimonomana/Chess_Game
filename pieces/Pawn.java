package pieces;
import java.awt.image.BufferedImage;

import main.Board;

public class Pawn extends Piece{
	boolean enPassantVulnerable = false;
	public Pawn(Board board, int row, int col, boolean iswhite) {
		super(board);
		
		this.row = row;
		this.col = col;
		this.xpos = col * board.tileSize;
		this.ypos = row * board.tileSize;
		
		this.iswhite = iswhite;
		
		this.sprite= sheet.getSubimage(5 * sheetscale, iswhite ? 0 : sheetscale, sheetscale, sheetscale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
		
	}

	@Override
	public boolean canMove(int newrow, int newcol) {
	    int x1 = this.row;
	    int y1 = this.col;
	    int x2 = newrow;
	    int y2 = newcol;
	    
	    if (x1 == x2 && y1 == y2) {
	        return false;
	    } else if (x1 < 0 || x1 > 7 || y1 < 0 || y1 > 7 || x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7) {
	        return false;
	    } else if (board.get(x2, y2) != null && board.get(x2, y2).iswhite == this.iswhite) {
	        return false;
	    }
	    
	    int dy = y2 - y1;
	    int dx = x2 - x1;
	    
	    if (this.iswhite) {
	        if (dx == -1 && Math.abs(dy) == 1 && board.get(x2, y2) != null) {
	            // Capture a piece diagonally
	            return true;
	        } else if (dx == -1 && dy == 0 && board.get(x2, y2) == null) {
	            // Move one step forward
	            return true;
	        } else if (dx == -2 && dy == 0 && x1 == 6 && board.get(x2, y2) == null && board.get(x1 - 1, y1) == null) {
	            // Move two steps forward in the first move
	        	enPassantVulnerable = true;
	            return true;
	        } else if (dx == -1 && Math.abs(dy) == 1 && board.get(x2 + 1, y2) instanceof Pawn) {
	            // En passant
	            Pawn pawn = (Pawn) board.get(x2 + 1, y2);
	            if (pawn.enPassantVulnerable) {
	                return true;
	            }
	        }
	    } else {
	        if (dx == 1 && Math.abs(dy) == 1 && board.get(x2, y2) != null) {
	            // Capture a piece diagonally
	            return true;
	        } else if (dx == 1 && dy == 0 && board.get(x2, y2) == null) {
	            // Move one step forward
	            return true;
	        } else if (dx == 2 && dy == 0 && x1 == 1 && board.get(x2, y2) == null && board.get(x1 + 1, y1) == null) {
	            // Move two steps forward in the first move
	        	enPassantVulnerable = true;
	            return true;
	        } else if (dx == 1 && Math.abs(dy) == 1 && board.get(x2 - 1, y2) instanceof Pawn) {
	            // En passant
	            Pawn pawn = (Pawn) board.get(x2 - 1, y2);
	            if (pawn.enPassantVulnerable) {
	                return true;
	            }
	        }
	    }
	    
	    return false;
	}


}