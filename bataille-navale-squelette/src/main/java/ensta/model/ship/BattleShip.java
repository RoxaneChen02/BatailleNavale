package ensta.model.ship;

import ensta.util.Orientation;

public class BattleShip extends AbstractShip{
    String label = "B";
    String name = "Battelship";
    int length = 4;

    public BattleShip(String label, String name, int length, Orientation o ){super(label,name,length, o);}

    public BattleShip(){super();}

}


