package lab10_hw;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
class MyCanva extends Canvas {
    @Override
    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height) - 50;
        int x = (width - diameter) / 2;
        int y = (height - diameter) / 2;
        for (int angle = 0; angle < 360; angle++) {
            float hue = angle / 360.0f;
            Color color = Color.getHSBColor(hue, 1.0f, 1.0f);
            g.setColor(color);
            g.fillArc(x, y, diameter, diameter,
                    angle, 1);
        }
    }
}
class MyFram extends Frame implements WindowListener {
    MyFram(String title) {
        super(title);
        add(new MyCanva());
        addWindowListener(this);
        setSize(600, 600);
        setVisible(true);
    }
    public void windowActivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
}
public class sichet_andrei_lab10_prb5 {
    public static void main(String[] args) {
        new MyFram("lab10_prb5");
    }
}