package main;

import java.awt.*;
import javax.swing.*;
// import java.util.Scanner;

public class Main extends JFrame {

	public void startGame(int mode, String timeMode, String text1, String text2) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(1200, 836);
		frame.setBackground(new Color(50, 50, 100));

		Board board = new Board(mode);
		ChessTimerGUI timer = new ChessTimerGUI(board, timeMode, text1, text2);

		frame.add(board);
		frame.add(timer, BorderLayout.EAST);

//		frame.pack();
//		frame.validate();

		timer.startGame();
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
    // public static void main(String[] args) {
    //     Main chessGame = new Main();
    //     Scanner input = new Scanner(System.in);
	// 	int mode = input.nextInt();
	// 	String timeMode = input.next();
    //     chessGame.startGame(mode,timeMode);
    // }
}
