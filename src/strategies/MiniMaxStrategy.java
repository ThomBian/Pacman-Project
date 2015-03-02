package strategies;

import java.util.ArrayList;
import java.util.Collection;

import model.*;

public class MiniMaxStrategy implements IStratetgy {

	private final static int PROFONDEUR_MAX = 4;

	private boolean deplacerPacMan;
	private Entity pacMan;
	private Ghost ghost;
	private static Tile destinationPacMan;
	private static Tile destinationGhost;

	/**
	 * @param deplacerPacMan
	 * @param pacMan
	 * @param ghost
	 */
	public MiniMaxStrategy (boolean deplacerPacMan, Entity pacMan, Ghost ghost){
		this.deplacerPacMan = deplacerPacMan;
		this.pacMan = pacMan;
		this.ghost = ghost;
		destinationPacMan = null;
		destinationGhost = null;
	}

	/**
	 * Set les destinations de Pacman et du Ghost
	 * @param board
	 */
	private void miniMax (Board board){
		Collection<Tile> parcourues	= new ArrayList<Tile>();
		parcourues.add(pacMan.getPosition());
		fonctionMax(1, pacMan, ghost, parcourues, board);
	}

	/**
	 * Calcul d'un noeud Max, Pacman
	 * @param profondeur
	 * @param pacManVirtuel
	 * @param ghostVirtuel
	 * @param parcourues
	 * @param board
	 * @return
	 */
	private int fonctionMax (int profondeur, Entity pacManVirtuel, Entity ghostVirtuel, Collection<Tile> parcourues, Board board){
		int max = Integer.MIN_VALUE;
		int valeur;

		Content ghostContent = Content.fromChar(board.get(ghostVirtuel.getPosition().getY(), ghostVirtuel.getPosition().getX()).val());
		board.set(ghostVirtuel.getPosition().getY(), ghostVirtuel.getPosition().getX(), Content.GHOST);
		Content[] contenuAutour = board.getSurrounding(pacManVirtuel.getPosition().getX(), pacManVirtuel.getPosition().getY());
		board.set(ghostVirtuel.getPosition().getY(), ghostVirtuel.getPosition().getX(), ghostContent);

		for (int i = 0; i < 4; i++){
			if (!contenuAutour[i].equals(Content.WALL)) {
				Entity nouveauPacManVirtuel = new Entity(deplacerCase(pacManVirtuel.getPosition(), i), pacManVirtuel.getRef());

				valeur = evaluerPacMan(contenuAutour[i], parcourues.contains(nouveauPacManVirtuel.getPosition())) + fonctionMin(profondeur, nouveauPacManVirtuel, ghostVirtuel, parcourues, board.cloner());
				if (valeur > max) {
					max = valeur;
					if (profondeur == 1) destinationPacMan = nouveauPacManVirtuel.getPosition();
				}
			}
		}
		return max;
	}

	/**
	 * Calcul d'un noeud Min, Ghost
	 * @param profondeur
	 * @param pacManVirtuel
	 * @param ghostVirtuel
	 * @param parcourues
	 * @param board
	 * @return
	 */
	private int fonctionMin (int profondeur, Entity pacManVirtuel, Entity ghostVirtuel, Collection<Tile> parcourues, Board board){
		int min = Integer.MAX_VALUE;
		int valeur;

		board.set(pacManVirtuel.getPosition().getY(), pacManVirtuel.getPosition().getX(), Content.PACMAN);
		Content[] contenuAutour = board.getSurrounding(ghostVirtuel.getPosition().getX(), ghostVirtuel.getPosition().getY());
		board.set(pacManVirtuel.getPosition().getY(), pacManVirtuel.getPosition().getX(), Content.EMPTY);

		Collection<Tile> nouvelleListe = new ArrayList<Tile>(parcourues);
		if (!nouvelleListe.contains(pacManVirtuel.getPosition())) nouvelleListe.add(pacManVirtuel.getPosition());

		for (int i = 0; i < 4; i++){
			if (!contenuAutour[i].equals(Content.WALL)) {
				Entity nouveauGhostVirtuel = new Entity(deplacerCase(ghostVirtuel.getPosition(), i), ghostVirtuel.getRef());

				valeur = -evaluerGhost(contenuAutour[i], nouvelleListe.contains(nouveauGhostVirtuel.getPosition())) + (profondeur < PROFONDEUR_MAX ? fonctionMax(profondeur + 1, pacManVirtuel, nouveauGhostVirtuel, nouvelleListe, board.cloner()) : 0);
				if (valeur < min) {
					min = valeur;
					if (profondeur == 1) destinationGhost = nouveauGhostVirtuel.getPosition();
				}
			}
		}
		return min;
	}

	/**
	 * Fonction d'évaluation de Pacman
	 * @param content
	 * @param parcourue
	 * @return
	 */
	private int evaluerPacMan (Content content, boolean parcourue){
		switch (content.val()){
		case ' ':
			return parcourue ? -1 : 1;
		case '.':
			return 5;
		case 'O':
			return 10;
		case 'F':
			return -100;
		default :
			return 0;
		}
	}

	/**
	 * Fonction d'évaluation des fantomes
	 * @param content
	 * @param parcourue
	 * @return
	 */
	private int evaluerGhost (Content content, boolean parcourue){
		switch (content.val()){
		case ' ':
			return parcourue ? 1 : -1;
		case '.':
			return -5;
		case 'O':
			return -10;
		case 'P':
			return 100;
		default :
			return 0;
		}
	}

	/**
	 * Retourne la case adjacente en fonction d'une direction
	 * @param tile
	 * @param direction
	 * @return
	 */
	private Tile deplacerCase (Tile tile, int direction){
		switch (direction){
		case 1:
			return new Tile (tile.getContent(), tile.getX(), tile.getY() - 1);
		case 3:
			return new Tile (tile.getContent(), tile.getX(), tile.getY() + 1);
		case 0:
			return new Tile (tile.getContent(), tile.getX() - 1, tile.getY());
		case 2:
			return new Tile (tile.getContent(), tile.getX() + 1, tile.getY());
		default :
			return null;
		}
	}

	/**
	 * Retourne la destination de Pacman evaluée par Minimax
	 * @param board
	 * @return
	 */
	private Tile getDestinationPacman (Board board){
		if (destinationPacMan == null) miniMax(board);
		Tile tile = new Tile (destinationPacMan.getContent(), destinationPacMan.getX(), destinationPacMan.getY());
		destinationPacMan = null;
		return tile;
	}

	/**
	 * Retourne la destination d'un ghost evaluée par Minimax
	 * @param board
	 * @return
	 */
	private Tile getDestinationGhost (Board board){
		if (destinationPacMan == null) miniMax(board);
		Tile tile = new Tile (destinationGhost.getContent(), destinationGhost.getX(), destinationGhost.getY());
		destinationGhost = null;
		return tile;
	}

	/* (non-Javadoc)
	 * @see strategies.IStratetgy#move(model.Tile, model.Board)
	 */
	@Override
	public Tile move(Tile pos, Board board) {
		Board b = board.cloner();

		b.set(ghost.getPosition().getY(), ghost.getPosition().getX(), ghost.getLastContent());
		b.set(pacMan.getPosition().getY(), pacMan.getPosition().getX(), Content.EMPTY);

		if (deplacerPacMan) return getDestinationPacman(board);
		else return getDestinationGhost(board);
	}

}
