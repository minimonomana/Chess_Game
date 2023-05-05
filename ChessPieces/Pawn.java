package ChessPieces;
import Board.*;

public class Pawn extends Piece {

    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Chessboard board, Spot start, Spot end) {
        int startX = start.getx();
        int startY = start.gety();
        int endX = end.getx();
        int endY = end.gety();

        // Check if the move is vertical and in the correct direction for the pawn
        if (startX == endX && ((isWhite() && endY == startY - 1) || (!isWhite() && endY == startY + 1))) {
            // Check if the destination is empty
            if (board.square(endX, endY).getpiece() == null) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void move(Chessboard board, Spot start, Spot end) {
        if (canMove(board, start, end)) {
            // Move the piece to the destination
            end.setpiece(this);
            start.setpiece(null);
            hasMoved = true;
        }
    }

    @Override
    public boolean attack(Chessboard board, Spot start, Spot end) {
        int startX = start.getx();
        int startY = start.gety();
        int endX = end.getx();
        int endY = end.gety();

        // Check if the move is diagonal and in the correct direction for the pawn
        if (Math.abs(startX - endX) == 1 && ((isWhite() && endY == startY - 1) || (!isWhite() && endY == startY + 1))) {
            // Check if there is a piece of the opposite color in the destination
            if (board.square(endX, endY).getpiece() != null && board.square(endX, endY).getpiece().isWhite() != isWhite()) {
                return true;
            }
        }

        return false;
    }
}
