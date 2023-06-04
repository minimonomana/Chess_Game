package main;

import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {

	public void startGame(int mode, String timeMode, String text1, String text2) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(1200, 836);
		frame.setBackground(new Color(50, 50, 100));

		Board board = new Board(mode);
		ChessTimerGUI timer = new ChessTimerGUI(board, timeMode, text1, text2);
		board.p1.name = text1;
		board.p2.name = text2;

		frame.add(board);
		frame.add(timer, BorderLayout.EAST);;

		timer.startGame();
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
}
