import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RectangleDrawer extends JFrame {

    private class RectanglePanel extends JPanel {
        private List<Rectangle> rectangles;

        public RectanglePanel(List<Rectangle> rectangles) {
            this.rectangles = rectangles;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Rectangle rect : rectangles) {
                g.setColor(Color.BLUE);
                g.drawRect(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }

    public RectangleDrawer(List<Rectangle> rectangles) {
        setTitle("Rectangle Drawer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        RectanglePanel panel = new RectanglePanel(rectangles);
        add(panel);

        setVisible(true);
    }

    public static void start() {
        java.util.List<Rectangle> rectangles = List.of(
                new Rectangle(10, 10, 50, 50),
                new Rectangle(70, 70, 100, 100),
                new Rectangle(180, 180, 70, 70)
        );
        RectangleDrawer RectangleDrawer = new RectangleDrawer(rectangles);
    }
}