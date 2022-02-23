package ensta.model;

public class Coords {
    private int x, iy;

    public Coords(int x, int iy) {
        this.x = x;
        this.iy = iy;
    }

    public Coords() {
        this.x= 0;
        this.iy = 0;
    }

    public Coords(Coords coords) {
        this.x = coords.getX();
        this.iy = coords.getY();
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
