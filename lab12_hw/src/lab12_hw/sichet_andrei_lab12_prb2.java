package lab12_hw;
import java.awt.*;
import java.awt.event.*;
public class sichet_andrei_lab12_prb2 extends Frame implements AdjustmentListener {
    // Scrollbars for RGB
    Scrollbar redSlider;
    Scrollbar greenSlider;
    Scrollbar blueSlider;
    // Canvas for drawing the square
    ColorCanvas canvas;
    public sichet_andrei_lab12_prb2() {
        // Frame settings
        setTitle("RGB Square Color");
        setSize(500, 400);
        setLayout(new BorderLayout());
        // PANEL FOR SLIDERS
        Panel sliderPanel = new Panel();
        sliderPanel.setLayout(new GridLayout(3, 2, 10, 10));

        // Red slider
        sliderPanel.add(new Label("Red:"));
        redSlider = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
        redSlider.addAdjustmentListener(this);
        sliderPanel.add(redSlider);

        // Green slider
        sliderPanel.add(new Label("Green:"));
        greenSlider = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
        greenSlider.addAdjustmentListener(this);
        sliderPanel.add(greenSlider);

        // Blue slider
        sliderPanel.add(new Label("Blue:"));
        blueSlider = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
        blueSlider.addAdjustmentListener(this);
        sliderPanel.add(blueSlider);

        add(sliderPanel, BorderLayout.NORTH);
        
        // CANVAS
        
        canvas = new ColorCanvas();
        add(canvas, BorderLayout.CENTER);

        // Window close event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        // Get RGB values
        int r = redSlider.getValue();
        int g = greenSlider.getValue();
        int b = blueSlider.getValue();
        // Update canvas color
        canvas.setColor(new Color(r, g, b));
    }
    // CUSTOM CANVAS CLASS
    class ColorCanvas extends Canvas {
        private Color squareColor = Color.BLACK;
        public void setColor(Color c) {
            squareColor = c;
            repaint();
        }
        @Override
        public void paint(Graphics g) {
            // Set selected color
            g.setColor(squareColor);
            // Draw 100x100 square
            g.fillRect(150, 80, 100, 100);
            // Draw border
            g.setColor(Color.BLACK);
            g.drawRect(150, 80, 100, 100);
        }
    }
    public static void main(String[] args) {

        new sichet_andrei_lab12_prb2();
    }
}
