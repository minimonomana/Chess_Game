package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.*;

import pieces.*;

public class EndGameScreen extends JFrame {
    boolean isWhite;
    String result;

    public EndGameScreen(boolean isWhite, String result) {
        this.isWhite = isWhite;
        this.result = result;
        this.setSize(400, 200);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JLabel resultText = new JLabel();
        resultText.setVerticalAlignment(JLabel.CENTER);
        resultText.setHorizontalAlignment(JLabel.CENTER);
        if (result == "Stalemate") {
            resultText.setText("Stalemate");
        } else if (result == "Checkmate") {
            if (isWhite) {
                resultText.setText("Checkmate by White");
            }
            else {
                resultText.setText("Checkmate by Black");
            }
        }
        resultText.setFont(new Font("Calibri", Font.BOLD, 30));
        this.add(resultText);
    }
}