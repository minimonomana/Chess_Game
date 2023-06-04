package main;

import javax.swing.*;
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
    static String name1 = "Black";
    static String name2 = "White";

    static Board board;
    static boolean canStart;
    JPanel panel;
    ImageIcon bg;
    JLabel lb;
    
    public ChessTimerGUI(Board board, String timeMode, String t1, String t2) {
        ChessTimerGUI.board = board;
    	if (!t1.equals("")) name1 = t1;
    	if (!t2.equals("")) name2 = t2;
        if (timeMode.equals("bullet")) {
            timer1 = timer2 = 60;
        }
        if (timeMode.equals("blizt")) {
            timer1 = timer2 = 180;
        }
        if (timeMode.equals("rapid")) {
            timer1 = timer2 = 600;
        }

        isTimerRunning = false;
        isTimer1Active = true;
        isTimer2Active = false;
        canStart = false;

        // Set layout for this panel
        setLayout(new BorderLayout());

        // Create and set layout for main panel
        bg = new ImageIcon("resources\\extrabackground.png");
        this.panel = new JPanel();
        lb = new JLabel(bg);
        lb.setPreferredSize(new Dimension(545, 200));
        this.add(lb);
        panel.setLayout(new GridBagLayout());

        // Create labels for timer display
        labelTimer1 = new JLabel(name1 + ":\n " + formatTime(timer1));
        labelTimer1.setFont(new Font("Arial", Font.BOLD, 60));
        labelTimer1.setOpaque(true);
        labelTimer1.setBackground(Color.BLACK);
        labelTimer1.setForeground(Color.WHITE);

        labelTimer2 = new JLabel(name2 + ":\n " + formatTime(timer2));
        labelTimer2.setFont(new Font("Arial", Font.BOLD, 60));
        labelTimer2.setOpaque(true);
        labelTimer2.setBackground(Color.BLACK);
        labelTimer2.setForeground(Color.WHITE);

        // Create a panel to hold the background image
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BorderLayout());

        // Create timers panel
        JPanel timersPanel = new JPanel();
        timersPanel.setLayout(new GridLayout(2, 1, 0, 100));
        //timersPanel.setBackground(Color.BLACK);
        timersPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        timersPanel.add(labelTimer1);
        timersPanel.add(labelTimer2);

        // Add panels
        backgroundPanel.add(timersPanel, BorderLayout.CENTER);
        panel.add(timersPanel);
        this.add(panel, BorderLayout.CENTER);

        // Set panel properties
        this.setPreferredSize(new Dimension(545, 200));
        this.setBackground(new Color(232, 103, 103)); // Set the background color to red

    }

    // Automatically start Timer2 at the start of the game
    public void startGame() {
        if (canStart) {
            isTimerRunning = true;
            timer1Obj = new Timer(1000, new TimerAction(timer1, labelTimer1));
            startTime1 = (int) System.currentTimeMillis() / 1000;
            timer1Obj.start();
        }
    }
 
    private String formatTime(int time) {
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public void stopTimer(){
        isTimer1Active = false;
        isTimer2Active = false;
        timer1Obj.stop();
        timer2Obj.stop();
    }

    public void changeTimer(boolean changed) {
        if (changed) {
            if (isTimer1Active) {
                endTime1 = (int) System.currentTimeMillis() / 1000;
                timer1Obj.stop();
                timer1 = timer1 - (endTime1 - startTime1);
                isTimer1Active = false;
                isTimer2Active = true;
            } else if (isTimer2Active){
                endTime2 = (int) System.currentTimeMillis() / 1000;
                timer2Obj.stop();
                timer2 = timer2 - (endTime2 - startTime2);
                isTimer2Active = false;
                isTimer1Active = true;
            }
            isTimerRunning = false;
        }

        if (isTimer1Active) {
            timer1Obj = new Timer(1000, new TimerAction(timer1, labelTimer1));
            startTime1 = (int) System.currentTimeMillis() / 1000;
            timer1Obj.start();
        } else if (isTimer2Active){
            timer2Obj = new Timer(1000, new TimerAction(timer2, labelTimer2));
            startTime2 = (int) System.currentTimeMillis() / 1000;
            timer2Obj.start();
        }

        isTimerRunning = true;
    }

    private class TimerAction implements ActionListener {
        private int time;
        private JLabel label;

        public TimerAction(int time, JLabel label) {
            this.time = time;
            this.label = label;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (canStart) {
                time--;
                label.setText(isTimer1Active ? name1 +": \n" + formatTime(time) : name2 +": \n" + formatTime(time));

                // Set red background if player has less than 30 seconds less
                if (time < 30) {
                    label.setBackground(new Color(232, 103, 103));
                }

                if (time <= 0) {
                    // Player's time has run out
                    if (isTimer1Active) {
                        new EndGameScreen(ChessTimerGUI.board.p1.turn(), GameStatus.WHITE_WIN, name1, name2);
                        timer1Obj.stop();
                    } else if (isTimer2Active){
                        new EndGameScreen(ChessTimerGUI.board.p2.turn(), GameStatus.BLACK_WIN, name1, name2);
                        timer2Obj.stop();
                    }
                    isTimerRunning = false;
                }
            }
        }
    }
}
