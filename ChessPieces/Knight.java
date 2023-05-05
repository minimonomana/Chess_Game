package ChessPieces;
import Board.*;

public class Knight extends Piece {
    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Chessboard board, Spot start, Spot end) {
        // A knight can move to a spot that is 2 squares away vertically and 1 square away horizontally
        // or 1 square away vertically and 2 squares away horizontally
        int startX = start.getx();
        int startY = start.gety();
        int endX = end.getx();
        int endY = end.gety();

        int diffX = Math.abs(endX - startX);
        int diffY = Math.abs(endY - startY);

        return (diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1);
    }

    @Override
    public void move(Chessboard board, Spot start, Spot end) {
        if (canMove(board, start, end)) {
            end.setpiece(this);
            start.setpiece(null);
            this.hasMoved = true;
        } else {
            throw new IllegalArgumentException("Invalid move");
        }
    }

    @Override
    public boolean attack(Chessboard board, Spot start, Spot end) {
        // A knight can attack any enemy piece that is on a spot that it can move to
        return canMove(board, start, end) && end.getpiece() != null && end.getpiece().isWhite() != this.isWhite();
    }
}
