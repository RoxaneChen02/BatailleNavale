package ensta.ai;

import java.io.Serializable;

import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.IBoard;
import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import java.util.Random;

public class BattleShipsAI implements Serializable {

	/*
	 * ** Attributs
	 */

	/**
	 * My board. My ships have to be put on this one.
	 */
	private final IBoard board;

	/**
	 * My opponent's board. My hits go on this one to strike his ships.
	 */
	private final IBoard opponent;

	/**
	 * Coordss of last known strike. Would be a good idea to target next hits around
	 * this point.
	 */
	private Coords lastStrike;

	/**
	 * If last known strike lead me to think the underlying ship has vertical
	 * placement.
	 */
	private Boolean lastVertical;



	/*
	 * ** Constructeur
	 */

	/**
	 *
	 * @param myBoard       board where ships will be put.
	 * @param opponentBoard Opponent's board, where hits will be sent.
	 */
	public BattleShipsAI(IBoard myBoard, IBoard opponentBoard) {
		this.board = myBoard;
		this.opponent = opponentBoard;
	}

	/*
	 * ** Méthodes publiques
	 */

	/**
	 * Put the ships on owned board.
	 * 
	 * @param ships the ships to put
	 */
	public void putShips(AbstractShip ships[]) {
		Coords coords = new Coords();
		Orientation orientation;
		Orientation[] orientations = Orientation.values();

		for (AbstractShip ship : ships) {
			
			do {

				
				coords.setCoords(pickRandomCoords());
				orientation = Orientation.randomOrientation();
				ship.setOrientation(orientation);
			} while (!board.canPutShip(ship, coords));

			
			board.putShip(ship, coords);
			
		}
	}

	/**
	 *
	 * @param coords array must be of size 2. Will hold the coords of the send hit.
	 * @return the status of the hit.
	 */
	public Hit sendHit(Coords coords) {
		
		Coords res = null;
		if (coords == null) {
			throw new IllegalArgumentException("must provide an initialized array of size 2");
		}

		
		// already found strike & orientation?
		if (lastVertical != null) {
			
			if (lastVertical) {
				res = pickVCoords();
			} else {
				res = pickHCoords();
			}

			if (res == null) {
				// no suitable coords found... forget last strike.
				lastStrike = null;
				lastVertical = null;
			}
		} 
		else if (lastStrike != null) {
			
			// if already found a strike, without orientation
			// try to guess orientation
			res = pickVCoords();
			if (res == null) {
				res = pickHCoords();
			}
			if (res == null) {
				// no suitable coords found... forget last strike.
				lastStrike = null;
			}
		}

		if (lastStrike == null) {
			
			res = pickRandomCoords();
		}
		
		Hit hit = opponent.sendHit(res);
		board.setHit(hit != Hit.MISS, res);

		if (hit != Hit.MISS) {
			if (lastStrike != null) {
				lastVertical = guessOrientation(lastStrike, res);
			}
			lastStrike = res;
		}

		coords.setCoords(res);
		//sleep(1000);
		return hit;
	}

	/*
	 * *** Méthodes privées
	 */

	private boolean guessOrientation(Coords lastStrike2, Coords res) {
		return lastStrike2.getX() == res.getY();
	}

	private boolean isUndiscovered(Coords coords) {
		if(coords.isInBoard(board.getSize())){
			if(board.getHit(coords) == null){
				return true;
			}
		}
		return  false;
	}

	private Coords pickRandomCoords() {
		Coords coords;
		
		do {
			coords = Coords.randomCoords(board.getSize());
		} while (!isUndiscovered(coords));

		return coords;
	}

	/**
	 * pick a coords verically around last known strike
	 * 
	 * @return suitable coords, or null if none is suitable
	 */
	private Coords pickVCoords() {
		int x = (int) lastStrike.getX();
		int y = (int) lastStrike.getY();

		for (int iy : new int[] { y - 1, y + 1 }) {
			
			Coords coords = new Coords(x, iy);
			if (isUndiscovered(coords)) {
				return coords;
			}
		}
		return null;
	}

	/**
	 * pick a coords horizontally around last known strike
	 * 
	 * @return suitable coords, or null if none is suitable
	 */
	private Coords pickHCoords() {
		int x = (int) lastStrike.getX();
		int y = (int) lastStrike.getY();

		for (int ix : new int[] { x - 1, x + 1 }) {
			Coords coords = new Coords(ix, y);
			if (isUndiscovered(coords)) {
				return coords;
			}
		}
		return null;
	}

	private static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
