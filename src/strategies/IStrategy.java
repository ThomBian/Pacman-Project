package strategies;

import model.Board;
import model.Tile;

/**
 * @authors Bianchini - Couret - Taboulot - Valette
 * strategie de deplacement d'un personnage
 */

public interface IStrategy {

    /**
     * calcul le deplacement d'un personnage en fonction de sa case courante
     * et du plateau
     * @param pos : case courante du personnage
     * @param board : plateau
     * @return la nouvelle position du personnage
     */
    public Tile move(Tile pos, Board board);

}
