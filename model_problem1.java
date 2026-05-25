package test;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class MyDrawCanvas extends Canvas {
    private ArrayList<int[]> positions = new ArrayList<>();
    private ArrayList<Character> chars = new ArrayList<>();
    private Random rnd = new Random();

    MyDrawCanvas() {
        setBackground(Color.white);
    }

    public void addChar(char c) {
        int x = 20 + rnd.nextInt(getWidth()  - 40);
        int y = 20 + rnd.nextInt(getHeight() - 40);
        positions.add(new int[]{x, y});
        chars.add(c);
    }

    public void clear() {
        positions.clear();
        chars.clear();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(new Font("Arial", Font.BOLD, 20));
        for (int i = 0; i < chars.size(); i++) {
            g.setColor(new Color(rnd.nextInt(200), rnd.nextInt(200), rnd.nextInt(200)));
            g.drawString(String.valueOf(chars.get(i)), positions.get(i)[0], positions.get(i)[1]);
        }
    }
}

public class model_problem1 extends Frame {

    private TextField inputField;
    private MyDrawCanvas canvas;
    private Button saveBtn;
    private Button clearBtn;
    private Label statusLabel;

    model_problem1() {
        super("Exam Application");
        setSize(600, 500);
        setLayout(new BorderLayout());

        // ── CONTROLS (GridLayout) ──────────────────────────────
        Panel controls = new Panel(new GridLayout(3, 2, 10, 10));

        inputField  = new TextField();
        saveBtn     = new Button("Save to file");
        clearBtn    = new Button("Clear");
        statusLabel = new Label("Ready");

        controls.add(new Label("Input:", Label.RIGHT)); controls.add(inputField);
        controls.add(saveBtn);                          controls.add(clearBtn);
        controls.add(new Label("Status:", Label.RIGHT));controls.add(statusLabel);

        // ── CANVAS ────────────────────────────────────────────
        canvas = new MyDrawCanvas();

        add(controls, BorderLayout.NORTH);
        add(canvas,   BorderLayout.CENTER);

        // ── EVENTS ────────────────────────────────────────────
        inputField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                canvas.addChar(c);
                canvas.repaint();
            }
        });

        saveBtn.addActionListener(e -> {
            String text = inputField.getText();
            try {
                PrintWriter pw = new PrintWriter(new FileWriter("date.txt"));
                pw.println(text);
                pw.close();
                statusLabel.setText("Saved to date.txt");
            } catch (IOException ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

        clearBtn.addActionListener(e -> {
            inputField.setText("");
            canvas.clear();
            canvas.repaint();
            statusLabel.setText("Cleared");
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });

        setVisible(true);
    }

    public static void main(String[] args) { new model_problem1(); }
}