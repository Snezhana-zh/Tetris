//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Main {
    public static boolean endGame = false;
    public static JFrame frame = new JFrame("Tetris");
    public static void main(String[] args) {
        MyFrame menu = new MyFrame("Menu", 400, 400);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setResizable(false);
        menu.setLocationRelativeTo(null); // окно - в центре экрана
        menu.setVisible(true);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(510, 830); // размеры окна
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

        while (!endGame) {
            if (Controller.action.equals("await")) {
                continue;
            }
            controller.genericNewShape();
            controller.isStatic = false;
            panel.run();
            endGame = controller.finish();
        }
        try {
            File file = new File("src/records.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(Controller.getRecords()[0]+ "\n" + Controller.getRecords()[1] + "\n" +Controller.getRecords()[2]);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}