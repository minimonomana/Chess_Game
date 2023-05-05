package GUI;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import Board.*;
import ChessPieces.*;

public class Board extends JFrame implements ActionListener{
	//create the board
	JFrame board;
	JLabel label;
	JButton[][] button = new JButton[8][8];
	JPanel[][] square = new JPanel[8][8];
	Border border = BorderFactory.createLineBorder(Color.black);
	
	//create an object board
	Chessboard chessboard = new Chessboard();
	
	Board(){
		//create a board
		board = new JFrame();
		this.setSize(1200, 836);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(100, 100, 100));
		this.setLayout(null);
	
		//create squares
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				square[i][j]= new JPanel();
				square[i][j].setBounds(100 * i, 100 * j, 100, 100);
				if ((i + j) % 2 == 0) {
					square[i][j].setBackground(Color.black);
				}else {
					square[i][j].setBackground(Color.white);
				}
				square[i][j].setLayout(new BorderLayout());
				this.add(square[i][j]);
			}
		}
		
		//create buttons for move
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				button[i][j]= new JButton();
				button[i][j].setBounds(100 * i, 100 * j, 100, 100);
				button[i][j].addActionListener(this);
				button[i][j].setEnabled(true);
				button[i][j].setBackground(square[i][j].getBackground());
				button[i][j].setBorder(border);
				this.add(button[i][j]);
			}
		}
		
		this.setVisible(true);
		
		while (true) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					button[i][j].addActionListener(this);
				}
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (e.getSource() == button[i][j]) {
					for (int m = 0; m < 8; m++) {
						for (int n = 0; n < 8; n++) {
							if(chessboard.square(i, j).getpiece().canMove(chessboard, chessboard.square(i, j), chessboard.square(m, n))) {
								System.out.printf("%d;%d can move to %d;%d\n", i, j, m, n);
								Color oldcolor = button[m][n].getBackground();
								button[m][n].setBackground(new Color(8, 128, 80));
								if (e.getSource() == button[m][n]) {
									button[m][n].setBackground(oldcolor);
								}
							}
//							else {
//								System.out.printf("%d;%d can not move to %d;%d\n", i, j, m, n);
//							}
						}
					}
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent[] e) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (e[0].getSource() == button[i][j]) {
					for (int m = 0; m < 8; m++) {
						for (int n = 0; n < 8; n++) {
							if(chessboard.square(i, j).getpiece().canMove(chessboard, chessboard.square(i, j), chessboard.square(m, n))) {
								System.out.printf("%d;%d can move to %d;%d\n", i, j, m, n);
								Color oldcolor = button[m][n].getBackground();
								button[m][n].setBackground(new Color(8, 128, 80));
								if (e[1].getSource() == button[m][n]) {
									button[m][n].setBackground(oldcolor);
								}
							}
//							else {
//								System.out.printf("%d;%d can not move to %d;%d\n", i, j, m, n);
//							}
						}
					}
				}
			}
		}
	}

}
