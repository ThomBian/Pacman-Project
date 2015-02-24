/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import strategies.IStratetgy;
import strategies.ShortPathStrategy;
import errors.ErrorDisplay;

/**
 * @author Thomas
 *
 */
public class ModeleImpl implements IModele {

	private List<Entity> persos;
	private Board gameBoard;
	private Board initBoard;
	private PacMan pacman;
	private String map;

	public ModeleImpl(String path) {
		this.map = path;
		init(path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IModele#initialiser()
	 */
	@Override
	public void init(String path) {
		persos = new ArrayList<Entity>();
		try {
			parse(path);
		} catch (IOException | ErrorDisplay e) {
			e.printStackTrace();
		}
	}

	public void parse(String path) throws IOException, FileNotFoundException,
			ErrorDisplay {

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
			if (toTreat.charAt(i) == 'P') {
				Tile tile = new Tile(Content.PACMAN, i, curLine);
				pacman = new PacMan(tile);
			}
			if (toTreat.charAt(i) == 'F'){
				Tile tile = new Tile(Content.GHOST, i, curLine);
				Entity g = new Ghost();
				g.position = tile;
				persos.add(g);
			}
			this.initBoard.set(curLine, i, Content.fromChar(toTreat.charAt(i)));
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IModele#detruire()
	 */
	@Override
	public void remove() {
		persos = null;
		pacman = null;
		initBoard = null;
		gameBoard = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IModele#deplacePacman()
	 */
	@Override
	public Tile movePacman() {
		IStratetgy strat = new ShortPathStrategy();
		return strat.move(pacman.getPosition(), gameBoard);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IModele#deplaceGhost()
	 */
	@Override
	public Tile moveGhost(int idx) {
		//TODO
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IModele#getBoardGameHeight()
	 */
	@Override
	public int getBoardGameHeight() {
		return this.gameBoard.getHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see view.IModele#getBoardGameLength()
	 */
	@Override
	public int getBoardGameWidth() {
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

	/**
	 * @return the pacman
	 */
	public PacMan getPacman() {
		return pacman;
	}

	@Override
	/**
	 * Cette méthode met à jour la position d'un personnage
	 */
	public void updateEntityPosition(Entity p, Tile t) {
		if (p instanceof PacMan) {
			Tile entityPos = p.getPosition();
			gameBoard.set(entityPos.getY(), entityPos.getX(), p.getRef());
			gameBoard.set(t.getY(), t.getX(), Content.EMPTY);
		}
	}

	@Override
	public Collection<Entity> getPersos() {
		return persos;
	}

	@Override
	public String getMap() {
		return this.map;
	}

	@Override
	public void restartModel() {
		this.remove();
		this.init(this.map);
	}

}
