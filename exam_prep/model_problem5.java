package test;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class GaugeCanvas extends Canvas {
    private int target;
    private int lastGuess = -1;

    GaugeCanvas(int target) {
        this.target = target;
        setBackground(Color.lightGray);
    }

    public void update(int guess)      { this.lastGuess = guess; }
    public void reset(int newTarget)   { this.target = newTarget; lastGuess = -1; }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (lastGuess == -1) {
            g.setColor(Color.black);
            g.drawString("Make a guess to see the gauge", 10, getHeight() / 2 + 5);
            return;
        }
        double closeness = 1.0 - (Math.abs(lastGuess - target) / 100.0);
        int barWidth = (int)(closeness * getWidth());
        int red   = (int)(255 * (1 - closeness));
        int green = (int)(255 * closeness);
        g.setColor(new Color(red, green, 0));
        g.fillRect(0, 0, barWidth, getHeight());
        g.setColor(Color.black);
        g.drawString("Closeness: " + (int)(closeness * 100) + "%", 10, getHeight() / 2 + 5);
    }
}

public class model_problem5 extends Frame {
    private TextField guessField;
    private Button    guessBtn;
    private Button    playAgainBtn;
    private Label     feedbackLabel;
    private Label     attemptsLabel;
    private GaugeCanvas gauge;
    private int       target;
    private int       attempts;
    private Random    rnd;

    public model_problem5() {
        rnd     = new Random();
        target  = rnd.nextInt(100) + 1;
        attempts = 0;

        setTitle("Number Guessing Game");
        setSize(500, 350);
        setLayout(new BorderLayout());

        Panel controls = new Panel(new GridLayout(4, 2, 10, 10));

        guessField    = new TextField();
        guessBtn      = new Button("Guess");
        playAgainBtn  = new Button("Play Again");
        playAgainBtn.setEnabled(false);
        feedbackLabel = new Label("Make your first guess!");
        attemptsLabel = new Label("Attempts: 0");

        controls.add(new Label("Your guess (1-100):", Label.RIGHT)); controls.add(guessField);
        controls.add(new Label("Feedback:",           Label.RIGHT)); controls.add(feedbackLabel);
        controls.add(new Label("Attempts:",           Label.RIGHT)); controls.add(attemptsLabel);
        controls.add(guessBtn);                                      controls.add(playAgainBtn);

        gauge = new GaugeCanvas(target);

        add(controls, BorderLayout.NORTH);
        add(gauge,    BorderLayout.CENTER);

        guessField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) e.consume();
            }
        });

        guessBtn.addActionListener(e -> {
            String text = guessField.getText().trim();
            if (text.isEmpty()) { feedbackLabel.setText("Enter a number!"); return; }
            int guess = Integer.parseInt(text);
            if (guess < 1 || guess > 100) { feedbackLabel.setText("Must be between 1-100!"); return; }
            attempts++;
            attemptsLabel.setText("Attempts: " + attempts);
            gauge.update(guess);
            gauge.repaint();
            guessField.setText("");
            if      (guess < target) feedbackLabel.setText("Too low!");
            else if (guess > target) feedbackLabel.setText("Too high!");
            else {
                feedbackLabel.setText("Correct! Got it in " + attempts + " tries!");
                guessField.setEnabled(false);
                guessBtn.setEnabled(false);
                playAgainBtn.setEnabled(true);
            }
        });

        playAgainBtn.addActionListener(e -> {
            target   = rnd.nextInt(100) + 1;
            attempts = 0;
            gauge.reset(target);
            gauge.repaint();
            guessField.setText("");
            guessField.setEnabled(true);
            guessBtn.setEnabled(true);
            playAgainBtn.setEnabled(false);
            feedbackLabel.setText("Make your first guess!");
            attemptsLabel.setText("Attempts: 0");
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });

        setVisible(true);
    }

    public static void main(String[] args) { new model_problem5(); }
}