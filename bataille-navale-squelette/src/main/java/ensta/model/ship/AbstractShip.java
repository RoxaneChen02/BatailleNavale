package ensta.model.ship;

import ensta.util.Orientation;

public class AbstractShip {
    private int length;

    public Object getName() {
        return null;
    }

    public int getLength() {
        return length;
    }

    public boolean isSunk() {
        return false;
    }

    public Orientation getOrientation() {
        return null;
    }

}
