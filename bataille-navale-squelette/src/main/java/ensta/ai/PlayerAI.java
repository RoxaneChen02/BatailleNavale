package ensta.ai;
import java.io.Serializable;
import java.util.List;

import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;

public class PlayerAI extends Player implements Serializable {
    /* **
     * Attribut
     */
    private BattleShipsAI ai;

    /* **
     * Constructeur
     */
    public PlayerAI(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    

	public void putShips(AbstractShip ships[]) {
		ai.putShips(ships);
	}

	public Hit sendHit(Coords coords) {
		return ai.sendHit(coords);
    }

}
