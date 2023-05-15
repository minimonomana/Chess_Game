package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ChessTimerGUI extends JFrame implements ActionListener {
    private int timer1;
    private int timer2;
    private int startTime1;
    private int endTime1;
    private int startTime2;
    private int endTime2;
    private boolean isTimerRunning;
    private boolean isTimer1Active;
    private boolean isTimer2Active;
    private JLabel labelTimer1;
    private JLabel labelTimer2;
    private Timer timer1Obj;
    private Timer timer2Obj;

    public ChessTimerGUI() {
        super("Chess Timer");

        /* Set timer values as game mode
        - Ultrabullet: 15
        - Bullet: 60
        - Blitz: 300
        - Rapid: 900
        - Traditional: 3600
         */

        Scanner scanner = new Scanner(System.in);
        System.out.println("Which game mode would you like to play? (Ultrabullet, Bullet, Blitz, Rapid, Traditional)");
        String mode = scanner.next();

        switch (mode) {
            case "Ultrabullet":
                timer1 = timer2 = 15;
                break;
            case "Bullet":
                timer1 = timer2 = 60;
                break;
            case "Blitz":
                timer1 = timer2 = 300;
                break;
            case "Rapid":
                timer1 = timer2 = 900;
                break;
            case "Traditional":
                timer1 = timer2 = 3600;
                break;
        }

        isTimerRunning = false;
        isTimer1Active = true;
        isTimer2Active = false;

        // Create labels for timer display
        labelTimer1 = new JLabel("Player 1: " + formatTime(timer1));
        labelTimer2 = new JLabel("Player 2: " + formatTime(timer2));

        // Create start/stop button
        JButton button = new JButton("Start/Stop");
        button.addActionListener(this);

        // Add components to the frame
        JPanel panel = new JPanel(new GridLayout(3, 0));
        panel.add(labelTimer1);
        panel.add(labelTimer2);
        panel.add(button);
        add(panel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start/Stop")) {
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
    }

    private String formatTime(int time) {
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private class TimerAction implements ActionListener {
        private int time;
        private JLabel label;

        public TimerAction(int time, JLabel label) {
            this.time = time;
            this.label = label;
        }

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
                JOptionPane.showMessageDialog(null, isTimer1Active ? "Player 2 wins!" : "Player 1 wins!");
            }
        }
    }

    public static void main(String[] args) {
        new ChessTimerGUI();
    }

    // setLookAndFeel()
}