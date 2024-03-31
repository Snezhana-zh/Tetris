import java.io.*;

public class Controller {
    private Shape curShape;
    public boolean isStatic = true;
    private static int[] records = new int[3];
    private int score = 0;
    private final int[] countNonEmptyCell = new int[Constants.MAX_COUNT_CELL_IN_COL];
    private static final int[][] board = new int[Constants.MAX_COUNT_CELL_IN_COL][Constants.MAX_COUNT_CELL_IN_LINE];
    public static volatile String action;
    Controller() {
        action = "await";
        InputStream in;
        try {
            in = new FileInputStream("src/records.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String firstStr = reader.readLine();
            String secondStr = reader.readLine();
            String thirdStr = reader.readLine();
            records[0] = Integer.parseInt(firstStr);
            records[1] = Integer.parseInt(secondStr);
            records[2] = Integer.parseInt(thirdStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int[] getRecords() {
        return records;
    }
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
    public static int getElem(int c, int r) {
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
        int[][] coords = curShape.getCoords();
        for (int[] dim : coords) {
            board[dim[1]][dim[0]] = 0;
        }
        curShape.tryRotate();
        for (int[] dim : coords) {
            board[dim[1]][dim[0]] = -2;
        }
    }
    public void removeLine(int n) {
        for (int i = n; i > 0; --i) {
            for (int j = 0; j < Constants.MAX_COUNT_CELL_IN_LINE; ++j) {
                board[i][j] = board[i - 1][j];
            }
            countNonEmptyCell[i] = countNonEmptyCell[i - 1];
        }
        score += 100;
        if (score > records[0]) {
            records[2] = records[1];
            records[1] = records[0];
            records[0] = score;
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
        return countNonEmptyCell[3] != 0;
    }
    public void checkRemove(int[][] coords) {
        for (int[] dim : coords) {
            if (lineIsOverFilled(dim[1])) {
                removeLine(dim[1]);
            }
        }
    }
    public void fallDown() {
        //printBoard();
        int[][] coords = curShape.getCoords();
        for (int[] dim : coords) {
            if (!(dim[1] + 1 < board.length && board[dim[1] + 1][dim[0]] != 1)) {
                addFigure(coords);
                isStatic = true;
                checkRemove(coords);
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
