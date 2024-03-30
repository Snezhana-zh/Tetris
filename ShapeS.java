public class ShapeS extends Shape {
    ShapeS() {
        color = (int)(Math.random() * 4); //[0,4)
        coords[0][0] = Constants.X_CENTRE;
        coords[0][1] = Constants.Y_MAX - 1;
        coords[1][0] = Constants.X_CENTRE + 1;
        coords[1][1] = Constants.Y_MAX - 1;
        coords[2][0] = Constants.X_CENTRE + 1;
        coords[2][1] = Constants.Y_MAX;
        coords[3][0] = Constants.X_CENTRE + 2;
        coords[3][1] = Constants.Y_MAX;
        lowerBorder = coords[0][1];
    }
    @Override
    public void rotate() {
        if (formSide == 1 || formSide == 3) {
            coords[0][0] += 2;

            coords[1][0]++;
            coords[1][1]--;
            coords[3][0]--;
            coords[3][1]--;
            formChange();
        }
        if (formSide == 2 || formSide == 4) {
            coords[0][0] -= 2;

            coords[1][0]--;
            coords[1][1]++;
            coords[3][0]++;
            coords[3][1]++;
            formChange();
        }
    }
}

