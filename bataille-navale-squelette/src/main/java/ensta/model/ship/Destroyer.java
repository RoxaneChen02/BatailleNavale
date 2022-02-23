package ensta.model.ship;

import ensta.util.Orientation;

public class Destroyer extends AbstractShip {
    
    String label = "D";
    String name = "Destroyer";
    int length = 2;

    public Destroyer(String label, String name, int length, Orientation o ){super(label,name,length, o);}

    public Destroyer(){super();}
}
