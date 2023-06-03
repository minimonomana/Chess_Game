package pieces;

import java.awt.image.BufferedImage;
import main.Board;

public class Knight extends Piece{
	public Knight(Board board, int row, int col, boolean iswhite) {
		super(board);
		
		this.row = row;
		this.col = col;
		this.xpos = col * board.tileSize;
		this.ypos = row * board.tileSize;
		
		this.iswhite = iswhite;
		
		this.name = "Knight";
		
		this.sprite= sheet.getSubimage(3 * sheetscale, iswhite ? 0 : sheetscale, sheetscale, sheetscale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);
		
	}

	public boolean canMove(int newrow, int newcol) {
		int x1 = this.row;
		int y1 = this.col;
		int x2 = newrow;
		int y2 = newcol;

        int diffX = Math.abs(x1 - x2);
        int diffY = Math.abs(y1 - y2);
        
        if (board.get(x2, y2) == null ||(board.get(x2, y2) != null && board.get(x2, y2).iswhite != this.iswhite)) {
        	return (diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1);
        }

		return false;
    }
	
	public boolean canAttack(int newrow, int newcol) {
		int x1 = this.row;
		int y1 = this.col;
		int x2 = newrow;
		int y2 = newcol;

        int diffX = Math.abs(x1 - x2);
        int diffY = Math.abs(y1 - y2);
        
        if (board.get(x2, y2) == null ||(board.get(x2, y2) != null)) {
        	return (diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1);
        }

		return false;
	}

}
