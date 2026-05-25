package test;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class DrawCanvas extends Canvas {
    private ArrayList<int[]> shapes = new ArrayList<>();
    // each int[]: {x, y, size, shapeType, r, g, b}
    // shapeType: 0=circle, 1=rectangle, 2=line

    DrawCanvas() {
        setBackground(Color.white);
    }

    public void addShape(int x, int y, int size, int shapeType, Color color) {
        shapes.add(new int[]{x, y, size, shapeType,
                color.getRed(), color.getGreen(), color.getBlue()});
    }

    public void clear() {
        shapes.clear();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        for (int[] s : shapes) {
            g.setColor(new Color(s[4], s[5], s[6]));
            int x = s[0], y = s[1], size = s[2], type = s[3];
            if      (type == 0) g.fillOval(x - size/2, y - size/2, size, size);
            else if (type == 1) g.fillRect(x - size/2, y - size/2, size, size);
            else                g.drawLine(x - size/2, y, x + size/2, y);
        }
    }
}

public class model_problem4 extends Frame {
    private Choice shapeChoice;
    private Scrollbar sizeBar;
    private Label sizeLabel;
    private Button colorBtn;
    private Button clearBtn;
    private DrawCanvas canvas;
    private Color[] colors = {Color.red, Color.green, Color.blue, Color.black};
    private String[] colorNames = {"Red", "Green", "Blue", "Black"};
    private int colorIndex = 0;

    public model_problem4() {
        setTitle("Drawing Tool");
        setSize(600, 500);
        setLayout(new BorderLayout());

        Panel controls = new Panel(new GridLayout(3, 3, 10, 10));

        shapeChoice = new Choice();
        shapeChoice.add("Circle");
        shapeChoice.add("Rectangle");
        shapeChoice.add("Line");

        sizeBar   = new Scrollbar(Scrollbar.HORIZONTAL, 30, 1, 10, 101);
        sizeLabel = new Label("Size: 30");
        colorBtn  = new Button("Color: Red");
        clearBtn  = new Button("Clear");

        controls.add(new Label("Shape:", Label.RIGHT)); controls.add(shapeChoice);  controls.add(colorBtn);
        controls.add(new Label("Size:",  Label.RIGHT)); controls.add(sizeBar);      controls.add(sizeLabel);
        controls.add(new Label(""));                    controls.add(clearBtn);     controls.add(new Label(""));

        canvas = new DrawCanvas();

        add(controls, BorderLayout.NORTH);
        add(canvas,   BorderLayout.CENTER);

        sizeBar.addAdjustmentListener(e -> {
            sizeLabel.setText("Size: " + sizeBar.getValue());
        });

        colorBtn.addActionListener(e -> {
            colorIndex = (colorIndex + 1) % colors.length;
            colorBtn.setLabel("Color: " + colorNames[colorIndex]);
        });

        clearBtn.addActionListener(e -> {
            canvas.clear();
            canvas.repaint();
        });

        canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                canvas.addShape(
                    e.getX(), e.getY(),
                    sizeBar.getValue(),
                    shapeChoice.getSelectedIndex(),
                    colors[colorIndex]
                );
                canvas.repaint();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });

        setVisible(true);
    }

    public static void main(String[] args) { new model_problem4(); }
}