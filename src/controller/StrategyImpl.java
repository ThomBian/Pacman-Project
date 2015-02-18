/**
 * 
 */

package controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import model.Board;
import model.Content;
import model.Direction;
import model.Tile;
import Dijkstra.*;

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
    },
    
    COURT_CHEMIN{
        public Tile move(Tile tile, Board board) {
        	if(board.isGotSuperPacGum()){
        		//Si le terrain contient une SUPER_PAC_GUM
        		List<Vertex> vertices = new ArrayList<Vertex>();
        		Vertex [] obj = board.calculateGraph(vertices);
        		List<Vertex> chemin = Dijkstra.run(obj[1], obj[0]);
        		return chemin.get(0).tile;
        	}
        	else
        		//Sinon c'est qu'elles sont toutes mangée donc déplacement aléatoire
        		return StrategyImpl.RANDOM_PAC.move(tile, board);
      }
   }
}