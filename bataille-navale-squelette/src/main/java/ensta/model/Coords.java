package ensta.model;

public class Coords {
    private int x, iy;

    public Coords(int x, int iy) {
    }

    public Coords() {
    }

    public Coords(Coords coords) {
    }

    public void setCoords(Coords res) {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        
        return iy;
    }

    public boolean isInBoard(int size) {
        return false;
    }

    public static Coords randomCoords(int size) {
        return null;
    }

    public void setX(int i) {
        this.x = i;
    }

    public void setY(int i) {
        this.iy = i;
    }

}
