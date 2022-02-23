package ensta;

import ensta.model.Board;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Destroyer;
import ensta.util.Orientation;
import ensta.model.Coords;

public class TestBoard {
    public static void main(String args[]) {
       Board board_test = new Board("board_test",15);
       BattleShip ship = new BattleShip(Orientation.SOUTH);
       Coords cor = new Coords(7,7);
        board_test.putShip(ship,cor);
        Destroyer ship1 = new Destroyer(Orientation.SOUTH);
        Coords cor1 = new Coords(7,7);
        board_test.putShip(ship1,cor1);
        board_test.print();

        

    }
    
}
