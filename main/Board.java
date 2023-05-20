package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;

import pieces.*;


public class Board extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int tileSize = 80;
	int cols = 8;
	int rows = 8;
	Players p1;
	Players p2;
	ChessTimerGUI timer;
	
	
	public Board(){
		this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
		this.setBackground(Color.GRAY);
		this.addMouseListener(input);
		this.addMouseMotionListener(input);
		Scanner input = new Scanner(System.in);
		int mode = input.nextInt();
		if (mode == 0) {
			this.resetBoard();
		}
		else if (mode == 1) {
			this.chess960Board();
		}
//		timer = new ChessTimerGUI();
//		this.add(timer);
		
		
		input.close();
		
		p1 = new Players(true);
		p2 = new Players(false);
	}
	
	public void resetBoard() {
		// Adding Pawns
		for(int i=0; i<8; i++) {
		    pieceList.add(new Pawn(this, 1, i, false));
		    pieceList.add(new Pawn(this, 6, i, true));
		}

		// Adding Rooks
		pieceList.add(new Rook(this, 0, 0, false));
		pieceList.add(new Rook(this, 0, 7, false));
		pieceList.add(new Rook(this, 7, 0, true));
		pieceList.add(new Rook(this, 7, 7, true));

		// Adding Knights
		pieceList.add(new Knight(this, 0, 1, false));
		pieceList.add(new Knight(this, 0, 6, false));
		pieceList.add(new Knight(this, 7, 1, true));
		pieceList.add(new Knight(this, 7, 6, true));

		// Adding Bishops
		pieceList.add(new Bishop(this, 0, 2, false));
		pieceList.add(new Bishop(this, 0, 5, false));
		pieceList.add(new Bishop(this, 7, 2, true));
		pieceList.add(new Bishop(this, 7, 5, true));

		// Adding Queens
		pieceList.add(new Queen(this, 0, 3, false));
		pieceList.add(new Queen(this, 7, 3, true));

		// Adding Kings
		pieceList.add(new King(this, 0, 4, false));
		pieceList.add(new King(this, 7, 4, true));

		
	}
	
	public void chess960Board() {
		int arr[] = new int[8];
		
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		while (list.get(2) % 2 == list.get(5) % 2 || (list.get(4) - list.get(0)) * (list.get(4) - list.get(7)) > 0) {
			Collections.shuffle(list);
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list.get(i);
		}
		
		// Adding Pawns
		for(int i=0; i<8; i++) {
		    pieceList.add(new Pawn(this, 1, i, false));
		    pieceList.add(new Pawn(this, 6, i, true));
		}
		
		// Adding Rooks
		pieceList.add(new Rook(this, 0, arr[0], false));
		pieceList.add(new Rook(this, 0, arr[7], false));
		pieceList.add(new Rook(this, 7, arr[0], true));
		pieceList.add(new Rook(this, 7, arr[7], true));

		// Adding Knights
		pieceList.add(new Knight(this, 0, arr[1], false));
		pieceList.add(new Knight(this, 0, arr[6], false));
		pieceList.add(new Knight(this, 7, arr[1], true));
		pieceList.add(new Knight(this, 7, arr[6], true));

		// Adding Bishops
		pieceList.add(new Bishop(this, 0, arr[2], false));
		pieceList.add(new Bishop(this, 0, arr[5], false));
		pieceList.add(new Bishop(this, 7, arr[2], true));
		pieceList.add(new Bishop(this, 7, arr[5], true));

		// Adding Queens
		pieceList.add(new Queen(this, 0, arr[3], false));
		pieceList.add(new Queen(this, 7, arr[3], true));

		// Adding Kings
		pieceList.add(new King(this, 0, arr[4], false));
		pieceList.add(new King(this, 7, arr[4], true));


	}
	
	ArrayList<Piece> pieceList = new ArrayList<>();
	
	Piece selectedpiece;
	
	Input input = new Input(this);
	
	public Piece get(int r, int c) {
		for (Piece piece: pieceList) {
			if (r == piece.row && c == piece.col) {
				return piece;
			}
		}
		return null;
	}
	
	Stack<Move> moveList = new Stack<Move>();
	
	public void move(Move m) {
		if (p1.turn() == true) {
			if (m.piece.name.equals("King")) {
				castle(m);
			}
			
			m.piece.col = m.newcol;
			m.piece.row = m.newrow;
			m.piece.xpos = m.newcol * tileSize;
			m.piece.ypos = m.newrow * tileSize;
			m.piece.hasMoved = true;

			
			capture(m);
			
			moveList.push(m);
			p1.nextTurn(false);
			p2.nextTurn(true);

		}
		else if (p2.turn() == true){
			if (m.piece.name.equals("King")) {
				castle(m);
			}
			
			m.piece.col = m.newcol;
			m.piece.row = m.newrow;
			m.piece.xpos = m.newcol * tileSize;
			m.piece.ypos = m.newrow * tileSize;
			m.piece.hasMoved = true;

			
			capture(m);
			
			moveList.push(m);
			p2.nextTurn(false);
			p1.nextTurn(true);
			
		}
		
	}
	
	public void undo() {
		Move m = moveList.pop();
		if (m.piece.name.equals("King")) {
			if (Math.abs(m.newcol - m.oldcol) > 1) {
				Piece rook;
				if (m.newcol == 2) {
					rook = this.get(m.piece.row, 3);
					rook.col = 0;
				}
				else {
					rook = this.get(m.piece.row, 5);
					rook.col = 7;
				}
				rook.xpos = rook.col * tileSize;
				rook.ypos = rook.row * tileSize;
				rook.hasMoved = false;
				m.piece.hasMoved = false;
				
			}
		}
		m.piece.col = m.oldcol;
		m.piece.row = m.oldrow;
		m.piece.xpos = m.piece.col * tileSize;
		m.piece.ypos = m.piece.row * tileSize;
		if (m.capture != null) {
			pieceList.add(m.capture);
			m.capture.col = m.newcol;
			m.capture.row = m.newrow;
			m.capture.xpos = m.newcol * tileSize;
			m.capture.ypos = m.newrow * tileSize;
		}
		p1.nextTurn(!p1.turn());
		p2.nextTurn(!p2.turn());
		
	}
	
	public void capture(Move m) {
		pieceList.remove(m.capture);
	}
	
	public void castle(Move m) {
		System.out.println("hihihehe");
		
		if (Math.abs(m.newcol - m.piece.col) == 2) {
			Piece rook;
			if (m.piece.col < m.newcol) {
				rook = this.get(m.piece.row, 7);
				rook.col = 5;
			}
			else {
				rook = this.get(m.piece.row, 0);
				rook.col = 3;
			}
			rook.xpos = rook.col * tileSize;
			rook.ypos = rook.row * tileSize;
			rook.hasMoved = true;
		}
		
	}
	
	public boolean isValidMove(Move move) {

		if (p1.turn() == move.piece.iswhite) {
			
			if (move.piece.canMove(move.newrow, move.newcol)) {
				System.out.printf("%s %s can move from %d %d to %d %d\n", move.piece.iswhite ? "white" : "black", move.piece.name, move.piece.row, move.piece.col, move.newrow, move.newcol);
				return true;
			}
			

		}
		
		return false;
	}
	
	public boolean checkAttacked(int row, int col, boolean white) {
		
		for (Piece piece: pieceList) {
			System.out.println(piece.name);
			if (piece.iswhite != white) {
				if (piece.canAttack(row, col)) {
					System.out.printf("%d %d attacks %d %d\n", piece.row, piece.col, row, col);
					return true;
				}
			}
			
		}
		
		return false;
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				g2d.setColor((i + j) % 2 == 0 ? new Color(100, 153, 76) : new Color(255, 235, 200));
				g2d.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
				
			}
		}
		
		if (selectedpiece != null) {
			
			for (int r = 0; r < rows; r++) {
				for (int c = 0; c < cols; c++) {
					if (isValidMove(new Move(this, selectedpiece, r, c))) {
						g2d.setColor(new Color(210, 100, 100));
						g2d.fillRect(selectedpiece.col * tileSize, selectedpiece.row * tileSize, tileSize, tileSize);
						g2d.setColor(new Color(100, 100, 210));
						g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
					}
				}
			}
			System.out.println();
		}
		
		for (Piece piece: pieceList) {
			if (piece.name.equals("King")) {
				if (piece.isAttacked()) {
					g2d.setColor(new Color(150, 50, 40));
					g2d.fillRect(piece.col * tileSize, piece.row * tileSize, tileSize, tileSize);
				}
			}
			piece.paint(g2d);
			
		}
	}
	

}
