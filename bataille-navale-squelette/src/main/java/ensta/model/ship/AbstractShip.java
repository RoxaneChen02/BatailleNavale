package ensta.model.ship;

import java.io.Serializable;

import ensta.util.Orientation;

public class AbstractShip implements Serializable {
    private Character label;
    private String name;
    private int length;
    private Orientation ship_orientation;
    private int strikeCount;

    public AbstractShip(Character ship_label, String ship_name, int ship_length, Orientation o  ) {
        this.label = ship_label;
        this.name = ship_name;
        this.length = ship_length;
        this.ship_orientation = o;
        this.strikeCount = 0;
    }

    public AbstractShip(){this.ship_orientation = Orientation.EAST;this.strikeCount = 0;}
    public AbstractShip(Orientation o ){this.ship_orientation =o; this.strikeCount = 0; }


    public Character getLabel(){
        return label;
    }
    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public Orientation getOrientation() {
        return ship_orientation;
    }

    public void setOrientation(Orientation o) {
        this.ship_orientation = o;
    }

    public boolean isSunk() {
        if(strikeCount == this.length){
			return true;
		}
		return false;
    }

    public void addStrikeCount(){
        this.strikeCount +=1;
    }

 
}
