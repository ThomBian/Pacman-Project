/**
 * 
 */
package view;

import ihm.IGSimulateur;
import ihm.MapIndexOutOfBoundsException;
import ihm.Simulable;

import java.awt.Color;

import model.SimulationData;

/**
 * @author Thomas
 *
 */
public class Simulateur implements Simulable {

	private IGSimulateur ihm;
	private SimulationData db;

	public Simulateur(String map) {
		db = SimulationData.getInstance(map);
		System.out.println(db.toString());
		ihm = new IGSimulateur(this.db.getNbColomns(), this.db.getNbLines());
		dessine();
	}

	private void dessine() {
		int col = this.db.getNbColomns();
		int line = this.db.getNbLines();
		for (int i = 0; i<line; ++i){
			for (int j = 0; j < col; ++j){
				processChar(j, i, this.db.getCase(i,j));
			}
		}
	}


	/**
	 * @param x
	 * @param y
	 * @param t
	 */
	private void processChar(int x, int y, char t) {
		switch (t) {
		case '#':
			drawWall(x, y);
			break;
		case '.':
			drawPacGomme(x, y);
			break;
		case 'O':
			drawSuperPacGomme(x, y);
			break;
		case 'P':
			drawPacMan(x, y);
			break;
		case 'F':
			drawGhosts(x, y);
			break;
		case ' ':
			drawSpace(x, y);
			break;
		default:
			break;

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ihm.Simulable#next()
	 */
	@Override
	public void next() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ihm.Simulable#restart()
	 */
	@Override
	public void restart() {
		// TODO Auto-generated method stub

	}

	private void drawWall(int x, int y) {
		try {
			ihm.paintBox(x, y, Color.BLACK);
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	private void drawSpace(int x, int y) {
		try {
			ihm.paintBox(x, y, Color.WHITE);
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	private void drawPacGomme(int x, int y) {
		try {
			ihm.paintString(x, y, Color.BLACK, ".");
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	private void drawSuperPacGomme(int x, int y) {
		try {
			ihm.paintString(x, y, Color.BLACK, "O");
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	private void drawPacMan(int x, int y) {
		try {
			ihm.paintImage(x, y, "images/Pacman.png", 0.6, 0.6);
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	private void drawGhosts(int x, int y) {
		try {
			ihm.paintImage(x, y, "images/Ghost.png", 0.6, 0.6);
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

}
