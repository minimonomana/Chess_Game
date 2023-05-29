package main;

import java.awt.*;
import javax.swing.JFrame;

// extends JFrame
public class Main extends JFrame {
	
	
	public void startGame() {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(954, 678);
		frame.setBackground(new Color(100, 100, 100));
		//frame.setBackground(new Color(200, 200, 200));
		//frame.setLayout(new GridBagLayout());

		frame.add(new Board(), BorderLayout.WEST);
		frame.add(new ChessTimerGUI(), BorderLayout.EAST);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

	}
	
    public static void main(String[] args) {
        Main chessGame = new Main();
        chessGame.startGame();
    }
}