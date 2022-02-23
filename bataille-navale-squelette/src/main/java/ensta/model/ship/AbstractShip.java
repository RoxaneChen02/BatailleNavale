package ensta.model.ship;

import ensta.util.Orientation;

public class AbstractShip {
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
    }

    public AbstractShip(){this.ship_orientation = Orientation.EAST;}
    public AbstractShip(Orientation o ){this.ship_orientation =o; }


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
        ship_orientation = o;
    }

    public boolean isSunk() {
        if(strikeCount == this.length){
			return true;
		}
		return false;
    }

 
}
