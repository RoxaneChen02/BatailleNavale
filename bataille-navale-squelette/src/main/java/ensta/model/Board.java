package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;
	private int size;
	
	public Board() {
	}

	public void print() {
	}

	public int getSize() {
		return size;}

	public boolean putShip(AbstractShip ship, Coords coords){
		return true;
	}
	
	public boolean hasShip(Coords coords){
		return true;
	}

	public void setHit(boolean hit, Coords coords){
		
	}
	public Boolean getHit(Coords coords){
		return true;
	}

	public Hit sendHit(Coords res){
		return null;
		
	}

	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (o == Orientation.EAST) {
			if ((coords.getX() + ship.getLength()) >= this.size) {
				return false;
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if ((coords.getY()+ship.getLength()) >= this.size) {
				return false;
			}
			dy = 1;
		} else if (o == Orientation.NORTH) {
			if ((coords.getY() + 1 - ship.getLength()) < 0) {
				return false;
			}
			dy = -1;
		} else if (o == Orientation.WEST) {
			if ((coords.getX() + 1 - ship.getLength()) < 0) {
				return false;
			}
			dx = -1;
		}

		Coords iCoords = new Coords(coords);

		for (int i = 0; i < ship.getLength(); ++i) {
			if (this.hasShip(iCoords)) {
				return false;
			}
			iCoords.setX(iCoords.getX() + dx);
			iCoords.setY(iCoords.getY() + dy);
		}

		return true;
	}
}
