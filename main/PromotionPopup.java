package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.*;

import pieces.*;

public class PromotionPopup extends JFrame implements ActionListener {
    int cols = 4;
    int rows = 1;
    int tileSize = 80; 
    Board board;
    int row;
    int col;
    boolean isWhite;

    JButton knight;
    JButton bishop;
    JButton rook;
    JButton queen;
    JPanel king;

    public PromotionPopup(Board board, boolean isWhite, int row, int col){
        this.board = board;
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;

        knight = new JButton();
        knight.setBounds(0, 0, tileSize, tileSize);
        knight.setBackground(null);
        knight.addActionListener(this);
        knight.setIcon(new ImageIcon(new Knight(board, row, col, isWhite).sprite));

        bishop = new JButton();
        bishop.setBounds(tileSize, 0, tileSize, tileSize);
        bishop.addActionListener(this);
        bishop.setIcon(new ImageIcon(new Bishop(board, row, col, isWhite).sprite));

        rook = new JButton();
        rook.setBounds(2 * tileSize, 0, tileSize, tileSize);
        rook.addActionListener(this);
        rook.setIcon(new ImageIcon(new Rook(board, row, col, isWhite).sprite));
        
        queen = new JButton();
        queen.setBounds(3 * tileSize, 0, tileSize, tileSize);
        queen.addActionListener(this);
        queen.setIcon(new ImageIcon(new Queen(board, row, col, isWhite).sprite));

        king = new JPanel();
        king.setBounds(0, 0, 4 * tileSize + 12, tileSize + 36);

        this.setSize(4 * tileSize, tileSize);
        this.add(queen);
        this.add(knight);
        this.add(bishop);
        this.add(rook);
        this.add(king);
       
        this.setUndecorated(true);
        this.setLocationRelativeTo(board);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent choose) {
        if (choose.getSource() == knight) {
            board.pieceList.remove(board.get(row, col));
            board.pieceList.add(new Knight(board, row, col, isWhite));
            System.out.println("Promoted to a knight!");
        }
        else if (choose.getSource() == bishop) {
            board.pieceList.remove(board.get(row, col));
            board.pieceList.add(new Bishop(board, row, col, isWhite));
            System.out.println("Promoted to a bishop!");
        }
        else if (choose.getSource() == rook) {
            board.pieceList.remove(board.get(row, col));
            board.pieceList.add(new Rook(board, row, col, isWhite));
            System.out.println("Promoted to a rook!");
        }
        else if (choose.getSource() == queen) {
            board.pieceList.remove(board.get(row, col));
            board.pieceList.add(new Queen(board, row, col, isWhite));
            System.out.println("Promoted to a queen!");
        }
        this.dispose();
    }

}