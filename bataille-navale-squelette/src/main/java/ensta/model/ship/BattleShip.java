package ensta.model.ship;

import ensta.util.Orientation;

public class BattleShip extends AbstractShip{

    public BattleShip(Orientation o ){super('B',"Battleship",4 ,o);}

    public BattleShip(){super('B',"Battleship",4 ,Orientation.EAST);}

}


