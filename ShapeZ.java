public class ShapeZ extends Shape {
    ShapeZ() {
        color = (int)(Math.random() * 4); //[0,4)
        coords[0][0] = Constants.X_CENTRE;
        coords[0][1] = Constants.Y_MAX - 1;
        coords[1][0] = Constants.X_CENTRE + 1;
        coords[1][1] = Constants.Y_MAX - 1;
        coords[2][0] = Constants.X_CENTRE + 1;
        coords[2][1] = Constants.Y_MAX;
        coords[3][0] = Constants.X_CENTRE + 2;
        coords[3][1] = Constants.Y_MAX;
        lowerBorder = coords[2][1];
    }
    @Override
    public void tryRotate() {
        if (formSide == 1 || formSide == 3) {
            if (isfreeCell(coords[0][0], coords[0][1] + 2) &&
                    isfreeCell(coords[1][0] - 1, coords[1][1] + 1) &&
                    isfreeCell(coords[3][0] - 1, coords[3][1] - 1)) {
                coords[0][1] += 2;

                coords[1][0]--;
                coords[1][1]++;
                coords[3][0]--;
                coords[3][1]--;

                lowerBorder = coords[3][1];
                formChange();
            }
        }
        else if (formSide == 2 || formSide == 4) {
            if (isfreeCell(coords[0][0], coords[0][1] - 2) &&
                    isfreeCell(coords[1][0] + 1, coords[1][1] - 1) &&
                    isfreeCell(coords[3][0] + 1, coords[3][1] + 1)) {
                coords[0][1] -= 2;

                coords[1][0]++;
                coords[1][1]--;
                coords[3][0]++;
                coords[3][1]++;

                lowerBorder = coords[2][1];
                formChange();
            }
        }
    }
}

