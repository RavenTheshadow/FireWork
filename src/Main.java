import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JFrame Frame = new JFrame("FireWork");
        Frame.setLayout(new GridLayout());
        Frame.setMinimumSize(new Dimension(600, 400));
        Panel panel = new Panel();
        Frame.getContentPane().add(panel);

        Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Frame.setLocationRelativeTo(null);
        Frame.setVisible(true);
    }
}