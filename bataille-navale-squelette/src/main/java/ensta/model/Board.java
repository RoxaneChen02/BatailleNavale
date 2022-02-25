package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.model.ship.ShipState;
import ensta.util.ColorUtil;
import ensta.util.Orientation;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;
	private int size;
	private String name;
	private ShipState ships[][];
	private Boolean hits[][];


	public Board(String board_name, int board_size) {
		this.name = board_name;
		this.size = board_size;
		this.ships = new ShipState[board_size][board_size];
		for (int i = 0 ; i<board_size;++i){
			for (int j =0 ; j<board_size;++j){
				ships[i][j] = new ShipState();
			}
		}

		this.hits = new Boolean[board_size][board_size];
		for (int i = 0 ; i<board_size;++i){
			for (int j =0 ; j<board_size;++j){
				hits[i][j] = null;
			}
		}
	}

	public Board(String board_name){
		this.name = board_name;
		this.size = DEFAULT_SIZE;
		this.ships = new ShipState[DEFAULT_SIZE][DEFAULT_SIZE];
		for (int i = 0 ; i<DEFAULT_SIZE;++i){
			for (int j =0 ; j<DEFAULT_SIZE;++j){
				ships[i][j] = new ShipState();
			}
		}
		this.hits = new Boolean[DEFAULT_SIZE][DEFAULT_SIZE];
		for (int i = 0 ; i<DEFAULT_SIZE;++i){
			for (int j =0 ; j<DEFAULT_SIZE;++j){
				hits[i][j] = null;
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
			if (this.size>=10){
				if ((i) < 10) System.out.print((i) + "  ");
				else System.out.print((i) + " ");}
				else System.out.print((i) + " ");
			for (int j = 0 ; j< this.size ; j++){
				if (this.ships[i][j].getShip() !=null) System.out.print(" "+this.ships[i][j].toString());
				else System.out.print(" .");
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
			if (this.size>=10){
			if ((i) < 10) System.out.print((i) + "  ");
			else System.out.print((i) + " ");}
			else System.out.print((i) + " ");
			for (int j = 0 ; j<this.size ; j++){
				if (hits[i][j]==null) System.out.print(" .");
				else if (hits[i][j]==false) System.out.print(" x");
				else if (hits[i][j] == true ) System.out.print(" "+ ColorUtil.colorize("x", ColorUtil.Color.RED));
			}
			System.out.print("\n");
		}
	}

	public int getSize() {
		return size;}

	public boolean putShip(AbstractShip ship, Coords coords){
		
		if(canPutShip(ship, coords)){
			
			int x = coords.getX();
			int y = coords.getY();
			Orientation o = ship.getOrientation();
			
			
			if (o == Orientation.EAST) {
				for (int k = 0;k<ship.getLength();++k){
					ships[y][x+k] = new ShipState(ship);
				}
				
			}else if (o == Orientation.SOUTH) {

				for (int k = 0;k<ship.getLength();++k){
					this.ships[y+k][x] = new ShipState(ship);
				}
			
				
			} else if (o == Orientation.NORTH) {

				for (int k = 0;k<ship.getLength();++k){
					ships[y-k][x]= new ShipState(ship);
				}
			} else if (o == Orientation.WEST) {
				for (int k = 0;k<ship.getLength();++k){
					ships[y][x-k]= new ShipState(ship);
				}
			}
			return true;
		}
		System.out.print("error ship cannot be put at : (" + coords.getX() +","+coords.getY()+ ") \n");
		return false;
		
	}
	
	public boolean hasShip(Coords coords){
		
		if (this.ships[coords.getY()][coords.getX()].getShip() != null){
			return true;
		}
		else return false;
	}

	public void setHit(boolean hit, Coords coords){
		hits[coords.getY()][coords.getX()] = hit;

	}

	public Boolean getHit(Coords coords){
		
		return hits[coords.getY()][coords.getX()];
	}

	public Hit sendHit(Coords res){
		
		int x = res.getX();
		int y = res.getY();
		
		if(hasShip(new Coords(x,y))){
			
			this.ships[y][x].addStrike();
			
			
			if(ships[y][x].isSunk()){
				Character ship = ships[y][x].getShip().getLabel(); 
				if (ship == 'D') { System.out.print("D coulé \n") ;return Hit.DESTROYER;}
				else if (ship == 'B') { System.out.print("B coulé \n") ;return Hit.BATTLESHIP;}
				else if (ship == 'C') { System.out.print("C coulé \n") ;return Hit.CARRIER;}
				else if (ship == 'S') { System.out.print("S coulée \n") ;return Hit.SUBMARINE;}
			} 
			return Hit.STRIKE;
		}
		else{ return Hit.MISS;} 
		
	}

	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;

		
		if (o == Orientation.EAST) {
			if ((coords.getX() + ship.getLength()) > this.size) {
				return false;
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if ((coords.getY()+ship.getLength()) > this.size) {
				return false;
			}
			dy = 1;
		} else if (o == Orientation.NORTH) {
			if ((coords.getY() +1   - ship.getLength()) < 0) {
				return false;
			}
			dy = -1;
		} else if (o == Orientation.WEST) {
			if ((coords.getX() +1 - ship.getLength()) < 0) {
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
