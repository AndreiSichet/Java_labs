package lab10_hw;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
class myFrame extends Frame implements WindowListener{
	myFrame(String title){
		super(title);
		add(new myCanvas());
		addWindowListener(this);
		setSize(800,500);
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
class myCanvas extends Canvas{
	@Override
	public void paint(Graphics g) {
		int w=getWidth();
		int h=getHeight();
		Color[] colors= {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN};
		int colorW=w/colors.length;
		for(int i=0;i<colors.length;i++) {
			g.setColor(colors[i]);
			g.fillRect(i*colorW, 0, colorW, h/2);
		}
		int greyW=w/10;
		for(int i=0;i<10;i++) {
			int grey=i*25;
			g.setColor(new Color(grey,grey,grey));
			g.fillRect(i*greyW, h/2, greyW, h/2);
		}
	}
}
public class sichet_andrei_lab10_prb4{
	public static void main(String[] args) {
		new myFrame("lab10_prb4");

	}

}
