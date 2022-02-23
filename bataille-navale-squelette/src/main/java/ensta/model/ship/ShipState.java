package ensta.model.ship;

import ensta.util.ColorUtil;

public class ShipState {
    private AbstractShip ship;
    private boolean struck;

    public ShipState(AbstractShip ship){
        this.ship = ship;
        struck = false;
    }

    public ShipState(){
        this.ship = null;
        struck = false;
    }

    public void addStrike(){
        this.struck = true;
    }

    public boolean isStruck(){
        return struck;
    }

    public String toString(){
        if (struck == true){
            Character label = ship.getLabel();
            return (ColorUtil.colorize(label, ColorUtil.Color.RED));
        }
        else {return ship.getLabel().toString();}
    }

    public boolean isSunk () {
        if (ship.isSunk()){
            return true;
        }

        else return false;
    }

    public AbstractShip getShip() {
        return this.ship;
    }
}
