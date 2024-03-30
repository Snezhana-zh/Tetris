import java.awt.*;
import javax.swing.*;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Shape curShape;
    public boolean isStatic = true;
    private final int[] countNonEmptyCell = new int[Constants.MAX_COUNT_CELL_IN_COL];
    private static final int[][] board = new int[Constants.MAX_COUNT_CELL_IN_COL][Constants.MAX_COUNT_CELL_IN_LINE];
    public String action = "down";
    void genericNewShape() {
        curShape = Shape.createShape();
        int[][] coords = curShape.getCoords();
        for (int[] dim : coords) {
            board[dim[1]][dim[0]] = -2;
        }
    }
    public int[][] getCurCoords() {
        if (curShape == null) return null;
        return curShape.getCoords();
    }
    public int getCurColor() {
        return curShape.color;
    }
    public int getElem(int c, int r) {
        return board[c][r];
    }
    public void shiftLeft() {
        int[][] coords = curShape.getCoords();
        for (int[] dim : coords) {
            if (!(dim[0] - 1 >= 0 && board[dim[1]][dim[0] - 1] != 1)) {
                return;
            }
        }
        for (int[] dim : coords) {
            board[dim[1]][dim[0]] = 0;
        }
        for (int[] dim : coords) {
            board[dim[1]][dim[0] - 1] = -2;
        }
        curShape.moveLeft();
    }
    public void shiftRight() {
        int[][] coords = curShape.getCoords();
        for (int[] dim : coords) {
            if (!(dim[0] + 1 < Constants.MAX_COUNT_CELL_IN_LINE && board[dim[1]][dim[0] + 1] != 1)) {
                return;
            }
        }
        for (int[] dim : coords) {
            board[dim[1]][dim[0]] = 0;
        }
        for (int[] dim : coords) {
            board[dim[1]][dim[0] + 1] = -2;
        }
        curShape.moveRight();
    }
    public void rotate() {
        //check
        //получить координаты и проверить

        curShape.rotate();
    }
    public void removeLine(int n) {
        for (int i = n; i > 0; --i) {
            for (int j = 0; j < Constants.MAX_COUNT_CELL_IN_LINE; ++j) {
                board[i][j] = board[i - 1][j];
            }
            countNonEmptyCell[i] = countNonEmptyCell[i - 1];
        }
    }
    public void printBoard() {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                System.out.print(board[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
        System.out.print("\n\n");
    }
    public void addFigure(int[][] coords) {
        for (int[] dim : coords) {
            board[dim[1]][dim[0]] = 1;
            countNonEmptyCell[dim[1]]++;
        }
    }
    public boolean finish() {
        if (countNonEmptyCell[3] != 0) {
            System.out.print("GAME OVER");
        }
        return countNonEmptyCell[3] != 0;
    }
    public void fallDown() {
        //printBoard();
        int[][] coords = curShape.getCoords();
        for (int[] dim : coords) {
            if (!(dim[1] + 1 < board.length && board[dim[1] + 1][dim[0]] != 1)) {
                addFigure(coords);
                isStatic = true;
                int lowerBorder = curShape.getLowerBorder();
                if (lineIsOverFilled(lowerBorder)) {
                    removeLine(lowerBorder);
                }
                return;
            }
        }
        for (int[] dim : coords) {
            board[dim[1]][dim[0]] = 0;
        }
        for (int[] dim : coords) {
            board[dim[1] + 1][dim[0]] = -2;
        }
        curShape.moveDown();
    }
    public int getLowerBorder() {
        if (curShape == null) return -1;
        return curShape.getLowerBorder() * 50;
    }
    public boolean lineIsOverFilled(int col) {
        return countNonEmptyCell[col] == Constants.MAX_COUNT_CELL_IN_LINE;
    }
}
