package main;

import java.awt.*;
import javax.swing.*;

public class EndGameScreen extends JFrame {
    boolean isWhite;
    GameStatus result;

    public EndGameScreen(boolean isWhite, GameStatus result, String t1, String t2) {
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
            resultText.setText(t1 + " wins!!");
        } 
        else if (result == GameStatus.WHITE_WIN) {
            resultText.setText(t2 + " wins!!");
        } 

        resultText.setFont(new Font("Calibri", Font.BOLD, 30));
        this.add(resultText);
    }
}