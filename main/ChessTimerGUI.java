package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

class MouseHandling extends MouseAdapter {

}
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

    public ChessTimerGUI() {

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
//                timer1 = timer2 = 300;
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
//
//        }

        timer1 = 300;
        timer2 = 300;

        isTimerRunning = false;
        isTimer1Active = true;
        isTimer2Active = false;

        // Create labels for timer display
        labelTimer1 = new JLabel("Player 1: " + formatTime(timer1));
        labelTimer2 = new JLabel("Player 2: " + formatTime(timer2));

        // Add components to the panel
        JPanel panel = new JPanel(new GridLayout(3, 0));
        panel.add(labelTimer1);
        panel.add(labelTimer2);

        // Set panel properties
        // setPreferredSize() thay vi setSize() de size linh hoat hon
        panel.setPreferredSize(new Dimension(150, 300));
        panel.setVisible(true);

        // Start Timer 2 at the start of the game
        isTimerRunning = true;
        timer2Active();

        addMouseListener(new MouseAdapter() {
            // Handles the action performed when the mouse is released.
            // Starts or stops the timers based on the current player's turn.
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("mouse released!");
                // Start or stop timer for current player
                if (isTimerRunning) {
                    if (isTimer1Active) {
                        endTime1 = (int) System.currentTimeMillis() / 1000;
                        timer1Obj.stop();
                        timer1 = timer1 - (endTime1 - startTime1);
                        System.out.println("Player 1 delay time: " + timer1Obj.getDelay());
                        System.out.println("Player 1 time remaining: " + timer1);
                        isTimer1Active = false;
                        isTimer2Active = true;
                    } else {
                        endTime2 = (int) System.currentTimeMillis() / 1000;
                        timer2Obj.stop();
                        timer2 = timer2 - (endTime2 - startTime2);
                        System.out.println("Player 2 time remaining: " + timer2);
                        isTimer2Active = false;
                        isTimer1Active = true;
                    }
                    isTimerRunning = false;
                }
            }
        });
    }

    public void timer2Active() {
        isTimer2Active = true;
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

    // Inner class implementing ActionListener interface
    // Update the timer label and check if player's time has run out
    private class TimerAction implements MouseListener, ActionListener {
        private int time;
        private JLabel label;

        public TimerAction(int time, JLabel label) {
            this.time = time;
            this.label = label;
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {
            time--;
            label.setText(isTimer1Active ? "Player 1: " + formatTime(time) : "Player 2: " + formatTime(time));
            if (time == 0) {
                // Player's time has run out
                if (isTimer1Active) {
                    timer1Obj.stop();
                } else {
                    timer2Obj.stop();
                }
                isTimerRunning = false;
                // Display a message dialog when a player's time runs out, indicating the winner of the game.
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), isTimer1Active ? "Player 2 wins!" : "Player 1 wins!");
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) { }

        @Override
        public void actionPerformed(ActionEvent e) {
            time--;
            label.setText(isTimer1Active ? "Player 1: " + formatTime(time) : "Player 2: " + formatTime(time));
            if (time == 0) {
                // Player's time has run out
                if (isTimer1Active) {
                    timer1Obj.stop();
                } else {
                    timer2Obj.stop();
                }
                isTimerRunning = false;
                // Display a message dialog when a player's time runs out, indicating the winner of the game.
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), isTimer1Active ? "Player 2 wins!" : "Player 1 wins!");
            }
        }
    }

    public static void main(String[] args) {
        new ChessTimerGUI();
    }

    // setLookAndFeel()
}
