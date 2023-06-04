package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
	int mode;

	GameStatus status;

	int[] fakeBlack;
	int[] fakeWhite;
	
	public Board(int mode){
		this.setPreferredSize(new Dimension(cols * tileSize, rows * tileSize));
		this.setBackground(Color.GRAY);
		this.addMouseListener(input);
		this.addMouseMotionListener(input);
	
		this.mode = mode;
		if (mode == 0) {
			this.resetBoard();
		}
		else if (mode == 1) {
			this.chess960Board();
		}
		else if (mode == 2){
			this.fakeChess();
		}
		else if (mode == 3){
			this.semiFakeChess();
		}

		status = GameStatus.ACTIVE;
		Sound.playSound("resources\\StartEndGame.wav");
		timer = new ChessTimerGUI(this, "","","");
	
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

	public void fakeChess(){
		fakeBlack = new int[15];
		fakeWhite = new int[15];
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < 15; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		for (int i = 0; i < 15; i++){
			fakeBlack[i] = list.get(i);
		}

		Collections.shuffle(list);
		for (int i = 0; i < 15; i++){
			fakeWhite[i] = list.get(i);
		}

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

		for (Piece piece: pieceList){
			piece.sprite = piece.fakesheet.getSubimage(piece.iswhite ? 0: piece.fakesheetscale, 0, piece.fakesheetscale, piece.fakesheetscale).getScaledInstance(tileSize, tileSize, BufferedImage.SCALE_SMOOTH);
		}
		// Adding Kings
		pieceList.add(new King(this, 0, 4, false));
		pieceList.add(new King(this, 7, 4, true));

	}

	public void semiFakeChess(){
		fakeBlack = new int[15];
		fakeWhite = new int[15];
		List<Integer> list = new LinkedList<Integer>();

		for (int i = 8; i < 15; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		for (int i = 8; i < 15; i++){
			fakeBlack[i] = list.get(i - 8);
		}

		Collections.shuffle(list);
		for (int i = 8; i < 15; i++){
			fakeWhite[i] = list.get(i - 8);
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

		for (Piece piece: pieceList){
			piece.sprite = piece.fakesheet.getSubimage(piece.iswhite ? 0: piece.fakesheetscale, 0, piece.fakesheetscale, piece.fakesheetscale).getScaledInstance(tileSize, tileSize, BufferedImage.SCALE_SMOOTH);
		}

		// Adding Pawns
		for(int i=0; i<8; i++) {
		    pieceList.add(new Pawn(this, 1, i, false));
		    pieceList.add(new Pawn(this, 6, i, true));
		}
		for (Piece piece: pieceList){
			if (piece.name.equals("Pawn")){
				piece.hasFakeMoved = true;
			}
		}

		// Adding Kings
		pieceList.add(new King(this, 0, 4, false));
		pieceList.add(new King(this, 7, 4, true));

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
	
	public Stack<Move> moveList = new Stack<Move>();
	
	public void move(Move m) {
		if (m.capture != null){
			Sound.playSound("resources\\Capture.wav");
		}
		else{
			Sound.playSound("resources\\Move.wav");
		}
		
		if (m.piece.name.equals("King")) {
			castle(m);
		}
		else if (m.piece.name.equals("Pawn") && Math.abs(m.newrow - m.oldrow) == 1 && Math.abs(m.newcol - m.oldcol) == 1) {
			enPassant(m);
		}

		m.piece.col = m.newcol;
		m.piece.row = m.newrow;
		m.piece.xpos = m.newcol * tileSize;
		m.piece.ypos = m.newrow * tileSize;
		m.piece.hasMoved = true;

		capture(m);

		moveList.push(m);
		if (p1.turn() == true) {
			p1.nextTurn(false);
			p2.nextTurn(true);
		}
		else if (p2.turn() == true){
			p2.nextTurn(false);
			p1.nextTurn(true);

		}
		if (isCheckMated(p1.turn())) {
			System.out.println("CHECKMATE!!!");
			this.status = GameStatus.WHITE_WIN;
		}
		if (isCheckMated(p2.turn())) {
			System.out.println("CHECKMATE!!!");
			this.status = GameStatus.BLACK_WIN;
		}
		if (isStaleMated(p1.turn())) {
			System.out.println("Stalemate.");
			this.status = GameStatus.DRAW;
		}

		if ((mode == 2 || mode == 3) && !m.piece.hasFakeMoved){
			pieceList.remove(m.piece);
			Piece fakePiece = null;
			if (m.piece.iswhite){
				int newPiece = fakeWhite[8 * (m.oldrow - 6) + m.oldcol == 15 ? 12 : 8 * (m.oldrow - 6) + m.oldcol];
				System.out.println(newPiece + 1000);
				if (newPiece < 8){
					fakePiece = new Pawn(this, m.newrow, m.newcol, m.piece.iswhite);
				}
				else{
					if (newPiece == 8 || newPiece == 14){
						fakePiece = new Rook(this, m.newrow, m.newcol, m.piece.iswhite);
					}
					else if(newPiece == 9 || newPiece == 13){
						fakePiece = new Knight(this, m.newrow, m.newcol, m.piece.iswhite);
					}
					else if (newPiece == 10 || newPiece == 12){
						fakePiece = new Bishop(this, m.newrow, m.newcol, m.piece.iswhite);
					}
					else if (newPiece == 11){
						fakePiece = new Queen(this, m.newrow, m.newcol, m.piece.iswhite);
					}
				}
			}
			else{
				int newPiece = fakeBlack[8 * (1 - m.oldrow) + m.oldcol == 15 ? 12 : 8 * (1 - m.oldrow) + m.oldcol];
				System.out.println(newPiece + 2000);
				if (newPiece < 8){
					fakePiece = new Pawn(this, m.newrow, m.newcol, m.piece.iswhite);
				}
				else{
					if (newPiece == 8 || newPiece == 14){
						fakePiece = new Rook(this, m.newrow, m.newcol, m.piece.iswhite);
					}
					else if(newPiece == 9 || newPiece == 13){
						fakePiece = new Knight(this, m.newrow, m.newcol, m.piece.iswhite);
					}
					else if (newPiece == 10 || newPiece == 12){
						fakePiece = new Bishop(this, m.newrow, m.newcol, m.piece.iswhite);
					}
					else if (newPiece == 11){
						fakePiece = new Queen(this, m.newrow, m.newcol, m.piece.iswhite);
					}
				}
			}
			pieceList.add(fakePiece);
			fakePiece.hasFakeMoved = true;
		}
		win();
		
	}
	
	public void undo() {
		Sound.playSound("resources\\Move.wav");
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
		m.piece.hasMoved = false;
		p1.nextTurn(!p1.turn());
		p2.nextTurn(!p2.turn());
		
	}
	
	public void capture(Move m) {
		pieceList.remove(m.capture);
	}
	
	public void castle(Move m) {
		
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


			if (mode == 2 || mode == 3){
				pieceList.remove(rook);
				Piece fakePiece = null;
				if (m.piece.iswhite){
					int newPiece = fakeWhite[m.piece.col > m.newcol ? 8 : 12];
					if (newPiece < 8){
						fakePiece = new Pawn(this, rook.row, rook.col, m.piece.iswhite);
					}
					else{
						if (newPiece == 8 || newPiece == 14){
							fakePiece = new Rook(this, rook.row, rook.col, m.piece.iswhite);
						}
						else if(newPiece == 9 || newPiece == 13){
							fakePiece = new Knight(this, rook.row, rook.col, m.piece.iswhite);
						}
						else if (newPiece == 10 || newPiece == 12){
							fakePiece = new Bishop(this, rook.row, rook.col, m.piece.iswhite);
						}
						else if (newPiece == 11){
							fakePiece = new Queen(this, rook.row, rook.col, m.piece.iswhite);
						}
					}
				}
				else{
					int newPiece = fakeBlack[m.piece.col > m.newcol ? 8: 12];
					if (newPiece < 8){
						fakePiece = new Pawn(this, rook.row, rook.col, m.piece.iswhite);
					}
					else{
						if (newPiece == 8 || newPiece == 14){
							fakePiece = new Rook(this, rook.row, rook.col, m.piece.iswhite);
						}
						else if(newPiece == 9 || newPiece == 13){
							fakePiece = new Knight(this, rook.row, rook.col, m.piece.iswhite);
						}
						else if (newPiece == 10 || newPiece == 12){
							fakePiece = new Bishop(this, rook.row, rook.col, m.piece.iswhite);
						}
						else if (newPiece == 11){
							fakePiece = new Queen(this, rook.row, rook.col, m.piece.iswhite);
						}
					}
				}
				pieceList.add(fakePiece);
				fakePiece.hasFakeMoved = true;
			}
			
		}
		
	}
	
	public void enPassant(Move m) {
		if (!moveList.isEmpty()) {
			Move lastMove = moveList.peek();
			Piece lastPiece = moveList.peek().piece;
			if (p1.turn() && m.oldrow == 3 && m.newrow == 2 && lastPiece.name.equals("Pawn") &&
					lastMove.newrow == 3 && lastMove.oldrow == 1 && m.newcol == lastMove.newcol && Math.abs(m.newcol - m.oldcol) == 1) {
				pieceList.remove(get(3, m.newcol));
			}
			else if (p2.turn() && m.oldrow == 4 && m.newrow == 5 && lastPiece.name.equals("Pawn") &&
					lastMove.newrow == 4 && lastMove.oldrow == 6 && m.newcol == lastMove.newcol && Math.abs(m.newcol - m.oldcol) == 1) {
				pieceList.remove(get(4, m.newcol));
			}
		}
	}
	
	public boolean isValidMove(Move move) {
		if (ChessTimerGUI.timer1 <= 0){
			this.status = GameStatus.BLACK_WIN;
			timer.stopTimer();
			return false;
		}
		if (ChessTimerGUI.timer2 <= 0){
			this.status = GameStatus.WHITE_WIN;
			timer.stopTimer();
			return false;
		}

		int rowKing = 0;
		int colKing = 0;
		boolean isWhiteKing = move.piece.iswhite;
		for (Piece piece : pieceList) {
			if (piece.iswhite == move.piece.iswhite && piece.name.equals("King")) {
				rowKing = piece.row;
				colKing = piece.col;
				break;
			}
		}
		if (p1.turn() == move.piece.iswhite && move.piece.canMove(move.newrow, move.newcol)) {
			if (move.piece.name.equals("King")) {
				if (checkAttacked(move.newrow, move.newcol, isWhiteKing)) {
					return false;
				}
				return true;
			}
			boolean isPinned = false;
			int rowIncrement = 0;
			int colIncrement = 0;
			if (move.piece.row > rowKing) {
				rowIncrement = 1;
			}
			else if (move.piece.row < rowKing) {
				rowIncrement = -1;
			}
			if (move.piece.col > colKing) {
				colIncrement = 1;
			}
			else if (move.piece.col < colKing) {
				colIncrement = -1;
			}
			int curRow = rowKing;
			int curCol = colKing;
			boolean wasPiece = false;
			boolean isMoveInPinLine = false;
			while (curRow > -1 && curRow < 8 && curCol > -1 && curCol < 8) {
				curRow += rowIncrement;
				curCol += colIncrement;
				if (move.newrow == curRow && move.newcol == curCol) {
					isMoveInPinLine = true;
				}
				if (wasPiece && get(curRow, curCol) != null) {
					Piece pinner = get(curRow, curCol);
					if (pinner.iswhite == p1.turn()) {
						break;
					}
					if ((pinner.name.equals("Bishop") || pinner.name.equals("Queen")) && Math.abs(rowIncrement) + Math.abs(colIncrement) == 2) {
						isPinned = true;
						break;
					}
					else if ((pinner.name.equals("Rook") || pinner.name.equals("Queen")) && Math.abs(rowIncrement) + Math.abs(colIncrement) == 1) {
						isPinned = true;
						break;
					}
				}
				else if (!wasPiece && get(curRow, curCol) != null) {
					if (get(curRow, curCol) == move.piece) {
						wasPiece = true;
					}
					else {
						break;
					}
				}
			}

			if (!checkAttacked(rowKing, colKing, isWhiteKing)) {
				if (isPinned) {
					if (isMoveInPinLine) {
						return true;
					}
					return false;
				}
				return true;
			}
			int numOfChecks = 0;
			Piece curAttacker = move.piece;
			for (Piece attacker : pieceList) {
				if (attacker.canAttack(rowKing, colKing) && attacker.iswhite != isWhiteKing) {
					numOfChecks += 1;
					curAttacker = attacker;
				}
			}
			if (numOfChecks > 1) {
				return false;
			}
			if (move.newrow == curAttacker.row && move.newcol == curAttacker.col && !move.piece.name.equals("King")) {
				return true;
			}
			if (curAttacker.name.equals("Bishop") || curAttacker.name.equals("Rook") || curAttacker.name.equals("Queen")) {
				boolean canBlock = false;
				if (move.newrow <= Math.max(curAttacker.row, rowKing) && move.newrow >= Math.min(curAttacker.row, rowKing)
						&& move.newcol <= Math.max(curAttacker.col, colKing) && move.newcol >= Math.min(curAttacker.col, rowKing)) {
					rowIncrement = 0;
					colIncrement = 0;
					if (curAttacker.row > rowKing) {
						rowIncrement = 1;
					} else if (curAttacker.row < rowKing) {
						rowIncrement = -1;
					}
					if (curAttacker.col > colKing) {
						colIncrement = 1;
					} else if (curAttacker.col < colKing) {
						colIncrement = -1;
					}
					curRow = rowKing;
					curCol = colKing;
					while (curRow != curAttacker.row && curCol != curAttacker.col) {
						curRow += rowIncrement;
						curCol += colIncrement;
						if (move.newrow == curRow && move.newcol == curCol) {
							canBlock = true;
							break;
						}
					}
				}
				if (canBlock) {
					if (isPinned && !isMoveInPinLine) {
						return false;
					}
					return true;
				}
				return false;
			}
			return true;
			// System.out.printf("%s %s can move from %d %d to %d %d\n", move.piece.iswhite ? "white" : "black", move.piece.name, move.piece.row, move.piece.col, move.newrow, move.newcol);
		}
		return false;
	}
	
	public boolean checkAttacked(int row, int col, boolean white) {

		for (Piece piece: pieceList) {
			// System.out.println(piece.name);
			if (piece.iswhite != white) {
				if (piece.canAttack(row, col)) {
					return true;
				}
			}

		}

		return false;
	}

	public boolean isCheckMated(boolean isWhite) {
		for (Piece piece : pieceList) {
			if (piece.iswhite == isWhite) {
				if (piece.name.equals("King")) {
					if (!checkAttacked(piece.row, piece.col, isWhite)) {
						return false;
					}
				}
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (isValidMove(new Move(this, piece, i, j))) {
							return false;
						}
          			}
        		}
      		}
    	}
    	return true;
	}
  
	public boolean isStaleMated(boolean isWhite) {
		for (Piece piece : pieceList) {
			if (piece.iswhite == isWhite) {
				if (piece.name.equals("King")) {
					if (checkAttacked(piece.row, piece.col, isWhite)) {
						return false;
					}
				}
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (isValidMove(new Move(this, piece, i, j))) {
							return false;
						}
					}
				}
			}
		}
		return true;
    
	}
	
	public boolean win() {
		if (this.status == GameStatus.RESIGNATION){
			System.out.println(0);
			this.status = p2.turn() ? GameStatus.BLACK_WIN : GameStatus.WHITE_WIN;
		}
		else if (this.status == GameStatus.OFFER_A_DRAW){
			System.out.println(1);
			this.status = GameStatus.DRAW;
		}

		if (this.status == GameStatus.WHITE_WIN){
			System.out.println("White win!");
			timer.stopTimer();
			new EndGameScreen(p1.turn(), GameStatus.WHITE_WIN);
			return true;
		}
		else if (this.status == GameStatus.BLACK_WIN){
			System.out.println("Black win!");
			timer.stopTimer();
			new EndGameScreen(p2.turn(), GameStatus.WHITE_WIN);
			return true;
		}
		else if (this.status == GameStatus.DRAW){
			System.out.println("Draw!");
			timer.stopTimer();
			new EndGameScreen(p1.turn(), GameStatus.DRAW);
			return true;
		}
		return false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(Piece.boardground, 0, 0, 1200 , 836 , null);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				g2d.setColor((i + j) % 2 == 0 ? new Color(100, 153, 76) : new Color(255, 235, 200));
				g2d.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
				
			}
		}
		
		g2d.drawImage(Piece.undoImage, 240, 672, 160 , 100 , null);
		g2d.drawImage(Piece.resignImage, 40, 672, 160, 100, null);
		g2d.drawImage(Piece.drawImage, 440, 672, 160, 100, null);
		
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
			// System.out.println();
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
