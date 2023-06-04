package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import pieces.Piece;

public class Input extends MouseAdapter{

	Board board;
	ChessTimerGUI timer = new ChessTimerGUI(board, "","","");
	
	public Input(Board board) {
		this.board = board;
	}

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
		
		if ((e.getX() > 240 && e.getX() <= 400 &&  e.getY() >= 672 && e.getY() <= 772) && (board.mode == 0 || board.mode == 1)) {
			board.undo();
			timer.changeTimer(true);
			board.repaint();
		}

		if ((e.getX() >  40 && e.getX() <= 200 &&  e.getY() >= 672 && e.getY() <= 772)) {
			board.status = GameStatus.RESIGNATION;
			System.out.println("Resign!");
			board.win();
		}

		if ((e.getX() >= 440 && e.getX() <= 600 &&  e.getY() >= 672 && e.getY() <= 772)) {
			board.status = GameStatus.DRAW;
			System.out.println("Offering a draw!");
			board.win();
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

	public void mousePressed(MouseEvent e) {
		int col = e.getX() / board.tileSize;
		int row = e.getY() / board.tileSize;
				
		Piece piecexy = board.get(row, col);
		if (piecexy != null) {
			board.selectedpiece = piecexy;
		}		
	}

	public void mouseReleased(MouseEvent e) {
		
		int col = e.getX() / board.tileSize;
		int row = e.getY() / board.tileSize;
				
		if (board.selectedpiece != null) {
			Move m = new Move(board, board.selectedpiece, row, col);
			if (board.isValidMove(m)) {
				board.move(m);

				if (!ChessTimerGUI.canStart) {
					ChessTimerGUI.canStart = true;
					timer.startGame();
				}
				else {
					timer.changeTimer(true);
				}

				if (m.piece.name.equals("Pawn") && ((m.piece.row == 0 && m.piece.iswhite) || (m.piece.row == 7 && !m.piece.iswhite))){
					new PromotionPopup(board, m.piece.iswhite, m.piece.row, m.piece.col);
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

}
