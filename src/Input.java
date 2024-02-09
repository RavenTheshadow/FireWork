import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input extends MouseAdapter {
    Panel panel;
    public  Input(Panel panel) {
        this.panel = panel;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int X = e.getX();
        int Y = e.getY();
        panel.XSelected = X;
        panel.YSelected = Y;
        panel.WORK();
    }
}
