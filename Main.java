//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    private static boolean endGame = false;
    private static int loop_number = 0;
    private static final int FRAMES_PER_MOVE = 60;

    public static void main(String[] args) {
        //RectangleDrawer.start();
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300); // размеры окна
        frame.setLocationRelativeTo(null); // окно - в центре экрана

        List<Rectangle> rectangles = List.of(
                new Rectangle(10, 10, 50, 50),
                new Rectangle(60, 10, 50, 50),
                new Rectangle(110, 10, 50, 50),
                new Rectangle(60, 60, 50, 50)
        );
        GraphicsPanel panel = new GraphicsPanel(rectangles);
        frame.add(panel);
        frame.setVisible(true);



//        Controller controller = new Controller();
//        while (!endGame) {
//            if (controller.isStatic) {
//                controller.genericNewShape();
//                controller.printBoard();
//                controller.isStatic = false;
//            }
//            // Listener button shift left/right, rotate
////            if (leftBut) {
////                controller.shiftLeft();
////            }
////            if (rightBut) {
////                controller.shiftRight();
////            }
////            if (rotateBut) {
////                controller.rotate();
////            }
//            if (loop_number % FRAMES_PER_MOVE == 0) {
//                controller.fallDown();
//            }
//            loop_number++;
//            endGame = controller.finish();
//        }
    }
}