package ensta.model.ship;
import ensta.util.Orientation;

public class Carrier extends AbstractShip {
    String label = "C";
    String name = "Carrier";
    int length = 5;

    public Carrier(String label, String name, int length, Orientation o ){super(label,name,length, o);}

    public Carrier(){super();}
}
