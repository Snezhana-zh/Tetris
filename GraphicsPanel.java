import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphicsPanel extends JPanel {
    private List<Rectangle> rectangles;

    public GraphicsPanel(List<Rectangle> rectangles) {
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
