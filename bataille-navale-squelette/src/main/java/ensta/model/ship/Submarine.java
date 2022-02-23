package ensta.model.ship;
import ensta.util.Orientation;

public class Submarine extends AbstractShip {
    String label = "S";
    String name = "Submarine";
    int length = 3;
    
    public Submarine(String label, String name, int length, Orientation o ){super(label,name,length, o);}

    public Submarine(){super();}
}
