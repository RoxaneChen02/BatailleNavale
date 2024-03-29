package ensta.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ensta.ai.PlayerAI;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.ColorUtil;

public class Game implements Serializable{

	/*
	 * *** Constante
	 */
	public static final File SAVE_FILE = new File("savegame.dat");

	/*
	 * *** Attributs
	 */
	private Player player1;
	private Player player2;
	private PlayerAI playerAI;
	private Scanner sin;

	/*
	 * *** Constructeurs
	 */
	public Game() {
	}

	public Game init() {
		//if (!loadSave()) {

			Board Board1 = new Board("Board1",10);
			Board Board2 = new Board("Board2",10);
			List<AbstractShip> list1 = createDefaultShips();
			List<AbstractShip> list2 = createDefaultShips();
			
			this.player1 = new Player(Board1, Board2, list1 );
			
			this.player2 = new Player(Board2, Board1, list2 );
			System.out.print("le joueur1 place ses bateaux \n ");
			player1.putShips();
			System.out.print("le joueur2 place ses bateaux \n ");
			player2.putShips();
			
			
		//}
		return this;
	}

	public Game init_vs_AI() {
		//if (!loadSave()) {

			Board Board1 = new Board("Board1",10);
			Board Board2 = new Board("Board2",10);
			List<AbstractShip> list1 = createDefaultShips();
			List<AbstractShip> list2 = createDefaultShips();

			this.player1 = new Player(Board1, Board2, list1 );
			this.playerAI = new PlayerAI(Board2, Board1, list2 );
			playerAI.putShips(list2.toArray(new AbstractShip[0]));

			player1.putShips();
			
			
		//}
		return this;
	}


	/*
	 * *** Méthodes
	 */
	public void run() {

		System.out.print (" LA PARTIE COMMENCE ");
		Coords coords = new Coords();
		Board b1 = player1.getBoard();
		Board b2 = player2.getBoard();
		Hit hit;

		// main loop
		b1.print();
		boolean done;
		do {
			System.out.print("le joueur1 joue " + "voici son board : \n");
			b1.print();
			hit = Hit.MISS; 
			hit = player1.sendHit(coords);
			boolean strike = hit != Hit.MISS; 
			b1.setHit(strike, coords);
			b1.print();
			
			done = updateScore();
			
			System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

			// save();

			if (!done && !strike) {
				do {

					System.out.print("le joueur2 joue " + "voici son board : \n");
					b2.print();
					hit = Hit.MISS; 
					hit = player2.sendHit(coords);
					strike = hit != Hit.MISS;
					b2.setHit(strike, coords);
					b2.print();
					
					System.out.println(makeHitMessage(true /* incoming hit */, coords,  hit));
					done = updateScore();

					if (!done) {
//						save();
					}
				} while (strike && !done);
			}

		} while (!done);

		SAVE_FILE.delete();
		System.out.println(String.format("joueur %d gagne", player1.isLose() ? 2 : 1));
		//sin.close();
	}


	public void run_vs_AI() {

		System.out.print (" LA PARTIE COMMENCE ");
		Coords coords = new Coords();
		Board b1 = player1.getBoard();
		Board b2 = playerAI.getBoard();
		Hit hit;

		// main loop
		b1.print();
		boolean done;
		do {
			System.out.print("le joueur1 joue " + "voici son board : \n");
			b1.print();
			hit = Hit.MISS; 
			hit = player1.sendHit(coords);
			boolean strike = hit != Hit.MISS; 
			b1.setHit(strike, coords);
			b1.print();
			
			done = updateScore_vs_AI();
			
			System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

			// save();

			if (!done && !strike) {
				do {

					System.out.print("le joueur2 joue " + "voici son board : \n");
					b2.print();
					hit = Hit.MISS; 
					hit = playerAI.sendHit(coords);
					strike = hit != Hit.MISS;

					if (strike) {
						b1.print();
					}
					
					System.out.println(makeHitMessage(true /* incoming hit */, coords,  hit));
					done = updateScore_vs_AI();

					if (!done) {
//						save();
					}
				} while (strike && !done);
			}

		} while (!done);

		SAVE_FILE.delete();
		System.out.println(String.format("joueur %d gagne", player1.isLose() ? 2 : 1));
		//sin.close();
	}

	private void save() {
//		try {
//			// TODO bonus 2 : uncomment
//			// if (!SAVE_FILE.exists()) {
//			// SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
//			// }
//
//			// TODO bonus 2 : serialize players
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private boolean loadSave() {
//		if (SAVE_FILE.exists()) {
//			try {
//				// TODO bonus 2 : deserialize players
//
//				return true;
//			} catch (IOException | ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
		return false;
	}

	private boolean updateScore() {
		for (Player player : new Player[] { player1, player2 }) {
			int destroyed = 0;
			for (AbstractShip ship : player.getShips()) {
				if (ship.isSunk()) {
					destroyed++;
				}
			}

			player.setDestroyedCount(destroyed);
			player.setLose(destroyed == player.getShips().length);
			if (player.isLose()) {
				return true;
			}
		}
		return false;
	}

	private boolean updateScore_vs_AI() {
		for (Player player : new Player[] { player1, playerAI }) {
			int destroyed = 0;
			for (AbstractShip ship : player.getShips()) {
				if (ship.isSunk()) {
					destroyed++;
				}
			}

			player.setDestroyedCount(destroyed);
			player.setLose(destroyed == player.getShips().length);
			if (player.isLose()) {
				return true;
			}
		}
		return false;
	}


	private String makeHitMessage(boolean incoming, Coords coords, Hit hit) {
		String msg;
		ColorUtil.Color color = ColorUtil.Color.RESET;
		switch (hit) {
		case MISS:
			msg = hit.toString();
			break;
		case STRIKE:
			msg = hit.toString();
			color = ColorUtil.Color.RED;
			break;
		default:
			msg = hit.toString() + " coulé";
			color = ColorUtil.Color.RED;
		}
		msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>", ((char) ('A' + coords.getX())),
				(coords.getY() ), msg);
		return ColorUtil.colorize(msg, color);
	}

	private static List<AbstractShip> createDefaultShips() {
		return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new BattleShip(),
				new Carrier() });
	}
}
