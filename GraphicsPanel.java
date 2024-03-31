import javax.swing.*;
import java.awt.*;

public class GraphicsPanel extends JPanel implements Runnable {
    private final Controller controller;
    public void run() {
        while(controller.getLowerBorder() <= 750 && !controller.isStatic) {
            move();
            super.repaint();
            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public GraphicsPanel(Controller c) {
        controller = c;
        (new Thread(this)).start();
    }
    public void move() {
        if (controller.action.equals("left")) {
            controller.shiftLeft();
            controller.action = "down";
        }
        else if (controller.action.equals("right")) {
            controller.shiftRight();
            controller.action = "down";
        }
        else if (controller.action.equals("rotate")) {
            controller.rotate();
            controller.action = "down";
        }
        else if (controller.action.equals("down")) {
            controller.fallDown();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0,200, 500, 200);
        int[][] coords = controller.getCurCoords();
        if (coords == null) return;
        for (int i = 0; i < Constants.MAX_COUNT_CELL_IN_COL; ++i) {
            for (int j = 0; j < Constants.MAX_COUNT_CELL_IN_LINE; ++j) {
                if (Controller.getElem(i, j) != 0) {
                    if (coords[0][0] == j && coords[0][1] == i || coords[1][0] == j && coords[1][1] == i ||
                            coords[2][0] == j && coords[2][1] == i || coords[3][0] == j && coords[3][1] == i) {
                        switch (controller.getCurColor()) {
                            case 0 -> g.setColor(Color.GREEN);
                            case 1 -> g.setColor(Color.RED);
                            case 2 -> g.setColor(Color.YELLOW);
                            default -> g.setColor(Color.ORANGE);
                        };
                    }
                    else g.setColor(Color.BLUE);
                    g.fillRect(j * 50, i * 50, 50, 50);
                }
            }
        }
    }
}
