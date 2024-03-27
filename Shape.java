public class Shape {
    protected int[][] coords = new int[4][2];
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
    }
    public int minCoordY() {
        return coords[0][1];
    }
    public void rotate() {
        return;
    }
    public static Shape createShape() {
        int num = (int)(Math.random() * 7); //[0,7)
        return switch (num) {
            case 0 -> new ShapeJ();
            case 1 -> new ShapeL();
            case 2 -> new ShapeI();
            case 3 -> new ShapeS();
            case 4 -> new ShapeZ();
            case 5 -> new ShapeT();
            default -> new ShapeO();
        };
    }
}
