/**
 * 
 */

package controller;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import model.Board;
import model.Content;
import model.Tile;

/**
 * <p>
 * Effectue des vérifications sur une carte de jeu.
 * 
 * @author Nathanael COURET
 */
public final class BoardChecker {

    private Board board;

    private int   initI, initJ;

    public BoardChecker(Board board) {

        this.board = board;
    }

    /**
     * <p>
     * Vérifie si les cases non-murs de la carte sont connectés. Cela signifie
     * que toutes les cases non-murs sont accessible par un personnage (pacman
     * ou ghost)
     * 
     * @return true si les cases non-murs sont connectées, false sinon.
     */
    public boolean isConnected() {

        Set<Tile> visited = new HashSet<Tile>();
        int total = pathableTilesCount(); // on récupère le nombre de cases
                                          // non-mur
        if (total == 0) { throw new IllegalArgumentException("Carte composée uniquement de murs"); }
        Tile first = new Tile(board.get(initI, initJ), initI, initJ);
        checkConnected(visited, first); // on vérifie la connectivité des cases
                                        // non-mur
        return visited.size() == total;
    }

    /**
     * <p>
     * Vérifie récurisvement la connectivité des cases non-murs de la carte.
     * 
     * @param visited
     *            les cases déjà visités
     * @param current
     *            la case actuelle
     */
    private void checkConnected(Set<Tile> visited, Tile current) {

        if (!visited.add(current)) { return; } // si cette case est déjà dans le
                                               // graphe on arrête le calcul
        for (Tile t : getSurroundingTiles(current)) {
            checkConnected(visited, t); // on vérifie les voisins
        }
    }

    /**
     * <p>
     * Rcupère un tableau contenant les voisins non-murs de cette case
     * 
     * @param t
     *            la case dont on veut les voisins
     * @return les cases voisines non-murs
     */
    private Tile[] getSurroundingTiles(Tile t) {

        List<Tile> tiles = new LinkedList<Tile>();
        Content[] surr = board.getSurrounding(t.getY(), t.getX());
        if (surr[0] != null && !surr[0].equals(Content.WALL)) {
            tiles.add(new Tile(surr[0], t.getX(), t.getY() - 1));
        }
        if (surr[1] != null && !surr[1].equals(Content.WALL)) {
            tiles.add(new Tile(surr[1], t.getX() - 1, t.getY()));
        }
        if (surr[2] != null && !surr[2].equals(Content.WALL)) {
            tiles.add(new Tile(surr[2], t.getX(), t.getY() + 1));
        }
        if (surr[3] != null && !surr[3].equals(Content.WALL)) {
            tiles.add(new Tile(surr[3], t.getX() + 1, t.getY()));
        }
        return tiles.toArray(new Tile[tiles.size()]);
    }

    /**
     * <p>
     * Cacule le nombre total de cases non-mur de la carte.
     * 
     * @return le nombre de cases non-mur
     */
    private int pathableTilesCount() {

        boolean found = false; // tile != wall
        int count = 0;
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (!board.get(i, j).equals(Content.WALL)) {
                    if (!found) {
                        initI = i;
                        initJ = j;
                        found = true;
                    }
                    count++;
                }
            }
        }
        return count;
    }
}
