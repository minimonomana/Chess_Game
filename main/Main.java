package main;

import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {

	public void startGame() {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(954, 678);
		frame.setBackground(new Color(100, 100, 100));

		Board board = new Board();
		ChessTimerGUI timer = new ChessTimerGUI(board);

		frame.add(board);
		frame.add(timer, BorderLayout.EAST);

//		frame.pack();
//		frame.validate();

		timer.startGame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
    public static void main(String[] args) {
        Main chessGame = new Main();
	Scanner input = new Scanner(System.in);
	int mode = input.nextInt();
        chessGame.startGame(mode);
    }
}
