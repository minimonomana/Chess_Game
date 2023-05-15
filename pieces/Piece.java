package pieces;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Board;

public class Piece {
	
	public int row, col;
	public int xpos, ypos;
	
	public boolean iswhite;
	public boolean hasMoved = false;
	
	BufferedImage sheet;
	{
		try {
			sheet = ImageIO.read(new File("C:\\Users\\nguye\\java_eclipse_workspace\\ChessFromYoutube\\src\\res\\piece.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	Image sprite;
	
	public int sheetscale = sheet.getWidth() / 6;
	
	Board board;
	
	public Piece(Board board) {
		this.board = board;
	}
	
	public boolean canMove(int r, int c) {
		return true;
	}
	
	public void paint(Graphics2D g2d) {
		g2d.drawImage(sprite, xpos, ypos, null);
		
	}

	public boolean canCastle(Board board, Rook rook) {
		return false;
	}

}
