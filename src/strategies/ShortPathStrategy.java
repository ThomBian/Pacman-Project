package strategies;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Content;
import model.Tile;
import Dijkstra.Dijkstra;
import Dijkstra.Vertex;

/**
 * 
 * @author Clément Taboulot
 *
 */
public class ShortPathStrategy implements IStratetgy {
	
	//Chemin à emprunter
	private List<Vertex> lChemin = new ArrayList<Vertex>();
	private Content obj = null;
	
	public ShortPathStrategy(Content c){
		obj = c;
	}
	
    public Tile move(Tile tile, Board board) {	
    	chooseObj(board);
		if(obj != null){
    		//Si le terrain contient un objectif
			if(lChemin.isEmpty()){
				//Si nous avons pas de chemin à suivre
	    		Vertex source = null;
	    		List<Vertex> lTarget = new ArrayList<Vertex>();
	    		source = board.calculateGraph(lTarget, obj);
	    		lChemin = Dijkstra.run(source, lTarget);
			}
    		Tile nextTile = lChemin.get(0).tile;
    		lChemin.remove(0);
    		return nextTile;
    	}
    	else{
    		//Sinon c'est qu'elles sont toutes mangée donc déplacement aléatoire
    		IStratetgy rand = new RandomStrategy();
    		return rand.move(tile, board);
    	}
    }
    
    private void chooseObj(Board board){
    	if(obj != null){
	    	if(!board.hasGotContent(obj)){
	    		switch (obj) {
				case SUPER_PAC_GUM:
					obj = Content.PAC_GUM;
					break;
					
				case PAC_GUM:
					obj = null;;
					break;

				default:
					break;
				}
	    	}
    	}
    }
	
}
