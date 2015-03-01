package strategies;

import java.util.Random;

import model.Board;
import model.Content;
import model.Direction;
import model.Tile;

/**
 * @author Bianchini - Couret - Taboulot - Valette
 * strategie de déplacement RANDOM
 * regarde les cases avoisinantes du personnages Est, Nord, Ouest, Sud
 * choisit au hasard parmi ces 4 possibilités
 * on vérifie si ce nest pas un mur et que ce nest pas un fantome
 */
public class RandomStrategy implements IStrategy {

	public Tile move(Tile tile, Board board) {
        Content[] tab = board.getSurrounding(tile.getX(), tile.getY());
        while (true) {
            int dir = (new Random()).nextInt(4-0)+0;
            Tile cont = new Tile();
            switch (dir) {
                case 0:
                    cont.setContent(tab[Direction.WEST.val()]);
                    cont.setX(tile.getX()-1);
                    cont.setY(tile.getY());
                break;
                case 1:
                    cont.setContent(tab[Direction.NORTH.val()]);
                    cont.setX(tile.getX());
                    cont.setY(tile.getY()-1);
                break;
                case 2:
                    cont.setContent(tab[Direction.EAST.val()]);
                    cont.setX(tile.getX()+1);
                    cont.setY(tile.getY());
                break;
                case 3:
                    cont.setContent(tab[Direction.SOUTH.val()]);
                    cont.setX(tile.getX() );
                    cont.setY(tile.getY()+1);
                break;
                default:
                    continue;
            }
            if (cont.getContent() != null && cont.getContent() != Content.WALL) { return cont; }
        }
    }
	
}
