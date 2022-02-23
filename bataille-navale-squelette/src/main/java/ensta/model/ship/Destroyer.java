package ensta.model.ship;

import ensta.util.Orientation;

public class Destroyer extends AbstractShip {
    
    public Destroyer(String ship_label, String ship_name, int ship_length, Orientation o){super(ship_label,ship_name, ship_length, o);}

    public Destroyer(){super();}
}
