/**
 * 
 */

package controller;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;
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

    private OptionalInt initI = OptionalInt.empty(), initJ = OptionalInt.empty(),
                    pathTiles = OptionalInt.empty();

    public BoardChecker(Board board) {

        this.board = board;
        if (pathableTilesCount() == 0) { throw new IllegalArgumentException(
            "Carte composée uniquement de murs"); }
    }

    /**
     * <p>
     * Appel static pour vérifier si la carte donnée est connection
     * 
     * @param board
     *            la carte à vérifier
     * @return le résultat de {@link #isConnected()}
     * @see #isConnected()
     */
    public static boolean checkBoardConnected(Board board) {

        return (new BoardChecker(board)).isConnected();
    }

    /**
     * <p>
     * Appel statique pour obtenir le nombre de pommes restantes.
     * 
     * @param board
     *            La carte à parcourir
     * @return le résultat de {@link #remainingGumsCount()}
     * @see #remainingGumsCount()
     */
    public static int remainingGumsCount(Board board) {

        return (new BoardChecker(board)).remainingGumsCount();
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
        if (pathableTilesCount() == 0) { throw new IllegalStateException(
            "Carte composée uniquement de murs"); }
        Tile first = new Tile(board.get(initI(), initJ()), initI(), initJ());
        checkConnected(visited, first); // on vérifie la connectivité des cases
                                        // non-mur
        return visited.size() == pathableTilesCount();
    }

    /**
     * <p>
     * Récupère le nombre de pommes restantes sur la carte
     * 
     * @return Le nombre de pommes restqnte sur la carte
     */
    public int remainingGumsCount() {

        if (pathableTilesCount() == 0) { throw new IllegalStateException(
            "Carte composée uniquement de murs"); }
        int count = 0;
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.get(i, j).equals(Content.PAC_GUM) ||
                    board.get(i, j).equals(Content.SUPER_PAC_GUM)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * <p>
     * Vérifie récurisvement la connectivité des cases non-murs de la carte.
     * 
     * @param visited
     *            les cases déjà visitées
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
     * Récupére un tableau contenant les voisins non-murs de cette case
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

        if (!pathTiles.isPresent()) {
            int count = 0;
            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (!board.get(i, j).equals(Content.WALL)) {
                        count++;
                    }
                }
            }
            pathTiles = OptionalInt.of(count);
        }

        return pathTiles.getAsInt();
    }

    /**
     * La ligne de la première case non mur
     * 
     * @return
     */
    private int initI() {

        if (!initI.isPresent()) {

            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (!board.get(i, j).equals(Content.WALL)) {
                        initI = OptionalInt.of(i);
                    }
                }
            }
        }
        return initI.getAsInt();
    }

    /**
     * La colonne de la première case non mur
     * 
     * @return
     */
    private int initJ() {

        if (!initJ.isPresent()) {

            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (!board.get(i, j).equals(Content.WALL)) {
                        initJ = OptionalInt.of(j);
                    }
                }
            }
        }
        return initJ.getAsInt();
    }
}
