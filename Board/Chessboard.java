package Board;
import ChessPieces.*;

public class Chessboard{
	int size = 8;
	Spot[][] board= new Spot[size][size];
	public Chessboard() {
		initializeBoard();
	}
	
	public Spot square(int x, int y) {
		if (x >= 0 && x < size && y >= 0 && y < size) {
			return board[x][y];
		}else {
			return null;
		}
	}
	
	private void initializeBoard() {
        // Initialize black pieces
        board[0][0] = new Spot(new Rook(false), 0, 0);
        board[1][0] = new Spot(new Knight(false), 1, 0);
        board[2][0] = new Spot(new Bishop(false), 02, 0);
        board[03][0] = new Spot(new Queen(false), 03, 0);
        board[04][0] = new Spot(new King(false), 04, 0);
        board[05][0] = new Spot(new Bishop(false), 05, 0);
        board[06][0] = new Spot(new Knight(false), 06, 0);
        board[07][0] = new Spot(new Rook(false), 07, 0);
        board[0][1] = new Spot(new Pawn(false), 0, 1);
        board[1][1] = new Spot(new Pawn(false), 1, 1);
        board[2][1] = new Spot(new Pawn(false), 2, 1);
        board[3][1] = new Spot(new Pawn(false), 3, 1);
        board[4][1] = new Spot(new Pawn(false), 4, 1);
        board[5][1] = new Spot(new Pawn(false), 5, 1);
        board[6][1] = new Spot(new Pawn(false), 6, 1);
        board[7][1] = new Spot(new Pawn(false), 7, 1);

        // Initialize empty spots
        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 6; j++) {
                board[i][j] = new Spot(null, i, j);
            }
        }

        // Initialize white pieces
        board[0][7] = new Spot(new Rook(true), 0, 7);
        board[1][7] = new Spot(new Knight(true), 1, 7);
        board[2][7] = new Spot(new Bishop(true), 2, 7);
        board[3][7] = new Spot(new Queen(true), 3, 7);
        board[4][7] = new Spot(new King(true), 4, 7);
        board[5][7] = new Spot(new Bishop(true), 5, 7);
        board[6][7] = new Spot(new Knight(true), 6, 7);
        board[7][7] = new Spot(new Rook(true), 7, 7);
        board[0][6] = new Spot(new Pawn(true), 0, 6);
        board[1][6] = new Spot(new Pawn(true), 1, 6);
        board[2][6] = new Spot(new Pawn(true), 2, 6);
        board[3][6] = new Spot(new Pawn(true), 3, 6);
        board[4][6] = new Spot(new Pawn(true), 4, 6);
        board[5][6] = new Spot(new Pawn(true), 5, 6);
        board[6][6] = new Spot(new Pawn(true), 6, 6);
        board[7][6] = new Spot(new Pawn(true), 7, 6);
    }
}
