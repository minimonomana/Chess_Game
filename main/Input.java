package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import pieces.Piece;

public class Input extends MouseAdapter{

	Board board;
	int i = 0;
	
	public Input(Board board) {
		this.board = board;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (board.selectedpiece != null) {
			board.selectedpiece.xpos = e.getX() - board.tileSize / 2;
			board.selectedpiece.ypos = e.getY() - board.tileSize / 2;
			
			board.repaint();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int col = e.getX() / board.tileSize;
		int row = e.getY() / board.tileSize;
		
		if (col > 7 || row > 7) {
			board.undo();
			board.repaint();
		}
		
		if (board.selectedpiece != null) {
			Move m = new Move(board, board.selectedpiece, row, col);
			if (board.isValidMove(m)) {
				board.move(m);
			}
			else {
				board.selectedpiece.xpos = board.selectedpiece.col * board.tileSize;
				board.selectedpiece.ypos = board.selectedpiece.row * board.tileSize;
			}

			board.selectedpiece = null;
			board.repaint();
			
		}
		else {
			i = 1;
			Piece piecexy = board.get(row, col);
			if (piecexy != null) {
				board.selectedpiece = piecexy;
			}
		}
		
		System.out.println(i);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int col = e.getX() / board.tileSize;
		int row = e.getY() / board.tileSize;
		
		
		
		System.out.println(i);
		i = 0;
		Piece piecexy = board.get(row, col);
		if (piecexy != null) {
			board.selectedpiece = piecexy;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		int col = e.getX() / board.tileSize;
		int row = e.getY() / board.tileSize;
		System.out.println(i);
		
		
		if (i == 0) {
			if (board.selectedpiece != null) {
				Move m = new Move(board, board.selectedpiece, row, col);
				if (board.isValidMove(m)) {
					board.move(m);
					
				}
				else {
					board.selectedpiece.xpos = board.selectedpiece.col * board.tileSize;
					board.selectedpiece.ypos = board.selectedpiece.row * board.tileSize;
				}
				
				
			}
			
			board.selectedpiece = null;
			board.repaint();
			System.out.println(i);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
