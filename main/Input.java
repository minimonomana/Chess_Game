package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import pieces.Piece;

public class Input extends MouseAdapter{

	Board board;
	ChessTimerGUI timer = new ChessTimerGUI(board);
	
	public Input(Board board) {
		this.board = board;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (board.selectedpiece != null) {
			board.selectedpiece.xpos = e.getX() - board.tileSize / 2;
			board.selectedpiece.ypos = e.getY() - board.tileSize / 2;
			
			board.repaint();
		}
		
	}

	public void mouseClicked(MouseEvent e) {
		int col = e.getX() / board.tileSize;
		int row = e.getY() / board.tileSize;
		
		if ((e.getX() > 9 * board.tileSize && e.getX() <= 9 * board.tileSize + 40 &&  e.getY() >= 4 * board.tileSize && e.getY() <= 4 * board.tileSize + 40) && (board.mode == 0 || board.mode == 1)) {
			board.undo();
			timer.changeTimer(true);
			board.repaint();
		}

		if ((e.getX() > 9 * board.tileSize + 40 && e.getX() <= 9 * board.tileSize + 80 &&  e.getY() >= 4 * board.tileSize && e.getY() <= 4 * board.tileSize + 40)) {
			board.status = GameStatus.RESIGNATION;
			System.out.println("Resign!");
			//board.repaint();
		}

		if ((e.getX() >= 9 * board.tileSize - 40 && e.getX() <= 9 * board.tileSize &&  e.getY() >= 4 * board.tileSize && e.getY() <= 4 * board.tileSize + 40)) {
			board.status = GameStatus.OFFER_A_DRAW;
			System.out.println("Offering a draw!");
			//board.repaint();
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
			Piece piecexy = board.get(row, col);
			if (piecexy != null) {
				board.selectedpiece = piecexy;
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int col = e.getX() / board.tileSize;
		int row = e.getY() / board.tileSize;
		
		
		
		Piece piecexy = board.get(row, col);
		if (piecexy != null) {
			board.selectedpiece = piecexy;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		int col = e.getX() / board.tileSize;
		int row = e.getY() / board.tileSize;
		
		
		if (board.selectedpiece != null) {
			Move m = new Move(board, board.selectedpiece, row, col);
			if (board.isValidMove(m)) {
				board.move(m);
				timer.changeTimer(true);
				if (m.piece.name.equals("Pawn") && ((m.piece.row == 0 && m.piece.iswhite) || (m.piece.row == 7 && !m.piece.iswhite))){
					new PromotionPopup(board, m.piece.iswhite, m.piece.row, m.piece.col);
					//promote.paint(getGraphics());
					board.repaint();
				}
				
			}
			else {
				board.selectedpiece.xpos = board.selectedpiece.col * board.tileSize;
				board.selectedpiece.ypos = board.selectedpiece.row * board.tileSize;
			}
			
			
		}
		
		board.selectedpiece = null;
		board.repaint();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

}
