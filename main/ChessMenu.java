package main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessMenu extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    int mode;
    String timeMode;
    
    public ChessMenu() {
        setTitle("LỳChess");
        setResizable(false);
        setSize(1200, 836);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the main panel
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // Create sub-panels 
        JPanel menuPanel = createSubPanel("menuPanel", "gamePanel", "game3Panel", "game2Panel","game4Panel");
        JPanel gamePanel = createSubPanel("gamePanel", "menuPanel");
        JPanel recordsPanel = createSubPanel("game3Panel", "menuPanel");
        JPanel modePanel = createSubPanel("game2Panel", "menuPanel");
        JPanel settingsPanel = createSubPanel("game4Panel", "menuPanel");

        // Add the sub-panels to the main panel
        mainPanel.add(menuPanel, "menuPanel");
        mainPanel.add(gamePanel, "gamePanel");
        mainPanel.add(recordsPanel, "game3Panel");
        mainPanel.add(modePanel, "game2Panel");
        mainPanel.add(settingsPanel, "game4Panel");

        // Set the main panel as the content pane
        setContentPane(mainPanel);

        // Show the main panel
        cardLayout.show(mainPanel, "menuPanel");
    }
    
    private void setButton(JButton button, String path) {
        // Create an ImageIcon with the texture image
        ImageIcon buttonTexture = new ImageIcon(path);
        // Get the button's size
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();
        // Scale the texture image to match the button's size
        Image scaledTexture = buttonTexture.getImage().getScaledInstance(
                buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        // Create a new ImageIcon with the scaled texture image
        ImageIcon scaledButtonTexture = new ImageIcon(scaledTexture);
        // Set the button's icon to the scaled texture
        button.setIcon(scaledButtonTexture);

        // Adjust the position of the icon to align with the right end of the button
        button.setHorizontalTextPosition(SwingConstants.LEADING);
        button.setHorizontalAlignment(SwingConstants.TRAILING);

        // Adjust the margin to move the text and icon horizontally
        int horizontalMargin = buttonWidth - scaledButtonTexture.getIconWidth();
        button.setMargin(new Insets(0, horizontalMargin, 0, 0));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(button.getBackground());
        button.setBorder(null);
    }

    private JPanel createSubPanel(String name, String... buttons) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a layered pane to hold the components
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        // Add the background image to the layered pane
        ImageIcon backgroundImage = null;
        backgroundImage = new ImageIcon("resources\\menu.png");
        if(name.equals("menuPanel")) {
        	backgroundImage = new ImageIcon("resources\\menu.png");
        }
        else if(name.equals("gamePanel")) {
            backgroundImage = new ImageIcon("resources\\game.png");
            // Create text fields
            textField1 = new JTextField(80);
            textField2 = new JTextField(80);
            // Set font size for text fields
            Font textFieldFont = textField1.getFont().deriveFont(60f); // Specify the desired font size
            textField1.setFont(textFieldFont);
            textField2.setFont(textFieldFont);
            // Set text fields' bounds
            textField1.setBounds(550, 200, 600, 120);
            textField2.setBounds(550, 400, 600, 120);
            panel.add(textField1);
            panel.add(textField2);
        }
        else if(name.equals("game2Panel")) {
            backgroundImage = new ImageIcon("resources\\game.png");
            // Create text fields
            textField3 = new JTextField(80);
            textField4 = new JTextField(80);
            // Set font size for text fields
            Font textFieldFont = textField3.getFont().deriveFont(60f); // Specify the desired font size
            textField3.setFont(textFieldFont);
            textField4.setFont(textFieldFont);
            // Set text fields' bounds
            textField3.setBounds(550, 200, 600, 120);
            textField4.setBounds(550, 400, 600, 120);
            panel.add(textField3);
            panel.add(textField4);
        }
        else if(name.equals("game3Panel")) {
            backgroundImage = new ImageIcon("resources\\game.png");
            // Create text fields
            textField5 = new JTextField(80);
            textField6 = new JTextField(80);
            // Set font size for text fields
            Font textFieldFont = textField5.getFont().deriveFont(60f); // Specify the desired font size
            textField5.setFont(textFieldFont);
            textField6.setFont(textFieldFont);
            // Set text fields' bounds
            textField5.setBounds(550, 200, 600, 120);
            textField6.setBounds(550, 400, 600, 120);
            panel.add(textField5);
            panel.add(textField6);
        }
        else if(name.equals("game4Panel")) {
            backgroundImage = new ImageIcon("resources\\game.png");
            // Create text fields
            textField7 = new JTextField(80);
            textField8 = new JTextField(80);
            // Set font size for text fields
            Font textFieldFont = textField7.getFont().deriveFont(60f); // Specify the desired font size
            textField7.setFont(textFieldFont);
            textField8.setFont(textFieldFont);
            // Set text fields' bounds
            textField7.setBounds(550, 200, 600, 120);
            textField8.setBounds(550, 400, 600, 120);
            panel.add(textField7);
            panel.add(textField8);
        }

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        panel.add(backgroundLabel, BorderLayout.CENTER);
        
        JLabel label = new JLabel(name);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null); 

        // Create buttons and set their sizes and positions
        JButton[] menuButtons = new JButton[buttons.length];
        int buttonY = 28;
        String[] pathToButtonTexture = {
                "resources\\newgamebutton.png",
                "resources\\modesbutton.png",
                "resources\\recordsbuttom.png",
                "resources\\settingsbutton.png",
                "resources\\backbutton.png",
                "resources\\bullet.png",
                "resources\\blizt.png",
                "resources\\rapid.png"
        };

        if(name.equals("menuPanel")) {
        	
            for (int i = 0; i < buttons.length; i++) {
                menuButtons[i] = new JButton(buttons[i]);
                menuButtons[i].addActionListener(this);
                menuButtons[i].setBounds(85, buttonY, 500, 144);
                buttonY += 190;

                setButton(menuButtons[i], pathToButtonTexture[i]);
                buttonPanel.add(menuButtons[i]);
            }        
        }else {
	        for (int i = 0; i < buttons.length; i++) {
	            menuButtons[i] = new JButton(buttons[i]);
	            menuButtons[i].addActionListener(this);
	            menuButtons[i].setBounds(920, 10, 200, 90);

                setButton(menuButtons[i], pathToButtonTexture[4]);
	            buttonPanel.add(menuButtons[i]);
	        }
	        
	        JButton bullet = new JButton("bullet");
	        bullet.addActionListener(this);
	        bullet.setBounds(43, 560, 335, 150);
            setButton(bullet, pathToButtonTexture[5]);
	        buttonPanel.add(bullet);
	        
	        JButton blizt = new JButton("blizt");
	        blizt.addActionListener(this);
	        blizt.setBounds(417, 560, 335, 150);
            setButton(blizt, pathToButtonTexture[6]);
	        buttonPanel.add(blizt);
	        
	        JButton rapid = new JButton("rapid");
	        rapid.addActionListener(this);
	        rapid.setBounds(791, 560, 335, 150);
            setButton(rapid, pathToButtonTexture[7]);
	        buttonPanel.add(rapid);
        }
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command != null && !command.isEmpty()) {
            cardLayout.show(mainPanel, command);
        }
        if (command.equals("gamePanel")) {
        	mode = 0;
        	System.out.println("Classic!");        	
        }
        if (command.equals("game2Panel")) {
        	mode = 3;
        	System.out.println("Blindfold!");
        }
        if (command.equals("game3Panel")) {

        	mode = 1;
        	System.out.println("Randomized!");

        }
        if (command.equals("game4Panel")) {
        	mode = 2;
        	System.out.println("Advanced Blindfold!");

        }
        if (command.equals("bullet")||command.equals("blizt")||command.equals("rapid")) {
        	String text1 = "Black";
        	String text2 = "White";
        	if (mode == 0) {
                text1 = textField1.getText();
                text2 = textField2.getText();
        	}
        	if (mode == 3) {
                text1 = textField3.getText();
                text2 = textField4.getText();
        	}
        	if (mode == 1) {
                text1 = textField5.getText();
                text2 = textField6.getText();
        	}
        	if (mode == 2) {
                text1 = textField7.getText();
                text2 = textField8.getText();
        	}

            System.out.println("Player 1: " + text1);
            System.out.println("Player 2: " + text2);
            if (text1.equals("")) text1 = "Black";
            if (text2.equals("")) text2 = "White";


            System.out.println("Game mode: " + command);
            Main chessGame = new Main();
            chessGame.startGame(mode, command, text1, text2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	ChessMenu example = new ChessMenu();
            example.setVisible(true);
        });
    }
}
