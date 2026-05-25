package lab12_hw;

import java.awt.*;
import java.awt.event.*;

public class sichet_andrei_lab12_prb6 extends Frame implements MouseMotionListener {

    MazeSurface labyrinthPanel;

    int playerX=40,playerY=40;
    final int CELL_SIZE=40;

    Rectangle[] wallSegments;
    Rectangle finishZone;

    boolean finished=false;

    public sichet_andrei_lab12_prb6() {

        setTitle("Maze Game");
        setSize(700,500);
        setLayout(new BorderLayout());

        labyrinthPanel=new MazeSurface();
        add(labyrinthPanel);

        labyrinthPanel.addMouseMotionListener(this);

        wallSegments=new Rectangle[]{
            new Rectangle(120,0,40,320),
            new Rectangle(240,120,40,320),
            new Rectangle(360,0,40,320),
            new Rectangle(480,120,40,320),
            new Rectangle(0,360,520,40)
        };

        finishZone=new Rectangle(560,400,40,40);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){dispose();}
        });

        setVisible(true);
    }

    void reset(){
        playerX=40;playerY=40;
        finished=false;
        labyrinthPanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e){

        if(finished)return;

        playerX=e.getX()-10;
        playerY=e.getY()-10;

        Rectangle p=new Rectangle(playerX,playerY,20,20);

        if(playerX<0||playerY<0||playerX>labyrinthPanel.getWidth()-20||playerY>labyrinthPanel.getHeight()-20){
            reset();return;
        }

        for(Rectangle w:wallSegments){
            if(p.intersects(w)){reset();return;}
        }

        if(p.intersects(finishZone)){
            finished=true;
        }

        labyrinthPanel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e){}

    class MazeSurface extends Canvas{

        public void paint(Graphics g){

            g.setColor(Color.BLACK);
            for(Rectangle r:wallSegments){
                g.fillRect(r.x,r.y,r.width,r.height);
            }

            g.setColor(Color.GREEN);
            g.fillRect(20,20,40,40);

            g.setColor(Color.RED);
            g.fillRect(finishZone.x,finishZone.y,finishZone.width,finishZone.height);

            g.setColor(Color.BLUE);
            g.fillRect(playerX,playerY,20,20);

            if(finished){
                g.setFont(new Font("Arial",Font.BOLD,28));
                g.setColor(Color.BLUE);
                g.drawString("MAZE COMPLETED",200,220);
            }
        }
    }

    public static void main(String[] args){
        new sichet_andrei_lab12_prb6();
    }
}