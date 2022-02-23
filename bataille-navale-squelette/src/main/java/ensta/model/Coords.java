package ensta.model;

import java.util.Random;

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
        this.x = res.getX();
        this.iy = res.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        
        return iy;
    }

    public boolean isInBoard(int size) {
        return (this.x>= 0 && this.x< size && this.iy <size && this.iy >= 0);
    }

    public static Coords randomCoords(int size) {
        int x = new Random().nextInt(size);
		int y = new Random().nextInt(size);
        Coords coord = new Coords(x,y);
        return coord;}

    public void setX(int i) {
        this.x = i;
    }

    public void setY(int i) {
        this.iy = i;
    }

}
