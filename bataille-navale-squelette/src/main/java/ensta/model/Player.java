package ensta.model;

import java.io.Serializable;
import java.util.List;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import ensta.view.InputHelper;

public class Player {
	/*
	 * ** Attributs
	 */
	private Board board;
	protected Board opponentBoard;
	private int destroyedCount = 0;
	protected AbstractShip[] ships;
	private boolean lose ;

	/*
	 * ** Constructeur
	 */
	public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
		this.setBoard(board);
		this.ships = ships.toArray(new AbstractShip[0]);
		this.opponentBoard = opponentBoard;
	}

	/*
	 * ** Méthodes
	 */

	/**
	 * Read keyboard input to get ships coordinates. Place ships on given
	 * coodrinates.
	 */
	public void putShips() {
		boolean done = false;
		int i = 0;

		do {
			AbstractShip ship = ships[i];
			String msg = String.format("placer %d : %s(%d)", i + 1, ship.getName(), ship.getLength());
			System.out.println(msg);
			InputHelper.ShipInput res = InputHelper.readShipInput();

			
			
			if(res.orientation .equals("north")){

				ship.setOrientation(Orientation.NORTH);
			}

			if(res.orientation .equals("south")){
				ship.setOrientation(Orientation.SOUTH);
			}

			if(res.orientation.equals("east")){
				ship.setOrientation(Orientation.EAST);
			}

			if(res.orientation .equals("west")){
				
				ship.setOrientation(Orientation.WEST);
				
			}

			
			Coords c = new Coords(res.x,res.y);
			if (board.putShip(ship, c)){
				++i;
				done = i == 5;

				board.print();
			}
			

			
			
		} while (!done);
	}

	public Hit sendHit(Coords coords) {
		boolean done = false;
		Hit hit = null;
		Coords t = new Coords();
		
		do {
			
			System.out.println("où frapper?");
			InputHelper.CoordInput hitInput = InputHelper.readCoordInput();

			t = new Coords(hitInput.x, hitInput.y+1);
			if (t.isInBoard(board.getSize())){
				if(this.board.getHit(t) != null){
					System.out.print ("error already struck there \n");
					done = false;
				}
				else{
					hit = opponentBoard.sendHit(t);

					if(hit != Hit.MISS && hit != Hit.STRIKE){++destroyedCount;}


					done =true;}
			}

			else {System.out.print("Coords out of bound \n");done = false;}
			
		
		} while (!done);
		coords.setX(t.getX());
		coords.setY(t.getY());
		return hit;
	}

	public AbstractShip[] getShips() {
		return ships;
	}

	public void setShips(AbstractShip[] ships) {
		this.ships = ships;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getDestroyedCount() {
		return destroyedCount;
	}

	public void setDestroyedCount(int destroyedCount) {
		this.destroyedCount = destroyedCount;
	}

	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}
}
