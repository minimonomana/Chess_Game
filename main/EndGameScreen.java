package main;

import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import javax.swing.ImageIcon;
import javax.swing.*;

// import pieces.*;

public class EndGameScreen extends JFrame {
    boolean isWhite;
    GameStatus result;

    public EndGameScreen(boolean isWhite, GameStatus result) {
        this.isWhite = isWhite;
        this.result = result;
        this.setSize(400, 200);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JLabel resultText = new JLabel();
        resultText.setVerticalAlignment(JLabel.CENTER);
        resultText.setHorizontalAlignment(JLabel.CENTER);
        if (result == GameStatus.DRAW) {
            resultText.setText("Draw!!");
        } 
        else if (result == GameStatus.BLACK_WIN) {
            resultText.setText("Black wins!!");
        } 
        else if (result == GameStatus.WHITE_WIN) {
            resultText.setText("White wins!!");
        } 
        resultText.setFont(new Font("Calibri", Font.BOLD, 30));
        this.add(resultText);
    }
}