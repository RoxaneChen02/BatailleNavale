package ensta.model.ship;
import ensta.util.Orientation;

public class Carrier extends AbstractShip {

    public Carrier(Orientation o ){super('C',"Carrier",5, o);}

    public Carrier(){super('C',"Carrier",5, Orientation.EAST);}
}
