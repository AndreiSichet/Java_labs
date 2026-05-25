package exam_prep;
import java.awt.*;
import java.awt.event.*;

class TempCanvas extends Canvas {
    private double celsius = 0;
    private boolean hasValue = false;

    TempCanvas() {
        setBackground(Color.white);
    }

    public void update(double celsius) {
        this.celsius = celsius;
        this.hasValue = true;
    }

    public void reset() {
        hasValue = false;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (!hasValue) {
            g.setColor(Color.black);
            g.drawString("Enter a value to see the thermometer", 10, getHeight() / 2);
            return;
        }

        double clamped   = Math.max(-50, Math.min(150, celsius));
        double range     = 200.0;
        double ratio     = (clamped + 50) / range;

        int barMaxHeight = getHeight() - 40;
        int barHeight    = (int)(ratio * barMaxHeight);
        int barWidth     = 40;
        int barX         = getWidth() / 2 - barWidth / 2;
        int barY         = 20 + (barMaxHeight - barHeight);

        if      (celsius < 0)   g.setColor(Color.blue);
        else if (celsius <= 37) g.setColor(Color.green);
        else                    g.setColor(Color.red);

        g.fillRect(barX, barY, barWidth, barHeight);

        g.setColor(Color.black);
        g.drawRect(barX, 20, barWidth, barMaxHeight);

        int[] ticks = {-50, 0, 37, 100, 150};
        g.setFont(new Font("Arial", Font.PLAIN, 11));
        for (int tick : ticks) {
            double tickRatio = (tick + 50) / range;
            int tickY = 20 + barMaxHeight - (int)(tickRatio * barMaxHeight);
            g.drawLine(barX - 10, tickY, barX, tickY);
            g.drawString(tick + "°C", barX - 45, tickY + 4);
        }

        g.setFont(new Font("Arial", Font.BOLD, 13));
        g.setColor(Color.black);
        g.drawString(String.format("%.1f°C", celsius), barX, 20 + barMaxHeight + 20);
    }
}

public class temp_convert extends Frame {
    private TextField celsiusField;
    private TextField fahrenheitField;
    private Button    CtoF;
    private Button    FtoC;
    private Button    clear;
    private Label     errorLabel;
    private TempCanvas canvas;

    public temp_convert() {
        setTitle("Temperature Converter");
        setSize(500, 450);
        setLayout(new BorderLayout());

        Panel controls = new Panel(new GridLayout(4, 3, 10, 10));

        celsiusField    = new TextField();
        fahrenheitField = new TextField();
        CtoF            = new Button("Celsius to Fahrenheit");
        FtoC            = new Button("Fahrenheit to Celsius");
        clear           = new Button("Clear");
        errorLabel      = new Label("");
        errorLabel.setForeground(Color.red);

        controls.add(new Label("Celsius:",    Label.RIGHT)); controls.add(celsiusField);    controls.add(new Label(""));
        controls.add(new Label("Fahrenheit:", Label.RIGHT)); controls.add(fahrenheitField); controls.add(new Label(""));
        controls.add(CtoF);                                  controls.add(FtoC);            controls.add(clear);
        controls.add(new Label(""));                         controls.add(errorLabel);      controls.add(new Label(""));

        // KEY FILTERS 
        KeyAdapter decimalOnly = new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE
                        && c != '-' && c != '.') {
                    e.consume();
                }
            }
        };
        celsiusField.addKeyListener(decimalOnly);
        fahrenheitField.addKeyListener(decimalOnly);

        // C → F 
        CtoF.addActionListener(e -> {
            errorLabel.setText("");
            String text = celsiusField.getText().trim();
            if (text.isEmpty()) { errorLabel.setText("Enter a Celsius value!"); return; }
            double c2   = Double.parseDouble(text);
            double f    = c2 * 9.0 / 5.0 + 32;
            fahrenheitField.setText(String.format("%.2f", f));
            canvas.update(c2);   
            canvas.repaint();    
        });

        // F → C 
        FtoC.addActionListener(e -> {
            errorLabel.setText("");
            String text = fahrenheitField.getText().trim();
            if (text.isEmpty()) { errorLabel.setText("Enter a Fahrenheit value!"); return; }
            double f    = Double.parseDouble(text);
            double c2   = (f - 32) * 5.0 / 9.0;
            celsiusField.setText(String.format("%.2f", c2));
            canvas.update(c2);   
            canvas.repaint();    
        });

        // CLEAR 
        clear.addActionListener(e -> {
            celsiusField.setText("");
            fahrenheitField.setText("");
            errorLabel.setText("");
            canvas.reset();      
            canvas.repaint();
        });

        canvas = new TempCanvas();
        add(controls, BorderLayout.NORTH);
        add(canvas,   BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });

        setVisible(true);
    }

    public static void main(String[] args) { new temp_convert(); }
}