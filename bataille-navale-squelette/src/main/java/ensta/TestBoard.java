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
import ensta.model.Hit;
import ensta.controller.Game;
import ensta.ai.*;

public class TestBoard {
    public static void main(String args[]) {
        Board MyBoard = new Board("MyBoard",10);
        MyBoard.print();
        List<AbstractShip> ships = Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new BattleShip(),
            new Carrier() });
        BattleShipsAI AIPlayer= new BattleShipsAI(MyBoard,MyBoard);
        int shipSunk = 0;
        AIPlayer.putShips(ships.toArray(new AbstractShip[0]));
        MyBoard.print();
        do{

            
            Coords coords  = new Coords();
            Hit hit = AIPlayer.sendHit(coords);
            if (hit != Hit.MISS && hit!=Hit.STRIKE){
                ++shipSunk;
            }
            
            System.out.print(hit.toString()+ "\n");
            MyBoard.print();
        }while(shipSunk<ships.size());

        /*
        Player player = new Player(board_test, board_test_opponent, ships );
        player.putShips();

        board_test.sendHit(new Coords(0,0));
        board_test.print();*/


       /*BattleShip ship = new BattleShip(Orientation.SOUTH);
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
        board_test.print();*/

    }
    
}
