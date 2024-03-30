//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    private static boolean endGame = false;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(511, 830); // размеры окна
        frame.setLocationRelativeTo(null); // окно - в центре экрана

        Controller controller = new Controller();

        GraphicsPanel panel = new GraphicsPanel(controller);
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                    controller.action = "right";
                else if(e.getKeyCode() == KeyEvent.VK_LEFT)
                    controller.action = "left";
                else if(e.getKeyCode() == KeyEvent.VK_UP)
                    controller.action = "rotate";
            }
        });

        frame.add(panel);

        frame.setVisible(true);

        while (!endGame) {
            controller.genericNewShape();
            controller.isStatic = false;
            panel.run();
            endGame = controller.finish();
        }
    }
}