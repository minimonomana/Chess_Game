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
	public boolean hasFakeMoved = false;
	public String name;
	
	BufferedImage sheet;
	public BufferedImage fakesheet;
	public static BufferedImage undoImage;
	public static BufferedImage resignImage;
	public static BufferedImage drawImage;
	{
		try {
			sheet = ImageIO.read(new File("resources\\piece.png"));
			fakesheet = ImageIO.read(new File("resources\\fakePiece.png"));
			undoImage = ImageIO.read(new File("resources\\undo.png"));
			resignImage = ImageIO.read(new File("resources\\resign.png"));
			drawImage = ImageIO.read(new File("resources\\draw.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public Image sprite;
	
	public int sheetscale = sheet.getWidth() / 6;
	public int fakesheetscale = fakesheet.getHeight();
	
	Board board;
	
	public Piece(Board board) {
		this.board = board;
	}
	
	public boolean canMove(int r, int c) {
		return true;
	}
	
	public boolean canAttack(int r, int c) {
		return true;
	}
	
	public void paint(Graphics2D g2d) {
		g2d.drawImage(sprite, xpos, ypos, null);
		
	}

	public boolean canCastle(int newrow, int newcol) {
		return false;
	}
	
	public boolean isAttacked() {
		return false;
	}

}
