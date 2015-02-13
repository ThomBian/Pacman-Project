/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import errors.ErrorDisplay;

/**
 * @author Thomas
 *
 */
public class ModeleImpl implements IModele {

	private List<Entity> persos;
	private Board gameBoard;
	private Board initBoard;
	
	public ModeleImpl(String path) {
		persos = new ArrayList<Entity>();
		initialiser(path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IModele#initialiser()
	 */
	@Override
	public void initialiser(String path) {
		try {
			parse(path);
		} catch (IOException | ErrorDisplay e) {
			e.printStackTrace();
		}
	}

	public void parse(String path) throws IOException,
			FileNotFoundException, ErrorDisplay {

		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line = reader.readLine();
		if (line != null) {
			while (line.charAt(0) == '/') {
				line = reader.readLine();
			}
			String[] dimensions = line.split(" ");
			initBoard = new Board(Integer.valueOf(dimensions[0]),
					Integer.valueOf(dimensions[1]));
			for (int i = 0; (line = reader.readLine()) != null; i++) {
				treatLine(line, i);
			}
			gameBoard = initBoard;
			reader.close();
		}
	}

	private void treatLine(String toTreat, int curLine) {
		for (int i = 0; i < toTreat.length(); ++i) {
			this.initBoard.set(curLine, i, toTreat.charAt(i));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IModele#restart()
	 */
	@Override
	public void restart() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IModele#detruire()
	 */
	@Override
	public void detruire() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IModele#deplacePacman()
	 */
	@Override
	public Tile deplacePacman() {
		System.out.println("PACMAN BOUGE");
		Tile newTile = new Tile();
		newTile.setX(0);
		newTile.setY(1);
		//Tile newTile = Pacman.move()
		return newTile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IModele#deplaceGhost()
	 */
	@Override
	public void deplaceGhost() {
		System.out.println("GHOST BOUGE");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IModele#getBoardGameHeight()
	 */
	@Override
	public int getBoardGameHeight() {
		// TODO Auto-generated method stub
		return this.gameBoard.getHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IModele#getBoardGameLength()
	 */
	@Override
	public int getBoardGameLength() {
		return this.gameBoard.getWidth();
	}

	@Override
	public Board getBoard() {
		return this.gameBoard;
	}

	@Override
	public Board getInitBoard() {
		return this.initBoard;
	}

}
