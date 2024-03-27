public class ShapeO extends Shape {
    ShapeO() {
        coords[0][0] = Constants.X_CENTRE;
        coords[0][1] = Constants.Y_MAX;
        coords[1][0] = Constants.X_CENTRE + 1;
        coords[1][1] = Constants.Y_MAX;
        coords[2][0] = Constants.X_CENTRE;
        coords[2][1] = Constants.Y_MAX - 1;
        coords[3][0] = Constants.X_CENTRE + 1;
        coords[3][1] = Constants.Y_MAX - 1;
    }
}
