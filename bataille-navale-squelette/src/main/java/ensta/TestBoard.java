package ensta;

import java.util.Arrays;
import java.util.List;

import ensta.model.Board;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.model.ship.AbstractShip;
import ensta.model.Player;
import ensta.util.Orientation;
import ensta.model.Coords;
import ensta.controller.Game;

public class TestBoard {
    public static void main(String args[]) {
        Board board_test = new Board("board_test",15);
        /*Board board_test_opponent = new Board("board_test_opponent",15);
        List<AbstractShip> ships = Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new BattleShip(),
            new Carrier() });
        Player player = new Player(board_test, board_test_opponent, ships );
        player.putShips();

        board_test.sendHit(new Coords(0,0));
        board_test.print();*/


       BattleShip ship = new BattleShip(Orientation.SOUTH);
       Coords cor = new Coords(7,7);
        board_test.putShip(ship,cor);
        Destroyer ship1 = new Destroyer(Orientation.SOUTH);
        Coords cor1 = new Coords(7,7);
        board_test.putShip(ship1,cor1);
        board_test.print();

        board_test.sendHit(new Coords(7,7));
        board_test.print();

        board_test.sendHit(new Coords(7,8));
        board_test.print();

        board_test.sendHit(new Coords(7,9));
        board_test.print();

        board_test.sendHit(new Coords(7,10));
        board_test.print();

        board_test.sendHit(new Coords(7,10));
        board_test.print();

    }
    
}
