/**
 * 
 */
package main;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import errors.errorDisplay;
import ihm.IGSimulateur;
import ihm.MapIndexOutOfBoundsException;
import ihm.Simulable;

/**
 * @author Thomas
 *
 */
public class Simulateur implements Simulable {

	private IGSimulateur ihm;

	public Simulateur(String map) {
		dessine(map);
	}

	private void dessine(String map) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(map));
			String cur = bf.readLine();
			if (cur != null) {
				while (cur.charAt(0) == '/') {
					cur = bf.readLine();
				}
				String[] firstLine = cur.split(" ");
				int col = Integer.valueOf(firstLine[0]);
				int ligne = Integer.valueOf(firstLine[1]);
				ihm = new IGSimulateur(col, ligne);

				int curLine = 0;
				while ((cur = bf.readLine()) != null) {
					treatLine(cur, curLine, col, ligne);
					curLine++;
				}

				bf.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (errorDisplay e) {
			e.printStackTrace();
		}
	}

	private void treatLine(String toTreat, int curLine, int maxCol, int maxLine)
			throws errorDisplay {
		if (toTreat.length() > maxCol) {
			String errorMsg = "Colonne inacessible :" + toTreat.length()
					+ " Colonne Max Plateau :" + maxCol;
			throw new errorDisplay(errorMsg, ihm);
		} else if (curLine >= maxLine) {
			String errorMsg = "Ligne inacessible :" + curLine
					+ " Ligne Max Plateau :" + maxLine;
			throw new errorDisplay(errorMsg, ihm);
		} else {
			for (int i = 0; i < toTreat.length(); ++i) {
				treatChar(i, curLine, toTreat.charAt(i));
			}
		}
	}

	/**
	 * @param colCur
	 * @param ligneCur
	 * @param t
	 */
	private void treatChar(int colCur, int ligneCur, char t) {
		switch (t) {
		case '#':
			drawWall(colCur, ligneCur);
			break;
		case '.':
			drawPacGomme(colCur, ligneCur);
			break;
		case 'O':
			drawSuperPacGomme(colCur, ligneCur);
			break;
		case 'P':
			drawPacMan(colCur, ligneCur);
			break;
		case 'F':
			drawGhosts(colCur, ligneCur);
			break;
		case ' ':
			drawSpace(colCur, ligneCur);
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
