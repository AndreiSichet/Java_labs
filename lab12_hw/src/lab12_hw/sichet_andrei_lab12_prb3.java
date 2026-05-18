package lab12_hw;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class sichet_andrei_lab12_prb3 extends Frame implements ActionListener {
    // Drawing area
    ReactionCanvas reactionBoard;
    // Buttons
    Button scarletButton;
    Button obsidianButton;
    // Info label
    Label gameStatusLabel;
    // Random generator
    Random colorPickerEngine = new Random();
    // Current circle color
    Color displayedCircleColor;
    // Statistics
    int playedRoundsCounter = 0;
    int successfulClicksCounter = 0;
    long reactionStartMoment;
    long totalReactionDuration = 0;
    final int TOTAL_GAME_ROUNDS = 10;
    public sichet_andrei_lab12_prb3() {
        // Frame settings
        setTitle("Reaction Time Game");
        setSize(600, 450);
        setLayout(new BorderLayout());
        // TOP PANEL
        Panel upperInfoPanel = new Panel();
        gameStatusLabel = new Label(
                "Round 0 / 10 | Correct Answers: 0",
                Label.CENTER
        );
        gameStatusLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );
        upperInfoPanel.add(gameStatusLabel);
        add(upperInfoPanel, BorderLayout.NORTH);
        // CANVAS AREA
        reactionBoard = new ReactionCanvas();
        add(reactionBoard, BorderLayout.CENTER);
        // BUTTON PANEL
        Panel commandButtonPanel = new Panel();
        commandButtonPanel.setLayout(new GridLayout(1, 2, 20, 20));
        scarletButton = new Button("RED");
        obsidianButton = new Button("BLACK");
        scarletButton.setFont(new Font("Arial", Font.BOLD, 20));
        obsidianButton.setFont(new Font("Arial", Font.BOLD, 20));
        scarletButton.addActionListener(this);
        obsidianButton.addActionListener(this);
        commandButtonPanel.add(scarletButton);
        commandButtonPanel.add(obsidianButton);
        add(commandButtonPanel, BorderLayout.SOUTH);
        // Window close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        // Start first round
        generateNewCircle();
        setVisible(true);
    }
    // GENERATE RANDOM CIRCLE
    public void generateNewCircle() {
        boolean randomChoice =colorPickerEngine.nextBoolean();
        if (randomChoice) {
            displayedCircleColor = Color.RED;
        } else {
            displayedCircleColor = Color.BLACK;
        }
        reactionBoard.updateDisplayedColor(displayedCircleColor);
        reactionStartMoment =System.currentTimeMillis();
    }
    // BUTTON EVENTS
    @Override
    public void actionPerformed(ActionEvent e) {
        if (playedRoundsCounter >= TOTAL_GAME_ROUNDS) {
            return;
        }
        long clickTimestamp =System.currentTimeMillis();
        long playerReactionTime =clickTimestamp - reactionStartMoment;
        totalReactionDuration += playerReactionTime;
        String selectedAnswer =e.getActionCommand();
        boolean answerIsCorrect = false;
        if (selectedAnswer.equals("RED")&& displayedCircleColor == Color.RED) {
            answerIsCorrect = true;
        }
        if (selectedAnswer.equals("BLACK")&& displayedCircleColor == Color.BLACK) {
            answerIsCorrect = true;
        }
        if (answerIsCorrect) {
            successfulClicksCounter++;
        }
        playedRoundsCounter++;
        gameStatusLabel.setText(
                "Round "
                + playedRoundsCounter
                + " / 10 | Correct Answers: "
                + successfulClicksCounter
        );
        // End game
        if (playedRoundsCounter == TOTAL_GAME_ROUNDS) {
            double averageReactionSpeed =(double) totalReactionDuration/ TOTAL_GAME_ROUNDS;
            gameStatusLabel.setText(
                    "Game Over | Correct: "
                    + successfulClicksCounter
                    + " | Average Reaction: "
                    + averageReactionSpeed
                    + " ms"
            );
            scarletButton.setEnabled(false);
            obsidianButton.setEnabled(false);
            return;
        }
        generateNewCircle();
    }
    // CUSTOM CANVAS CLASS
    class ReactionCanvas extends Canvas {
        Color activeCircleShade = Color.RED;
        public void updateDisplayedColor(Color newShade) {
            activeCircleShade = newShade;
            repaint();
        }
        @Override
        public void paint(Graphics graphicsPen) {
            graphicsPen.setColor(activeCircleShade);
            graphicsPen.fillOval(200, 90, 150, 150);
            graphicsPen.setColor(Color.GRAY);
            graphicsPen.drawOval(200, 90, 150, 150);
        }
    }
    public static void main(String[] args) {
        new sichet_andrei_lab12_prb3();
    }
}