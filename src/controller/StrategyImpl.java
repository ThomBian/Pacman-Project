/**
 * 
 */

package controller;

import java.util.Random;

import model.Board;
import model.Content;
import model.Direction;
import model.Tile;

/**
 * @author Nathanael COURET
 */
public enum StrategyImpl
    implements IStratetgy {

    RANDOM_PAC {

        public Tile move(Tile tile, Board board) {
            Content[] tab = board.getSurrounding(tile.getX(), tile.getY());
            while (true) {
                int dir = (new Random()).nextInt(4-0)+0;
                System.out.println(dir);
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
                if (cont.getContent() != null && cont.getContent() != Content.WALL &&
                    cont.getContent() != Content.GHOST) { return cont; }
            }
        }
    },
    
    RANDOM_GHOST {

        public Tile move(Tile tile, Board board) {
            Content[] tab = board.getSurrounding(tile.getX(), tile.getY());
            while (true) {
                int dir = (new Random()).nextInt(4-0)+0;
                System.out.println(dir);
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
}
