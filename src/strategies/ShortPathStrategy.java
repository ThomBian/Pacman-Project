package strategies;

import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Content;
import model.Tile;
import Dijkstra.Dijkstra;
import Dijkstra.Vertex;

/**
 * @author Bianchini - Couret - Taboulot - Valette
 * strategie du plus court chemin entre une source et une destination
 * grace a une implémentation de l'algorithme de Djikstra
 */
public class ShortPathStrategy implements IStratetgy {
	
	/**
	 * Chemin à emprunter
	 */
	private List<Vertex> lChemin = new ArrayList<Vertex>();

    /**
     * contenu a atteindre
     */
    private Content obj = null;

    /**
     * constructeur de strategie
     * @param c
     */
	public ShortPathStrategy(Content c){
		obj = c;
	}

    /**
     * @see strategies.IStratetgy
     */
    @Override
    public Tile move(Tile tile, Board board) {	
    	
    	if(obj != Content.PACMAN)
    		//Si je ne suis pas un fantome je chosis mon objectif
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
    		if(board.badNextTile(nextTile))
    			return tile;
    		lChemin.remove(0);
    		return nextTile;
    	}
    	else{
    		//Sinon c'est qu'elles sont toutes mangée donc déplacement aléatoire
    		IStratetgy rand = new RandomStrategy();
    		return rand.move(tile, board);
    	}
    }

    /**
     * definit les nouveaux objectifs a atteindre
     * quand il n'y a plus de super pac gomme -> pac gomme -> gagné !
     * @param board
     */
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
