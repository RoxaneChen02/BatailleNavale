package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;
	private int size;
	private String name;
	private Character ships[][];
	private Boolean hits[][];//pense à verouiller ton pc

	public Board(String board_name, int board_size) {
		this.name = board_name;
		this.size = board_size;
		this.ships = new Character[board_size][board_size];
		for (int i = 0 ; i<board_size;++i){
			for (int j =0 ; j<board_size;++j){
				ships[i][j] = '.';
			}
		}

		this.hits = new Boolean[board_size][board_size];
		for (int i = 0 ; i<board_size;++i){
			for (int j =0 ; j<board_size;++j){
				hits[i][j] = false;
			}
		}
	}

	public Board(String board_name){
		this.name = board_name;
		this.size = DEFAULT_SIZE;
		this.ships = new Character[DEFAULT_SIZE][DEFAULT_SIZE];
		for (int i = 0 ; i<DEFAULT_SIZE;++i){
			for (int j =0 ; j<DEFAULT_SIZE;++j){
				ships[i][j] = '.';
			}
		}
		this.hits = new Boolean[DEFAULT_SIZE][DEFAULT_SIZE];
		for (int i = 0 ; i<DEFAULT_SIZE;++i){
			for (int j =0 ; j<DEFAULT_SIZE;++j){
				hits[i][j] = false;
			}
		}
	}

	public void print() {
		


		System.out.print("Navires : \n");
		if(this.size>=10) System.out.print("    ");
		else System.out.print("   ");
		for (int c = 65 ; c < 65+this.size ; c++){
			System.out.print((char)c + " ");
		}
		System.out.print("\n");

		for (int i = 0 ; i<this.size ; i++){
			if ((i+1) < 10) System.out.print((i+1) + "  ");
			else System.out.print((i+1) + " ");
			for (int j = 0 ; j< this.size ; j++){
				System.out.print(" "+this.ships[i][j]);
			}
			System.out.print("\n");
		}

		System.out.print("\n"+"Frappes : \n");
		if(this.size>=10) System.out.print("    ");
		else System.out.print("   ");
		for (int c = 65 ; c < 65+this.size ; c++){
			System.out.print((char)c + " ");
		}
		System.out.print("\n");

		for (int i = 0 ; i<this.size ; i++){
			if ((i+1) < 10) System.out.print((i+1) + "  ");
			else System.out.print((i+1) + " ");
			for (int j = 0 ; j<this.size ; j++){
				if (hits[i][j]==false) System.out.print(" .");
				else System.out.print(" x");
			}
			System.out.print("\n");
		}
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
