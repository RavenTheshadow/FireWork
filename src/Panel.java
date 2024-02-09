import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Panel extends JPanel {
    Random Rand = new Random();
    int Width = 600, Height = 400;

    public int XSelected = -1;
    public int YSelected = -1;
    ArrayList<Point> PList = new ArrayList<>();
    Input input = new Input(this);
    public Panel() {
        setPreferredSize(new Dimension(Width, Height));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
    }
    public void WORK() {
        Point pi = new Point(Width/2, Height);
        pi.CapturedX = XSelected;
        pi.CapturedY = YSelected;
        PList.add(pi);
        for(Point p : PList) {
            int RR = Rand.nextInt(1, 10);
            MoveToPoint(p.CapturedX, p.CapturedY, p);
        }
    }
    public void MoveToPoint(int newX, int newY, Point p) {
        final int targetX = newX;
        final int targetY = newY;

        Timer timer = new Timer(10, new ActionListener() {
            int dx = (targetX - p.X) / 100; // calculate the increment for X
            int dy = (targetY - p.Y) / 100; // calculate the increment for Y
            int steps = 100; // number of steps to reach the target
            @Override
            public void actionPerformed(ActionEvent e) {
                if (steps > 0) {
                    p.TimeDelay += 10;
                    p.X += dx; // move X
                    p.Y += dy; // move Y
                    steps--; // decrement the steps
                    repaint(); // repaint the component to update the position
                } else {
                    ((Timer) e.getSource()).stop(); // stop the timer when reached the target
                }
            }
        });
        timer.start();
    }
    private class Point {
        public int TimeDelay = 0;
        public int CapturedX;
        public int CapturedY;
        public  int X;
        public  int Y;
        public boolean IS = false;
        Point(int X, int Y) {
            this.X = X;
            this.Y = Y;
        }
        public void DrawCircle(Graphics2D g2d) {
            int RegTangle    = 100;
            int RandomColorR = Rand.nextInt(0, 255);
            int RandomColorG = Rand.nextInt(0, 255);
            int RandomColorB = Rand.nextInt(0, 255);
            if(X  >= CapturedX - 20 && X <= CapturedX + 20  && Y >= CapturedY - 20 && Y <= CapturedY + 20 || this.TimeDelay > 1500) {
                IS = true;
                for (int i = 1; i < 100; i++) {
                    int RandX = Rand.nextInt(X - RegTangle, X + RegTangle);
                    int RandY = Rand.nextInt(Y - RegTangle, Y + RegTangle);
                    RandomColorR = Rand.nextInt(0, 255);
                    RandomColorG = Rand.nextInt(0, 255);
                    RandomColorB = Rand.nextInt(0, 255);
                    g2d.setColor(new Color(RandomColorR, RandomColorG, RandomColorB));
                    g2d.fillRect(RandX, RandY, 3, 3);
                }
                Timer timer = new Timer(200, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PList.remove(Point.this);
                        repaint();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
            else {
                g2d.setColor(new Color(RandomColorR, RandomColorG, RandomColorB));
                g2d.fillOval(X , Y, 5, 5);
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Width, Height);

        for(Point p : PList) {
            p.DrawCircle(g2d);
        }
        g2d.dispose();
    }

}
