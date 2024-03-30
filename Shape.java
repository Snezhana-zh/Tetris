import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public abstract class Shape {
    protected int[][] coords = new int[4][2];
    protected int lowerBorder = coords[0][1];
    public int color;
    protected int formSide = 1;
    protected void formChange() {
        if (formSide == 4) {
            formSide = 1;
        }
        else formSide++;
    }
    public int[][] getCoords() {
        return coords;
    }
    public void moveLeft() {
        for (int[] dim : coords) {
            dim[0]--;
        }
    }
    public void moveRight() {
        for (int[] dim : coords) {
            dim[0]++;
        }
    }
    public void moveDown() {
        for (int[] dim : coords) {
            dim[1]++;
        }
        lowerBorder++;
    }
    public int getLowerBorder() {
        return lowerBorder;
    }
    public abstract void rotate();
    public static Shape createShape() {
        int num = (int)(Math.random() * 7); //[0,7)
        return switch (num) {
//            case 0 -> new ShapeJ();
//            case 1 -> new ShapeL();
//            case 2 -> new ShapeO();
//            case 3 -> new ShapeZ();
//            case 4 -> new ShapeS();
//            case 5 -> new ShapeT();
            default -> new ShapeI();
        };
    }
}
