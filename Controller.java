import java.net.StandardSocketOptions;
import java.util.ArrayList;

public class Controller {
    private Shape curShape;
    public boolean isStatic = true;
    private final int[] countNonEmptyCell = new int[Constants.MAX_COUNT_CELL_IN_COL];
    private static final int[][] board = new int[Constants.MAX_COUNT_CELL_IN_COL][Constants.MAX_COUNT_CELL_IN_LINE];
    //сверху 4 клетки поля не отображаются - это пространство для создания фигуры
    void genericNewShape() {
        curShape = Shape.createShape();
        int[][] coords = curShape.getCoords();
        for (int[] dim : coords) {
            board[dim[1]][dim[0]] = -2;
        }
    }
    void shiftLeft() {
        int[][] coords = curShape.getCoords();
        for (int[] dim : coords) {
            if (!(dim[0] - 1 < Constants.MAX_COUNT_CELL_IN_LINE && board[dim[1]][dim[0] - 1] != 1)) {
                return;
            }
        }
        for (int[] dim : coords) {
            board[dim[1]][dim[0]] = 0;
            board[dim[1]][dim[0] - 1] = -2;
        }
        curShape.moveLeft();
    }
    void shiftRight() {
        int[][] coords = curShape.getCoords();
        for (int[] dim : coords) {
            if (!(dim[0] + 1 < Constants.MAX_COUNT_CELL_IN_LINE && board[dim[1]][dim[0] + 1] != 1)) {
                return;
            }
        }
        for (int[] dim : coords) {
            board[dim[1]][dim[0]] = 0;
            board[dim[1]][dim[0] + 1] = -2;
        }
        curShape.moveRight();
    }
    void rotate() {
        //check
        curShape.rotate();
    }
    void removeLine(int n) {
        //make
    }
    void printBoard() {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                System.out.print(board[i][j]);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
        System.out.print("\n\n");
    }
    void addFigure(int[][] coords) {
        for (int[] dim : coords) {
            board[dim[1]][dim[0]] = 1;
            countNonEmptyCell[dim[1]]++;
        }
    }
    boolean finish() {
        return countNonEmptyCell[3] != 0;
    }
    void fallDown() {
        int[][] coords = curShape.getCoords();
        for (int[] dim : coords) {
            if (!(dim[1] + 1 < board.length && board[dim[1] + 1][dim[0]] != 1)) {
                addFigure(coords);
                isStatic = true;
                return;
            }
        }
        for (int[] dim : coords) {
            board[dim[1]][dim[0]] = 0;
            board[dim[1] + 1][dim[0]] = -2;
        }
        curShape.moveDown();
        int minCoordY = curShape.minCoordY();
        if (lineIsOverFilled(minCoordY)) {
            removeLine(minCoordY);
        }
    }
    boolean lineIsOverFilled(int col) {
        return countNonEmptyCell[col] == Constants.MAX_COUNT_CELL_IN_LINE;
    }
}
