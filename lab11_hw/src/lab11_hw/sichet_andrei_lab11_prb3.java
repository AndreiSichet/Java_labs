package lab11_hw;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class sichet_andrei_lab11_prb3 extends JFrame {

    public sichet_andrei_lab11_prb3() {
        setTitle("Tabbed View Application");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane();
        
        tabbedPane.addTab("Panel 1", createPanel());
        tabbedPane.addTab("Panel 2", createPanel());
        tabbedPane.addTab("Panel 3", createPanel());

        add(tabbedPane);
        setVisible(true);
    }

    
    private JPanel createPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());    
        // TOP CONTROL PANEL     
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 2, 10, 10));

        // Font selection
        JLabel fontLabel = new JLabel("Font:");
        String[] fonts = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();

        JComboBox<String> fontBox = new JComboBox<>(fonts);

        // Font size
        JLabel sizeLabel = new JLabel("Font Size:");
        Integer[] fontSizes = {12, 16, 20, 24, 28, 32};
        JComboBox<Integer> sizeBox = new JComboBox<>(fontSizes);

        // Color selection
        JLabel colorLabel = new JLabel("Color:");
        String[] colors = {"Black", "Red", "Blue", "Green"};
        JComboBox<String> colorBox = new JComboBox<>(colors);

        // Shape selection
        JLabel shapeLabel = new JLabel("Shape:");
        String[] shapes = {"Circle", "Square", "Rectangle", "Triangle"};
        JComboBox<String> shapeBox = new JComboBox<>(shapes);

        // Shape size
        JLabel shapeSizeLabel = new JLabel("Shape Size:");
        JSlider shapeSizeSlider = new JSlider(20, 200, 80);

        // Add controls
        controlPanel.add(fontLabel);
        controlPanel.add(fontBox);

        controlPanel.add(sizeLabel);
        controlPanel.add(sizeBox);

        controlPanel.add(colorLabel);
        controlPanel.add(colorBox);

        controlPanel.add(shapeLabel);
        controlPanel.add(shapeBox);

        controlPanel.add(shapeSizeLabel);
        controlPanel.add(shapeSizeSlider);

        mainPanel.add(controlPanel, BorderLayout.NORTH);        
        // DRAWING AREA        
        DrawingPanel drawingPanel = new DrawingPanel();

        mainPanel.add(drawingPanel, BorderLayout.CENTER);
       
        // EVENTS       
        ActionListener updateListener = e -> {

            drawingPanel.setSelectedFont(
                    (String) fontBox.getSelectedItem()
            );

            drawingPanel.setFontSize(
                    (Integer) sizeBox.getSelectedItem()
            );

            drawingPanel.setSelectedColor(
                    (String) colorBox.getSelectedItem()
            );

            drawingPanel.setShape(
                    (String) shapeBox.getSelectedItem()
            );

            drawingPanel.repaint();
        };
        fontBox.addActionListener(updateListener);
        sizeBox.addActionListener(updateListener);
        colorBox.addActionListener(updateListener);
        shapeBox.addActionListener(updateListener);

        shapeSizeSlider.addChangeListener(e -> {
            drawingPanel.setShapeSize(shapeSizeSlider.getValue());
            drawingPanel.repaint();
        });

        return mainPanel;
    }

    
    // DRAWING PANEL CLASS
    
class DrawingPanel extends JPanel {

        private String selectedFont = "Arial";
        private int fontSize = 20;
        private String selectedColor = "Black";
        private String shape = "Circle";
        private int shapeSize = 80;

        public void setSelectedFont(String selectedFont) {
            this.selectedFont = selectedFont;
        }

        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }

        public void setSelectedColor(String selectedColor) {
            this.selectedColor = selectedColor;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public void setShapeSize(int shapeSize) {
            this.shapeSize = shapeSize;
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;

            // Set color
            switch (selectedColor) {
                case "Red":
                    g2.setColor(Color.RED);
                    break;

                case "Blue":
                    g2.setColor(Color.BLUE);
                    break;

                case "Green":
                    g2.setColor(Color.GREEN);
                    break;

                default:
                    g2.setColor(Color.BLACK);
            }

            // Set font
            g2.setFont(new Font(selectedFont, Font.PLAIN, fontSize));

            // Draw sample text
            g2.drawString("Sample Text", 50, 50);

            // Draw selected shape
            int x = 200;
            int y = 150;

            switch (shape) {

                case "Circle":
                    g2.drawOval(x, y, shapeSize, shapeSize);
                    break;

                case "Square":
                    g2.drawRect(x, y, shapeSize, shapeSize);
                    break;

                case "Rectangle":
                    g2.drawRect(x, y, shapeSize + 50, shapeSize);
                    break;

                case "Triangle":

                    int[] xPoints = {
                            x,
                            x + shapeSize / 2,
                            x + shapeSize
                    };

                    int[] yPoints = {
                            y + shapeSize,
                            y,
                            y + shapeSize
                    };

                    g2.drawPolygon(xPoints, yPoints, 3);
                    break;
            }
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new sichet_andrei_lab11_prb3();
        });
    }
}