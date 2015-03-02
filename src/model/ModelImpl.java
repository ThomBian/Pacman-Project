
package model;

import strategies.IStrategy;
import strategies.RandomStrategy;
import strategies.ShortPathStrategy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import controller.BoardChecker;
import errors.InvalidMapException;


/**
 * @author Bianchini - Couret - Taboulot - Valette
 * Implemente l'interface du modele du MVC
 * @see model.IModel
 */
public class ModelImpl implements IModel {

    /**
     * Liste des fantomes de la carte
     */
	private List<Entity> ghosts;

    /**
     * plateau de jeu
     * @see model.Board
     */
	private Board gameBoard;

    /**
     * personnage Pacman
     */
	private Entity pacman;

    /**
     * chemin relatif du fichier a parser
     * representant la carte de simulation
     */
	private String map;

    /**
     * Constructeur du modele
     * @param path
     */
	public ModelImpl(String path) throws InvalidMapException {
		this.map = path;
		init(path);
	}

    /**
     * Initilialisation du modele
     * @param path
     */
	@Override
	public void init(String path) throws InvalidMapException {
		ghosts = new ArrayList<Entity>();
		try {
			parse(path);
		} catch (IOException e) {
			throw InvalidMapException.mapParseException(e);
		}
		if(!BoardChecker.checkBoardConnected(gameBoard)) {
		    throw InvalidMapException.notConnectedPathException;
		}
    }

    /**
     * parse le fichier passé en paramètre et remplit le tableau
     * de jeu, cree les personnages...
     * @param path
     * @throws IOException
     * @throws FileNotFoundException
     */
	private void parse(String path) throws IOException, FileNotFoundException {

		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line = reader.readLine();
		if (line != null) {
			while (line.charAt(0) == '/') {
				line = reader.readLine();
			}
			String[] dimensions = line.split(" ");
			gameBoard = new Board(Integer.valueOf(dimensions[0]),
					Integer.valueOf(dimensions[1]));
			for (int i = 0; (line = reader.readLine()) != null; i++) {
				processLine(line, i);
			}
			reader.close();
		}
	}

    /**
     * traite la ligne lue caractère par caractère afin
     * de creer une case pour chacun de ceux ci
     * @param lineToProcess
     * @param curLine
     */
	private void processLine(String lineToProcess, int curLine) {
		for (int i = 0; i < lineToProcess.length(); ++i) {
			if (lineToProcess.charAt(i) == 'P') {
				Tile tile = new Tile(Content.PACMAN, i, curLine);
				pacman = new Entity(tile, Content.PACMAN);
			}
			if (lineToProcess.charAt(i) == 'F'){
				Tile tile = new Tile(Content.GHOST, i, curLine);
				Entity g = new Ghost(tile);
				ghosts.add(g);
			}
			this.gameBoard.set(curLine, i, Content.fromChar(lineToProcess.charAt(i)));
		}
	}

    /**
     * @see model.IModel
     */
	@Override
	public void remove() {
		ghosts = null;
		pacman = null;
		gameBoard = null;
	}

    /**
     * @see model.IModel
     */
	@Override
	public Tile movePacman() {
		return pacman.move(gameBoard);
	}

    /**
     * @see model.IModel
     */
	@Override
	public Tile moveGhost(int idx) {
		return ghosts.get(idx).move(gameBoard);
	}

    /**
     * @see model.IModel
     */
	@Override
	public int getBoardGameHeight() {
		return this.gameBoard.getHeight();
	}

    /**
     * @see model.IModel
     */
	@Override
	public int getBoardGameWidth() {
		return this.gameBoard.getWidth();
	}

    /**
     * @see model.IModel
     */
	@Override
	public Board getBoard() {
		return this.gameBoard;
	}

	/**
	 * @return the pacman
	 */
	public Entity getPacman() {
		return pacman;
	}

    /**
     * @see model.IModel
     */
	@Override
	public void updateEntityPosition(Entity p, Tile t) {
		if (p instanceof Ghost){
			Tile entityPos = p.getPosition();
			gameBoard.set(t.getY(), t.getX(), ((Ghost) p).getLastContent());
			((Ghost) p).setLastContent(gameBoard.get(entityPos.getY(), entityPos.getX()));
			gameBoard.set(entityPos.getY(), entityPos.getX(), p.getRef());
		}
		else {
			Tile entityPos = p.getPosition();
			gameBoard.set(entityPos.getY(), entityPos.getX(), p.getRef());
			gameBoard.set(t.getY(), t.getX(), Content.EMPTY);
		}
		
		
	}

    /**
     * @see model.IModel
     */
	@Override
	public Collection<Entity> getGhosts() {
		return ghosts;
	}

    /**
     * @see model.IModel
     */
	@Override
	public String getMap() {
		return this.map;
	}

    /**
     * @see model.IModel
     * Les strategies sont sauvegardees avant de detruire le modele
     * afin de les reaffecter une fois le modele reconstruit
     * on recupere seulement la premiere strategie dans
     * le tableau de ghosts car tous les ghosts ont la meme
     * strategie
     */
	@Override
	public void restartModel() throws InvalidMapException {
        IStrategy pStrat = pacman.getStrat();
        IStrategy gStrat = ghosts.get(0).getStrat();
		this.remove();
		this.init(this.map);
        pStrat = getiStratetgy(pStrat, Content.SUPER_PAC_GUM);
        pacman.setStrat(pStrat);
        for (int i = 0; i < ghosts.size(); i++) {
            ghosts.get(i).setStrat(getiStratetgy(gStrat, Content.PACMAN));
        }

    }


    /**
     * permet de recuperer une nouvelle strategie
     * en fonction d'une strategie. Utile dans le restart pour
     * creer une nouvelle instane de strategie pour les positions
     * calculées soient réinitialisées
     * @param strat :
     *               strategie avant restart d'un personnage
     * @param c :
     *          contenu pour la strat du plus court chemin
     * @return
     */
    private IStrategy getiStratetgy(IStrategy strat, Content c) {
        if (strat instanceof ShortPathStrategy)
            strat = new ShortPathStrategy(c);
        else if (strat instanceof RandomStrategy)
            strat = new RandomStrategy();
        else
            strat = new RandomStrategy();
        return strat;
    }

}
