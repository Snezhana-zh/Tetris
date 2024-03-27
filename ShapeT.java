public class ShapeT extends Shape {
    ShapeT() {
        coords[0][0] = Constants.X_CENTRE;
        coords[0][1] = Constants.Y_MAX;
        coords[1][0] = Constants.X_CENTRE + 1;
        coords[1][1] = Constants.Y_MAX - 1;
        coords[2][0] = Constants.X_CENTRE + 1;
        coords[2][1] = Constants.Y_MAX;
        coords[3][0] = Constants.X_CENTRE + 2;
        coords[3][1] = Constants.Y_MAX;
    }
    @Override
    public void rotate() {
        if (formSide == 1) {
            coords[0][0]++;
            coords[0][1]++;
            coords[1][0]++;
            coords[1][1]--;

            coords[3][0]--;
            coords[3][1]--;
            formChange();
        }
        if (formSide == 2) {
            coords[0][0]--;
            coords[0][1]--;
            coords[1][0]--;
            coords[1][1]--;

            coords[3][0]++;
            coords[3][1]++;
            formChange();
        }
        if (formSide == 3) {
            coords[0][0]++;
            coords[0][1]--;
            coords[1][0]--;
            coords[1][1]++;

            coords[3][0]--;
            coords[3][1]++;
            formChange();
        }
        if (formSide == 4) {
            coords[0][0]--;
            coords[0][1]++;
            coords[1][0]++;
            coords[1][1]++;

            coords[3][0]++;
            coords[3][1]--;
            formChange();
        }
    }
}

