package main;

import javax.swing.*;
//import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

public class ChessTimerGUI extends JPanel {
    static int timer1; // time for each player's timer
    static int timer2;
    static int startTime1; // start and end time of each player's turn
    static int endTime1;
    static int startTime2;
    static int endTime2;
    static boolean isTimerRunning; // indicates whether the timer is currently running or not
    static boolean isTimer1Active; // indicates which player's timer is currently active
    static boolean isTimer2Active;
    static JLabel labelTimer1; // JLabel objects to display the timer values for each player
    static JLabel labelTimer2;
    static Timer timer1Obj; // Timer objects to control the timing functionality
    static Timer timer2Obj;
    Board board;

    public ChessTimerGUI(Board board) {

        /* Set timer values as game mode
        - Ultrabullet: 15
        - Bullet: 60
        - Blitz: 300
        - Rapid: 900
        - Traditional: 3600
         */

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Which game mode would you like to play? (Ultrabullet, Bullet, Blitz, Rapid, Traditional)");
//        String mode = scanner.next();
//
//        switch (mode) {
//            case "Ultrabullet":
//                timer1 = timer2 = 15;
//                break;
//            case "Bullet":
//                timer1 = timer2 = 60;
//                break;
//            case "Blitz":
//                timer1 = timer2 = 300; +-5
//                break;
//            case "Rapid":
//                timer1 = timer2 = 900;
//                break;
//            case "Traditional":
//                timer1 = timer2 = 3600;
//                break;
//            default:
//                timer1 = scanner.nextInt();
//                timer2 = scanner.nextInt();
//                break;
//        }

        timer1 = 10;
        timer2 = 10;
        this.board = board;

        isTimerRunning = false;
        isTimer1Active = false;
        isTimer2Active = true;

        // Create labels for timer display
        labelTimer1 = new JLabel(formatTime(timer1));
        labelTimer1.setFont(new Font("Arial", Font.PLAIN, 20));
        labelTimer1.setOpaque(true);
        labelTimer1.setBackground(Color.black);
        labelTimer1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        labelTimer2 = new JLabel(formatTime(timer2));
        labelTimer2.setFont(new Font("Arial", Font.PLAIN, 20));
        labelTimer2.setOpaque(true);
        labelTimer2.setBackground(Color.white);
        labelTimer2.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        labelTimer2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // Set layout and Add components to the panel
        setLayout(new GridLayout(2, 1));
        add(labelTimer1);
        add(labelTimer2);

        // Set panel properties
        // setPreferredSize() thay vi setSize() de size linh hoat hon
        setPreferredSize(new Dimension(150, 300));
        setVisible(true);
    }

    // Automatically start Timer2 at the start of the game
    public void startGame() {
        isTimerRunning = true;
        timer2Obj = new Timer(1000, new TimerAction(timer2, labelTimer2));
        startTime2 = (int) System.currentTimeMillis() / 1000;
        timer2Obj.start();
    }

    // Formats the given time in minutes and seconds
    private String formatTime(int time) {
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    // Handles the action performed when a move is made.
    // Starts or stops the timers based on the current player's turn.
    public void changeTimer(boolean changed) {
        // Start or stop timer for current player
        // if (isTimerRunning)
        if (changed) {
            if (isTimer1Active) {
                endTime1 = (int) System.currentTimeMillis() / 1000;
                timer1Obj.stop();
                timer1 = timer1 - (endTime1 - startTime1);
                //System.out.println("Player 1-2 time remaining: " + timer1 + "-" + timer2);
                isTimer1Active = false;
                isTimer2Active = true;
            } else {
                endTime2 = (int) System.currentTimeMillis() / 1000;
                timer2Obj.stop();
                timer2 = timer2 - (endTime2 - startTime2);
                //System.out.println("Player 1-2 time remaining: " + timer1 + "-" + timer2);
                isTimer2Active = false;
                isTimer1Active = true;
            }
            isTimerRunning = false;
        }

        if (isTimer1Active) {
            timer1Obj = new Timer(1000, new TimerAction(timer1, labelTimer1));
            startTime1 = (int) System.currentTimeMillis() / 1000;
            timer1Obj.start();
        } else {
            timer2Obj = new Timer(1000, new TimerAction(timer2, labelTimer2));
            startTime2 = (int) System.currentTimeMillis() / 1000;
            timer2Obj.start();
        }

        isTimerRunning = true;
    }

    // Inner class implementing ActionListener interface
    // Update the timer label and check if player's time has run out
    private class TimerAction implements ActionListener {
        private int time;
        private JLabel label;

        public TimerAction(int time, JLabel label) {
            this.time = time;
            this.label = label;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            time--;
//            label.setText(isTimer1Active ? "Player 1: " + formatTime(time) : "Player 2: " + formatTime(time));
            label.setText(formatTime(time));
            // System.out.println("Time in actionPerformed(): " + time);
            // System.out.println("isTimer1Active-isTimer2Active: " + isTimer1Active + "-" + isTimer2Active);
            if (time <= 0) {
                // Player's time has run out
                if (isTimer1Active) {
                    timer1Obj.stop();
                    // board.status = GameStatus.BLACK_WIN;
                } else {
                    timer2Obj.stop();
                    // board.status = GameStatus.WHITE_WIN;
                }
                isTimerRunning = false;
                // Display a message dialog when a player's time runs out, indicating the winner of the game.
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), isTimer1Active ? "Player 2 wins!" : "Player 1 wins!");
            }
        }
    }

//    public static void main(String[] args) {
//        new ChessTimerGUI();
//    }

    // setLookAndFeel()
}