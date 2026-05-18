package lab12_hw;
import java.awt.*;
import java.awt.event.*;
public class sichet_andrei_lab12_prb4 extends Frame
        implements ActionListener, Runnable {
    // Control button
    Button motionControlButton;
    // Drawing canvas
    AnimationSurface movingCircleCanvas;
    // Thread
    Thread animationWorker;
    // Animation state
    boolean animationIsRunning = false;
    // Circle coordinates
    int circleAxisX = 20;
    int circleAxisY = 20;
    // Movement speed
    int horizontalDirection = 4;
    int verticalDirection = 4;
    // Circle size
    final int CIRCLE_DIAMETER = 60;
    public sichet_andrei_lab12_prb4() {
        // Frame settings
        setTitle("Moving Circle Animation");
        setSize(700, 500);
        setLayout(new BorderLayout());
        // TOP BUTTON PANEL
        Panel commandArea = new Panel();
        motionControlButton = new Button("Start");
        motionControlButton.setFont(new Font("Arial", Font.BOLD, 18));
        motionControlButton.addActionListener(this);
        commandArea.add(motionControlButton);
        add(commandArea, BorderLayout.NORTH);
        // CANVAS AREA
        movingCircleCanvas = new AnimationSurface();
        add(movingCircleCanvas, BorderLayout.CENTER);
        // WINDOW CLOSE EVENT
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                animationIsRunning = false;
                dispose();
            }
        });
        setVisible(true);
    }
    // BUTTON ACTION
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!animationIsRunning) {
            animationIsRunning = true;
            motionControlButton.setLabel("Stop");
            animationWorker = new Thread(this);
            animationWorker.start();
        } else {
            animationIsRunning = false;
            motionControlButton.setLabel("Start");
        }
    }
    // THREAD LOGIC
    @Override
    public void run() {
        while (animationIsRunning) {
            // Move circle
            circleAxisX += horizontalDirection;
            circleAxisY += verticalDirection;
            // Canvas dimensions
            int canvasWidth =movingCircleCanvas.getWidth();
            int canvasHeight =movingCircleCanvas.getHeight();
            // Left / Right collision
            if (circleAxisX <= 0|| circleAxisX + CIRCLE_DIAMETER >= canvasWidth) {
                horizontalDirection =-horizontalDirection;
            }
            // Top / Bottom collision
            if (circleAxisY <= 0|| circleAxisY + CIRCLE_DIAMETER >= canvasHeight) {
                verticalDirection =-verticalDirection;
            }
            // Redraw canvas
            movingCircleCanvas.repaint();
            // Animation delay
            try {
                Thread.sleep(20);
            } catch (InterruptedException interruptedError) {
                interruptedError.printStackTrace();
            }
        }
    }
    // CUSTOM CANVAS
    class AnimationSurface extends Canvas {
        @Override
        public void paint(Graphics drawingTool) {
            drawingTool.setColor(Color.BLUE);
            drawingTool.fillOval(
                    circleAxisX,
                    circleAxisY,
                    CIRCLE_DIAMETER,
                    CIRCLE_DIAMETER
            );
            drawingTool.setColor(Color.BLACK);
            drawingTool.drawOval(
                    circleAxisX,
                    circleAxisY,
                    CIRCLE_DIAMETER,
                    CIRCLE_DIAMETER
            );
        }
    }
    public static void main(String[] args) {
        new sichet_andrei_lab12_prb4();
    }
}