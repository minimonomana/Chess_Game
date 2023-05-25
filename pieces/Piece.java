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
	{
		try {
			sheet = ImageIO.read(new File("C:\\Users\\nguye\\java_eclipse_workspace\\ChessFromYoutube\\src\\res\\piece.png"));
			fakesheet = ImageIO.read(new File("C:\\Users\\nguye\\Downloads\\271268854_676252396701624_8691544580892285613_n.png"));
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
