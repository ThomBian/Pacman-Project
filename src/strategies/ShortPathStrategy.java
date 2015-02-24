package strategies;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Tile;
import Dijkstra.Dijkstra;
import Dijkstra.Vertex;

/**
 * 
 * @author Clément Taboulot
 *
 */
public class ShortPathStrategy implements IStratetgy {
	
    public Tile move(Tile tile, Board board) {
		if(board.hasGotSuperPacGum()){
    		Vertex source = null;
    		List<Vertex> lTarget = new ArrayList<Vertex>();
    		//Si le terrain contient une SUPER_PAC_GUM
    		source = board.calculateGraph(lTarget);
    		List<Vertex> chemin = Dijkstra.run(source, lTarget);
    		return chemin.get(0).tile;
    	}
    	else{
    		//Sinon c'est qu'elles sont toutes mangée donc déplacement aléatoire
    		IStratetgy rand = new RandomStrategy();
    		return rand.move(tile, board);
    	}
  }
	
}
