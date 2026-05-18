package lab12_hw;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class sichet_andrei_lab12_prb7 extends Frame implements MouseListener {

    DiceBoard playZone;
    Random zarEngine=new Random();

    int p1Die1,p1Die2,p2Die1,p2Die2;

    int p1Wins=0,p2Wins=0,draws=0;

    public sichet_andrei_lab12_prb7(){

        setTitle("Dice Battle");
        setSize(700,500);
        setLayout(new BorderLayout());

        playZone=new DiceBoard();
        add(playZone,BorderLayout.CENTER);

        playZone.addMouseListener(this);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){dispose();}
        });

        setVisible(true);
    }

    void rollGame(){

        p1Die1=zarEngine.nextInt(6)+1;
        p1Die2=zarEngine.nextInt(6)+1;
        p2Die1=zarEngine.nextInt(6)+1;
        p2Die2=zarEngine.nextInt(6)+1;

        int p1Total=p1Die1+p1Die2;
        int p2Total=p2Die1+p2Die2;

        if(p1Total>p2Total)p1Wins++;
        else if(p2Total>p1Total)p2Wins++;
        else draws++;

        playZone.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e){
        rollGame();
    }

    @Override public void mousePressed(MouseEvent e){}
    @Override public void mouseReleased(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){}

    class DiceBoard extends Canvas{

        public void paint(Graphics g){

            g.setFont(new Font("Arial",Font.BOLD,18));

            g.drawString("PLAYER 1",80,40);
            drawDice(g,80,60,p1Die1);
            drawDice(g,160,60,p1Die2);

            g.drawString("PLAYER 2",420,40);
            drawDice(g,420,60,p2Die1);
            drawDice(g,500,60,p2Die2);

            g.drawString("P1 Wins: "+p1Wins,80,250);
            g.drawString("P2 Wins: "+p2Wins,420,250);
            g.drawString("Draws: "+draws,300,300);
        }

        void drawDice(Graphics g,int x,int y,int val){

            g.drawRect(x,y,60,60);

            g.setFont(new Font("Arial",Font.BOLD,20));
            g.drawString(String.valueOf(val),x+25,y+35);
        }
    }

    public static void main(String[] args){
        new sichet_andrei_lab12_prb7();
    }
}